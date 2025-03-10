package michal.malek.avsystemtask.service;

import michal.malek.avsystemtask.configuration.IntersectionConfig;
import michal.malek.avsystemtask.mapper.IntersectionMapper;
import michal.malek.avsystemtask.model.Intersection;
import michal.malek.avsystemtask.model.dto.IntersectionResponse;
import michal.malek.avsystemtask.model.route.Road;
import michal.malek.avsystemtask.model.route.Route;
import michal.malek.avsystemtask.model.route.Trails;
import michal.malek.avsystemtask.model.route.enums.Direction;
import michal.malek.avsystemtask.model.route.enums.TrafficLightColor;
import michal.malek.avsystemtask.model.vehicle.Car;
import michal.malek.avsystemtask.model.vehicle.Tram;
import michal.malek.avsystemtask.model.vehicle.Vehicle;
import michal.malek.avsystemtask.service.vechicle.CarService;
import michal.malek.avsystemtask.service.vechicle.TramService;
import michal.malek.avsystemtask.util.IntersectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IntersectionService {
    private Intersection intersection;
    private final IntersectionMapper intersectionMapper;
    private final Random random;
    private final CarService carService;
    private final TramService tramService;
    private final LogService logService;
    private final IntersectionUtil intersectionUtil;
    private final int DEFAULT_VEHICLE_GROWTH = 3;
    private List<String> actualCombination;
    private int stepCounter = 0;

    public IntersectionService(@Autowired Intersection intersection, IntersectionMapper intersectionMapper, Random random,
                               CarService carService, TramService tramService, LogService logService, IntersectionUtil intersectionUtil) {
        this.intersection = intersection;
        this.intersectionMapper = intersectionMapper;
        this.random = random;
        this.carService = carService;
        this.tramService = tramService;
        this.logService = logService;
        this.intersectionUtil = intersectionUtil;
        this.fillRoutesRandom(DEFAULT_VEHICLE_GROWTH);
        this.setCombinations();
        this.actualCombination = intersection.getCombinations().get(1);
        this.changeTrafficLights();
        intersection.setVehiclesInside(new LinkedList<>());
    }

    public IntersectionResponse getIntersection() {
        return intersectionMapper.toDto(intersection);
    }

    public void fillRoutesRandomBound3(){
        this.fillRoutesRandom(DEFAULT_VEHICLE_GROWTH);
        logService.log("Na Drogach pojawiły się nowe samochody", stepCounter);
    }

    public List<Vehicle> nextStep(){
        stepCounter++;
        LinkedList<Route> routes = intersection.getRoutes();
        List<Vehicle> vehiclesInside = intersection.getVehiclesInside();
        this.changeTrafficLights();


        for (Route route : routes) {
            if(route.getColor().equals(TrafficLightColor.GREEN)){
                if(route instanceof Road){
                    if(!((Road) route).getVehicles().isEmpty()){
                        Car poll = ((Road) route).getVehicles().poll();
                        vehiclesInside.add(poll);
                        logService.log(poll + "z drogi " + route.getRouteUid() +
                                " wjechał na skrzyżowanie", stepCounter);
                    }
                }else if(route instanceof Trails){
                    if(!((Trails) route).getVehicles().isEmpty()){
                        Tram poll = ((Trails) route).getVehicles().poll();
                        vehiclesInside.add(poll);
                        logService.log(poll + "z torów " + route.getRouteUid() +
                                " wjechał na skrzyżowanie", stepCounter);
                    }
                }
            }
        }

        return updateCenterOfIntersection();
    }

    public void setDifferentIntersection(String intersectionName){
        ApplicationContext context = new AnnotationConfigApplicationContext(IntersectionConfig.class);
        this.intersection = context.getBean(intersectionName, Intersection.class);
        this.setCombinations();
        intersection.setVehiclesInside(new LinkedList<>());
        stepCounter = 0;
        logService.log("Nstąpiła zmiana skrzyżowania", stepCounter);
    }

    public void insertSingleCar(Car car){
        LinkedList<Route> routes = intersection.getRoutes();
        Direction carDestination = car.getDestination();
        Direction carStartDirection = car.getStartDirection();
        for(Route route : routes){
            if(route.getEndDirection().contains(carDestination) && route.getStartDirection().equals(carStartDirection)){
                if(route instanceof Road){
                    ((Road) route).getVehicles().add(car);
                    logService.log("Na drodze pojawił się samochód " + car, stepCounter);
                }
            }
        }
    }

    public void clearIntersection(){
        intersection.setVehiclesInside(new LinkedList<>());
        stepCounter = 0;
        LinkedList<Route> routes = intersection.getRoutes();
        for (Route route : routes) {
            if( route instanceof Road){
                ((Road) route).setVehicles(new LinkedList<>());
            }
            else if(route instanceof Trails){
                ((Trails) route).setVehicles(new LinkedList<>());
            }
        }
    }

    public void changeTrafficLights() {
        LinkedList<Route> routes = intersection.getRoutes();
        List<List<String>> combinations = intersection.getCombinations();

        List<String> finalCombination = getFinalCombination(routes, combinations);

        if(!actualCombination.equals(finalCombination)) {
            actualCombination = finalCombination;
            for( Route route : routes){
                route.setColor(TrafficLightColor.RED);
                if(actualCombination.contains(route.getRouteUid())){
                    route.setColor(TrafficLightColor.GREEN);
                }
            }
            logService.log("Światła na skrzyzowaniu zostały zmienione", stepCounter);
        }

    }

    private List<String> getFinalCombination(LinkedList<Route> routes, List<List<String>> combinations) {
        HashMap<String, Integer> carAmountRoadName = new HashMap<>();
        for (Route route : routes) {
            int vehicleAmount = route.getVehicleAmount();
            carAmountRoadName.put(route.getRouteUid(), vehicleAmount);
        }

        int max = 0;
        List<String> finalCombination = new LinkedList<>();
        for (List<String> combination : combinations) {
            int sum = 0;
            for( String uid : combination){
                Integer i = carAmountRoadName.get(uid);
                sum += i;
            }
            if((sum-4 >= max) || (max <= 4 && sum >= max)){
                max = sum;
                finalCombination = combination;
            }
        }
        return finalCombination;
    }

    private List<Vehicle> updateCenterOfIntersection(){
        List<Vehicle> vehiclesInside = intersection.getVehiclesInside();
        List<Vehicle> vehiclesLeftInThisStep = new LinkedList<>();
        if (!vehiclesInside.isEmpty()) {
            Iterator<Vehicle> iterator = vehiclesInside.iterator();
            while (iterator.hasNext()) {
                Vehicle vehicle = iterator.next();
                if(vehicle.getDistanceLeft() - vehicle.getSpeed() <= 0){
                    vehicle.setDistanceLeft(0);
                    vehiclesLeftInThisStep.add(vehicle);
                    iterator.remove();
                    logService.log(vehicle + " opuścił skrzyżowanie", stepCounter);
                } else {
                    vehicle.setDistanceLeft(vehicle.getDistanceLeft() - vehicle.getSpeed());
                }
            }
            intersection.setVehiclesInside(vehiclesInside);
        }
        return vehiclesLeftInThisStep;
    }

    private void setCombinations(){
        List<List<Route>> coveringCombinations = intersectionUtil.getCoveringCombinations(intersection.getRoutes());
        List<List<String>> combinations = new LinkedList<>();
        for (List<Route> routes : coveringCombinations) {
            List<String> list = routes.stream().map(Route::getRouteUid).toList();
            combinations.add(list);
        }
        intersection.setCombinations(combinations);
    }

    private void fillRoutesRandom(int bound){
        LinkedList<Route> routes = intersection.getRoutes();
        for(Route route : routes){
            int numberOfVehicles = random.nextInt(bound);
            if (route instanceof Road) {
                ((Road) route)
                        .getVehicles()
                        .addAll(carService.generateVehicles(numberOfVehicles, route.getStartDirection()));
            } else if (route instanceof Trails) {
                ((Trails) route)
                        .getVehicles()
                        .addAll(tramService.generateVehicles(numberOfVehicles, route.getStartDirection()));
            }
        }
    }

}

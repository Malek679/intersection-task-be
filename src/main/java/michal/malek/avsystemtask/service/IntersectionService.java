package michal.malek.avsystemtask.service;

import lombok.RequiredArgsConstructor;
import michal.malek.avsystemtask.model.Car;
import michal.malek.avsystemtask.model.Intersection;
import michal.malek.avsystemtask.model.TrafficLightState;
import michal.malek.avsystemtask.model.enums.TrafficLight;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;

@Service
@RequiredArgsConstructor
public class IntersectionService {
    private final Intersection intersection;

    private void generateCars(int amount){
        HashMap<TrafficLight, TrafficLightState> trafficLights = intersection.getTrafficLights();
        for (TrafficLightState trafficLightState : trafficLights.values()) {
            LinkedList<Car> cars = trafficLightState.getCars();
//            cars.offer()

        }
    }


}

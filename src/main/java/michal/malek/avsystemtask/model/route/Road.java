package michal.malek.avsystemtask.model.route;

import lombok.Getter;
import lombok.Setter;
import michal.malek.avsystemtask.model.route.enums.Direction;
import michal.malek.avsystemtask.model.route.enums.RouteType;
import michal.malek.avsystemtask.model.route.enums.TrafficLightColor;
import michal.malek.avsystemtask.model.vehicle.Car;

import java.util.LinkedList;

@Getter
@Setter
public class Road extends Route {

    private LinkedList<Car> vehicles;

    public Road( Direction startDirection, LinkedList<Direction> endDirection,
                TrafficLightColor color, LinkedList<Car> cars) {
        super(startDirection, endDirection, color);
        this.vehicles = cars;
        this.roadType = RouteType.ROAD;
    }

    @Override
    public int getVehicleAmount() {
        return vehicles.size();
    }
}

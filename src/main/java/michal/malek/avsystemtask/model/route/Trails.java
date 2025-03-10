package michal.malek.avsystemtask.model.route;

import lombok.Getter;
import lombok.Setter;
import michal.malek.avsystemtask.model.route.enums.Direction;
import michal.malek.avsystemtask.model.route.enums.RouteType;
import michal.malek.avsystemtask.model.route.enums.TrafficLightColor;
import michal.malek.avsystemtask.model.vehicle.Tram;

import java.util.LinkedList;

@Getter
@Setter
public class Trails extends Route {
    private LinkedList<Tram> vehicles;

    public Trails( Direction startDirection, LinkedList<Direction> endDirection,
                   TrafficLightColor color, LinkedList<Tram> trams) {
        super(startDirection, endDirection, color);
        this.vehicles = trams;
        this.roadType = RouteType.TRAILS;
    }

    @Override
    public int getVehicleAmount() {
        return vehicles.size();
    }
}

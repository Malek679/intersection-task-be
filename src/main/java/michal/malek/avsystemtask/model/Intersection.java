package michal.malek.avsystemtask.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import michal.malek.avsystemtask.model.route.Route;
import michal.malek.avsystemtask.model.vehicle.Vehicle;

import java.util.LinkedList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Intersection {
    private LinkedList<Route> routes;
    private List<List<String>> combinations;
    private List<Vehicle> vehiclesInside;
}

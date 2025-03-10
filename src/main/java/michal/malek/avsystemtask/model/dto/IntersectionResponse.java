package michal.malek.avsystemtask.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import michal.malek.avsystemtask.model.route.Route;
import michal.malek.avsystemtask.model.vehicle.Vehicle;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IntersectionResponse {
    private LinkedList<Route> routes;
    private List<Vehicle> vehiclesInside;
}

package michal.malek.avsystemtask.model.vehicle;

import lombok.Getter;
import lombok.Setter;
import michal.malek.avsystemtask.model.route.enums.Direction;

@Getter
@Setter
public class Tram extends Vehicle {
    private String lineName;

    public Tram(String name, int speed, int distanceLeft, String lineName, Direction direction, Direction startingDirection) {
        super(name, speed, distanceLeft, VehicleType.TRAM, direction, startingDirection);
        this.lineName = lineName;
    }
}

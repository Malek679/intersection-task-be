package michal.malek.avsystemtask.model.vehicle;

import lombok.Getter;
import lombok.Setter;
import michal.malek.avsystemtask.model.route.enums.Direction;


@Getter
@Setter
public class Car extends Vehicle {
    private String licensePlate;

    public Car(String name, int speed, int distanceLeft, String licensePlate, Direction direction, Direction startingDirection) {
        super(name, speed, distanceLeft, VehicleType.CAR, direction, startingDirection);
        this.licensePlate = licensePlate;
    }
}

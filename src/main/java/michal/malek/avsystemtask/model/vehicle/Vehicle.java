package michal.malek.avsystemtask.model.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import michal.malek.avsystemtask.model.route.enums.Direction;

import javax.print.attribute.standard.Destination;

@Getter
@Setter
@AllArgsConstructor
public abstract class Vehicle {
    private String name;
    private int speed;
    private int distanceLeft;
    private VehicleType vehicleType;
    private Direction destination;
    private Direction startDirection;

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", speed=" + speed +
                ", distanceLeft=" + distanceLeft +
                ", vehicleType=" + vehicleType +
                ", destination=" + destination +
                ", startDirection=" + startDirection +
                '}';
    }

}

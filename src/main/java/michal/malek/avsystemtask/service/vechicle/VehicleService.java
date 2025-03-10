package michal.malek.avsystemtask.service.vechicle;

import michal.malek.avsystemtask.model.route.enums.Direction;

import java.util.List;

public interface VehicleService {
    List<?> generateVehicles(int amount, Direction startingDirection);
}

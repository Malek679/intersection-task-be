package michal.malek.avsystemtask.service.vechicle;

import lombok.RequiredArgsConstructor;
import michal.malek.avsystemtask.model.dto.CarDataResponse;
import michal.malek.avsystemtask.model.route.enums.Direction;
import michal.malek.avsystemtask.model.vehicle.Car;
import michal.malek.avsystemtask.util.CarDataGenerator;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CarService implements VehicleService {
    private final CarDataGenerator carDataGenerator;
    private final Random random;

    @Override
    public List<Car> generateVehicles(int amount, Direction startingDirection) {
        LinkedList<Car> cars = new LinkedList<>();

        for (int i = 0; i < amount; i++) {
            CarDataResponse carDataResponse = carDataGenerator.getRandomCarData();
            String name = carDataResponse.getMake_and_model();
            String licensePlate = carDataResponse.getLicense_plate();
            int speed = random.nextInt(9) + 1;

            Direction random1 = Direction.getRandom();
            while(random1.equals(startingDirection)) {
                random1 = Direction.getRandom();
            }

            int distance = 10;
            Car car = new Car(name, speed, distance, licensePlate, random1 , startingDirection);
            cars.add(car);
        }
        return cars;
    }

}

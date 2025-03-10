package michal.malek.avsystemtask.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Random;

import michal.malek.avsystemtask.model.dto.CarDataResponse;
import michal.malek.avsystemtask.model.route.enums.Direction;
import michal.malek.avsystemtask.model.vehicle.Car;
import michal.malek.avsystemtask.model.vehicle.VehicleType;
import michal.malek.avsystemtask.service.vechicle.CarService;
import michal.malek.avsystemtask.util.CarDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CarServiceTest {

    @Mock
    private CarDataGenerator carDataGenerator;
    private CarService carService;
    private final Random fixedRandom = new Random() {
        @Override
        public int nextInt(int bound) {
            return 5;
        }
    };

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        carService = new CarService(carDataGenerator, fixedRandom);
    }

    @Test
    public void testGenerateVehicles() {
        String SAMPLE_CAR_NAME = "Toyota Corolla";
        String SAMPLE_LICENSE_PLATE = "ABC123";

        CarDataResponse carDataResponse = new CarDataResponse();
        carDataResponse.setMake_and_model(SAMPLE_CAR_NAME);
        carDataResponse.setLicense_plate(SAMPLE_LICENSE_PLATE);

        when(carDataGenerator.getRandomCarData()).thenReturn(carDataResponse);

        Direction startingDirection = Direction.NORTH;
        int amount = 3;

        List<Car> vehicles = carService.generateVehicles(amount, startingDirection);

        assertEquals(amount, vehicles.size());

        for (Car car : vehicles) {
            assertEquals(SAMPLE_CAR_NAME, car.getName());
            assertEquals(SAMPLE_LICENSE_PLATE, car.getLicensePlate());
            assertEquals(startingDirection, car.getStartDirection());
            assertNotEquals(startingDirection, car.getDestination());
            assertEquals(10, car.getDistanceLeft());
            assertEquals(VehicleType.CAR, car.getVehicleType());
            assertEquals(6, car.getSpeed());
        }

        verify(carDataGenerator, times(amount)).getRandomCarData();
    }
}


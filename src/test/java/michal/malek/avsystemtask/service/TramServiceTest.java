package michal.malek.avsystemtask.service;

import michal.malek.avsystemtask.model.route.enums.Direction;
import michal.malek.avsystemtask.model.vehicle.Tram;
import michal.malek.avsystemtask.service.vechicle.TramService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TramServiceTest {

    private TramService tramService;

    private final Random fixedRandom = new Random() {
        @Override
        public int nextInt(int bound) {
            return 5;
        }
    };

    @BeforeEach
    void setUp() {
        tramService = new TramService(fixedRandom);
    }

    @Test
    public void testGenerateVehicles() {
        Direction startingDirection = Direction.NORTH;
        int amount = 5;

        List<Tram> trams = tramService.generateVehicles(amount, startingDirection);
        assertNotNull(trams);
        assertEquals(amount, trams.size());

        for (Tram tram : trams) {
            assertEquals(6, tram.getSpeed());
            assertEquals(10, tram.getDistanceLeft());
            assertEquals("Sample Lane", tram.getLineName());
            assertEquals(startingDirection, tram.getStartDirection());
            assertNotEquals(startingDirection, tram.getDestination());
        }
    }
}

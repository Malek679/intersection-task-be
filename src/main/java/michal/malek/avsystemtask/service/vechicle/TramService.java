package michal.malek.avsystemtask.service.vechicle;

import lombok.RequiredArgsConstructor;
import michal.malek.avsystemtask.model.route.enums.Direction;
import michal.malek.avsystemtask.model.vehicle.Tram;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TramService implements VehicleService {
    private final Random random;

    @Override
    public List<Tram> generateVehicles(int amount, Direction startingDirection) {
        LinkedList<Tram> trams = new LinkedList<>();

        for (int i = 0; i < amount; i++) {
            int speed = random.nextInt(9) + 1;
            Direction random1 = Direction.getRandom();
            while(random1.equals(startingDirection)) {
                random1 = Direction.getRandom();
            }
            int distance = 10;
            Tram tram = new Tram("Tram GT6", speed, distance, "Sample Lane", random1, startingDirection);
            trams.add(tram);
        }
        return trams;
    }
}

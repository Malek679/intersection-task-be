package michal.malek.avsystemtask.configuration;

import michal.malek.avsystemtask.model.Intersection;
import michal.malek.avsystemtask.model.TrafficLightState;
import michal.malek.avsystemtask.model.enums.TrafficLight;
import michal.malek.avsystemtask.model.enums.TrafficLightColor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class IntersectionConfig {

    @Bean
    public Intersection intersection() {
        TrafficLightState defaultState = new TrafficLightState(TrafficLightColor.RED, new LinkedList<>());

        HashMap<TrafficLight, TrafficLightState> trafficLights = new HashMap<>();
        trafficLights.put(TrafficLight.NORTH_A, defaultState);
        trafficLights.put(TrafficLight.NORTH_B, defaultState);
        trafficLights.put(TrafficLight.SOUTH_A, defaultState);
        trafficLights.put(TrafficLight.SOUTH_B, defaultState);
        trafficLights.put(TrafficLight.WEST, defaultState);
        trafficLights.put(TrafficLight.EAST, defaultState);
        trafficLights.put(TrafficLight.TRAM, defaultState);

        List<TrafficLight> configuration1 = new ArrayList<>();
        configuration1.add(TrafficLight.NORTH_A);
        configuration1.add(TrafficLight.SOUTH_A);
        configuration1.add(TrafficLight.TRAM);

        List<TrafficLight> configuration2 = new ArrayList<>();
        configuration1.add(TrafficLight.NORTH_B);
        configuration1.add(TrafficLight.SOUTH_B);

        List<TrafficLight> configuration3 = new ArrayList<>();
        configuration1.add(TrafficLight.WEST);
        configuration1.add(TrafficLight.EAST);

        List<List<TrafficLight>> combinations = new ArrayList<>();
        combinations.add(configuration1);
        combinations.add(configuration2);
        combinations.add(configuration3);

        return Intersection.builder()
                .trafficLights(trafficLights)
                .combinations(combinations)
                .build();
    }
}

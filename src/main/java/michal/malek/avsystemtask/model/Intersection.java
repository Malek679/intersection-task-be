package michal.malek.avsystemtask.model;


import lombok.Builder;
import lombok.Data;
import michal.malek.avsystemtask.model.enums.TrafficLight;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
public class Intersection {
    private HashMap<TrafficLight, TrafficLightState> trafficLights;
    private List<List<TrafficLight>> combinations;
}

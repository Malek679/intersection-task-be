package michal.malek.avsystemtask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import michal.malek.avsystemtask.model.enums.TrafficLightColor;

import java.util.LinkedList;

@AllArgsConstructor
@Getter
@Setter
public class TrafficLightState {
    private TrafficLightColor color;
    private LinkedList<Car> cars;
}

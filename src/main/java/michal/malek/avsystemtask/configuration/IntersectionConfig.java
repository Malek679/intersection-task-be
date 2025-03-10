package michal.malek.avsystemtask.configuration;

import michal.malek.avsystemtask.model.Intersection;
import michal.malek.avsystemtask.model.route.Road;
import michal.malek.avsystemtask.model.route.Route;
import michal.malek.avsystemtask.model.route.Trails;
import michal.malek.avsystemtask.model.route.enums.Direction;
import michal.malek.avsystemtask.model.route.enums.TrafficLightColor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Configuration
public class IntersectionConfig {

    @Bean
    public Intersection intersection() {
        Road road1 = new Road(
                Direction.NORTH,
                new LinkedList<>(Arrays.asList(Direction.SOUTH, Direction.WEST)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road1.setRouteUid("R1");
        Road road2 = new Road(
                Direction.NORTH,
                new LinkedList<>(List.of(Direction.EAST)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road2.setRouteUid("R2");
        Road road3 = new Road(
                Direction.WEST,
                new LinkedList<>(Arrays.asList(Direction.EAST, Direction.SOUTH, Direction.NORTH)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road3.setRouteUid("R3");
        Road road4 = new Road(
                Direction.EAST,
                new LinkedList<>(Arrays.asList(Direction.NORTH, Direction.WEST, Direction.SOUTH)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road4.setRouteUid("R4");
        Road road5 = new Road(
                Direction.SOUTH,
                new LinkedList<>(List.of(Direction.NORTH, Direction.EAST)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road5.setRouteUid("R5");
        Trails road6 = new Trails(
                Direction.SOUTH,
                new LinkedList<>(List.of(Direction.NORTH)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road6.setRouteUid("T6");
        Road road7 = new Road(
                Direction.SOUTH,
                new LinkedList<>(List.of(Direction.WEST)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road7.setRouteUid("R7");

        LinkedList<Route> roads = new LinkedList<>();
        roads.add(road1);
        roads.add(road2);
        roads.add(road3);
        roads.add(road4);
        roads.add(road5);
        roads.add(road6);
        roads.add(road7);
        return new Intersection(roads, null , new LinkedList<>());
    }

    @Bean
    public Intersection intersection2() {
        Road road1 = new Road(
                Direction.NORTH,
                new LinkedList<>(Arrays.asList(Direction.EAST, Direction.WEST)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road1.setRouteUid("R1");

        Road road2 = new Road(
                Direction.WEST,
                new LinkedList<>(List.of(Direction.NORTH, Direction.SOUTH)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road2.setRouteUid("R2");

        Road road3 = new Road(
                Direction.SOUTH,
                new LinkedList<>(Arrays.asList(Direction.WEST, Direction.NORTH)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road3.setRouteUid("R3");

        Road road4 = new Road(
                Direction.EAST,
                new LinkedList<>(Arrays.asList(Direction.WEST, Direction.SOUTH)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road4.setRouteUid("R4");

        Road road5 = new Road(
                Direction.NORTH,
                new LinkedList<>(List.of(Direction.SOUTH)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road5.setRouteUid("R5");

        Road road6 = new Road(
                Direction.SOUTH,
                new LinkedList<>(Arrays.asList(Direction.EAST, Direction.NORTH)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road6.setRouteUid("R6");

        Trails road7 = new Trails(
                Direction.WEST,
                new LinkedList<>(List.of(Direction.EAST)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road7.setRouteUid("T7");

        LinkedList<Route> roads = new LinkedList<>();
        roads.add(road1);
        roads.add(road2);
        roads.add(road3);
        roads.add(road4);
        roads.add(road5);
        roads.add(road6);
        roads.add(road7);

        return new Intersection(roads, null , new LinkedList<>());
    }

    @Bean
    public Intersection intersection3() {
        Road road1 = new Road(
                Direction.NORTH,
                new LinkedList<>(Arrays.asList(Direction.SOUTH, Direction.WEST)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road1.setRouteUid("R1");

        Trails trails2 = new Trails(
                Direction.NORTH,
                new LinkedList<>(List.of(Direction.SOUTH)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        trails2.setRouteUid("T2");

        Road road3 = new Road(
                Direction.NORTH,
                new LinkedList<>(List.of(Direction.SOUTH)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road3.setRouteUid("R3");

        Road road4 = new Road(
                Direction.NORTH,
                new LinkedList<>(List.of(Direction.EAST)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road4.setRouteUid("R4");

        Road road5 = new Road(
                Direction.EAST,
                new LinkedList<>(List.of(Direction.NORTH)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road5.setRouteUid("R5");

        Road road6 = new Road(
                Direction.EAST,
                new LinkedList<>(List.of(Direction.WEST)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road6.setRouteUid("R6");

        Road road7 = new Road(
                Direction.EAST,
                new LinkedList<>(List.of(Direction.SOUTH)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road7.setRouteUid("R7");

        Road road8 = new Road(
                Direction.SOUTH,
                new LinkedList<>(Arrays.asList(Direction.WEST, Direction.NORTH)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road8.setRouteUid("R8");

        Trails road9 = new Trails(
                Direction.WEST,
                new LinkedList<>(List.of(Direction.EAST)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road9.setRouteUid("T9");

        LinkedList<Route> roads = new LinkedList<>();
        roads.add(road1);
        roads.add(trails2);
        roads.add(road3);
        roads.add(road4);
        roads.add(road5);
        roads.add(road6);
        roads.add(road7);
        roads.add(road8);
        roads.add(road9);
        return new Intersection(roads, null , new LinkedList<>());
    }

    @Bean
    public Intersection intersection4() {
        Road road1 = new Road(
                Direction.NORTH,
                new LinkedList<>(Arrays.asList(Direction.EAST,Direction.SOUTH, Direction.WEST)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road1.setRouteUid("R1");
        Road road2 = new Road(
                Direction.EAST,
                new LinkedList<>(List.of(Direction.WEST, Direction.NORTH, Direction.SOUTH)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road2.setRouteUid("R2");
        Road road3 = new Road(
                Direction.WEST,
                new LinkedList<>(Arrays.asList(Direction.EAST, Direction.SOUTH, Direction.NORTH)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road3.setRouteUid("R3");
        Road road4 = new Road(
                Direction.SOUTH,
                new LinkedList<>(Arrays.asList(Direction.NORTH, Direction.WEST, Direction.EAST)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        road4.setRouteUid("R4");


        LinkedList<Route> roads = new LinkedList<>();
        roads.add(road1);
        roads.add(road2);
        roads.add(road3);
        roads.add(road4);
        return new Intersection(roads, null , new LinkedList<>());
    }
}

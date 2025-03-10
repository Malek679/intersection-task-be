package michal.malek.avsystemtask.util;

import michal.malek.avsystemtask.model.route.Road;
import michal.malek.avsystemtask.model.route.Route;
import michal.malek.avsystemtask.model.route.enums.Direction;
import michal.malek.avsystemtask.model.route.enums.TrafficLightColor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class IntersectionUtilTest {
    private final IntersectionUtil intersectionUtil = new IntersectionUtil();

    @Test
    public void testSingleRoute() {
        Road r1 = new Road(
                Direction.NORTH,
                new LinkedList<>(Collections.singletonList(Direction.EAST)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );

        List<Route> routes = Collections.singletonList(r1);
        List<List<Route>> coverings = intersectionUtil.getCoveringCombinations(routes);

        Set<Route> coveredRoutes = new HashSet<>();
        coverings.forEach(coveredRoutes::addAll);
        assertEquals(1, coveredRoutes.size());
        assertTrue(coveredRoutes.contains(r1));
    }

    @Test
    public void testNonConflictingRoutes() {
        Road r1 = new Road(
                Direction.NORTH,
                new LinkedList<>(Collections.singletonList(Direction.EAST)),
                TrafficLightColor.GREEN,
                new LinkedList<>()
        );
        Road r2 = new Road(
                Direction.NORTH,
                new LinkedList<>(Collections.singletonList(Direction.WEST)),
                TrafficLightColor.GREEN,
                new LinkedList<>()
        );

        List<Route> routes = Arrays.asList(r1, r2);
        List<List<Route>> coverings = intersectionUtil.getCoveringCombinations(routes);

        Set<Route> coveredRoutes = new HashSet<>();
        coverings.forEach(coveredRoutes::addAll);
        assertEquals(2, coveredRoutes.size());
        assertTrue(coveredRoutes.contains(r1));
        assertTrue(coveredRoutes.contains(r2));

        boolean hasGroupOfTwo = coverings.stream().anyMatch(group -> group.size() == 2);
        assertTrue(hasGroupOfTwo);
    }

    @Test
    public void testConflictingRoutes() {
        Road r1 = new Road(
                Direction.NORTH,
                new LinkedList<>(Collections.singletonList(Direction.EAST)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );
        Road r2 = new Road(
                Direction.EAST,
                new LinkedList<>(Collections.singletonList(Direction.SOUTH)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );

        List<Route> routes = Arrays.asList(r1, r2);
        List<List<Route>> coverings = intersectionUtil.getCoveringCombinations(routes);

        for (List<Route> group : coverings) {
            assertTrue(group.size() <= 1);
        }
        Set<Route> coveredRoutes = new HashSet<>();
        coverings.forEach(coveredRoutes::addAll);
        assertEquals(2, coveredRoutes.size());
        assertTrue(coveredRoutes.contains(r1));
        assertTrue(coveredRoutes.contains(r2));
    }

    @Test
    public void testComplexCombination() {
        Road r1 = new Road(
                Direction.NORTH,
                new LinkedList<>(Collections.singletonList(Direction.EAST)),
                TrafficLightColor.GREEN,
                new LinkedList<>()
        );
        Road r2 = new Road(
                Direction.NORTH,
                new LinkedList<>(Collections.singletonList(Direction.WEST)),
                TrafficLightColor.GREEN,
                new LinkedList<>()
        );
        Road r3 = new Road(
                Direction.EAST,
                new LinkedList<>(Collections.singletonList(Direction.SOUTH)),
                TrafficLightColor.RED,
                new LinkedList<>()
        );

        List<Route> routes = Arrays.asList(r1, r2, r3);
        List<List<Route>> coverings = intersectionUtil.getCoveringCombinations(routes);

        Set<Route> coveredRoutes = new HashSet<>();
        coverings.forEach(coveredRoutes::addAll);
        assertEquals(3, coveredRoutes.size());

        boolean groupOfTwoFound = coverings.stream().anyMatch(group -> group.size() == 2);
        boolean groupOfOneFound = coverings.stream().anyMatch(group -> group.size() == 1);
        assertTrue(groupOfTwoFound);
        assertTrue(groupOfOneFound);
    }
}

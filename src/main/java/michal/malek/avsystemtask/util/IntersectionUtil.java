package michal.malek.avsystemtask.util;

import lombok.RequiredArgsConstructor;
import michal.malek.avsystemtask.model.route.Route;
import michal.malek.avsystemtask.model.route.enums.Direction;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class IntersectionUtil {

    public List<List<Route>> getCoveringCombinations(List<Route> routes) {
        List<List<Route>> validCombinations = new ArrayList<>();
        int n = routes.size();

        for (int i = 0; i < (1 << n); i++) {
            List<Route> currentCombination = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    currentCombination.add(routes.get(j));
                }
            }
            if (isValidCombination(currentCombination)) {
                validCombinations.add(currentCombination);
            }
        }

        Set<Route> uncovered = new HashSet<>(routes);
        List<List<Route>> coveringCombinations = new ArrayList<>();

        while (!uncovered.isEmpty()) {
            List<Route> bestCombo = null;
            int bestCoverCount = 0;
            for (List<Route> combo : validCombinations) {
                int coverCount = 0;
                for (Route r : combo) {
                    if (uncovered.contains(r)) {
                        coverCount++;
                    }
                }
                if (coverCount > bestCoverCount) {
                    bestCoverCount = coverCount;
                    bestCombo = combo;
                }
            }

            if (bestCombo == null) {
                break;
            }

            coveringCombinations.add(bestCombo);
            bestCombo.forEach(uncovered::remove);
        }
        return coveringCombinations;
    }

    private boolean isValidCombination(List<Route> combination) {
        for (int i = 0; i < combination.size(); i++) {
            for (int j = i + 1; j < combination.size(); j++) {
                if (routesConflict(combination.get(i), combination.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }


    private boolean routesConflict(Route r1, Route r2) {
        if (r1.getStartDirection().equals(r2.getStartDirection())) {
            return false;
        }
        if (isStraight(r1) && isStraight(r2) && areOpposite(r1.getStartDirection(), r2.getStartDirection())) {
            return false;
        }
        if (r1.getEndDirection().contains(r2.getStartDirection()) || r2.getEndDirection().contains(r1.getStartDirection())) {
            return true;
        }
        Direction leftOfR1 = leftOf(r1.getStartDirection());
        if (r1.getEndDirection().contains(leftOf(leftOfR1)) && r2.getEndDirection().contains(leftOfR1)) {
            return true;
        }
        Direction leftOfR2 = leftOf(r2.getStartDirection());
        if (r2.getEndDirection().contains(leftOf(leftOfR2)) && r1.getEndDirection().contains(leftOfR2)) {
            return true;
        }
        return false;
    }

    private Direction leftOf(Direction d) {
        return switch (d) {
            case NORTH -> Direction.WEST;
            case WEST -> Direction.SOUTH;
            case SOUTH -> Direction.EAST;
            case EAST -> Direction.NORTH;
        };
    }

    private boolean isStraight(Route route) {
        if(route.getEndDirection().size() == 1) {
            Direction end = route.getEndDirection().getFirst();
            return areOpposite(route.getStartDirection(), end);
        }
        return false;
    }

    private boolean areOpposite(Direction d1, Direction d2) {
        return (d1 == Direction.NORTH && d2 == Direction.SOUTH) ||
                (d1 == Direction.SOUTH && d2 == Direction.NORTH) ||
                (d1 == Direction.EAST && d2 == Direction.WEST) ||
                (d1 == Direction.WEST && d2 == Direction.EAST);
    }
}


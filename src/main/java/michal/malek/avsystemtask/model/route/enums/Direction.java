package michal.malek.avsystemtask.model.route.enums;


import lombok.Getter;

@Getter
public enum Direction {
    WEST(270), SOUTH(180), EAST(90), NORTH(0);

    private final int angle;

    Direction(int angle) {
        this.angle = angle;
    }

    public static Direction getRandom() {
        Direction[] directions = values();
        int randomIndex = java.util.concurrent.ThreadLocalRandom.current().nextInt(directions.length);
        return directions[randomIndex];
    }

    public static Direction fromString(String directionStr) {
        for (Direction direction : values()) {
            if (direction.name().equalsIgnoreCase(directionStr)) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Wrong direction: " + directionStr);
    }
}

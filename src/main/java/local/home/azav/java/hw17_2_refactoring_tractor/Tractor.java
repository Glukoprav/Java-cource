package local.home.azav.java.hw17_2_refactoring_tractor;

public class Tractor {

    private int posX = 0;
    private int posY = 0;
    private final Field field;
    Orientation orientation = Orientation.NORTH;

    public Tractor(Field field) {
        this.field = field;
    }

    public void move(String command) {
        switch (command) {
            case "F":
                moveForwards();
                break;
            case "T":
                turnClockwise();
                break;
            default:
                throw new RuntimeException("Неизвестная команда!");
        }
    }

    public void moveForwards() {
        switch (orientation) {
            case NORTH:
                posY++;
                break;
            case EAST:
                posX++;
                break;
            case SOUTH:
                posY--;
                break;
            case WEST:
                posX--;
                break;
        }
        checkCorrectNewPosition();
    }

    public void turnClockwise() {
        switch (orientation) {
            case NORTH:
                orientation = Orientation.EAST;
                break;
            case EAST:
                orientation = Orientation.SOUTH;
                break;
            case SOUTH:
                orientation = Orientation.WEST;
                break;
            case WEST:
                orientation = Orientation.NORTH;
                break;
        }
    }

    private void checkCorrectNewPosition() {
        if (posX > field.getFieldX() || posY > field.getFieldY() || posX < 0 || posY < 0) {
            throw new TractorInDitchException();
        }
    }

    public int getPositionX() {
        return posX;
    }

    public int getPositionY() {
        return posY;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}

// Old source code
//public class Tractor {
//    int[] position = new int[]{0, 0};
//    int[] field = new int[]{5, 5};
//    Orientation orientation = Orientation.NORTH;
//    public void move(String command) {
//        if (command == "F") {
//            moveForwards();
//        } else if (command == "T") {
//            turnClockwise();
//        }
//    }
//    public void moveForwards() {
//        if (orientation == Orientation.NORTH) {
//            position = new int[]{position[0], position[1] + 1};
//        } else if (orientation == Orientation.EAST) {
//            position = new int[]{position[0] + 1, position[1]};
//        } else if (orientation == Orientation.SOUTH) {
//            position = new int[]{position[0], position[1] - 1};
//        } else if (orientation == Orientation.WEST) {
//            position = new int[]{position[0] - 1, position[1]};
//        }
//        if (position[0] > field[0] || position[1] > field[1]) {
//            throw new TractorInDitchException();
//        }
//    }
//    public void turnClockwise() {
//        if (orientation == Orientation.NORTH) {
//            orientation = Orientation.EAST;
//        } else if (orientation == Orientation.EAST) {
//            orientation = Orientation.SOUTH;
//        } else if (orientation == Orientation.SOUTH) {
//            orientation = Orientation.WEST;
//        } else if (orientation == Orientation.WEST) {
//            orientation = Orientation.NORTH;
//        }
//    }
//    public int getPositionX() {
//        return position[0];
//    }
//    public int getPositionY() {
//        return position[1];
//    }
//    public Orientation getOrientation() {
//        return orientation;
//    }
//}
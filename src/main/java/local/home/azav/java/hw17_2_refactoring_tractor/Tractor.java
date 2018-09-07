package local.home.azav.java.hw17_2_refactoring_tractor;

class Tractor {

    private int posX = 0;
    private int posY = 0;
    private final Field field;
    private Orientation orientation = Orientation.NORTH;

    Tractor(Field field) {
        this.field = field;
    }

    void move(String command) {
        switch (command) {
            case "F":
                moveForwards();
                break;
            case "T":
                turnClockwise();
                break;
            default:
                throw new ArithmeticException("Неизвестная команда!");
        }
    }

    private void moveForwards() {
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

    private void turnClockwise() {
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

    int getPositionX() {
        return posX;
    }

    int getPositionY() {
        return posY;
    }

    Orientation getOrientation() {
        return orientation;
    }
}

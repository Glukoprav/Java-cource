package local.home.azav.java.hw17_2_refactoring_tractor;

public class Field {
    private final int fieldX;
    private final int fieldY;

    public Field(int fieldX, int fieldY) {
        this.fieldX = fieldX;
        this.fieldY = fieldY;
    }

    public int getFieldX() {
        return fieldX;
    }

    public int getFieldY() {
        return fieldY;
    }
}

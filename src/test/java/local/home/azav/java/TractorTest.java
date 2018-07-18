package local.home.azav.java;

import junit.framework.TestCase;
import local.home.azav.java.hw17_2_refactoring_tractor.Field;
import local.home.azav.java.hw17_2_refactoring_tractor.Orientation;
import local.home.azav.java.hw17_2_refactoring_tractor.Tractor;
import local.home.azav.java.hw17_2_refactoring_tractor.TractorInDitchException;
import org.junit.Before;
import org.junit.Test;

public class TractorTest extends TestCase {

    private static final int FIELD_HEIGHT = 5;
    private static final int FIELD_WIDTH = 5;

    Field field = new Field(FIELD_HEIGHT, FIELD_WIDTH);

    Tractor tractor;

    @Before
    public void setUp() {
        tractor = new Tractor(field);

    }

    @Test
    public void testShouldMoveForward(){
        tractor.move("F");
        assertEquals(0, tractor.getPositionX());
        assertEquals(1, tractor.getPositionY());
    }

    @Test
    public void testShouldTurn(){
        tractor.move("T");
        assertEquals(Orientation.EAST, tractor.getOrientation());
        tractor.move("T");
        assertEquals(Orientation.SOUTH, tractor.getOrientation());
        tractor.move("T");
        assertEquals(Orientation.WEST, tractor.getOrientation());
        tractor.move("T");
        assertEquals(Orientation.NORTH, tractor.getOrientation());
    }

    @Test
    public void testShouldTurnAndMoveInTheRightDirection(){
        tractor.move("F");
        tractor.move("T");
        tractor.move("F");
        assertEquals(1, tractor.getPositionX());
        assertEquals(1, tractor.getPositionY());
        tractor.move("T");
        tractor.move("F");
        assertEquals(1, tractor.getPositionX());
        assertEquals(0, tractor.getPositionY());
        tractor.move("T");
        tractor.move("F");
        assertEquals(0, tractor.getPositionX());
        assertEquals(0, tractor.getPositionY());
        tractor.move("T");
        tractor.move("F");
        assertEquals(0, tractor.getPositionX());
        assertEquals(1, tractor.getPositionY());
    }

    @Test
    public void testShouldThrowExceptionIfFallsOffPlateau(){
        tractor.move("F");
        tractor.move("F");
        tractor.move("F");
        tractor.move("F");
        tractor.move("F");
        try{
            tractor.move("F");
            fail("Tractor was expected to fall off the plateau");
        }catch(TractorInDitchException expected){
        }
    }
}

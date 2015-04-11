
package AntGame;

import AntGame.Tokens.Marker;
import AntGame.Tokens.*;
import AntGame.exceptions.*;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ms660
 */
public class PositionTest {
    
    public PositionTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of removeFood method, of class Position.
     */
    @Test
    public void testRemoveFood() throws Exception {
        
        System.out.println("-------- removeFood() --------");
        
        Position instance = new Position(1, 1);
                
        boolean thrown = false;
        
        try {
            instance.removeFood();
        } catch (PositionException e)
        {
            thrown = true;
        }
        
        assertTrue(thrown);
        
        instance.addFood(5);
        instance.removeFood();
        int result = instance.getFood();
        int exp = 4;
        
        assertEquals (result, exp);
        
    }

    /**
     * Test of hasFood method, of class Position.
     */
    @Test
    public void testHasFood() {
        System.out.println("-------- hasFood() --------");
        
        Position instance = new Position(1, 1);
        
        boolean exp = false;
        boolean result = instance.hasFood();
        
        assertEquals(exp, result);
        
        try {
            instance.addFood(5);
        } catch (PositionException e) {}
        
        exp = true;
        result = instance.hasFood();
        
        assertEquals(exp, result);
        
    }

    /**
     * Test of getFood method, of class Position.
     */
    @Test
    public void testGetFood() {
        System.out.println("-------- getFood() --------");
        
        Position instance = new Position(1, 1);
        
        int exp = 0;
        int result = instance.getFood();
        
        assertEquals(exp, result);
        
         try {
            instance.addFood(5);
        } catch (PositionException e) {}
        
        exp = 5;
        result = instance.getFood();
        
        assertEquals(exp, result);
        
        
    }

    /**
     * Test of addFood method, of class Position.
     */
    @Test
    public void testAddFood() throws Exception {
        
        System.out.println("-------- addFood() --------");
        
        Position instance = new Position(1, 1);
        int food = 0;
        boolean thrown = false;
        
        food = 5;
        instance.addFood(food);

        int exp = 5;
        int result = instance.getFood();
        
        assertEquals(exp, result);
        
        //Unable to add negative values
        food = -1;
        try {
            instance.addFood(food);
        } catch (PositionException e) {thrown = true;}
        
        assertTrue(thrown);
        
        //Unable to add food if rocky
        thrown = false;
        
        instance.setRocky();
        food = 5;
        try {
            instance.addFood(food);
        } catch (PositionException e) {thrown = true;}
                
        assertTrue(thrown);
    
    }

    /**
     * Test of addAnt method, of class Position.
     */
    @Test
    public void testAddAnt() throws Exception {
        System.out.println("-------- addAnt() --------");
        
        Ant ant = new Ant("Black");
        Position instance = new Position(1, 1);
        
        
        instance.addAnt(ant);
        Ant result = instance.getAnt();
        
        assertEquals(result, ant);
        
        //Unable to add if another ant exists
        boolean thrown = false;
        
        try {
            instance.addAnt(new Ant("Black"));
        } catch (PositionException e) {
            thrown = true;
        }
        
        assertTrue(thrown);
        
        //Unable to add if position is rocky.
        thrown = false;
        
        instance.setRocky();
        
        try {
            instance.addAnt(new Ant("Black"));
        } catch (PositionException e) {
            thrown = true;
        }
        
        assertTrue(thrown);
        
        
        
        
    }

    /**
     * Test of clearAnt method, of class Position.
     */
    @Test
    public void testClearAnt() {
        System.out.println("-------- clearAnt() --------");
        
        Ant ant = new Ant("Black");
        Position instance = new Position(1, 1);
        try {
            instance.addAnt(ant);
        } catch (PositionException e) {String _msg = e.getMessage();}
       
        instance.clearAnt();
        
        Ant result = instance.getAnt();
        Ant exp = null;
        
        assertEquals(exp, result);
    }

    /**
     * Test of getAnt method, of class Position.
     */
    @Test
    public void testGetAnt() {
        System.out.println("-------- getAnt() --------");
        
        Ant ant = new Ant("Black");
        Position instance = new Position(1, 1);
        
        try {
            instance.addAnt(ant);
        } catch (PositionException e) {String _msg = e.getMessage();}
        
        Ant result = instance.getAnt();
        
        assertEquals(result, ant);
    }

    /**
     * Test of setRocky method, of class Position.
     */
    @Test
    public void testSetRocky() throws Exception {
        
        System.out.println("-------- setRocky() --------");
        
        Position instance = new Position(1, 1);
        instance.setRocky();
        
        boolean result = instance.getRocky();
        
        assertTrue(result);
        
        instance = new Position(1, 1);
        instance.setAntHill("Black");
        
        boolean thrown = false;
        
        try {
            instance.setRocky();
        } catch (PositionException e) { thrown = true; }
        
        assertTrue(thrown);
        
    }

    /**
     * Test of getRocky method, of class Position.
     */
    @Test
    public void testGetRocky() {
        
        System.out.println("-------- getRocky() --------");
        
        Position instance = new Position(1, 1);
        
        boolean result = instance.getRocky();
        
        assertFalse(result);
        
        try {
            instance.setRocky();
        } catch (Exception e ) {String _msg = e.getMessage(); }
        
        result = instance.getRocky();
        
        assertTrue(result);
        
    }

    /**
     * Test of setMarker method, of class Position.
     */
    @Test
    public void testSetMarker() throws Exception {
        
        System.out.println("-------- setMarker() --------");
        
        Marker m = new Marker("Red", 1);
        Ant a = new Ant("Red");
        
        Position instance = new Position(1, 1);
        instance.addAnt(a);
        instance.setMarker(m);
        
        HashMap<String, Marker> markers = instance.senseMarkers(a);
        
        Marker result = markers.get(a.getColour());
        
        assertEquals(result, m);
        
        //Check the Exception is thrown when a marker already exists.
        boolean thrown = false;
        m = new Marker("Red", 2);
        
        try {
            instance.setMarker(m);
        } catch (PositionException e) { thrown = true; }
        
        assertTrue(thrown);
        
        //Check Exception is thrown when ant colour doesn't match the marker.
        thrown = false;
        m = new Marker("Black", 2);
        
        try {
            instance.setMarker(m);
        } catch (PositionException e) { thrown = true; }
        
        assertTrue(thrown);
        
    }

    /**
     * Test of clearMarker method, of class Position.
     */
    @Test
    public void testClearMarker() {
        
        System.out.println("-------- clearMarker() --------");
        
        Marker m = new Marker("Red", 1);
        Ant a = new Ant("Red");
        
        Position instance = new Position(1, 1);
        
        try {
            instance.addAnt(a);
            instance.setMarker(m);
            instance.clearMarker(m);
        } catch (Exception e) { String _msg = e.getMessage(); }
        
        HashMap<String, Marker> markers = instance.senseMarkers(a);
        
        Marker result = markers.get(a.getColour());
        Marker exp = null;
        
        assertEquals(result, exp);
        
        
    }

    /**
     * Test of senseMarkers method, of class Position.
     */
    @Test
    public void testSenseMarkers() {
        
        System.out.println("-------- senseMarker() --------");
        
        Ant ant = new Ant("Red");
        Marker antMarker = new Marker("Red", 1);
               
        Ant foe = new Ant("Black");
        Marker foeMarker = new Marker("Black", 1);
        
        Position instance = new Position(1, 1);
        
        try {
            //Red Ant
            instance.addAnt(ant);
            instance.setMarker(antMarker);
            instance.clearAnt();
            //Black Ant
            instance.addAnt(foe);
            instance.setMarker(foeMarker);
        } catch (Exception e) { String _msg = e.getMessage(); }
        
        HashMap<String, Marker> markers = instance.senseMarkers(ant);
        
        Marker teamResult = markers.get(ant.getColour());
        
        assertEquals(teamResult, antMarker);
        
        Marker foeResult = markers.get(foe.getColour());
        Marker foeExp = new Marker("Black", -1);
        
        assertEquals(foeResult.type, foeExp.type);
    }
    
    /**
     * Test of setAntHill method, of class Position.
     */
    @Test
    public void testSetAntHill() {
        System.out.println("-------- setAntHill() --------");
        
        String colour = "Black";
        
       
        Position instance = new Position(1, 1);
        instance.setAntHill(colour);
        String result = instance.getAntHill();
        
        assertEquals(result, colour);
        
        //Should still be black.
        instance.setAntHill("Red");
        result = instance.getAntHill();
        
        assertEquals(result, colour);
        
    }

    /**
     * Test of getAntHill method, of class Position.
     */
    @Test
    public void testGetAntHill() {
        System.out.println("-------- getAntHill() --------");
        
        Position instance = new Position(1, 1);
        String result = instance.getAntHill();
        
        assertEquals(result, null);
        
        String colour = "Black";
        instance.setAntHill(colour);
        result = instance.getAntHill();
        
        assertEquals(result, colour);
        
      
    }

    

    
}

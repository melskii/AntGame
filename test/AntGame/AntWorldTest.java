/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AntGame;

import AntGame.Tokens.Here;
import AntGame.Tokens.SenseDirection;
import AntGame.exceptions.PositionException;
import java.io.File;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Olivia
 */
public class AntWorldTest {
    
    public AntWorldTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPosition method, of class AntWorld.
     */
    //@Test
    public void testGetPosition_int_int() {
        System.out.println("getPosition");
        int x = 5;
        int y = 4;
        AntWorld instance = new AntWorld(x, y, null);
        
        Position expResult = new Position(2,2);
        Position result = instance.getPosition(2,2);
        
        boolean notNull = false;
        
        if(result != null){
            notNull = true;
        }
        
        assertTrue(notNull);
    }

    /**
     * Test of getPosition method, of class AntWorld.
     */
    //@Test
    public void testGetPosition_Coords() {
        System.out.println("getPosition");
        int x = 5;
        int y = 4;
        Coords c = new Coords(2, 2);
        AntWorld instance = new AntWorld(x, y , null);
        Position expResult = new Position(2,2);
        Position result = instance.getPosition(c);
                
        boolean notNull = false;
        
        if(result != null){
            notNull = true;
        }
        
        assertTrue(notNull);
    }

    /**
     * Test of getAnt method, of class AntWorld.
     */
    //@Test
    public void testGetAnt() throws PositionException {
        System.out.println("getAnt");
        AntWorld instance = new AntWorld(5,4, null);
        Position p = instance.getPosition(2,1);
        Ant ant = new Ant("red");
        p.addAnt(ant);
        Coords c = new Coords(2,1);
        
        Ant result = instance.getAnt(c);
        assertEquals(ant, result);
    }

    /**
     * Test of hasAnt method, of class AntWorld.
     */
    //@Test
    public void testHasAnt() throws PositionException {
        System.out.println("hasAnt");
        AntWorld instance = new AntWorld(5,4, null);
        Position p = instance.getPosition(2,1);
        Ant ant = new Ant("red");
        p.addAnt(ant);
        
        assertTrue(instance.hasAnt(p));
    }

    /**
     * Test of isSurrounding method, of class AntWorld.
     */
    //@Test
    public void testIsSurrounding() throws Exception {
        System.out.println("isSurrounding");
        AntWorld instance = new AntWorld(10,10, null);
        Position one = instance.getPosition(3,2);
        System.out.println("Added Position: " + one);
        Position two = instance.getPosition(2,2);
        System.out.println("Added Position: " + two);
        Position three = instance.getPosition(3,1);
        System.out.println("Added Position: " + three);
        Position four = instance.getPosition(1,1);
        System.out.println("Added Position: " + four);
        Position five = instance.getPosition(2,0);
        System.out.println("Added Position: " + five);
        Position ally = instance.getPosition(2,1);
        System.out.println("Added Position: " + ally);
        Ant ant1 = new Ant("red");
        Ant ant2 = new Ant("red");
        Ant ant3 = new Ant("red");
        Ant ant4 = new Ant("red");
        Ant ant5 = new Ant("red");
        Ant killed = new Ant("black");
        one.addAnt(ant1);
        two.addAnt(ant2);
        three.addAnt(ant3);
        four.addAnt(ant4);
        five.addAnt(ant5);
        ally.addAnt(killed);
        
        System.out.println("Ant: " + ally.getAnt());
        
        assertTrue(instance.isSurrounding(one, "red"));
    }

    /**
     * Test of isSurrounded method, of class AntWorld.
     */
    @Test
    public void testIsSurrounded() throws Exception {
        System.out.println("isSurrounded");
        AntWorld instance = new AntWorld(10,10, null);
        Position one = instance.getPosition(3,2);
        System.out.println("Added Position: " + one);
        Position two = instance.getPosition(2,2);
        System.out.println("Added Position: " + two);
        Position three = instance.getPosition(3,1);
        System.out.println("Added Position: " + three);
        Position four = instance.getPosition(1,1);
        System.out.println("Added Position: " + four);
        Position five = instance.getPosition(2,0);
        System.out.println("Added Position: " + five);
        Position ally = instance.getPosition(2,1);
        System.out.println("Added Position: " + ally);
        Ant ant1 = new Ant("red");
        Ant ant2 = new Ant("red");
        Ant ant3 = new Ant("red");
        Ant ant4 = new Ant("red");
        Ant ant5 = new Ant("red");
        Ant killed = new Ant("black");
        one.addAnt(ant1);
        two.addAnt(ant2);
        three.addAnt(ant3);
        four.addAnt(ant4);
        five.addAnt(ant5);
        ally.addAnt(killed);
        
        System.out.println("Ant: " + ally.getAnt());
        
        assertTrue(instance.isSurrounded(ally, "black"));
    }

    /**
     * Test of kill_ant method, of class AntWorld.
     */
    //@Test
    public void testKill_ant() throws Exception {
        System.out.println("kill_ant");
        
        AntWorld instance = new AntWorld(5,4, null);
        Position p = instance.getPosition(2,1);
        Ant ant = new Ant("red");
        p.addAnt(ant);
        instance.kill_ant(p);
        assertFalse(instance.hasAnt(p));
    }

    /**
     * Test of adjacentCell method, of class AntWorld.
     */
    //@Test
    public void testAdjacentCell() {
        System.out.println("adjacentCell");
        AntWorld instance = new AntWorld(5,4, null);
        Position p = instance.getPosition(2,1);
        Position adj = instance.adjacentCell(p, 0);
        Position expected = instance.getPosition(3,1);
        assertEquals(adj, expected);
   }

    /**
     * Test of sensed_cell method, of class AntWorld.
     */
    //@Test
    public void testSensed_cell() throws Exception {
        System.out.println("sensed_cell");
        AntWorld instance = new AntWorld(5,4, null);
        Position p = instance.getPosition(2,1);
        Here sense = new Here();
        Position sensed = instance.sensed_cell(p, 0, sense);
        assertEquals(sensed, p);
    }
    
}

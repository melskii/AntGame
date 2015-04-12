
package AntGame;

import AntGame.Tokens.*;
import AntGame.exceptions.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ms660
 */
public class AntTest {
    
    public AntTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of turn method, of class Ant.
     */
    @Test
    public void testTurn() throws Exception {
        System.out.println("-------- turn() --------");
        
        //Tests left and right
        
        Left_or_Right dir = new Left();
        Ant instance = new Ant("Black");
        
        int exp = 4;
        instance.turn(dir);
        int result = instance.turn(dir);
        
        assertEquals(exp, result);
        
        dir = new Right();
        exp = 0;
        instance.turn(dir);
        result = instance.turn(dir);
        
        assertEquals(exp, result);
        
        //Tests resting Exception
        instance.updateResting();
        boolean thrown = false;
        
        try {
            result = instance.turn(dir);
        } catch (AntException e)
        {
            System.out.println(e.getMessage());
            thrown = true;
        }
        
        assertTrue(thrown);
        
        //Tests alive Exception
        instance = new Ant("Black");
        instance.killAnt();
        thrown = false;
        
        try {
            result = instance.turn(dir);
        } catch (AntException e)
        {
            System.out.println(e.getMessage());
            thrown = true;
        }
        
        assertTrue(thrown);
        
    }

    /**
     * Test of getDirection method, of class Ant.
     */
    @Test
    public void testGetDirection() {
        
        System.out.println("-------- getDirection() --------");
        
        Ant instance = new Ant("Black");
        
        int exp = 0;
        int result = instance.getDirection();
        
        assertEquals(exp, result);
        
        Left_or_Right dir = new Right();
        
        try {
            instance.turn(dir);
            instance.turn(dir);
        } catch (Exception e) {}
        
        exp = 2;
        result = instance.getDirection();
        
        assertEquals(exp, result);
    }

    /**
     * Test of isAlive method, of class Ant.
     */
    @Test
    public void testIsAlive() {
        System.out.println("-------- isAlive() --------");
        
        Ant instance = new Ant("Black");
        
        boolean result = instance.isAlive();
        
        assertTrue(result);
        
        instance.killAnt();
        result = instance.isAlive();
        
        assertFalse(result);
    }

    /**
     * Test of killAnt method, of class Ant.
     */
    @Test
    public void testKillAnt() {
        System.out.println("-------- killAnt() --------");
        
        Ant instance = new Ant("Black");
        
        instance.killAnt();
        boolean result = instance.isAlive();
        
        assertFalse(result);
    }

    /**
     * Test of setState method, of class Ant.
     */
    @Test
    public void testSetAndGetState() {
        
        System.out.println("-------- setState() & getState() --------");
        
        int s = 55;
        Ant instance = new Ant("Black");
        instance.setState(s);
        
        int result = instance.getState();
        
        assertEquals(s, result);
        
    }

    /**
     * Test of getColour method, of class Ant.
     */
    @Test
    public void testSetAndGetColour() {
        System.out.println("-------- setColour() & getColour() --------");
        
        String exp = "Black";
        Ant instance = new Ant(exp);
        String result = instance.getColour();
        
        assertEquals(exp, result);
        
        String c = "Red";
        instance.setColour(c);
        
        result = instance.getColour();
        
        assertEquals(c, result);
        
    }

    /**
     * Test of resting method, of class Ant.
     */
    @Test
    public void testRestingAndUpdateResting() {
        
        System.out.println("-------- resting() & updateResting() --------");
        
        Ant instance = new Ant("Black");
        boolean result = instance.resting();
        
        assertFalse(result);
        
        instance.updateResting();
        result = instance.resting();
        
        assertTrue(result);
        
        for (int i = 0; i < 13; i++)
        {
            instance.updateResting();
        }
        
        result = instance.resting();
        
        assertFalse(result);
        
        
    }

   

    /**
     * Test of setFood method, of class Ant.
     */
    @Test
    public void testSetFood() throws Exception {
        System.out.println("-------- setFood() --------");
        Ant instance = new Ant("Black");
                
        instance.setFood();
        boolean result = instance.hasFood();
        
        assertTrue(result);
        
        //Check exception when already has food.
        boolean thrown = false;
        
        try {
            instance.setFood();
        }catch (AntException e)
        {
            System.out.println(e.getMessage());
            thrown = true;
        }
        
        assertTrue(thrown);
        
        //Tests resting Exception
        instance.updateResting();
        thrown = false;
        
        try {
            instance.setFood();
        } catch (AntException e)
        {
            System.out.println(e.getMessage());
            thrown = true;
        }
        
        assertTrue(thrown);
        
        //Tests alive Exception
        instance = new Ant("Red");
        instance.killAnt();
        thrown = false;
        
        try {
            instance.setFood();
        } catch (AntException e)
        {
            System.out.println(e.getMessage());
            thrown = true;
        }
        
        assertTrue(thrown);
    }

    /**
     * Test of clearFood method, of class Ant.
     */
    @Test
    public void testClearFood() {
        System.out.println("-------- clearFood() --------");
        
        Ant instance = new Ant("Black");
        try {
            instance.setFood();
        } catch (Exception e) {}
        instance.clearFood();
        boolean result = instance.hasFood();
        
        assertFalse(result);
        
    }

    /**
     * Test of hasFood method, of class Ant.
     */
    @Test
    public void testHasFood() {
        System.out.println("-------- hasFood() --------");
        
        Ant instance = new Ant("Black");
        try {
            instance.setFood();
        } catch (Exception e) {}
        
        boolean result = instance.hasFood();
        assertTrue(result);
        
        instance.clearFood();
        result = instance.hasFood();
        
        assertFalse(result);
    }
    
    /*
     * Test that ant starts facing east and have no food
     */
    @Test
    public void testAntFacingEastAndNoFood()
    {
        System.out.println("-------- Is the ant facing east and has no food when first initialised --------");
        
        Ant instance = new Ant("Black");
        
        int dir = instance.getDirection();
        
        //facing east
        assertEquals (dir, 0);
        
        boolean hasfood = instance.hasFood();
        
        assertFalse(hasfood);
        
        
    }
    
}

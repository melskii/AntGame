/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import AntGame.Tokens.Instruction;
import AntGame.exceptions.*;
import java.util.ArrayList;
import java.io.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ms660
 */
public class AntBrainTest {
    
    public AntBrainTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of the AntBrain constructor method, of class AntBrain.
     */
    @Test
    public void testAntBrainConstructor() throws Exception {
        System.out.println("-------- AntBrain() --------");
      
        
        File f = new File("N:\\Documents\\Year 2\\Software Engineering\\AntGame\\AntGame\\files\\sample.ant");
        AntBrain instance = new AntBrain(f);
        
    }
    
    /**
     * Test of validAntBrain method, of class AntBrain.
     */
    @Test
    public void testValidAntBrain() throws Exception {
        
        System.out.println("-------- validAntBrain() : valid --------");
      
        File f = new File("N:\\Documents\\Year 2\\Software Engineering\\AntGame\\AntGame\\files\\sample.ant");
        
        boolean thrown = false;
        
        try {
            AntBrain instance = new AntBrain(f);
        }
        catch (AntBrainException e)
        {
            thrown = true;
            System.out.println(e.getMessage());
        }
        
        assertFalse(thrown);
        
        System.out.println("-------- validAntBrain() : incorrect regex --------");
      
        f = new File("N:\\Documents\\Year 2\\Software Engineering\\AntGame\\AntGame\\files\\wrongregex.ant");
        
        thrown = false;
        
        try {
            AntBrain instance = new AntBrain(f);
        }
        catch (AntBrainException e)
        {
            thrown = true;
            System.out.println(e.getMessage());
        }
        
        assertTrue(thrown);
        
        
        
    }

    /**
     * Test of setAntWorld method, of class AntBrain.
     */
    //@Test
    public void testSetAntWorld() {
        System.out.println("setAntWorld");
        AntWorld world = null;
        AntBrain instance = null;
        instance.setAntWorld(world);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
    
    

    /**
     * Test of setColour method, of class AntBrain.
     */
    //@Test
    public void testSetColour() {
        System.out.println("setColour");
        String c = "";
        AntBrain instance = null;
        instance.setColour(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColour method, of class AntBrain.
     */
    //@Test
    public void testGetColour() {
        System.out.println("getColour");
        AntBrain instance = null;
        String expResult = "";
        String result = instance.getColour();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populateAnts method, of class AntBrain.
     */
    //@Test
    public void testPopulateAnts() {
        System.out.println("populateAnts");
        AntBrain instance = null;
        instance.populateAnts();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of step method, of class AntBrain.
     */
    //@Test
    public void testStep() {
        System.out.println("step");
        AntBrain instance = null;
        instance.step();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
}
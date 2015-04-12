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
    //@Test
    public void testAntBrainConstructor() throws Exception {
        System.out.println("-------- AntBrain() --------");
      
        
        File f = new File("N:\\Documents\\Year 2\\Software Engineering\\AntGame\\AntGame\\files\\sample.ant");
        AntBrain instance = new AntBrain(f);
        
    }
    
    /**
     * Test of validAntBrain method, of class AntBrain.
     */
    //@Test
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
     * Test of setColour method, of class AntBrain.
     */
    //@Test
    public void testSetAndGetColour() throws Exception {
        
        System.out.println("-------- setColour() & getColour() --------");
        
        String c = "Red";
        
        File f = new File("N:\\Documents\\Year 2\\Software Engineering\\AntGame\\AntGame\\files\\sample.ant");
        AntBrain instance = new AntBrain(f);
        
        instance.setAntWorld(new AntWorld(10,10));
        
        instance.setColour(c);
        String result = instance.getColour();
        
        assertEquals(result, c);
        
    }

    /**
     * Test of populateAnts method, of class AntBrain.
     */
    //@Test
    public void testPopulateAnts() throws Exception {
        
        System.out.println("-------- populateAnts() --------");
        
        
        
        File f = new File("N:\\Documents\\Year 2\\Software Engineering\\AntGame\\AntGame\\files\\sample.ant");
        AntBrain instance = new AntBrain(f);
        
        instance.setAntWorld(new AntWorld(10,10));
        
        instance.setColour("Red");
        
        instance.populateAnts();
        
       
    }
    
    /**
     * Test of getBrainScore method, of class AntBrain.
     */
    //@Test
    public void testGetBrainScore() throws Exception {
        
        System.out.println("-------- getBrainScore() --------");
        
        File f = new File("N:\\Documents\\Year 2\\Software Engineering\\AntGame\\AntGame\\files\\sample.ant");
        AntBrain instance = new AntBrain(f);
        
        AntWorld w = new AntWorld(10,10);
        
        w.getPosition(2, 4).setAntHill("Red");
        
        instance.setAntWorld(w);
        
        instance.setColour("Red");
        
        w.getPosition(2,4).addFood(5);
        
        
        instance.populateAnts();
        
        
        
        
        int result = instance.getBrainScore();
        int exp = 5;
        
        assertEquals(result, exp);
        
       
    }

    /**
     * Test of step method, of class AntBrain.
     */
    @Test
    public void testStep() throws Exception {
        
        System.out.println("-------- step() --------");
        
        File f = new File("N:\\Documents\\Year 2\\Software Engineering\\AntGame\\AntGame\\files\\sample.ant");
        AntBrain instance = new AntBrain(f);
        
        AntWorld w = new AntWorld(10,10);
        
        w.getPosition(2, 4).setAntHill("Red");
        w.getPosition(3, 4).setAntHill("Red");
        
        w.getPosition(0, 0).addFood(5);
        
        w.getPosition(8,9).setRocky();
        
        instance.setColour("Red");
        
        
        
        instance.setAntWorld(w);
        
        int _i = 0;
        
        while (_i < 300000)
        {
            instance.step();
            _i++;
        }
        
        
        System.out.println("------- Positions -------");
        
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
               System.out.println(w.getPosition(i, j).toString());
            }
        }
        
        
        
        
        
        
        
    }
    
    
}
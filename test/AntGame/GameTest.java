/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ms660
 */
public class GameTest {
    
    public GameTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of runGame method, of class Game.
     */
    @Test
    public void testRunGame() {
               
        System.out.println("runGame");
        
        File f = new File("N:\\Documents\\Year 2\\Software Engineering\\AntGame\\AntGame\\files\\sample.ant");
        
        boolean thrown = false;
        
        AntBrain player1;
        AntBrain player2;
        
        try {
            player1 = new AntBrain(f);
            player2 = new AntBrain(f);
            
            Game instance = new Game(player1, player2);
            AntBrain expResult = null;

            AntBrain result = instance.runGame();
            
            assertEquals(expResult, result);
            
        } catch (Exception e)
        {
            String _msg = e.getMessage();
        }
        
        
    }
}
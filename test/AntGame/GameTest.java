/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import AntGame.exceptions.*;
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
    public void testRunGame() throws AntBrainException, AntException, AntWorldGeneratorException, PositionException, IOException {
               
        System.out.println("runGame");
        
        File f = new File("N:\\Documents\\sample2.ant");
        File w = new File("N:\\Documents\\Year 2\\Software Engineering\\AntGame\\AntGame\\files\\tiny.world");
        
        boolean thrown = false;
        
        AntBrain player1;
        AntBrain player2;
        AntWorld world;
          
        player1 = new AntBrain(f);
        player2 = new AntBrain(f);
        AntWorldGenerator gen = new AntWorldGenerator();


        world = gen.antWorldGenerator(w);
        
        System.out.println(world.xlength + " x " + world.ylength);

        Game instance = new Game(player1, player2, world);
        
        AntBrain result = instance.runGame();

        AntBrain exp = null;
        
        if (player1.getBrainScore() > player2.getBrainScore())
        {
            exp = player1;
        }
        else if (player1.getBrainScore() < player2.getBrainScore())
        {
            exp = player2;
        }
        
        System.out.println("Player 1 : " + player1.getBrainScore() + " - " + player1.getDeadCount());
        System.out.println("Player 2 : " + player2.getBrainScore() + " - " + player2.getDeadCount());
        

        assertEquals(exp, result);
               
    }
}
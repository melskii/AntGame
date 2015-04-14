/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AntGame;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
public class TournamentTest {
    
    public TournamentTest() {
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
     * Test of runTournament method, of class Tournament.
     */
    @Test
    public void testRunTournament() throws Exception {
        System.out.println("runTournament");
        ArrayList<AntBrain> players = new ArrayList<>();
        AntWorld world = null;
        
        for (int i = 0; i < 3; i++) {
             File f = new File("N:\\Documents\\sample2.ant");
            
            AntWorldGenerator gen = new AntWorldGenerator();
            File fw =  gen.antWorldGenerator("test.world");
            AntBrain brain = new AntBrain(f);
            
            
            world = gen.antWorldGenerator(fw);
            
            players.add(brain);
        }
                
        Tournament instance = new Tournament(players, world);
        System.out.println("Uploaded Brains: " + instance.brains);
        System.out.println("Player Number: " + instance.playerNo);
        System.out.println("Scores: " + instance.score);
        
        instance.runTournament();
    }
    
    
}

/**
 * @author ms660
 */
package AntGame;

import java.io.*;
import java.util.Random;

public class Game {
    
    AntBrain antbrain1;
    AntBrain antbrain2;
    AntWorld world;
    
    
    
    /**
     * Class Constructor for Game. 
     * @param b1 Ant Brain for player 1
     * @param b2 Ant Brain for player 2
     */
    public Game(AntBrain b1, AntBrain b2) {
      
        AntWorldGenerator gen = new AntWorldGenerator();
        world = gen.antWorldGenerator(false);
        
        
        
        antbrain1 = b1;
        antbrain2 = b2;
            
       
        
    }
    
    /**
     * Class Constructor for Game. 
     * @param b1 Ant Brain for player 1
     * @param b2 Ant Brain for player 2
     * @param w Ant World
     */
    public Game (AntBrain b1, AntBrain b2, AntWorld w)
    {
        AntWorldGenerator gen = new AntWorldGenerator();
        
        antbrain1 = b1;
        antbrain2 = b2;
        
        System.out.println(world);
         
        this.world = w;
        
    }
    
    /**
     * Assigns each ant brain a colour and runs each game 300,000 turns.
     * 
     * @return winning AntBrain
     */
    public AntBrain runGame(){
        
        int counter = 300000;
        
        antbrain1.setAntWorld(world);
        antbrain2.setAntWorld(world);
        
        //Pick a team colour Randomly for ant brain 1 
        Random ran = new Random();
        
        if (ran.nextInt(2) == 0)
        {
            antbrain1.setColour("Black");
        }
        
        //Set the other team as the opposite colour
        antbrain2.setColour(antbrain2.getFoeColour());
        
        
        
        while (counter >= 0)
        {
            int play = 1;
            
            switch (play) {
                case 1: antbrain1.step();
                        play = 2;
                        break; 
                case 2: antbrain2.step();
                        play = 1;
                        break;
            }
            
            
            counter--;
            
           }
        
        int _score1 = antbrain1.getBrainScore();
        int _score2 = antbrain2.getBrainScore();
        AntBrain win = null;
        
        if (_score1 > _score2)
        {
            win = antbrain1;
        }
        
        else if (_score2 > _score1)
        {
            win = antbrain2;
        }
        
        
        return win;
        
        
        
    }
    
}

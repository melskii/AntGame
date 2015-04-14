/**
 * @author ms660
 */
package AntGame;

import AntGame.exceptions.*;
import java.io.*;
import java.util.Random;

public class Game {
    
    AntBrain antbrain1;
    AntBrain antbrain2;
    AntWorld world;
    
    int counter = 300000;
   
    
    /**
     * Class Constructor for Game. 
     * @param b1 Ant Brain for player 1
     * @param b2 Ant Brain for player 2
     */
    public Game(AntBrain b1, AntBrain b2) throws PositionException, AntWorldGeneratorException, IOException , AntBrainException{
      
        AntWorldGenerator gen = new AntWorldGenerator();
        File f = gen.antWorldGenerator("new_world");
        
        world = gen.antWorldGenerator(f);
      
        antbrain1 = b1;
        antbrain2 = b2;
        
        setupGame();
            
      
    }
    
    /**
     * Class Constructor for Game. 
     * @param b1 Ant Brain for player 1
     * @param b2 Ant Brain for player 2
     * @param w Ant World
     */
    public Game (AntBrain b1, AntBrain b2, AntWorld w) throws AntBrainException
    {
       //AntWorldGenerator gen = new AntWorldGenerator();
        this.world = w;
        
        antbrain1 = b1;
        antbrain2 = b2;
        
        setupGame();
           
    }
    
    public void setupGame() throws AntBrainException
    {
         //Pick a team colour Randomly for ant brain 1 
        Random ran = new Random();
        
        if (ran.nextInt(2) == 0)
        {
            antbrain1.setColour("Black");
        }
        else {
            antbrain1.setColour("Red");
        }
        
        //Set the other team as the opposite colour
        antbrain2.setColour(antbrain1.getFoeColour());
    
        
        antbrain1.setAntWorld(world);
        antbrain2.setAntWorld(world);
        
    }
    
    /**
     * Assigns each ant brain a colour and runs each game 300,000 turns.
     * 
     * @return winning AntBrain
     */
    public AntBrain runGame() throws PositionException, AntException, AntBrainException {
        
        System.out.println("Start Here");
        
        counter = 300000;
        
        long timer = 0;
        long step = 3000;
        //long step = 100;
  
        int play = 1;
        
        while (counter >= 0)
        {
             
           
            if (timer == 0) {
                
                

            
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
            
            timer = (timer+1) % step;
          
            timer = (timer+1) % step;
            //System.out.println("timer = " + timer);
            
            
        }
        
        System.out.println("counter = " + counter);
        
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
        
        
        
        System.out.println("End Game");
        return win;
        
        
        
    }
    
    public int getRemainingMoves()
    {
        return counter;
    }
    
}

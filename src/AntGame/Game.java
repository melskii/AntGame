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
     * Class Constructor for Game. Creates a Random world and validates each Ant Brain
     * @param b1 File with Ant Brain for player 1
     * @param b2 File with Ant Brain for player 2
     */
    public Game(File b1, File b2) {
      
        AntWorldGenerator gen = new AntWorldGenerator();
        world = gen.antWorldGenerator(false);
        
        
        try {
            antbrain1 = new AntBrain(b1);
            antbrain2 = new AntBrain(b2);
            
        }
        
        catch (Exception e) {
            
            String _msg;
            
            if (e instanceof IOException)
            {
                _msg = "File Error: Please try uploading the file again";
            }
            else {
                _msg = e.getMessage();
            }
            
            System.out.println(_msg);
        }
        
    }
    
    /**
     * Class Constructor for Game. Validates a Random world and validates each Ant Brain
     * @param b1 File with an Ant Brain for player 1
     * @param b2 File with an Ant Brain for player 2
     * @param w File with an Ant World
     */
    public Game (File b1, File b2, File w)
    {
        AntWorldGenerator gen = new AntWorldGenerator();
        
        String world = null;
        
        try {
        
            BufferedReader br = new BufferedReader(new FileReader(w));
        
            String line = null;

            while ((line = br.readLine()) != null) {

                world += line;
                
            }
        }
        catch (IOException io)
        {
            String _msg = io.getMessage();
        }
        
        System.out.println(world);
         
        this.world = gen.antWorldGenerator(world, false);
        
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

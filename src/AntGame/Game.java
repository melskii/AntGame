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
        
        //this needs to be changed to include a calc winner instead of always choosing antbrain1.
        AntBrain win = antbrain1;
        
        return win;
        
        
        
    }
    
}

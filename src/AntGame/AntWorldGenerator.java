/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import java.util.Random;


public class AntWorldGenerator {
    
    
    AntWorld antWorld;
    
    public AntWorld antWorldGenerator(String file, boolean tournament)
    {
        return antWorld;
    }
    
    public AntWorld antWorldGenerator(boolean tournament)
    {
        
        
        if (tournament)
        {
            antWorld = new AntWorld(150, 150);
        }
        
        else 
        {
            Random size = new Random();
            
            antWorld = new AntWorld(size.nextInt(250), size.nextInt(250));
        }
        
        return antWorld;
        
        
    }
    
    public Boolean validWorld(AntWorld a)
    {
        return false;
    }
    
    
    
}

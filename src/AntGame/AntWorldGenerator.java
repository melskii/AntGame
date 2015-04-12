/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import AntGame.exceptions.PositionException;
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
            
            //rocky perimeter
            for (int i = 0; i < 150; i++) {
                for (int j = 0; j < 150; j++) {
                    if (i == 0 || j == 0 || i == 149 || j == 149) {
                        try {
                            antWorld.getPosition(i, j).setRocky();
                        } 
                        catch (PositionException ex) {
                        }
                    }
                }
            }
            
            //generate where
            //antWorld = hillGen(antWorld, x, y, "Black");
            
            //generate where
            //antWorld = hillGen(antWorld, x, y, "Red");
            
            
//            Random rand = new Random();
//            int d = rand.nextInt(6);
//            //generate where
//            antWorld = foodGen(antWorld, x, y, d); //to be done 11 times
            
            //make 14 rocks
            
            
                
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
    
    //generates anthill by horizontal lines from top left corner
    private AntWorld hillGen(AntWorld antWorld, int x, int y, String colour) {
        Position position = antWorld.getPosition(x, y);
        Position startOfLine;
        int nextDir = 2;
        int lengthLine = 7;
        for (int i = 0; i < 13; i++) {
            
            if (i == 7) {
                nextDir = 1;
            }
            
            position.setAntHill(colour);
            startOfLine = position;
            
            for (int j = 0; j < lengthLine - 1; j++) {
                position = antWorld.adjacentCell(position, 0);
                position.setAntHill(colour);
                System.out.println("test1");
            }
            
            position = antWorld.adjacentCell(startOfLine, nextDir);
            System.out.println("test2");
            
            if (nextDir == 2) {
                lengthLine += 1;
            }
            else if (nextDir == 1) {
                lengthLine -= 1;
            }
            System.out.println(i);
            
        } 
        
        return antWorld;
    }
    
    //int d is randomly generated to determine orientation of food blob
    //use adjacentCell() to find next position
    private AntWorld foodGen(AntWorld antworld, int x, int y, int d) throws PositionException {
        Position position = antworld.getPosition(x, y);
        Position startOfLine;
        int nextLine = (d + 1) % 6;
        
        for (int i = 0; i < 5; i++) {
            
            position.addFood(5);
            startOfLine = position;
            
            for (int j = 0; j < 4; j ++) {
                position = antWorld.adjacentCell(position, d);
                position.addFood(5);
            }
            
            position = antWorld.adjacentCell(startOfLine, nextLine);
            
            
        }
        
        return antWorld;
    }
    
    
    
}

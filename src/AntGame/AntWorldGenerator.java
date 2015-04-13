/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import AntGame.exceptions.AntWorldGeneratorException;
import AntGame.exceptions.PositionException;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;



public class AntWorldGenerator {
    
    
    private AntWorld antWorld;
    public int x;
    public int y;
    
    public AntWorld antWorldGenerator(File a) throws IOException, AntWorldGeneratorException, PositionException  
    {
        ArrayList<String> world = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(a));
        
        String line = null;
        
        while ((line = br.readLine()) != null) {
            
            //System.out.println(line);
            world.add(line);
            
        }
        
        String firstLine = world.get(0);
        String secondLine = world.get(1);
        
        try {
            
            x = Integer.parseInt(firstLine);
            y = Integer.parseInt(secondLine);
            antWorld = new AntWorld(x, y);
            
        } catch (NumberFormatException e) {
            
            throw new AntWorldGeneratorException("First two lines must be integers only");
        }
        
        world.remove(0);
        world.remove(0);
        
        boolean even = true;
        
        for (int y = 0; y < world.size(); y++) {
            
            even = (y % 2 == 0);
            
            line = world.get(y);
            StringBuilder sb = new StringBuilder(line);
            int x = 0;
            boolean prevWasSpace = false;
            char position;
            
            
            while(line.length() > 0) {
                
                
                position = line.charAt(0);
                
                if (!even) {
                    if(!Character.isWhitespace(position)) {
                        throw new AntWorldGeneratorException("Every other line must start with a space");
                    }
                    else {
                        sb.deleteCharAt(0);
                        line = sb.toString();
                        prevWasSpace = false;
                        if (line.isEmpty()) {
                            continue;
                        }
                        position = line.charAt(0);
                    }  
                }
                      
                if (x == 0) {
                    if (position == '#') {
                        try {
                            antWorld.getPosition(x, y).setRocky();
                            sb.deleteCharAt(0);
                            line = sb.toString();
                            x += 1;
                            prevWasSpace = false;
                            continue;
                        } catch (PositionException ex) {
                            
                        }
                    }
                    else 
                        throw new AntWorldGeneratorException("Perimeter must be rocky");
                }
                
                if (position == '#') {
                    antWorld.getPosition(x, y).setRocky();
                    sb.deleteCharAt(0);
                    line = sb.toString();
                    x += 1;
                    prevWasSpace = false;
                    continue;
                }
                
                if (position == '.') {
                    sb.deleteCharAt(0);
                    line = sb.toString();
                    x += 1;
                    prevWasSpace = false;
                    continue;
                }
                
                if (Character.isDigit(position)) {
                    antWorld.getPosition(x, y).addFood(Character.getNumericValue(position));
                    sb.deleteCharAt(0);
                    line = sb.toString();
                    x += 1;
                    prevWasSpace = false;
                    continue;
                }
                
                if (position == '+') {
                    antWorld.getPosition(x, y).setAntHill("Red");
                    sb.deleteCharAt(0);
                    line = sb.toString();
                    x += 1;
                    prevWasSpace = false;
                    continue;                    
                }
                
                if (position == '-') {
                    antWorld.getPosition(x, y).setAntHill("Black");
                    sb.deleteCharAt(0);
                    line = sb.toString();
                    x += 1;
                    prevWasSpace = false;
                    continue;                    
                }
                
                if (Character.isWhitespace(position)) {
                    if (prevWasSpace)
                        throw new AntWorldGeneratorException("Cells must be separated by only one whitespace");
                    sb.deleteCharAt(0);
                    line = sb.toString();
                    prevWasSpace = true;
                    continue;
                }
                
                else
                    throw new AntWorldGeneratorException("Unknown character in world file!");
                
            }
            
        }
        
        
        
        return antWorld;
    }
    
    public AntWorld antWorldGenerator(boolean tournament)
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
            
            //generate where then make first anthill
            //antWorld = hillGen(antWorld, 10, 10, "Black");
            
            //generate where then make second
            //antWorld = hillGen(antWorld, x, y, "Red");
            
            
//            Random rand = new Random();
//            int d = rand.nextInt(6);
//            //generate where
//            antWorld = foodGen(antWorld, x, y, d); //to be done 11 times
            
            //make 14 rocks

        
        return antWorld;
        
        
    }
    
    public Boolean validWorld(AntWorld a)
    {
        return false;
    }
    
    //generates hexagon of side length 7 by horizontal lines from top left corner
    private AntWorld hillGen(AntWorld antWorld, int x, int y, String colour) {
        Position position = antWorld.getPosition(x, y);
        Position startOfLine;
        int nextDir = 2;
        int lengthLine = 7;
        for (int i = 0; i < 13; i++) {
            
            if (i == 6) {        //  i == 6 at the widest part of the hexagon so change direction
                nextDir = 1;
            }
            
            position.setAntHill(colour);
            //System.out.println(position);
            startOfLine = position;
            
            for (int j = 0; j < lengthLine - 1; j++) {
                position = antWorld.adjacentCell(position, 0);
                position.setAntHill(colour);
                //System.out.println(position);
            }
            
            position = antWorld.adjacentCell(startOfLine, nextDir);
            //System.out.println("test2");
            
            if (nextDir == 2) {
                lengthLine += 1;
            }
            else if (nextDir == 1) { //if the direction has changed then each line needs to be smaller
                lengthLine -= 1;
            }
            //System.out.println(i);
            //System.out.println(lengthLine);
        } 
        
        return antWorld;
    }
    
    //int d is randomly generated to determine orientation of food blob
    //use adjacentCell() to find next position
    private AntWorld foodGen(AntWorld antworld, int x, int y, int d) throws PositionException {
        Position position = antworld.getPosition(x, y);
        Position startOfLine;
        int nextLine = (d + 1) % 6; // one right turn 
        
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import AntGame.exceptions.AntWorldGeneratorException;
import AntGame.exceptions.PositionException;
import java.io.*;
import static java.lang.Math.abs;
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
            antWorld = new AntWorld(x, y, a);
            
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
                    
                    if(position != ' ' && x == 0) {   
                      sb = new StringBuilder(' ' + line);
                    }
                   
                    sb.deleteCharAt(0);
                    line = sb.toString();
                    prevWasSpace = false;
                    if (line.isEmpty()) {
                        continue;
                    }
                    position = line.charAt(0);
                    
                     
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
    
    public File antWorldGenerator(String fileName) throws PositionException, AntWorldGeneratorException, IOException
    {
        ArrayList<Coords> generated = new ArrayList<>();
        antWorld = new AntWorld(150, 150, null);
            
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
            
        Coords firstHill = findFirstHillSpace();
        Coords secondHill = findSecondHillSpace(firstHill);
        generated.add(firstHill);
        generated.add(secondHill);
        
        for(int i = 0; i < 11; i++) {
            generated.add(findFoodSpace(generated));
        }
        
        Random rand = new Random();
        
        //generated holds coordinates for placement of anthills and food blobs - indices 0 and 1 are valid for anthills
        for (int i = 0; i < generated.size(); i++) {
            int x = generated.get(i).getX();
            int y = generated.get(i).getY();
            if (i == 0) {
                antWorld = hillGen(antWorld, x, y, "Black");
            }
            else if (i == 1) {
                antWorld = hillGen(antWorld, x, y, "Red");
            }
            else {
                int d = rand.nextInt(6);
                antWorld = foodGen(antWorld, x, y, d);
            }
        }

        antWorld = rockGen(antWorld); //adds fourteen rocks

        return antWorldOutput(antWorld, fileName);
    }
    
    public Boolean validWorld(AntWorld a)
    {
        return false;
    }
    
    private Coords findFirstHillSpace() {

        int potentialX = 0;
        int potentialY = 0;
        Random rand = new Random();     //the antworld will be empty aside from the rocky perimer so it does not matter where the hill generates
        potentialX = rand.nextInt(136) + 4;
        potentialY = rand.nextInt(134) + 1;
        return new Coords(potentialX, potentialY);
        
    }
    
    private Coords findSecondHillSpace(Coords firstHill) {
        
        int potentialX = 0;
        int potentialY = 0;
        int firstX = firstHill.getX();
        int firstY = firstHill.getY();
        boolean found = false;
        Random rand = new Random();
        
        while (!found) {
            potentialX = rand.nextInt((136 - 4) + 1) + 4;
            potentialY = rand.nextInt((134 - 1) + 1) + 1;
            
            if (abs(potentialX - firstX) > 13 || abs(potentialY - firstY) > 13)
                    found = true;
        }
        return new Coords(potentialX, potentialY);
    }
    
    private Coords findFoodSpace(ArrayList<Coords> coordList) {
        
        Random rand = new Random();
        int potentialX = 0;
        int potentialY = 0;
        boolean found = false;
        Coords current;
        
        while(!found) {
            
            potentialX = rand.nextInt(139 - 10) + 10;
            potentialY = rand.nextInt(139 - 10) + 10;
            
            for(int i = 0; i < coordList.size(); i++) {
                current = coordList.get(i);
                
                if (i == 0 || i == 1) {
                    found = abs(potentialX - current.getX()) > 23 || abs(potentialY - current.getY()) > 23;
                    if (!found)
                        break;
                }
                
                found = abs(potentialX - current.getX()) > 18 || abs(potentialY - current.getY()) > 18;
                if (!found)
                    break;
            }
        }
        
        return new Coords(potentialX, potentialY);
    }    
    
    //generates hexagon of side length 7 by horizontal lines from top left corner
    private AntWorld hillGen(AntWorld antworld, int x, int y, String colour) throws AntWorldGeneratorException { 
        
        Position position = antworld.getPosition(x, y);
        Position startOfLine;
        int nextDir = 2;
        int lengthLine = 7;
        for (int i = 0; i < 13; i++) {
            
            if (i == 6) {        //  i == 6 at the widest part of the hexagon so change direction
                nextDir = 1;
            }
            if (position.getRocky() || position.getAntHill() != null)
                throw new AntWorldGeneratorException("Already an anthill");
            position.setAntHill(colour);
            startOfLine = position;
            
            for (int j = 0; j < lengthLine - 1; j++) {
                position = antworld.adjacentCell(position, 0);
                if (position.getRocky() || position.getAntHill() != null)
                    throw new AntWorldGeneratorException("Already an anthill");
                position.setAntHill(colour);
            }
            
            position = antworld.adjacentCell(startOfLine, nextDir);
            
            if (nextDir == 2) {
                lengthLine += 1;
            }
            else if (nextDir == 1) { //if the direction has changed then each line needs to be smaller
                lengthLine -= 1;
            }
        } 
        
        return antworld;
    }
    
    //int d is randomly generated to determine orientation of food blob
    //use adjacentCell() to find next position
    public AntWorld foodGen(AntWorld antworld, int x, int y, int d) throws PositionException, AntWorldGeneratorException { 
        Position position = antworld.getPosition(x, y);
        Position startOfLine;
        int nextLine = (d + 1) % 6; // one right turn 
        
        for (int i = 0; i < 5; i++) {
            
            if (position.getAntHill() != null)
                throw new AntWorldGeneratorException("Already an anthill, can't make food blob");
            position.addFood(5);
            startOfLine = position;
            
            for (int j = 0; j < 4; j ++) {
                position = antworld.adjacentCell(position, d);
                if (position.getAntHill() != null)
                    throw new AntWorldGeneratorException("Already an anthill, can't make food blob");
                position.addFood(5);
            }
            
            position = antworld.adjacentCell(startOfLine, nextLine);
            
            
        }
        
        return antworld;
    }
    
    public AntWorld rockGen(AntWorld antworld) throws PositionException {
        
        Random rand = new Random();
        int potentialX = 0;
        int potentialY = 0;
        int rockCount = 0;
        boolean valid = true;
        
        while(rockCount < 14) {
            
            potentialX = rand.nextInt(148) + 1;
            potentialY = rand.nextInt(148) + 1;
            valid = true;
            Position position = antworld.getPosition(potentialX, potentialY);
            
            if (position.getRocky() || position.getAntHill() != null || position.hasFood()) 
                continue;
            
            for (int i = 0; i < 6; i++) {
                Position surrounding = antworld.adjacentCell(position, i);
                if (surrounding.getRocky() || surrounding.getAntHill() != null)
                    valid = false;
            }
            
            if(valid) {
                    position.setRocky();
                    rockCount += 1;
            }
            
        }
        
        return antworld;
    }
    
    public File antWorldOutput(AntWorld antWorld, String fileName) throws UnsupportedEncodingException, IOException {
        ArrayList<String> lines = new ArrayList<>();
        Position position;
        boolean even;
        StringBuilder sb;
        
        for (int y = 0; y < 150; y++) {
            
            sb = new StringBuilder();
            
            even = (y % 2 == 0);
            
            for (int x = 0; x < 150; x++) {
                position = antWorld.getPosition(x, y);
                
                if(!even){
                    if (x == 0) {
                        sb.append(' ');
                    }
                }
                
                if (position.getRocky()) {
                    sb.append('#');
                    sb.append(' ');
                    continue;
                }
                
                if ("Black".equals(position.getAntHill())) {
                    sb.append('-');
                    sb.append(' ');
                    continue;
                }
                
                if ("Red".equals(position.getAntHill())) {
                    sb.append('+');
                    sb.append(' ');
                    continue;
                }
                
                if (position.hasFood()) {
                    sb.append(position.getFood());
                    sb.append(' ');
                    continue;
                }
                
                else {
                    sb.append('.');
                    sb.append(' ');
                }
                
            }
            lines.add(sb.toString());
        }
        
        File file = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("150\n");
            writer.write("150\n");
            for (int i = 0; i < lines.size(); i++) {
                writer.write(lines.get(i) + '\n');
            }
        }
        
        return file;
    }
    
    

}

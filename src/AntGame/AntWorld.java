/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import java.util.ArrayList;
import AntGame.Tokens.*;
import AntGame.Exceptions.*;
import AntGame.exceptions.PositionException;

public class AntWorld {
    
    Position[][] antworld;
    
    public AntWorld(int x, int y) {   //might be better to have a contructor with antworld[][] parameter to be passed from generator already made rocky etc 
        
        antworld = new Position[x][y];
        
    }
    
    public Position getPosition(int x, int y) { //superfluous?
        return antworld[x][y];
    }
    
    public Position getPosition(Coords c) {
        int x = c.getX();
        int y = c.getY();
        
        return antworld[x][y];
    }
    
    public void killAnt(Coords c) {
        int x = c.getX();
        int y = c.getY();
        
        antworld[x][y].clearAnt();
        try {
            antworld[x][y].addFood(5);
        } catch (Exception e) { String _msg = e.getMessage(); }
    }
    
    public Ant getAnt(Coords c) {
        int x = c.getX();
        int y = c.getY();
        
        return antworld[x][y].getAnt();
    }
    
    public boolean hasAnt(Position p) {
        int x = p.x;
        int y = p.y;
        
        return (!(antworld[x][y].getAnt() == null)); //Ugly because Position has no hasAnt()
    }
    
    public boolean isSurrounding(Position p, String colour) throws PositionException{
        boolean surrounding = false;
        
        for(int j = 0; j < 6; j++){
            Position search = adjacentCell(p, j);
            if(hasAnt(search)){
                Ant potentialKill = search.getAnt();
                
                String col = potentialKill.getColour();
                
                if(!col.equals(colour)){
                    Position enemy = potentialKill.getPosition();
                    isSurrounded(enemy, col);
                }
            }
        }
        
        return surrounding;
    }
    
    public boolean isSurrounded(Position p, String colour) throws PositionException { //I did this wrong, doesn't check colour doh
        boolean surrounded = true;

        int i = 0;
        
        for(int j = 0; j < 6; j++){
            while(i < 3){
                Position search = adjacentCell(p, j);
                if(!hasAnt(search)){
                    i++;
                }
                
                else if(i == 2){
                    surrounded = false;
                    break;
                }
                
                else if(hasAnt(search)){
                    Ant surrounding = search.getAnt();
                    
                    String surColour = surrounding.getColour();
                    
                    if(surColour.equals(colour)){
                        i++;
                    }
                }
            }
        }
        
        if(surrounded){
            kill_ant(p);
        }
        return surrounded;
    }
    
    public void kill_ant(Position p) throws PositionException{
        p.clearAnt();
        Ant killed = p.getAnt();
        
        killed.setPosition(null);
        
        p.addFood(3);
    }
    
    public Position adjacentCell(Position c, int dir){  
        int x = c.x;
        int y = c.y;
        int newX = x;
        int newY = y;
        
        switch(dir){
            
            case 0:
                newX = x + 1;
                newY = y;
                break;
                
            case 1:
                if (y % 2 == 0) {
                    newX = x;
                    newY = y + 1;
                    break;
                }
                else {
                    newX = x + 1;
                    newY = y + 1;
                    break;
                }
                
            case 2: 
                if (y % 2 == 0) {
                    newX = x - 1;
                    newY = y + 1;
                    break;
                }
                else {
                    newX = x;
                    newY = y + 1;
                    break;
                }
                
            case 3:
                newX = x - 1;
                newY = y;
                break;
                
            case 4:
                if (y % 2 == 0) {
                    newX = x - 1;
                    newY = y -1;
                    break;
                }
                else {
                    newX = x;
                    newY = y - 1;
                    break;
                }
                
            case 5:
                if (y % 2 == 0) {
                    newX = x;
                    newY = y - 1;
                    break;
                }
                else {
                    newX = x + 1;
                    newY = y - 1;
                    break;
                }
        }
        
        //return antworld[newX][newY];
        
        return new Position(newX, newY);
        
    }

    /**
     * Returns the position objects in the anthill
     * @param colour team colour
     * @return ArrayList of Positions.
     */
    public ArrayList<Position> getAntHill (String colour)
    {
      
        ArrayList anthill = new ArrayList();
        
        for (int i = 0;  i < antworld.length; i++)
        {
            for (int j = 0; i < antworld.length; j++)
            {
                if (antworld[i][j].getAntHill() == colour)
                {
                    anthill.add(antworld[i][j]);
                }
            }      
        }
        
        return anthill;
            
    }
    
    public Position sensed_cell(Position antPos, int direction, SenseDirection sensedir) throws PositionException{
        if(sensedir.equals("Here")){
            return antPos;
        }
        
        else if(sensedir.equals("Ahead")){            
            Position aheadPos = adjacentCell(antPos, direction);
            
            return aheadPos;
        }
        
        else if(sensedir.equals("LeftAhead")){
            if (direction == 0)
                {
                    direction = 5;
                }
            else {
                direction = (direction - 1) % 6;
            }
            
            Position laheadPos = adjacentCell(antPos, direction);
            
            return laheadPos;
        }
        
        else if(sensedir.equals("RightAhead")){
            direction = (direction + 1) % 6;
            
            Position raheadPos = adjacentCell(antPos, direction);
            
            return raheadPos;
        }
        
        else{
            throw new PositionException("Ant Could not Sense");
        }
    }
    
}

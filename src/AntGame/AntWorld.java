/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import java.util.ArrayList;
import AntGame.Tokens.*;
import AntGame.exceptions.AntBrainException;
import AntGame.exceptions.PositionException;

public class AntWorld {
    
    Position[][] antworld;
    public int xlength;
    public int ylength;
    
    
    public AntWorld(int x, int y) {   //might be better to have a contructor with antworld[][] parameter to be passed from generator already made rocky etc 
        
        antworld = new Position[x][y];
        
        xlength = x;
        ylength = y;
        
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                antworld[i][j] = new Position(i,j);
            }
        }
        
    }
    
    public Position getPosition(int x, int y) { //superfluous?
        return antworld[x][y];
    }
    
    public Position getPosition(Coords c) {
        int x = c.getX();
        int y = c.getY();
        
        return antworld[x][y];
    }
        
    public Ant getAnt(Coords c) {
        int x = c.getX();
        int y = c.getY();
        
        return antworld[x][y].getAnt();
    }
    
    public boolean hasAnt(Position p) {
        return (!(p.getAnt() == null)); //Ugly because Position has no hasAnt()
    }
    
    public boolean isSurrounding(Position p, String colour) throws PositionException{
        boolean surrounding = false;
        
        System.out.println("Dead Surrounding here");
        
        System.out.println("Postion Given: " + p);
        for(int j = 0; j < 6; j++){
            Position search = adjacentCell(p, j);
            
            if(search != null && hasAnt(search)){
                Ant potentialKill = search.getAnt();
                
                String col = potentialKill.getColour();
                
                if(!col.equals(colour)){
                    System.out.println("Potential Kill Colour: " + col);
                    Position enemy = search;
                    System.out.println("Potential Kill At: " + enemy.x + ", " + enemy.y);
                    if(isSurrounded(enemy, col)){
                        return true;   
                    }
                }
            }
        }
        
        return surrounding;
    }
    
    public boolean isSurrounded(Position p, String colour) throws PositionException { //I did this wrong, doesn't check colour doh
        boolean surrounded = true;
        
        int i = 0;
        int surrBy = 0;
        
        System.out.println("Given Postion: " + p);
        
        for(int j = 0; j < 6; j++){
            System.out.println("Direction Given: " + j);
            while(i < 2){
                Position search = adjacentCell(p, j);
                
                System.out.println("Search");
                System.out.println(search);
                System.out.println("Searching position: " + search.x + "," + search.y);
                if(!hasAnt(search)){
                    System.out.println("Didn't have ant: " + i);
                    i++;
                    j++;
                }
                
                else if(i == 1){
                    surrounded = false;
                    break;
                }
                
                else if(hasAnt(search)){
                    Ant surrounding = search.getAnt();
                    System.out.println("Position has ant: " + surrounding.getColour());
                    
                    String surColour = surrounding.getColour();
                    
                    if(surColour.equals(colour)){
                        i++;
                        j++;
                    }
                    
                    else{
                        surrBy++;
                        if(surrBy == 5){
                            System.out.println("ant surrounded");
                            kill_ant(p);
                            return true;                            
                        }
                    }
                    
                    break;
                }
            }
        }
        
        return surrounded;
    }
    
    public void kill_ant(Position p) throws PositionException{
        Position kill = p;
        
        Ant killed = kill.getAnt();
        kill.clearAnt();
                
        killed.killAnt();
        
        kill.addFood(3);
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
                    
                    System.out.println("if");
                    newX = x - 1;
                    newY = y -1;
                    break;
                }
                else {
                    
                    System.out.println("else");
                    newX = x;
                    newY = y - 1;
                    
                    System.out.println(newX + ", " + newY);
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
        
        if (newX < xlength && newY < ylength && newX >= 0 && newY >= 0)
        {
            return antworld[newX][newY];
        }
        
        return null;
        
        
    }

    /**
     * Returns the position objects in the anthill
     * @param colour team colour
     * @return ArrayList of Positions.
     */
    public ArrayList<Position> getAntHill (String colour) throws AntBrainException
    {      
        ArrayList anthill = new ArrayList();
        
        if (colour.equals("Red") || colour.equals("Black"))
        {
            for (int i = 0;  i < xlength; i++)
            {
                for (int j = 0; j < ylength; j++)
                {
                    if ((antworld[i][j].getAntHill()).equals(colour))
                    {
                        anthill.add(antworld[i][j]);

                    }
                }      
            }
        }
            
        else {
            
            throw new AntBrainException ("Anthill colour wasn't specified.");
        }
        
        return anthill;
            
    }
    
    public Position sensed_cell(Position antPos, int direction, SenseDirection sensedir) throws PositionException{
        if(sensedir instanceof Here){
            System.out.println("sense was here");
            return antPos;
        }
        
        else if(sensedir instanceof Ahead){            
            Position aheadPos = adjacentCell(antPos, direction);
            
            if (aheadPos != null)
            {
                return aheadPos;
            }
            else {
                throw new PositionException("Trying to sense outside the boundaries");
            }
        }
        
        else if(sensedir instanceof LeftAhead){
            if (direction == 0)
                {
                    direction = 5;
                }
            else {
                direction = (direction - 1) % 6;
            }
            
            Position laheadPos = adjacentCell(antPos, direction);
            
            if (laheadPos != null)
            {
                return laheadPos;
            }
            else {
                throw new PositionException("Trying to sense outside the boundaries");
            }
        }
        
        else if(sensedir instanceof RightAhead){
            direction = (direction + 1) % 6;
            
            Position raheadPos = adjacentCell(antPos, direction);
            
            if (raheadPos != null)
            {
                return raheadPos;
            }
            else {
                throw new PositionException("Trying to sense outside the boundaries");
            }
        }
        
        else{
            throw new PositionException("Ant Could not Sense");
        }
    }
    
    }

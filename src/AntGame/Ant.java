/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import AntGame.Tokens.*;
import AntGame.exceptions.AntException;


public class Ant {
    
    private int direction;
    private int resting;
    private String team;
    private int state;
    private boolean hasFood;
    private boolean alive;
    
    /**
     * Class constructor sets the Ant team colour.
     * 
     * @param team 
     */
    public Ant (String colour)
    {
        this.team = colour;
        this.alive = true;
        this.direction = 0; //All ants start facing east (d = 0)
    }
    
    /**
     * Turn the ant either left or right and update the direction (direction is between 0 and 5)
     * 
     * @param dir Left or Right 
     * @return the direction after the ant has turned
     * @throws AntException if the ant is resting or if the ant isn't alive
     */
    public int turn(Left_or_Right dir) throws AntException
    {
        if (alive)
        {
            if (!resting())
            {

                if (dir instanceof Left)
                {
                    if (direction == 0)
                    {
                        direction = 5;
                    }
                    else {
                        direction = (direction - 1) % 6;
                    }
                    
                }

                else if (dir instanceof Right)
                {
                    direction = (direction + 1) % 6;
                }
            }
            else {

                throw new AntException("Ant is resting");
            }
        }
        else {
            throw new AntException("Ant is dead!");
        }
        
        return direction;
    }
    
    /**
     * Returns the current direction of the Ant
     * @return direction
     */
    public int getDirection()
    {
        return direction;
    }
    
    /**
     * Returns true if the ant is alive, false otherwise
     * 
     * @return true if the ant is alive, false otherwise
     */
    public boolean isAlive()
    {
        return alive;
    }
    
    /**
     * Sets alive to false
     */
    public void killAnt()
    {
        alive = false;
    }
    
    /**
     * Sets the current state of the ant
     * @param s new state
     */
    public void setState(int s)
    {
        state = s;
    }
    
    /**
     * Returns the current state of the ant.
     * @return state
     */
    public int getState()
    {
        return state;
    }
    
    /**
     * Returns the colour of the team the ant belongs to.
     * 
     * @return team
     */
    public String getColour()
    {
        return team;
    }
    
    /**
     * Sets the Ant team colour.
     * @param c colour
     */
    public void setColour(String c)
    {
        team = c;
    }
    
    /**
     * An ant has to rest for 14 turns, returns true if resting, false otherwise
     * 
     * @return true if resting, false otherwise
     */
    public boolean resting()
    {
        if (resting > 0)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * Increments resting by one each turn. When an Ant has resting for 14 turns resting is set to 0.
     */
    public void updateResting()
    {
        resting = (resting + 1) % 14;
    }
    
    /**
     * Sets hasFood to true if the Ant is still alive, not resting and is not already carrying food. Throws an exception otherwise.
     * @throws AntException when the Ant isn't alive, resting or already carrying food.
     */
    public void setFood() throws AntException
    {
         if (alive)
         {
             if (!resting())
             {
                 if (!hasFood())
                 {
                     hasFood = true;
                 }
                 else {
                     throw new AntException("Ant is already carrying food");
                 }
             }
         
             else {

                throw new AntException("Ant is resting");
            }
         }
        
        else {
            throw new AntException("Ant is dead!");
        }
    }
    
    /**
     * Sets hasFood to false.
     */
    public void clearFood()
    {
        hasFood = false;
    }
    
    /**
     * Returns true if the Ant is carrying food, false otherwise.
     * @return true if the Ant is carrying food, false otherwise.
     */
    public boolean hasFood()
    {
        return hasFood;
    }
    
    
    
}

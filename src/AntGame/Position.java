/*
 * @author ms660
 */
package AntGame;

import AntGame.Tokens.*;
import AntGame.exceptions.PositionException;
import java.util.HashMap;


public class Position {
  
    public int x;
    public int y;
    private boolean isRedAntHill;
    private boolean isBlackAntHill;
    private int food;
    private Ant ant;
    private boolean rocky;
    private HashMap<String, Marker> marker;
    
    /**
     * The class constructor initializes sets the x and y co-ordinates from the antWorld
     * 
     * @param x
     * @param y 
     */
    public Position (int x, int y)
    {
        this.x = x;
        this.y = y;
        
        marker = new HashMap<String, Marker>();
    }
   
    
    /**
     * Decrements food by one if there is food available
     * 
     * @throws PositionException when trying to remove food that isn't available.
     */
    public void removeFood () throws PositionException
    {
        if (food > 0)
        {
            this.food--;
        }
        else {
            throw new PositionException("Attempting to remove unavailable food.");
        }
    }
    
    
    /**
     * Returns true or false dependent if there is food in the position.
     * 
     * @return true if food is more than zero, false otherwise.
     */
    public boolean hasFood()
    {
        if (food > 0)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * Returns the value in food, this should always be equal to or greater than 0.
     * 
     * @return the value in food.
     */
    public int getFood()
    {
        return this.food;
    }
    
    /**
     * Increments food by the parameter passed into the addFood, if the area is rocky then an Exception is thrown
     * 
     * @param food 
     * @throws PositionException if Position is Rocky
     */
    public void addFood(int food) throws PositionException
    {
        if (!this.rocky && food > 0)
        {
            this.food += food;
        }
        else {
            throw new PositionException("Unable to add food to this position");
        }
    }
    
    /**
     * Sets the ant with the input parameter if the ant field is empty and the Position isn't rocky, otherwise throws an exception.
     * 
     * @param ant
     * @throws PositionException if the ant field is not null or position is rocky
     */
    public void addAnt(Ant ant) throws PositionException {
        
        if (this.ant == null && !rocky)
        {
            this.ant = ant;
        }
        else {
            throw new PositionException("Unable to add ant to this position");
        }
        
    }
    
    /**
     * Sets the ant to null.
     */
    public void clearAnt() {
        
        this.ant = null;
        
    }
    
    /**
     * Returns the ant in the current position. If there is no ant then it returns null.
     * 
     * @return Ant
     */
    public Ant getAnt() {
        
        return ant;
        
    }
    
    /**
     * Sets the position to rocky if it isn't an Anthill and resets the other fields to their defaults
     * 
     * @throws PositionException if position is a teams anthill
     */
    public void setRocky() throws PositionException
    {
        if (!isRedAntHill && !isBlackAntHill)
        {
            rocky = true;
        
            ant = null;
            food = 0;
            marker = null;
        }
        else 
        {
            throw new PositionException("Unable to make position rocky");
        }
    }
    
    /**
     * Returns true if rocky, false otherwise. 
     * 
     * @return rocky
     */
    public boolean getRocky(){
        
        return rocky;
        
    }
    
    /**
     * Sets the marker if marker for the ants team doesn't already exist.
     * 
     * @param m Marker
     * @throws PositionException
     * 
     */
    public void setMarker(Marker m) throws PositionException {
        
        if (ant.getColour().equals(m.getColour()))
        {
          
            if (!marker.containsKey(m.getColour()))
            {
                marker.put(m.getColour(), m);
            }
            else {
                throw new PositionException ("Marker needs to cleared before a new marker is placed");
            }
        }
        
        else {
            throw new PositionException ("Marker does not match the Ant team");
        }
        
    }
    
    /**
     * Removes the marker for the ant in the current position.
     */
    public void clearMarker(Marker m) {
        
        if (marker.containsKey(ant.getColour()))
        {
            Marker _m = marker.get(ant.getColour());
            
            if (_m.type == m.type)
            {
                marker.remove(ant.getColour());
            }
        }
        
    }   
    
    /**
     * Returns the markers in the current Position. 
     * Returns the team marker for the Ant sensing and a blank marker for the opposite team
     * 
     * @param a Ant sensing 
     * @return Markers in the current position.
     */
    public HashMap<String, Marker> senseMarkers(Ant a) {
        
        HashMap<String, Marker> m = new HashMap<String, Marker>();
        String team = a.getColour();
        String foe = getFoeColour(team);
        
        if (!marker.isEmpty() && marker.containsKey(team))
        {
            m.put(team, marker.get(team));
        }
        
        //returns a blank Marker for the opposite team if marker is sensed.
        if (!marker.isEmpty() && marker.containsKey(foe))
        {
            m.put(foe, new Marker(foe, -1));
        }
        
        return m;
       
    }
    
    /**
     * Returns the AntHill colour or null if the position is not an Anthill.
     * 
     * @return the anthill colour.
     */
    public String getAntHill() {
        
        if (isRedAntHill)
        {
            return "Red";
        }
        else if (isBlackAntHill)
        {
            return "Black";
        }
        
        return null;
        
    }
    
    /**
     * Sets the position to an anthill. If it currently an ant hill for the other team nothing will be updated.
     * 
     * @param colour 
     */
    public void setAntHill(String colour)
    {
        if ("Red".equals(colour) && !isBlackAntHill)
        {
            isRedAntHill = true;
        }
        else if ("Black".equals(colour) && !isRedAntHill)
        {
            isBlackAntHill = true;
        }
    }
   
    
     public boolean cellMatches (Condition c, String colour)
     {
         String team = colour;
         String foe = getFoeColour(colour);
         
         if (c instanceof Friend)
         {
             if (ant != null && ant.getColour().equals(team))
             {
                 return true;
             }
          }
         
         else if (c instanceof Foe)
         {
             if (ant != null && ant.getColour().equals(foe))
             {
                 return true;
             }
         }
         
         else if (c instanceof FriendWithFood)
         {
             if (ant != null && ant.getColour().equals(team) && ant.hasFood())
             {
                 return true;
             }
         }
         
         else if (c instanceof FoeWithFood)
         {
             if (ant != null && ant.getColour().equals(foe) && ant.hasFood())
             {
                 return true;
             }
         }
         
         else if (c instanceof Food)
         {
             return hasFood();             
         }
         
         else if (c instanceof Rock)
         {
             return getRocky();             
         }
         
         else if (c instanceof Marker)
         {
             if (!marker.isEmpty() && marker.containsKey(team))
             {
                 Marker m = (Marker)c;
                 
                 if (m.type == marker.get(team).type)
                 {
                    return true;
                 }
             }
         }
         
         else if (c instanceof FoeMarker)
         {
             if (!marker.isEmpty() && marker.containsKey(team))
             {
                 return true;
             }
         }
         
         else if (c instanceof Home)
         {
             if (getAntHill().equals(team))
             {
                 return true;
             }
         }
         
         
         else if (c instanceof FoeHome)
         {
             if (getAntHill().equals(foe))
             {
                 return true;
             }
         }
         
         return false;
     }
    
    /**
     * Returns the other teams colour
     * 
     * @param c Current team colour
     * @return Opposite teams colour
     */
    private String getFoeColour(String c)
    {
        if ("Black".equals(c))
        {
            return "Red";
        }
        else {
            return "Black";
        }
    }
    
    
   
    
}

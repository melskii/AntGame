/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;


public class Ant {
    
    private String colour;
    
    /**
     * Class constructor
     * 
     * @param colour 
     */
    public Ant (String colour)
    {
        this.colour = colour;
    }
    
    /**
     * Returns the colour of the team the ant belongs to 
     * 
     * @return colour
     */
    public String getColour()
    {
        return colour;
    }
    
    
}

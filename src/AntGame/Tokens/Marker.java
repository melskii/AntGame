/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame.Tokens;




public class Marker implements Condition {

    public int type;
    public String colour;

    public Marker (String colour, int type)
    {
        this.type = type;
        this.colour = colour;
    }
    
    public String getColour(){
        return colour;
    }

}

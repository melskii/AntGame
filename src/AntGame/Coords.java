/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

/**
 *
 * @author tld22
 */
public class Coords {
    
    int x,y;
    /*
     * Wrapper class to pass coordinates for Positions in antworld to 
     * antworld methods.
     */
    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
        
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
}

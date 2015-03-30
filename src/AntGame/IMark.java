/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AntGame;

/**
 *
 * @author Anna
 */
public class IMark {
    public Marker marker;
    public int state;
    
    public void mark(Marker m, int s){
        marker = m;
        state = s;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AntGame.Tokens;

/**
 *
 * @author Anna
 */
public class IMark implements Instruction {
    public Marker marker;
    public int state;
    
    public IMark (Marker m, int s)
    {
        marker = m;
        state = s;
    }
    
}

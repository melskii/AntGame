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
public class IUnmark implements Instruction {
    public Marker marker;
    public int state;
    
    public IUnmark (Marker m,int s)
    {
        marker = m;
        state = s;
    }
    
}

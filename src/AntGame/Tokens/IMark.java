/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AntGame.Tokens;

import java.util.ArrayList;

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
    
    /**
     * Returns the next states
     * @return states
     */
    @Override
    public ArrayList<Integer> getStates() {
        
        ArrayList<Integer> s = new ArrayList<Integer>();
        s.add(state);
        
        return s;
    }
    
}

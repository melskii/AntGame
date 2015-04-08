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
public class IDrop implements Instruction {
    public int state;
    
    public IDrop (int s){
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

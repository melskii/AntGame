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
public class IFlip implements Instruction {
    public int flip;
    public int state1;
    public int state2;
    
    public IFlip (int f, int state1, int state2)
    {
        flip = f;
        this.state1 = state1;
        this.state2 = state2;
    }

    /**
     * Returns the next states
     * @return states
     */
    @Override
    public ArrayList<Integer> getStates() {
        
        ArrayList<Integer> s = new ArrayList<Integer>();
        s.add(state1);
        s.add(state2);
        
        return s;
    }
}

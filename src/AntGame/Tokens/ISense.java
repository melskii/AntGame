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
public class ISense implements Instruction {
    
    public SenseDirection sensedir;
    public int state1;
    public int state2;
    public Condition cond;
    
    public ISense (SenseDirection s, int s1, int s2, Condition cond){
        
        sensedir = s;
        state1 = s1;
        state2 = s2;
        this.cond = cond;
        
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

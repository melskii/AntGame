/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AntGame.Tokens;

import AntGame.Condition;

/**
 *
 * @author Anna
 */
public class ISense implements Instruction {
    
    public SenseDirection sensedir;
    public int state1;
    public int state2;
    public Condition cond;
    
    public void sense(SenseDirection s, int s1, int s2, Condition cond){
        sensedir = s;
        state1 = s1;
        state2 = s2;
        this.cond = cond;
        
    }
}

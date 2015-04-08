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
public interface Instruction {
    
    /**
     * Returns the next states
     * @return states
     */
    public ArrayList<Integer> getStates();

}

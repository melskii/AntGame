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
public class IDrop implements Instruction {
    public int state;
    
    public IDrop (int s){
        state = s;
    }
}

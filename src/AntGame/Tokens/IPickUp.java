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
public class IPickUp implements Instruction {
    public int state1;
    public int state2;
    
    public IPickUp(int s1, int s2)
    {
        state1 = s1;
        state2 = s2;
    }
    
}

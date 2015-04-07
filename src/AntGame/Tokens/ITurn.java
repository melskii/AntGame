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
public class ITurn implements Instruction {

    public Left_or_Right turn;
    public int state;
    
    public ITurn (Left_or_Right t, int s)
    {
        turn = t;
        state = s;
    }
}

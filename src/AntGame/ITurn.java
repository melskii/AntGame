/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AntGame;

/**
 *
 * @author Anna
 */
public class ITurn {
    public Left_or_Right turn;
    public int state;
    
    public void turn(Left_or_Right t, State s){
        turn = t;
        state = s;
    }
}

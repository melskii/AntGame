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
public class IPickUp {
    public int state1;
    public int state2;
    
    public void pickUp(State s1, State s2){
        state1 = s1;
        state2 = s2;
    }
    
}

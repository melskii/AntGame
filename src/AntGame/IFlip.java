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
public class IFlip {
    public int flip;
    public int state1;
    public int state2;
    
    public void flip(int f, int state1, int state2){
        flip = f;
        this.state1 = state1;
        this.state2 = state2;
    }
}

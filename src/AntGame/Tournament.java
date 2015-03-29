/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import java.util.ArrayList;


public class Tournament {
    ArrayList<Integer> playerNo = new ArrayList<>();
    ArrayList<AntBrain> brains = new ArrayList<>();
    
    public void Tournament(AntBrain[] uploaded){
        int i =0;
        
        for(AntBrain b : uploaded){
            brains.add(b);
            playerNo.add(i);
                    
            i++;
        }
    }
    
    public AntBrain runTournament() {
        int playersInRound = playerNo.size();     
        
        
        
        return null;
    }
    
    public ArrayList<AntBrain> runRound(){
         
        
        return null;
    }
    
}

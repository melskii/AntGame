/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import org.junit.Test;


public class Tournament {
    ArrayList<Integer> playerNo = new ArrayList<>();
    ArrayList<AntBrain> brains = new ArrayList<>();
    ArrayList<Integer> score = new ArrayList<>();
    HashMap played = new HashMap();
    
    public void Tournament(AntBrain[] uploaded){
        int i =0;
        
        for(AntBrain b : uploaded){
            brains.add(b);
            playerNo.add(i);
            score.add(0);
            
            i++;
        }
    }
    
    public AntBrain runTournament() {
        int playerX = 0;
        int playerY = playerX + 1;

        while (playerX < playerNo.size()){
            if(played.get(brains.get(playerX)) != brains.get(playerY)){
                Game round = new Game(brains.get(playerX), brains.get(playerY));                
                AntBrain winner = round.runGame(); // run the round and get a winner
                
                int addTo = brains.indexOf(winner); // get index of winner
                int currentScore = score.get(addTo);
                score.set(addTo, currentScore+1);
                
                played.put(brains.get(playerX), brains.get(playerY)); // mark both brains as played
                               
                playerY++;                
            }    
            
            else{
                playerX++;
            }   
        }    
        
        int highestScore = Collections.max(score);
        int owner = brains.indexOf(highestScore);
        AntBrain finalWinner = brains.get(owner);

        return finalWinner;
    }
    

}
 


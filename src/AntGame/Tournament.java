/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import AntGame.exceptions.*;
import java.util.ArrayList;
import java.io.*;
import java.util.Collections;
import java.util.HashMap;


public class Tournament {
    ArrayList<Integer> playerNo = new ArrayList<>();
    ArrayList<File> brains = new ArrayList<>();
    ArrayList<Integer> score = new ArrayList<>();
    HashMap played = new HashMap();
    
    public Tournament(File[] uploaded){
        int i =0;
        
        for(File b : uploaded){
            brains.add(b);
            playerNo.add(i);
            score.add(0);
            
            i++;
        }
    }
    
    public File runTournament() throws PositionException, AntException{
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
                playerY = playerX+1;
            }   
        }    
        
        int highestScore = Collections.max(score);
        int owner = brains.indexOf(highestScore);
        File finalWinner = brains.get(owner);

        return finalWinner;
    }
    

}
 


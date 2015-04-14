/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import AntGame.exceptions.*;
import java.awt.List;
import java.util.ArrayList;
import java.io.*;
import java.util.Collections;
import java.util.HashMap;


public class Tournament {
    ArrayList<Integer> playerNo = new ArrayList<>();
    ArrayList<AntBrain> brains = new ArrayList<>();
    ArrayList<Integer> score = new ArrayList<>();
    HashMap played = new HashMap();
    AntWorld world;
    
    public Tournament(ArrayList<AntBrain> uploaded, AntWorld world){
        int i =0;
        
        for(AntBrain b : uploaded){
            this.world = world;
            brains.add(b);
            playerNo.add(i);
            score.add(0);
            
            i++;
        }
    }
    
    public AntBrain runTournament() throws PositionException, AntException, AntBrainException, IOException, AntWorldGeneratorException{
        int playerX = 0;
        int playerY = 0;
        
        while (playerX < playerNo.size()){
           // System.out.println("Player X :" + playerX);
           // System.out.println("Player Y :" + playerY);
           // System.out.println("Brains size: " + brains.size());
            if(played.get(brains.get(playerX)) != brains.get(playerY)){
                //System.out.println("Player No Size: " + playerNo.size());
                if(playerY < playerNo.size()-1){
                //System.out.println("Jumped here");
                Game round = new Game(brains.get(playerX), brains.get(playerY), world);                
                AntBrain winner = round.runGame(); // run the round and get a winner
                if(winner != null){
                    //System.out.println("Winner: " + brains.indexOf(winner));
                    int addTo = brains.indexOf(winner); // get index of winner
                    int currentScore = score.get(addTo);
                    score.set(addTo, currentScore+2);
                }
                else{
                    //System.out.println("Draw");
                    int currentScoreOne = score.get(playerX);
                    score.set(playerX, currentScoreOne+1);
                    int currentScoreTwo = score.get(playerY);
                    score.set(playerY, currentScoreTwo+1);
                }
                played.put(brains.get(playerX), brains.get(playerY)); // mark both brains as played
                               
                playerY++;    
                }
                
                else if(playerX+2 < playerNo.size()){
                    playerX++;
                    playerY = playerX+1;
                }
                
                else{
                    break;
                }
            }    
  
        }    
        
        int highestScore = Collections.max(score);
        //System.out.println("High Score: " + highestScore);
        
        int j = 0;
        for (int i = 0; i < score.size(); i++) {
            if(score.get(i) == highestScore){
                j++;
                if(j > 2){
                    //System.out.println("It was a draw");
                    return null;
                }
            }         
        }
        
        int owner = score.indexOf(highestScore);
        //System.out.println("Owner: " + score.indexOf(highestScore));
        AntBrain finalWinner = brains.get(owner);

        return finalWinner;
    }
    

}
 


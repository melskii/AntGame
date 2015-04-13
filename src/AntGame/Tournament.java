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
    
    public Tournament(ArrayList<AntBrain> uploaded){
        int i =0;
        
        for(AntBrain b : uploaded){
            brains.add(b);
            playerNo.add(i);
            score.add(0);
            
            i++;
        }
    }
    
    public AntBrain runTournament() throws PositionException, AntException, AntBrainException, IOException, AntWorldGeneratorException{
        int playerX = 0;
        int playerY = playerX + 1;
        File validWorld = new File("C:\\Users\\Olivia\\Documents\\NetBeansProjects\\AntGame\\files\\tiny.world");
        AntWorldGenerator world = new AntWorldGenerator();
        AntWorld tournamentWorld = world.antWorldGenerator(validWorld);
        
        while (playerX < playerNo.size()){
            if(played.get(brains.get(playerX)) != brains.get(playerY)){
                Game round = new Game(brains.get(playerX), brains.get(playerY), tournamentWorld);                
                AntBrain winner = round.runGame(); // run the round and get a winner
                if(winner != null){
                    System.out.println("Winner: " + brains.indexOf(winner));
                    int addTo = brains.indexOf(winner); // get index of winner
                    int currentScore = score.get(addTo);
                    score.set(addTo, currentScore+1);
                }
                played.put(brains.get(playerX), brains.get(playerY)); // mark both brains as played
                               
                playerY++;                
            }    
            
            else if(playerY < playerNo.size()-1){
                playerY++;
            }
            
            else{                
                playerX++;
                playerY = playerX+1;
            }   
        }    
        
        int highestScore = Collections.max(score);
        int owner = brains.indexOf(highestScore);
        AntBrain finalWinner = brains.get(owner);

        return finalWinner;
    }
    

}
 


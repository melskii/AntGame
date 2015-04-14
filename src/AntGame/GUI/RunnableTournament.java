/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame.GUI;

import AntGame.AntBrain;
import AntGame.AntWorld;
import AntGame.Game;
import AntGame.Tournament;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author ms660
 */
public class RunnableTournament implements Runnable {

    private LinkedHashMap game;
    private JTextField loading, winnerLabel;
    private JButton start;
    
    public RunnableTournament(JTextField loading, JTextField winnerLabel, LinkedHashMap game, JButton start) {
        
        this.game = game;
        this.loading = loading;
        this.winnerLabel = winnerLabel;
        this.start = start;
    }

    public void run()
    {
        System.out.println("hit here");
        
        loading.setText("Running Tournament...");
        start.setEnabled(false);
        
        try {

                ArrayList<AntBrain> brains = new ArrayList<AntBrain>();
                AntWorld world = null;
                for (int i = 1; i <= game.size(); i++)  
                {
                    if (i == (game.size()))
                    {
                        String _key = "World";

                        world = (AntWorld)game.get(_key);
                        
                        System.out.println("world = " + world);
                    }

                    else {

                        String _key = "Brain " + i;
                        AntBrain b = (AntBrain)game.get(_key);

                        brains.add(b);
                        
                        System.out.println("b = " + b);

                    }

                }
              
              
                Tournament t = new Tournament(brains, world);  
              
                AntBrain win = t.runTournament();

                System.out.println("win = " + win);
                
                if (win == null)
                {
                    winnerLabel.setText("It's a draw");
                }

                else {

                    for (int j = 0; j < brains.size(); j++)
                    {
                        if (win == brains.get(j))
                        {
                            winnerLabel.setText("Player " + (j+1) + " wins!");
                        }
                    }



                }
                

                loading.setText("Finished!");
                start.setText("Reset Game");
                start.setEnabled(true);
                
        }
        catch (Exception e)  
         {
             loading.setText("Error Loading the competition");
             start.setText("Reset Game");
         }
            
    }
    
    
   
   
    
    
    
    
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame.GUI;

import AntGame.AntBrain;
import AntGame.AntWorld;
import AntGame.Game;
import java.util.LinkedHashMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author ms660
 */
public class RunnableGame implements Runnable {

    private Game game;
    private JPanel mapPanel;
    private JLabel statusLabel, winnerLabel;
    private JTable scoreboardTable;
    private LinkedHashMap gameHash;
    private JScrollPane scrollTable;
    ReRun finished;

    public RunnableGame(Game game, JPanel mapPanel, JLabel statusLabel, ReRun finished, JLabel winnerLabel, JTable scoreboardTable, LinkedHashMap gameHash, JScrollPane scrollTable) {
        
        this.game = game;
        this.mapPanel = mapPanel;
        this.statusLabel = statusLabel;
        this.winnerLabel = winnerLabel;
        this.finished = finished;
        this.scoreboardTable = scoreboardTable;
        this.gameHash = gameHash;
        this.scrollTable = scrollTable;
    }

    public void run()
    {
        
        
        
        
        
          try {
                statusLabel.setText("Running the game");
                //System.out.println("Running the game");
                AntBrain win = game.runGame();
  
                finished.setReRun(true);
                
                   String currentPlayers[] = new String[2];
                   currentPlayers[0] = "Player";
                   currentPlayers[1] = "Player";
                   String playerStats[][] = new String[4][2];
                
                   for (int j = 1; j < (gameHash.size()); j++)
                   {

                        String _key = "Brain " + j;
                        //System.out.println("_key = " + _key);

                        AntBrain _brain = (AntBrain)gameHash.get(_key);

                        //System.out.println(_brain);

                        //try {

                            playerStats[0][j-1] = "Player: " + j;
                            playerStats[1][j-1] = "Colour: " + _brain.getColour();
                            //System.out.println("_brain.getColour() = " + _brain.getColour());

                            playerStats[3][j-1] = "Dead: " + _brain.getDeadCount();
                            
                            

                           // System.out.println("_brain = " + _brain);



                        try {
                            playerStats[2][j-1] = "Score: " + _brain.getBrainScore();
                            System.out.println("_brain.getBrainScore() = " + _brain.getBrainScore());
                        } catch (Exception ex)
                        {
                           System.out.println("Error: " + ex.getMessage());
                        }


                    }
                   
                scrollTable.remove(scoreboardTable);
                   
                scoreboardTable = new JTable (playerStats, currentPlayers);
                
                scrollTable.setViewportView(scoreboardTable);
    
                scoreboardTable.repaint();
                scrollTable.repaint();
                
                if (win == null)
                {
                    winnerLabel.setText("It is a tie");
                    JOptionPane.showMessageDialog(mapPanel,  "It is a tie");
                }
                else {        
                    
                    
                    winnerLabel.setText("Winner: " + win.getColour() + "!!");
                    JOptionPane.showMessageDialog(mapPanel, "Winner: " + win.getColour() + " Score: " + win.getBrainScore());
                }
  
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(mapPanel, ex.getMessage());
            }
    }
    
    
   
   
    
    
    
    
}
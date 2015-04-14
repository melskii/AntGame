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

/**
 *
 * @author ms660
 */
public class RunnableGame implements Runnable {

    private Game game;
    private JPanel mapPanel;
    private JLabel statusLabel, winnerLabel;
    ReRun finished;

    public RunnableGame(Game game, JPanel mapPanel, JLabel statusLabel, ReRun finished, JLabel winnerLabel) {
        
        this.game = game;
        this.mapPanel = mapPanel;
        this.statusLabel = statusLabel;
        this.winnerLabel = winnerLabel;
        this.finished = finished;
    }

    public void run()
    {
        
        
        
        
        
          try {
                statusLabel.setText("Running the game");
              
                AntBrain win = game.runGame();
                
                
                finished.setReRun(true);
                
            
                if (win == null)
                {
                    winnerLabel.setText("It is a tie");
                    JOptionPane.showMessageDialog(mapPanel,  "It is a tie");
                }
                else {        
                    
                    
                    winnerLabel.setText("Winner: " + win.getColour() + "!!");
                    JOptionPane.showMessageDialog(mapPanel, "Winner: " + win.getColour());
                }
  
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(mapPanel, ex.getMessage());
            }
    }
    
   
    
    
    
    
}
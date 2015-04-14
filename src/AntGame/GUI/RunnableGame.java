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

    private LinkedHashMap<String, Object> game;
    private JPanel mapPanel;
    private JLabel statusLabel;
    ReRun finished;

    public RunnableGame(LinkedHashMap<String, Object> game, JPanel mapPanel, JLabel statusLabel, ReRun finished) {
        this.game = game;
        this.mapPanel = mapPanel;
        this.statusLabel = statusLabel;
        this.finished = finished;
    }

    public void run()
    {
        
        
        Game run  = new Game((AntBrain)game.get("Brain 1"), (AntBrain)game.get("Brain 2"), (AntWorld)game.get("World"));
        
        
          try {
                statusLabel.setText("Running the game");
              
                AntBrain win = run.runGame();
                
                AntBrain b1 = (AntBrain)game.get("Brain 1");
                AntBrain b2 = (AntBrain)game.get("Brain 2");
                  
                statusLabel.setText("Player 1 (Team: " + b1.getColour() + ", Dead: " + b1.getDeadCount() + " , Score: " + b1.getBrainScore() + ") Player 2 (Team: " + b2.getColour() + ", Dead: " + b2.getDeadCount() + " , Score: " + b2.getBrainScore() + ")");          
                finished.setReRun(true);
                
            
                if (win == null)
                {
                    
                    JOptionPane.showMessageDialog(mapPanel,  "It was a tie");
                }
                else {        
                    
                    JOptionPane.showMessageDialog(mapPanel, "Winner: " + win.getColour());
                }
  
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(mapPanel, ex.getMessage());
            }
    }
    
   
    
    
    
    
}
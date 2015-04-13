/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame.GUI;

import AntGame.AntBrain;
import AntGame.AntWorld;
import AntGame.Game;
import java.util.LinkedHashMap;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ms660
 */
public class RunnableGame implements Runnable {

    private LinkedHashMap<String, Object> game;
    private JPanel mapPanel;

    public RunnableGame(LinkedHashMap<String, Object> game, JPanel mapPanel) {
        this.game = game;
        this.mapPanel = mapPanel;
    }

     public void run()
    {
        Game run  = new Game((AntBrain)game.get("Brain 1"), (AntBrain)game.get("Brain 2"), (AntWorld)game.get("World"));
        
          try {
                AntBrain b = run.runGame();
                
                if (b == null)
                {
                    JOptionPane.showMessageDialog(mapPanel,  "It was a tie");
                }
                else {
                    JOptionPane.showMessageDialog(mapPanel, "Winner: " + b.getColour());
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(mapPanel, ex.getMessage());
            }
    }
    
    
}
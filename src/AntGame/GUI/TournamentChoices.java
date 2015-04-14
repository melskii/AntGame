/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame.GUI;

import AntGame.AntBrain;
import AntGame.AntWorld;
import AntGame.AntWorldGenerator;
import AntGame.exceptions.AntBrainException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ms660
 */
public class TournamentChoices extends JFrame{
    
    JButton next;
    JLabel labelPlayers;
    JTextField textPlayers;
    JCheckBox checkWorld;
    boolean valid;
    
    LinkedHashMap<String, Object> competition;
    
    
     public TournamentChoices ()
     {
         super("Tournament Choices");
         
         competition = new LinkedHashMap<String, Object>();
         
         mainFrame();
        
     }
    
     public void mainFrame()
     {   
   
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Container c = getContentPane();

        GroupLayout layout = new GroupLayout(c);
        c.setLayout(layout);

       
        next = new JButton("Next");
       
        labelPlayers = new JLabel("Number of Players");
        textPlayers = new JTextField("");
        checkWorld = new JCheckBox("Generate Random World");
        checkWorld.setSelected(true);
        checkWorld.setEnabled(false);
        valid = false;

        next.enable(valid);
        

        labelPlayers.setPreferredSize(new Dimension(350, 30));
        textPlayers.setPreferredSize(new Dimension(350, 30));


        setSize(300, 200);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();

        p1.setLayout(new FlowLayout());
        p2.setLayout(new FlowLayout());
        p3.setLayout(new FlowLayout());
        p4.setLayout(new FlowLayout());

        //p1.setBackground(Color.RED);
        //p2.setBackground(Color.blue);

        p1.add(labelPlayers);
        p2.add(textPlayers);
        p3.add(checkWorld);

        p4.add(next);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        //getContentPane().setBackground(Color.yellow);
        getContentPane().add(p1);
        getContentPane().add(p2);
        getContentPane().add(p3);
        getContentPane().add(p4);

        setPreferredSize(new Dimension(500, 200));
        
         addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                close(true);
            }
           });
        
        next.addActionListener(new ActionListener() {
            
            public void actionPerformed (ActionEvent e)
            {
                nextButton();
                
            }
            
            
        });

        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);  
        
  
     }
 
     
    /**
     * Either shows the next upload JFrame or the map
     */
     
    public void nextButton()
    {
        //valid = true;
        if (textPlayers.getText().matches("\\d+") && Integer.parseInt(textPlayers.getText()) > 1)      
        {
                for (int i = 1; i <= Integer.parseInt(textPlayers.getText()); i++)
                {
                    competition.put("Brain " + i, null);
                }
                
                if (checkWorld.isSelected())
                {
                    AntWorldGenerator gen = new AntWorldGenerator();
                    
                    try {
                        File f = gen.antWorldGenerator("competition.world");
                        AntWorld w = gen.antWorldGenerator(f);
                        competition.put("World", w);
                    } catch (Exception e)
                    {
                        JOptionPane.showMessageDialog(null, "Unable to generator random world");
                    }

                }
                
                else {
                        competition.put("World", null);
                }
                
                
                AntGameUpload upload = new AntGameUpload(competition, true);
                close(false);
                
                
                
        }
        
        else {
            
            JOptionPane.showMessageDialog(null, "Please enter a valid number (2 or more players)");
            
        }
          /*      
        if (valid)
        {
           
            close();

            Iterator it = game.entrySet().iterator();

            boolean map = true;

            while (it.hasNext())
            {
                Map.Entry pair = (Map.Entry)it.next();

                if (pair.getValue() == null)
                {
                    map = false;
                    TournamentChoices upload = new TournamentChoices(game);
                    break;
                }


            }

            if (map)
            {
                AntMap load = new AntMap(game);
            }


        }*/
                
    }
    
     
    /**
     * Exits the JFrame
     */
    public void close(boolean restart)
    {
        if (restart)
        {
            AntGameRun run = new AntGameRun();
            run.mainFrame();
        }
        
        
        setVisible(false);
        dispose();
                
    } 
     

     
    
    
}

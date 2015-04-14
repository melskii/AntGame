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
public class TournamentRun extends JFrame{
    
    JButton start;
    JLabel labelPlayers;
    JTextField textWinner;
    JTextField loading;
    boolean ran;
    
    LinkedHashMap<String, Object> tournament;
    
    
     public TournamentRun (LinkedHashMap tournament)
     {
         super("Tournament Choices");
         
         this.tournament = tournament;
         this.ran = false;
      
         mainFrame();
        
     }
    
     public void mainFrame()
     {   
   
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Container c = getContentPane();

        GroupLayout layout = new GroupLayout(c);
        c.setLayout(layout);
        
         addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                close();
            }
           });

       
        start = new JButton("Start");
       
        labelPlayers = new JLabel("Winner");
        textWinner = new JTextField("");
        loading = new JTextField("");
        
        textWinner.setEnabled(false);
        loading.setEnabled(false);
        boolean valid = false;
        //ran = false;

        start.enable(true);
        

        labelPlayers.setPreferredSize(new Dimension(350, 30));
        textWinner.setPreferredSize(new Dimension(350, 30));
        loading.setPreferredSize(new Dimension(350, 30));


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
        p2.add(textWinner);
        p3.add(loading);

        p4.add(start);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        //getContentPane().setBackground(Color.yellow);
        getContentPane().add(p1);
        getContentPane().add(p2);
        getContentPane().add(p3);
        getContentPane().add(p4);

        setPreferredSize(new Dimension(500, 200));
        
        start.addActionListener(new ActionListener() {
            
            public void actionPerformed (ActionEvent e)
            {
                            runTournament();
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
     
     
    public void runTournament()
    {
        if (!ran)
        {
            
            ran = true;
            
            System.out.println("hit here");
            
            Thread runThread = null;
            boolean startThread = false;
                

            if (!tournament.isEmpty())
            {
                RunnableTournament run = new RunnableTournament(loading, textWinner, tournament, start);
                runThread = new Thread(run);
                runThread.start();

            }
            
            //loading.setText("Running Tournament...");
            
            
            
            
        }
        
        else if (start.getText() == "Reset Game")
        {
             close();
            
            //AntGameRun run = new AntGameRun();
            //run.mainFrame();
            
        }
    }
     
    
     
    /**
     * Exits the JFrame
     */
    public void close()
    {
        if (start.isEnabled())
        {
            AntGameRun run = new AntGameRun();
            run.mainFrame();

            setVisible(false);
            dispose();
        }
                
    } 
     

     
    
    
}

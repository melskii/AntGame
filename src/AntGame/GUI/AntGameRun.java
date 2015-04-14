/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame.GUI;

import AntGame.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author ms660
 */
public class AntGameRun extends JFrame {
    
    
    JButton run;
    LinkedHashMap<String, Object> game;
    
    public static void main(String[] args) {  

        AntGameRun t = new AntGameRun();
        t.mainFrame();
    
    }  
    
    public AntGameRun()
    {
        super("Run Ant Game");
    }
        
    
    public void mainFrame()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Container c = getContentPane();
        
        GroupLayout layout = new GroupLayout(c);
        c.setLayout(layout);

        
        
        run = new JButton("Run Normal Game"); 
        run.setBounds(30,20,150, 30);
        
        JButton tournament = new JButton("Run Tournament");
        JTextField players = new JTextField("Enter Number of Players");
        JCheckBox checkbox = new JCheckBox("Random World");
    
        //JButton quick = new JButton("Quick Round");
        //quick.setBounds(70,20,150,30);
        
        createMenuBar();
        
        
        
        
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
       

        p1.setLayout(new FlowLayout());
        p2.setLayout(new FlowLayout());
       

        p1.add(run);
        
        p2.add(tournament);
        


       // p2.add(textValid);

        //p3.add(next);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        //getContentPane().setBackground(Color.yellow);
        getContentPane().add(p1);
        getContentPane().add(p2);
       
      
        
        
        
 
        run.addActionListener(new ActionListener() {

            public void actionPerformed (ActionEvent e)
            {
               close();
               
               game = new LinkedHashMap<String, Object>();
               
               game.put("Brain 1", null);
               game.put("Brain 2", null);   
               game.put("World", null);
               
               AntGameUpload upload = new AntGameUpload(game, false);
               
            }
        });
        
        tournament.addActionListener (new ActionListener() {
           
            public void actionPerformed (ActionEvent e)
            {
                close();
                
                TournamentChoices t = new TournamentChoices();
                        
                        
            }
            
            
        });
        
        
        
       // getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        //getContentPane().setBackground(Color.yellow);
       // getContentPane().add(p1);
        //getContentPane().add(p2);
        
        //add(getContentPane());
        //add(quick);
        
        pack();
        setLayout(null);//using no layout managers  
        setVisible(true);//making the frame visible  
        //setResizable(false);
        setLocationRelativeTo(null);  
        
        //setSize(400,400);//400 width and 400 height  
        
        
        
        

        
        
    }
    
    public void createMenuBar()
    {
        JMenuBar mb = new JMenuBar();
        JMenuItem quick = new JMenuItem("Test World & Brains");
        JMenuItem exit = new JMenuItem("Exit");
        
        JMenu control = new JMenu("File");
      
        
        quick.addActionListener(new ActionListener() {

            public void actionPerformed (ActionEvent e)
            {
               close();
               
               game = new LinkedHashMap<String, Object>();
               
               
               File _file = new File("sample2.ant");
               
               /*try {
                System.out.println(_file.getCanonicalPath());
               } catch (Exception ex)
               {
                   System.out.println("e: " + ex);
               }*/
               
               
               try {
               AntBrain brain1 = new AntBrain(_file);
               AntBrain brain2 = new AntBrain(_file);
               
               AntWorldGenerator gen = new AntWorldGenerator();
               File f = gen.antWorldGenerator("test.world");
               AntWorld world = gen.antWorldGenerator(f);
               
               
               game.put("Brain 1", brain1);
               game.put("Brain 2", brain2);   
               game.put("World", world);
               
               
               AntMap map = new AntMap(game, false);
               
               } catch (Exception ex)
               {
                   System.out.println(ex.getMessage());
               }
               
            }
        });
        
        
        exit.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e)
            {
                int exit = JOptionPane.showOptionDialog(null, 
                        "Are you sure you want to exit?", 
                        "Exit?", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, 
                        null, null, null);
                 // interpret the user's choice
                
                if (exit == JOptionPane.YES_OPTION)
                {
                  close();
                  System.exit(0);
                }
                
            }
        });
        
      


       // control.add(quick);
        control.add(exit);
        
        
        mb.add(control);
        
        setJMenuBar(mb);      


    }
    
    
      /**
     * Exits the JFrame
     */
    public void close()
    {
        setVisible(false);
        dispose();
                
    } 
     
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame.GUI;

import AntGame.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

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
        
        run = new JButton("Run Standard Game"); 
        run.setBounds(30,20,150, 30);
        
        JButton quick = new JButton("Quick Round");
        quick.setBounds(70,20,150,30);
        
        
        
         JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();


        p1.setLayout(new FlowLayout());
        p2.setLayout(new FlowLayout());
 ;

        //p1.setBackground(Color.RED);
        //p2.setBackground(Color.blue);

        p1.add(run);


        p2.add(quick);

        
      
        
       
        
        
        run.addActionListener(new ActionListener() {

            public void actionPerformed (ActionEvent e)
            {
               close();
               
               game = new LinkedHashMap<String, Object>();
               
               game.put("Brain 1", null);
               game.put("Brain 2", null);   
               game.put("World", null);
               
               AntGameUpload upload = new AntGameUpload(game);
               
            }
        });
        
        quick.addActionListener(new ActionListener() {

            public void actionPerformed (ActionEvent e)
            {
               close();
               
               game = new LinkedHashMap<String, Object>();
               
               
               File _file = new File("N:\\Documents\\sample2.ant");
               File _world = new File("N:\\Documents\\sample7a.world");
               
               try {
               AntBrain brain1 = new AntBrain(_file);
               AntBrain brain2 = new AntBrain(_file);
               
               AntWorldGenerator gen = new AntWorldGenerator();
               AntWorld world = gen.antWorldGenerator(_world);
               
               
               game.put("Brain 1", brain1);
               game.put("Brain 2", brain2);   
               game.put("World", world);
               
               
               AntMap map = new AntMap(game);
               
               } catch (Exception ex)
               {
                   System.out.println(ex.getMessage());
               }
               
            }
        });
        
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        //getContentPane().setBackground(Color.yellow);
        getContentPane().add(p1);
        getContentPane().add(p2);
        
        //add(getContentPane());
        //add(quick);
        setSize(250,100);//400 width and 400 height  
        pack();
        setLayout(null);//using no layout managers  
        setVisible(true);//making the frame visible  
        //setResizable(false);
        setLocationRelativeTo(null);  
        
        
        
        

        
        
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

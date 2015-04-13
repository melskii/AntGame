/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame.GUI;

import AntGame.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

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
        
        add(run);
        setSize(250,100);//400 width and 400 height  
        setLayout(null);//using no layout managers  
        setVisible(true);//making the frame visible  
        setResizable(false);
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

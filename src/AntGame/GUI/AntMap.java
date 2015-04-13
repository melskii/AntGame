    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame.GUI;


import AntGame.Ant;
import AntGame.AntBrain;
import AntGame.AntWorld;
import AntGame.AntWorldGenerator;
import AntGame.Game;
import java.awt.BorderLayout;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;



/**
 *
 * @author ms660
 */
public class AntMap extends JFrame {

    
    private AntWorld world;
    final JFileChooser fc = new JFileChooser();     
    private int x;
    private int y;
    
    private JMenu control;
    private JMenuItem run;
    
    private LinkedHashMap game;
    
    private MapPanel mapPanel;
    
    /*
    public static void main(String[] args) {
        
        AntMap m = new AntMap();  
    }*/

    
    public AntMap(LinkedHashMap<String, Object> game)
    {
        super("Ant Map");
        
        this.game = game;       
        
        createAndShowGUI();
    }


    private  void createAndShowGUI() {
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      if (game.containsKey("World"))
      {
          System.out.println(game.get("World"));
          world = (AntWorld)game.get("World");
      }
      
      mapPanel = new MapPanel(world);
      
      add(mapPanel);
      
      createMenuBar();

      //f.setContentPane(contentPane);
      //f.add(new MapPanel());

      pack();

      setVisible(true);

      setResizable(false);
      setLocationRelativeTo(null);  

      //f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

        
     /**
    * Creates a new Menu bar for the frame.
    */
    
    public void createMenuBar()
    {
        JMenuBar mb = new JMenuBar();
        
        control = new JMenu("File");
        
        run = new JMenuItem("Run");
        
        
        
        run.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e)
            {
                
                runGame();
                
            }
        });


        //Add in the shortest control.

        control.add(run);
        
        mb.add(control);
        
        setJMenuBar(mb);      


    }
    
    public void runGame()
    {
        JOptionPane.showMessageDialog(mapPanel, "Running the Game");
        control.remove(control);
        
        pack();
        
        RunnableGame run = new RunnableGame(game, mapPanel);
        Thread runThread = new Thread(run);
        runThread.start();
        
        for (int i = 0; i < 2000; i++)
        {
            remove(mapPanel);

            mapPanel = new MapPanel(world);
            add(mapPanel);

            pack();
        
        }
        
        
                
        
    }
   
    
    public void generateAntWorld(File f)
    {
        AntWorldGenerator gen = new AntWorldGenerator();
        
        try {
            world = gen.antWorldGenerator(f);
            
            remove(mapPanel);
            
            
            mapPanel = new MapPanel(world);
            add(mapPanel);
            
            pack();
            
            System.out.println(gen.x + " , " + gen.y);
            
            System.out.println("hit here");
        }
        catch (Exception e)
        {
           System.out.println(e.getMessage());
        }
        
        
        
        
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


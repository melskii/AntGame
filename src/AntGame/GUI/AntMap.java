    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame.GUI;


import AntGame.Ant;
import AntGame.AntWorld;
import AntGame.AntWorldGenerator;
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
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;



/**
 *
 * @author ms660
 */
public class AntMap extends JFrame {

    private JMenuItem upload;
    private AntWorld world;
    final JFileChooser fc = new JFileChooser();     
    
    private MapPanel mapPanel;
    
    
    public static void main(String[] args) {
        
        AntMap m = new AntMap();  
    }

    
    public AntMap()
    {
        super("Ant Map");
        
        createAndShowGUI();
    }


    private  void createAndShowGUI() {
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      mapPanel = new MapPanel(new Point(150, 150), null);
      
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
        JMenu control;
        
        upload = new JMenuItem("Upload World");   
        control = new JMenu("File");
        
        upload.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e)
            {
                //Handle open button action.
                if (e.getSource() == upload) 
                {
                    int returnVal = fc.showOpenDialog(AntMap.this);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        
                        File _file = fc.getSelectedFile();
                        
                        generateAntWorld(_file); 
                      
                    }
                    
                    else {
                        
                        //lblPlayer1.setText("Error Uploading");
                        
                    }

                 
                }      
            }
        });


        //Add in the shortest control.

        control.add(upload);
        
        mb.add(control);
        
        setJMenuBar(mb);      


    }
    
    public void generateAntWorld(File f)
    {
        AntWorldGenerator gen = new AntWorldGenerator();
        
        try {
            world = gen.antWorldGenerator(f);
            
            remove(mapPanel);
            
            
            mapPanel = new MapPanel(new Point(gen.x, gen.y), world);
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


class MapPanel extends JPanel  {
    
  
    private int x;
    private int y;
    private AntWorld antWorld;
    
    
    HashMap<Point, Polygon> points;
    
    
    public MapPanel(Point p, AntWorld world)  {
        
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        points = new HashMap<Point, Polygon>();
        
        this.x = (int)p.getX();
        this.y = (int)p.getY();
        antWorld = world;
 
    }

    
    
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);    
  
        int a = 1;
        
        
        
        boolean _line2 = false;
        
        for (int j = 0; j < this.x; j++)
        {
            
            int x = (j * 8) + 12;
   
            for (int i = 0; i < this.y; i++)
            {

                Polygon p = new Polygon();

                int y = (i * 9) + 12;
                
                if (_line2)
                {
                    //y = (i * 70) + 85;
                    y = (i * 9) + 16;
                }

               


                for (int c = 0; c < 6; c++)
                {
                    Point corner = hex_corner(x, y, 5, c);

                    //System.out.println(c + " " + corner.x +", " + corner.y + " : " + corner.toString());
                 
                    p.addPoint(corner.x, corner.y);
                    
                    

                }

                System.out.println("x: " + i +  "y: " + j);
                
                g.setColor(Color.BLACK);
                g.drawPolygon(p);
                
                points.put(new Point(i, j), p);
                
                
                if (antWorld != null)
                {
                    
                    if (antWorld.getPosition(i, j).getRocky())
                    {
                        g.setColor(Color.BLUE);
                        g.fillPolygon(p);
                    }
                    
                    else if (antWorld.getPosition(i, j).getAnt() != null)
                    {
                        Ant ant = antWorld.getPosition(i, j).getAnt();
                        
                        if (ant.getColour().equals("Red"))
                        {
                            g.setColor(Color.red);
                        }
                        
                        else if (ant.getColour().equals("Black"))
                        {
                            g.setColor(Color.black);
                        }
                        
                        
                        g.fillPolygon(p);
                    }
                    
                    else if (antWorld.getPosition(i, j).getAntHill() =="Red")
                    {
                        g.setColor(Color.pink);
                        g.fillPolygon(p);
                        g.setColor(Color.pink);
                    } 
                    
                    else if (antWorld.getPosition(i, j).getAntHill() == "Black")
                    {
                        g.setColor(Color.gray);
                        g.fillPolygon(p);
                        g.setColor(Color.gray);
                    } 
                    
                    else if (antWorld.getPosition(i, j).hasFood())
                    {
                        g.setColor(Color.orange);
                        g.fillPolygon(p);
                    }
                    
                    
                    
                    
                }
                
                
                

            }
            
            _line2 = !_line2;
            
        }
                
        
        

        // Draw Text
        //g.drawString("This is my custom Panel!",10,20);
        
      //  g.setColor(Color.RED);
        //g.fillRect(squareX,squareY,squareW,squareH);
        //g.setColor(Color.BLACK);
        //g.drawRect(squareX,squareY,squareW,squareH);
        
    }  
    
    
    public Dimension getPreferredSize() {
        
        int _x = (int)Math.ceil(x * 9.1) + 16;
        int _y = (int)Math.ceil(y * 8.1666667) + 12;
        
        return new Dimension(_x, _y);
    }

    
    /**
     * Creates a hexagon corner.
     * 
     * @param y Center co-ordinates 
     * @param x Center co-ordinates 
     * @param size of the hexagon
     * @param i Corner
     * @return Point 
     */
    public Point hex_corner(int y, int x, int size, int i) {

        int angle_deg = (60 * i   + 30);
        
        //System.out.println("ad: " + angle_deg);
        double angle_rad = (Math.PI / 180 * angle_deg);
        
        int _x = (int) (x + size * Math.cos(angle_rad));
        int _y = (int) (y + size * Math.sin(angle_rad));
        
        return new Point(_x,_y);
    }

    
}




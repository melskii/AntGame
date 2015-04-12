    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame.GUI;


import javax.swing.SwingUtilities;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Point;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;



/**
 *
 * @author ms660
 */
public class AntMap {

    
      public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    
    
      private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
                SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Ant Map");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        f.add(new MapPanel());
        f.pack();
        
        f.setVisible(true);
    }
    
    
    
}


class MapPanel extends JPanel {
    
    private int squareX = 50;
    private int squareY = 50;
    private int squareW = 20;
    private int squareH = 20;
    
    


    

    public MapPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        
         addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                moveSquare(e.getX(),e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                moveSquare(e.getX(),e.getY());
            }
        });
        
    }
    
     private void moveSquare(int x, int y) {
        int OFFSET = 1;
        if ((squareX!=x) || (squareY!=y)) {
            repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
            squareX=x;
            squareY=y;
            repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
        } 
    }
    
    

    public Dimension getPreferredSize() {
        return new Dimension(1000,1000);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);    
        
        
       
        
        
        
        
        double _x = 0;
        double _y = 0;
        
        int a = 1;
        
        
        
        for (int _j = 0; _j < 10; _j++)
        {
        
            for (int _i = 0; _i < 10; _i++)
            {
            
                Polygon p = new Polygon();
                for (int i = 0; i < 6; i++)
                {
                    int x = 0;
                    int y = 0;
                    
                    if (_j > 1) //(_j % 2) == 0 &&
                    {

                        x = (int) ((_i*75) + _x + 50 + 25 * Math.cos(i * 2 * Math.PI / 6));  
                        y = (int) (((_j*50) + (a * -57)) + _y + 50 + 25 * Math.sin(i * 2 * Math.PI / 6));
               
                    }
                    
                    else {
                        
                        x = (int) ((_i*75) + _x + 50 + 25 * Math.cos(i * 2 * Math.PI / 6));
                        y = (int) (((_j*50)) + _y + 50 + 25 * Math.sin(i * 2 * Math.PI / 6));
                        
                    }
                    
                    System.out.println("x:  " +x);
                    System.out.println("y: " +y);
                    
                    p.addPoint(x, y);
                }

                g.setColor(Color.BLACK);
                g.drawPolygon(p);
                
              
                
                System.out.println("npoints: " + p.npoints);
                
            }
            
            if (_x > 0)
            {
                _x = 0;
                _y = 0;
            }
            
            else {
                
                _x = 37.5;
                _y = -25.5;
                
            }
            
           
        }
        
        

        

        // Draw Text
        //g.drawString("This is my custom Panel!",10,20);
        
      //  g.setColor(Color.RED);
        //g.fillRect(squareX,squareY,squareW,squareH);
        //g.setColor(Color.BLACK);
        //g.drawRect(squareX,squareY,squareW,squareH);
        
    }  
    
    public Point hex_corner(int y, int x, int size, int i) {

        int angle_deg = 60 * i   + 90;
        int angle_rad = (int) Math.PI / 180 * angle_deg;
        
        return Point(x + size * Math.cos(angle_rad),
                     y + size * Math.sin(angle_rad))
    }
    
}




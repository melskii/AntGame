/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame.GUI;

import AntGame.Ant;
import AntGame.AntWorld;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author ms660
 */
public class MapPanel extends JPanel  {
    
  
    private int x;
    private int y;
    private AntWorld antWorld;
    
    
    HashMap<Point, Polygon> points;
    
    
    public MapPanel(AntWorld world)  {
        
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        
        
        points = new HashMap<Point, Polygon>();
        
        this.x = world.xlength;
        this.y = world.ylength;
                
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

                    p.addPoint(corner.x, corner.y);
                    
                    

                }

                g.setColor(Color.BLACK);
                g.drawPolygon(p);
                
                points.put(new Point(i, j), p);
                
                
                
                
                
                

            }
            
            _line2 = !_line2;
            
        }
        
        setHexagonColours(g);
                
        
        

        // Draw Text
        //g.drawString("This is my custom Panel!",10,20);
        
      //  g.setColor(Color.RED);
        //g.fillRect(squareX,squareY,squareW,squareH);
        //g.setColor(Color.BLACK);
        //g.drawRect(squareX,squareY,squareW,squareH);
        
    }  
    
    public void setHexagonColours(Graphics g)
    {
        repaint();
        
        Iterator it = points.entrySet().iterator();
        
         
         while (it.hasNext())
         {
             Map.Entry pair = (Map.Entry)it.next();
             Polygon p = (Polygon)pair.getValue();
             Point point = (Point)pair.getKey();
             
             int i = point.x;
             int j = point.y;
             
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
                    
                    else {
                        
                       // g.setColor(Color.white);
                        //g.fillPolygon(p);
                        
                        
                        
                    }
                    
                    g.setColor(Color.black);
                    
                    
                    
                    
                }
        
             
             
         }
         
        
        
        
        
        
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame.GUI;

import AntGame.AntBrain;
import AntGame.AntWorld;
import AntGame.AntWorldGenerator;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ms660
 */
public class AntGameUpload extends JFrame{
    
    JButton upload, next;
    JTextField textArea, textValid;
    boolean valid;
    boolean brain;
    String title;
    String key; 
   
    LinkedHashMap<String, Object> game;
 
    
    final JFileChooser fc = new JFileChooser(); 
     
    
    
     public AntGameUpload (LinkedHashMap<String, Object> game)
     {
         super();
         
         this.game = game;
                  
         Iterator it = game.entrySet().iterator();
      
         
         while (it.hasNext())
         {
             Map.Entry pair = (Map.Entry)it.next();
             
             if (pair.getValue() == null)
             {
                 this.key = (String)pair.getKey();
                 
                 if (key.startsWith("Brain"))
                 {
                     this.title = "Upload " + (String)pair.getKey();
                     brain = true;
                     setTitle(title);
                     
                 }
                 
                 else if (key.startsWith("World"))
                 {
                      this.title = "Upload " + (String)pair.getKey();
                      setTitle(title);
                 }
                 
                 break;
             }
             
             
         }
         
         mainFrame();
        
     }
    
     public void mainFrame()
     {   
   
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();

        GroupLayout layout = new GroupLayout(c);
        c.setLayout(layout);

        upload = new JButton("Upload");
        next = new JButton("Next");
        textArea = new JTextField("Upload a file");
        textValid = new JTextField("");
        valid = false;

        next.enable(valid);
        textArea.enable(false);
        textValid.enable(false);

        textArea.setPreferredSize(new Dimension(350, 30));
        textValid.setPreferredSize(new Dimension(425, 30));


        setSize(300, 200);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        p1.setLayout(new FlowLayout());
        p2.setLayout(new FlowLayout());
        p3.setLayout(new FlowLayout());

        //p1.setBackground(Color.RED);
        //p2.setBackground(Color.blue);

        p1.add(upload);
        p1.add(textArea);


        p2.add(textValid);

        p3.add(next);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        //getContentPane().setBackground(Color.yellow);
        getContentPane().add(p1);
        getContentPane().add(p2);
        getContentPane().add(p3);

        setPreferredSize(new Dimension(500, 150));
        
        next.addActionListener(new ActionListener() {
            
            public void actionPerformed (ActionEvent e)
            {
                nextButton();
                
            }
            
            
        });

        upload.addActionListener(new ActionListener() {

            public void actionPerformed (ActionEvent e)
            {
                 if (e.getSource() == upload) 
                 {
                     uploadFile();
                 }
            }
        });
        
        
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);  
        

        
        
        
     }
     
    public void uploadFile()
    {
       
        int val = fc.showOpenDialog(AntGameUpload.this);

        if (val == JFileChooser.APPROVE_OPTION) {

            File _file = fc.getSelectedFile();
            
            textArea.setText(_file.getPath().toString());
      
            String extension = "";
            boolean ext = false;
            
            int i = _file.getName().lastIndexOf('.');
            
            

            if (i > 0) {
                
                extension = _file.getName().substring(i+1);


                if ((!extension.equals("ant")) && brain)
                {
                    textValid.setText("Please upload a correct ant brain file (*.ant)");
                    valid = false;
                }

                else if ((!extension.equals("world")) && !brain)
                {
                    textValid.setText("Please upload a correct world file (*.world)");
                    valid = false;
                }

                else if ((extension.equals("ant") && brain) || (extension.equals("world") && !brain))
                {
                    ext = true;
                    textValid.setText("");
                    valid = false;
                }
                
                else {
                    
                    textValid.setText("Error");
                    valid = false;
                    
                }
            }
            
            else {
                
                textValid.setText("Please upload a file with the correct extension");
                valid = false;
                
            }
            
            if (ext)
            {
                
                if (!brain)
                {
                    game.put(key, null);
                    
                    try {
                    
                        AntWorldGenerator gen = new AntWorldGenerator();
                        AntWorld world = gen.antWorldGenerator(_file);
                        valid = true;
                        game.put(key, world);
                        
                        
                        
                    }
                    
                    catch (Exception e)
                    {
                        valid = false;
                        textValid.setText(e.getMessage());
                    }
                    
               
                    
                }
                
                
                else if (brain)
                {
                    game.put(key, null);
                    
                    try {
                    
                        AntBrain brain = new AntBrain(_file);
                        valid = true;
                        game.put(key, brain);
                        
                    }
                    
                    catch (Exception e)
                    {
                        valid = false;
                        textValid.setText(e.getMessage());
                    }
                    
               
                    
                }
                
                
                
            }
            
            if (valid)
            {
                 textValid.setText("Valid");
            }

                
            
            




        }

        else {

            textArea.setText("Upload a file");
            textValid.setText("");
            

        }


        
    }
     
    /**
     * Either shows the next upload JFrame or the map
     */
    public void nextButton()
    {
        //valid = true;
                
                
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
                    AntGameUpload upload = new AntGameUpload(game);
                    break;
                }


            }

            if (map)
            {
                AntMap load = new AntMap(game);
            }


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

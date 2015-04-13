
package AntGame.GUI;

import AntGame.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import javax.swing.border.BevelBorder;


// GUI START (PLAYING WITH SWING) 
public class TestGUI2 extends JFrame {

    private AntBrain player1;    
    final JFileChooser fc = new JFileChooser(); 
    JButton a, b, c, d;
    JLabel lblPlayer1, statusLabel;


    public static void main(String[] args) {  

        TestGUI2 t = new TestGUI2();
        t.mainFrame();
    }  

    public void mainFrame()
    {
        //JFrame f=new JFrame(); //create a frame

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        a = new JButton("Player 1"); 
        a.setBounds(20,20,100, 30);
        
        
        lblPlayer1  = new JLabel("", JLabel.CENTER);        
        lblPlayer1.setText("Upload");
        lblPlayer1.setOpaque(true);
        lblPlayer1.setVisible(true);
        lblPlayer1.setBackground(Color.GRAY);
        lblPlayer1.setForeground(Color.WHITE);
        lblPlayer1.setLocation(130,20);
        lblPlayer1.setSize(200, 30);

        a.addActionListener(new ActionListener() {

            public void actionPerformed (ActionEvent e)
            {
                //Handle open button action.
                if (e.getSource() == a) 
                {
                    int returnVal = fc.showOpenDialog(TestGUI2.this);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        
                        File _file = fc.getSelectedFile();
                        
                        String _msg = importAntBrain(player1, _file); 
                        
                        lblPlayer1.setText(_msg);
                        
                        
                    }
                    
                    else {
                        
                        //lblPlayer1.setText("Error Uploading");
                        
                    }

                 
                }
            }
        });

        JButton b=new JButton("Player 2");
        b.setBounds(20,70,100, 30);

        JButton c=new JButton("Upload Ant World");
        c.setBounds(20,130,170, 30);

        JButton d=new JButton("Generate Ant World"); 
        d.setBounds(20,180,170, 30);

        JButton e=new JButton("Run");  
        e.setBounds(20,230,100, 30);

        add(a);
        add(lblPlayer1);
        add(b);
        add(c); 
        add(d); 
        add(e);
        
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        
        statusPanel.setPreferredSize(new Dimension(getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        
        statusLabel = new JLabel("Status bar");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        
        statusPanel.add(statusLabel);

        add(statusPanel, BorderLayout.SOUTH);
        
        setLayout(null);//using no layout managers 
        //pack();
        setSize(400,400);//400 width and 400 height  
        //setLayout(new FlowLayout());
        setVisible(true);//making the frame visible  
    }


    public String importAntBrain(AntBrain p, File f)
    {
        
        String _msg = "";
        
        
        try {
        
            p = new AntBrain(f);
            _msg = p.getBrainLabel();
            

        }
        catch (Exception e) {
            
            _msg = e.getMessage();
            
        }
        
        return _msg;


    }

    

}  


package AntGame.GUI;
import java.awt.Color;
import javax.swing.*;

// GUI START (PLAYING WITH SWING) 

public class TestGUI extends JFrame {
      
public static void main(String[] args) {  

    TestGUI t = new TestGUI();
    t.mainFrame();
}  

public void mainFrame()
{
    //JFrame f=new JFrame(); //create a frame
    
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JButton a=new JButton("Player 1"); 
    a.setBounds(20,20,100, 30);

    JLabel label  = new JLabel("Upload");    
           label.setText("Upload");
           label.setOpaque(true);
           label.setBackground(Color.GRAY);
           label.setForeground(Color.WHITE);
           label.setLocation(130,20);

    JButton b=new JButton("Player 2");
    b.setBounds(20,70,100, 30);

    JButton c=new JButton("Upload Ant World");
    c.setBounds(20,130,170, 30);

    JButton d=new JButton("Generate Ant World"); 
    d.setBounds(20,180,170, 30);

    JButton e=new JButton("Run");  
    e.setBounds(20,230,100, 30);

    add(a);
    add(label);
    add(b);
    add(c); 
    add(d); 
    add(e);

    setSize(400,400);//400 width and 400 height  
    setLayout(null);//using no layout managers  
    //pack();
    setVisible(true);//making the frame visible  
}

}  

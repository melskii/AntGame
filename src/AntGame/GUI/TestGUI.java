
package AntGame.GUI;
import java.awt.Color;
import javax.swing.*;

// GUI START (PLAYING WITH SWING) 

public class TestGUI {
      
public static void main(String[] args) {  

    TestGUI t = new TestGUI();
    t.mainFrame();
}  

public void mainFrame()
{
    JFrame f=new JFrame();//creating instance of JFrame  

    JButton a=new JButton("Player 1");//creating instance of JButton  
    a.setBounds(20,20,100, 30);//x axis, y axis, width, height 

    JLabel label  = new JLabel("", JLabel.CENTER);        
          label.setText("Upload");
          label.setOpaque(true);
          label.setBackground(Color.GRAY);
          label.setForeground(Color.WHITE);
          //label.

    JButton b=new JButton("Player 2");//creating instance of JButton  
    b.setBounds(20,70,100, 30);//x axis, y axis, width, height  

    JButton c=new JButton("Upload Ant World");//creating instance of JButton  
    c.setBounds(20,130,170, 30);//x axis, y axis, width, height 

    JButton d=new JButton("Generate Ant World");//creating instance of JButton  
    d.setBounds(20,180,170, 30);//x axis, y axis, width, height 

    JButton e=new JButton("Run");//creating instance of JButton  
    e.setBounds(20,230,100, 30);//x axis, y axis, width, height 

    f.add(a); // add a
    f.add(b);// add b
    f.add(c); // add c
    f.add(d); // add d
    f.add(e);



    f.setSize(400,400);//400 width and 400 height  
    f.setLayout(null);//using no layout managers  
    f.setVisible(true);//making the frame visible  
}

}  

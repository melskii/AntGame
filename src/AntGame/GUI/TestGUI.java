
package AntGame.GUI;
import javax.swing.*;

// GUI START (PLAYING WITH SWING) 

public class TestGUI {
      
public static void main(String[] args) {  
JFrame f=new JFrame();//creating instance of JFrame  

JButton a=new JButton("Player 1");//creating instance of JButton  
a.setBounds(20,20,100, 20);//x axis, y axis, width, height 
          
JButton b=new JButton("Player 2");//creating instance of JButton  
b.setBounds(20,80,100, 20);//x axis, y axis, width, height  
          
JButton c=new JButton("Upload Ant World");//creating instance of JButton  
c.setBounds(20,140,170, 20);//x axis, y axis, width, height 

JButton d=new JButton("Generate Ant World");//creating instance of JButton  
d.setBounds(20,200,170, 20);//x axis, y axis, width, height 

f.add(a); // add a
f.add(b);// add b
f.add(c); // add c
f.add(d); // add d
          

f.setSize(400,500);//400 width and 500 height  
f.setLayout(null);//using no layout managers  
f.setVisible(true);//making the frame visible  
}  
}  

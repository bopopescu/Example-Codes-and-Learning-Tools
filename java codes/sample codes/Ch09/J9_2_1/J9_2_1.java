import java.awt.*;
import javax.swing.*;

class CGFrame extends JFrame{
  CGFrame(){
    setTitle("JFrame�e��");
    setBounds(50, 50, 200, 150);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
 
  public void paint(Graphics g){
    g.drawString("�j�a�Ӿ� Java2 !", 50, 70);
  }
}

public class J9_2_1{
  public static void main(String[] args){
    CGFrame frame = new CGFrame();
  }
}
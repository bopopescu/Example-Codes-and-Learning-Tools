import java.awt.*;
import javax.swing.*;

class CGFrame extends JFrame {
  CGFrame() {
    CGPanel pane = new CGPanel();  // JPanel���e��
    pane.setBounds(30, 20, 150, 80);
    add(pane);
        
    setTitle("JPanel�e��");
    setLayout(null);
    setBounds(50, 50, 200, 150);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
 
  class CGPanel extends JPanel {
    public void paintComponent(Graphics g) {
      g.drawString("�p�Ƥ����A�j������", 40, 70);
    }        
  } 
}

public class J9_3_1 {
  public static void main(String[] args) {
    CGFrame frame = new CGFrame();
  }
}
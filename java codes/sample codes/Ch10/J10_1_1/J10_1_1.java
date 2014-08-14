import javax.swing.*;
import java.awt.*;

class LayoutFrame extends JFrame {
  LayoutFrame() {
    JButton button[] = new JButton[9];
    for(int i = 0; i < button.length; i++) {
      button[i] = new JButton("���s" + i);
      add(button[i]);
    }
    
    setTitle("FlowLayout �G���覡");
    setLayout(new FlowLayout());
    setBounds(50, 50, 300, 160);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
}

public class J10_1_1 {
  public static void main(String[] args){
    LayoutFrame frame = new LayoutFrame();
  }
}
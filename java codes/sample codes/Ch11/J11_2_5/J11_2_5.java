import javax.swing.*;
import java.awt.*;

class cDialog extends JFrame {
  cDialog() {
    JPanel pane = new JPanel(); 
    JColorChooser chooser = new JColorChooser();
    Color color = chooser.showDialog(null, "�п�ܩ���", Color.gray);
    pane.setBackground(color); 
    
    add(pane);
    setTitle("��m��ܹ�ܮ�");
    setBounds(50, 50, 300, 150);   
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
}

public class J11_2_5 {
  public static void main(String[] args) {
    cDialog myFrame = new cDialog();
  }
}
import javax.swing.*;
import java.awt.event.*;

class CKeyFrame extends JFrame implements KeyListener {
  ImageIcon icon1 = new ImageIcon("fig_1.jpg");
  JLabel lblPic = new JLabel(icon1);
  int pos_x = 70, pos_y = 30;

  CKeyFrame() {
    lblPic.setBounds(pos_x, pos_y, 150, 160);
    add(lblPic);
        
    addKeyListener(this);        
    setTitle("����V�� ���ʹϧ�");
    setLayout(null);
    setBounds(100, 100, 300, 250);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }  
     
  public void keyPressed(KeyEvent e) {
    switch(e.getKeyCode()) {
      case KeyEvent.VK_UP:     // �V�W
           pos_y -= 5;
           break;
      case KeyEvent.VK_DOWN:   // �V�U
           pos_y += 5; 
           break;   
      case KeyEvent.VK_LEFT:   // �V��
           pos_x -= 5;
           break;  
      case KeyEvent.VK_RIGHT:  // �V�k
           pos_x += 5;
           break;  
    }
    lblPic.setLocation(pos_x, pos_y);
  }
  public void keyTyped(KeyEvent e) {}    
  public void keyReleased(KeyEvent e) {}
}

public class J7_5_1 {
  public static void main(String[] aegs) {
    CKeyFrame frame = new CKeyFrame();
  }  
}
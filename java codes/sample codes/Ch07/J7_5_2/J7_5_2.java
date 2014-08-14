import javax.swing.*;
import java.awt.event.*;

class CKeyFrame extends JFrame {
  ImageIcon icon1 = new ImageIcon("fig_1.jpg");
  JLabel lblMove = new JLabel(icon1);
  int pos_x = 70, pos_y = 30;

  CKeyFrame() {
    lblMove.setBounds(pos_x, pos_y, 150, 160);
    add(lblMove);
        
    CkeyMove keyMove = new CkeyMove();
    addKeyListener(keyMove);
    setTitle("�W�U���k�� ���ʹϧ�");
    setLayout(null);
    setBounds(100,100,300,250);        
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }  
     
  class CkeyMove extends KeyAdapter {
    public void keyPressed(KeyEvent e)  {
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
      lblMove.setLocation(pos_x, pos_y);
    }
  }
}

public class J7_5_2 {
  public static void main(String[] aegs) {
    CKeyFrame frame = new CKeyFrame();
  }  
} 

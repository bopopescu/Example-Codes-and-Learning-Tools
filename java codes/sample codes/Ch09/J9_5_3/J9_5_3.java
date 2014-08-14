import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class CDraw extends JFrame {
  CGPanel pane = new CGPanel();
  Point p1, p2;                 // �ŧi�I���Ъ���
  boolean isDraw = false;       // �M�w�O�_ø�s
                
  CDraw() {
    pane.setBounds(0, 0, 300, 200);
    add(pane);
    pane.addMouseListener(new CDrawPic1());    
    pane.addMouseMotionListener(new CDrawPic2());    
   
    setTitle("�ηƹ�ø�s�x��");
    setLayout(null);
    setBounds(50, 50, 300, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
  
  class CDrawPic1 extends MouseAdapter {
    public void mousePressed(MouseEvent e) {
      p1 = e.getPoint();       // ���o�_�I����
    }
       
    public void mouseReleased(MouseEvent e) {
      isDraw = false;         // ����ø�s
    }
  }  
  
  class CDrawPic2 extends MouseMotionAdapter {
    public void mouseDragged(MouseEvent e) {
      p2 = e.getPoint();       // ���o���I����
      isDraw = true;           // �i�Hø�s       
      repaint();               // ��ø�ϧ�   
    }
  }  
  
 class CGPanel extends JPanel {
    public void paintComponent(Graphics g) {
      if (!isDraw) return;    // �Y����ø�s,��^
      g.drawRect(p1.x, p1.y, p2.x-p1.x, p2.y-p1.y); // ø�s�x��
   }             
 } 
}

public class J9_5_3 {
  public static void main(String[] args) {
    CDraw frame1 = new CDraw();
  }
}
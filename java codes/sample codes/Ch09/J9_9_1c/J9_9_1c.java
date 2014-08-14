import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

class CDraw extends JFrame {
  CGPanel pane1 = new CGPanel();      // pane1�����e��
  JPanel pane2 = new JPanel();        // pane2��mø�Ϥu����s
  JPanel pane3 = new JPanel();        // pane3��m�C����s
  JButton btnDw[] = new JButton[5];    // 6��ø�Ϥu����s
  String draw_btn = new String();      // ø�s���ϧ�����
  String txtDraw[] = {"���u", "�x��", "���", "��߯x��", "��߾��"}; 
  JButton btnCr[] = new JButton[6];   // 6���C����s
  Color cr[] = {Color.black, Color.red, Color.magenta, Color.green, Color.pink, Color.cyan};
  Color draw_color = Color.black;      // �e���C��A�w�]���¦�
  Point p1, p2;                        // �̷s���_�I�B���I���� 
  boolean isDraw = false;              // �M�w�ƹ��O�_��b�e���Wø��
  String draw[] = new String[200];     // �s��ϧ������}�C�ܼ�
  Point p_b[] = new Point[200];        // �s��_�I�}�C�ܼ�
  Point p_e[] = new Point[200];        // �s����I�}�C�ܼ�
  Color pen_color[] = new Color[200];  // �s���C��}�C�ܼ�
  int count = 0;                       // �s��ϧέӼ��ܼ� 
           
  CDraw() {
    // �e���e��
    pane1.setBounds(20, 20, 300, 300);
    pane1.setBorder(BorderFactory.createLineBorder(Color.black, 5));
    add(pane1);
    pane1.addMouseListener(new CDrawPic1());    
    pane1.addMouseMotionListener(new CDrawPic2());       
    // ��mø�Ϥu����s�e��
    pane2.setBounds(330, 20, 100, 170);
    pane2.setLayout(null);
    add(pane2);    
    for(int i = 0; i < 5; i++) {
      btnDw[i] = new JButton(txtDraw[i]);
      btnDw[i].setBounds(10, i*30, 80, 20);  
      btnDw[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
      pane2.add(btnDw[i]);
      btnDw[i].addMouseListener(new CbtnDw());  
    }
    // ��m�C����s�e��   
    pane3.setBounds(330, 190, 100, 130);
    pane3.setLayout(null);
    add(pane3);        
    int k = 0 ;      
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 2; j++) {
        btnCr[k] = new JButton();
        btnCr[k].setBounds(15+j*40, 15+i*40, 30, 30);
        btnCr[k].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        btnCr[k].setBackground(cr[k]);
        pane3.add(btnCr[k]);
        btnCr[k].addMouseListener(new CbtnCr());
        k++;
      }
    }
 
    setTitle("²��ø�ϵ{��");
    setLayout(null);
    setBounds(50, 50, 450, 370);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
  
  class CbtnDw extends MouseAdapter {
    public void mouseClicked(MouseEvent e){
      for(int i = 0; i < 5; i++) {
        btnDw[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        if(e.getSource() == btnDw[i]) { 
          btnDw[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
          draw_btn = btnDw[i].getText();
        }    
      }
    }
  }  
    
  class CbtnCr extends MouseAdapter {
    public void mouseClicked(MouseEvent e){
      for(int k = 0; k < 6; k++) {
        btnCr[k].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        if(e.getSource() == btnCr[k]) { 
          draw_color = cr[k];
          btnCr[k].setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        }    
      }
    }
  }
  
  class CDrawPic1 extends MouseAdapter {
    public void mousePressed(MouseEvent e) {
      isDraw = false;
      p1 = e.getPoint();
    }
       
    public void mouseReleased(MouseEvent e) {
      if (isDraw) count++;
      isDraw = false;
    }
  }  
  
  class CDrawPic2 extends MouseMotionAdapter {
    public void mouseDragged(MouseEvent e) {
      p2 = e.getPoint();
      isDraw = true;
      repaint();
    }
  }  
 
  class CGPanel extends JPanel {
    public void paintComponent(Graphics g) {
      if (!isDraw) return;
      // ��ø�Y���ϧ�
      for(int i =0; i<count; i++) {
        g.setColor(pen_color[i]);
        drawing(g, draw[i], p_b[i].x, p_b[i].y, p_e[i].x, p_e[i].y);
      }  
      // ø�s�s�ϧ�     
      g.setColor(draw_color);
      drawing(g, draw_btn, p1.x, p1.y, p2.x, p2.y);
      // �N�̷sø�s���ϧΰO���b�}�C�ܼƤ�
      pen_color[count] = draw_color;
      p_b[count] = p1;
      p_e[count] = p2;
      draw[count]= draw_btn; 
    }             
  }
 
  public void drawing(Graphics g, String st, int px1, int py1, int px2, int py2) {
  	// �b�e��ø�s�ϧ�
  	if (st.equals("���u")) g.drawLine(px1, py1, px2, py2);   
    if (st.equals("�x��")) g.drawRect(px1, py1, px2-px1, py2-py1);   
    if (st.equals("���")) g.drawOval(px1, py1, px2-px1, py2-py1);   
    if (st.equals("��߯x��")) g.fillRect(px1, py1, px2-px1, py2-py1);   
    if (st.equals("��߾��")) g.fillOval(px1, py1, px2-px1, py2-py1);    
  } 
 
}
public class J9_9_1c {
  public static void main(String[] args) {
    CDraw frame1 = new CDraw();
  }
}
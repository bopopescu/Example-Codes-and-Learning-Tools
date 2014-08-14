import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.*;

class cChangeCircle extends JFrame {
  JScrollBar scroll;                        // �ŧi�վ����Y�񪺱��b
  JSlider[] slid = new JSlider[3];          // �ŧi�վ����C�⪺�վ㾹�}�C
  Color paintCr = new Color(0, 0, 0);       // ��Τ������
  CGPanel paneL = new CGPanel();            // ø�s��Ϊ��e���e�� 
  
  cChangeCircle() {
    paneL.setBounds(0, 15, 220, 230); 
    add(paneL);                             // �����[�JpaneL���b�e��
     
    JPanel paneR = new JPanel();            // ��m���b�νվ㾹���u��e��
    paneR.setBounds(240, 0, 210, 240);
    paneR.setLayout(null);
    add(paneR);                             // �����[�JpaneR�u��e��
    
    scroll = new JScrollBar(JScrollBar.HORIZONTAL, 50, 0, 20, 200);
    scroll.setBounds(0, 20, 200, 20); 
    scroll.setUnitIncrement(4);
    scroll.addAdjustmentListener(Lscroll);  // ���U���b�ƥ��ť��
    paneR.add(scroll);                      // �u��e���[�Jscroll���b
   
    Color[] SlidBackCr = {Color.red, Color.green, Color.blue};
    for (int i = 0; i < slid.length; i++) {
      slid[i] = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
      slid[i].setBounds(0, 60*(i+1), 200, 50); 
      slid[i].setBackground(SlidBackCr[i]);
      slid[i].setMajorTickSpacing(50);
      slid[i].setMinorTickSpacing(10);
      slid[i].addChangeListener(Lslid);     // ���U�վ㾹�ƥ��ť��
      slid[i].setPaintLabels(true);
      slid[i].setPaintTicks(true);
      paneR.add(slid[i]);                   // �u��e���[�Jslid[]�վ㾹
    }
           
    setTitle("�ܤƪ����");
    setLayout(null);
    setBounds(50, 50, 460, 280);   
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  } 
  
  public AdjustmentListener Lscroll = new AdjustmentListener() {
    public void adjustmentValueChanged(AdjustmentEvent e) {
      repaint();
    }
  };
 
  public ChangeListener Lslid = new ChangeListener() {
    public void stateChanged(ChangeEvent e) {
      paintCr = new Color(slid[0].getValue(), slid[1].getValue(), slid[2].getValue());
      repaint();
    }
  };

  class CGPanel extends JPanel {
    public void paintComponent(Graphics g) {
      int x1 = (paneL.getHeight() - scroll.getValue()) / 2;
      int x2 = (paneL.getWidth() - scroll.getValue()) / 2;
      g.setColor(paintCr);	
      g.fillOval(x1, x2, scroll.getValue(), scroll.getValue());
    }             
  }
}
 
public class J11_6_1 {
  public static void main(String[] args) {
    cChangeCircle myFrame = new cChangeCircle();
  }
}
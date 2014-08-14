import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

class cSlider extends JFrame implements ChangeListener {
  JSlider[] slid = new JSlider[3];      // �ŧi�T�Ӥ������վ㾹�}�C
  JPanel pane2= new JPanel();           // �ŧi�إ�pane2�e���e��
  Color paintCr = new Color(0, 0, 0);   // �e���I���զ��
    
  cSlider() {
    JPanel pane1 = new JPanel();
    pane1.setBounds(130, 20, 220, 140);
    pane1.setLayout(new FlowLayout());
    Color[] SlidBackCr = {Color.red, Color.green, Color.blue};
    for (int i = 0; i < slid.length; i++) {
      slid[i] = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
      slid[i].setBackground(SlidBackCr[i]);
      slid[i].setMajorTickSpacing(50);
      slid[i].setMinorTickSpacing(10);
      slid[i].addChangeListener(this);    // ���U��ť��
      slid[i].setPaintLabels(true);
      slid[i].setPaintTicks(true);
      pane1.add(slid[i]);
    }
    slid[1].setPaintTicks(false);
    slid[2].setPaintLabels(false);
    add(pane1);             // �[�Jpane1�e��
    
    pane2.setBounds(20, 30, 100, 100);
    pane2.setBorder(BorderFactory.createLineBorder(Color.black));
    pane2.setBackground(paintCr);
    add(pane2);             // �[�Jpane2�e���e��
    setTitle("�վ㾹");
    setLayout(null);
    setBounds(50, 50, 360, 190);   
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  } 

  public void stateChanged(ChangeEvent e) {
    paintCr = new Color(slid[0].getValue(), slid[1].getValue(), slid[2].getValue());
    pane2.setBackground(paintCr);
  }
}
 
public class J11_5_1 {
  public static void main(String[] args) {
    cSlider myFrame = new cSlider();
  }
}
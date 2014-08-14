import javax.swing.*;
import java.awt.event.*;

class Ccard extends JFrame {
  // �H���������ƶü�1��12���ŧi
  int min = 1, max = 12, rnd_no = 12;
  int tot_no = max - min + 1;
  int data[] = new int[tot_no];
  BBKMath OData = new BBKMath();
  // �@���ܼƩΪ���
  int i, j, k;                               // �j���ܼ�
  ImageIcon icon[] = new ImageIcon[13];      // 13�ӹϧΪ���
  JButton btn[] = new JButton[12];           // 12�ө�ϧΪ����s
  CbtnPic mouseObj = new CbtnPic();          // ��ť�̪���
       
  Ccard() {
    OData.GenRnd(data, min, max, rnd_no);  // ����1~12�ü�        
    // 13�ӹϧΪ���
    icon[0] = new ImageIcon("fig_0.jpg");  // �P�I����
    for (i = 1; i <= 6; i++) {
      icon[i] = new ImageIcon("fig_" + i +".jpg");
      icon[i + 6] = new ImageIcon("fig_" + i +".jpg");
    }
    // 12�ӹϧΫ��s�A�ҥ���J�P�I���ϡ@
    k = 0;
    for (i = 0; i <= 2; i++) {
      for (j = 0; j <= 3; j++){
        btn[k] = new JButton(icon[0]);
        btn[k].setBounds(10 + j * 160, 10 + i * 170, 150, 160);
        add(btn[k]);
        k++;
      }
    }
    for(k = 0; k <= 11; k++)
      btn[k].addMouseListener(mouseObj);    // ���U��ť��
        
    setTitle("4x3½�P�O�йC��");
    setLayout(null);
    setBounds(100, 100, 660, 570);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }  
 
  // ½�P�ƥ�B�z
  class CbtnPic extends MouseAdapter {
    public void mouseClicked(MouseEvent e){
      for(i = 0; i <= 11; i++) {
        // �����I�����@�Ӧ��ī��s
        if (btn[i].isEnabled()) {
          if(e.getSource() == btn[i]) { 
            btn[i].setIcon(icon[data[i]]);     // �Ϥ����s½�P  
          }    
        } 
      }
    }
  }         
} 

public class J7_9_1 {
  public static void main(String[] args)  {
    Ccard frame = new Ccard();
  }
}

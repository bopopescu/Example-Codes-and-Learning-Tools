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
  int p_num = 0;                             // �O��½1�i��½2�i
  int press1 = -1, press2 = -1;              // �O����1,2��½�P���s�s��
  int correct = 0;                           // �֭p�t�令�\���� 
  JLabel lblPass = new JLabel();             // �Ψ���ܧ����T�� 
    
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
      
    lblPass.setBounds(300, 515, 150, 20);     // �Ψ���ܧ����T�� 
    add(lblPass);                      
    setTitle("4x3½�P�O�йC��");
    setLayout(null);
    setBounds(100, 100, 660, 570);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }  
 
  // ½�P�ƥ�B�z
  class CbtnPic extends MouseAdapter {
    public void mouseClicked(MouseEvent e){
      if(press1 != -1) {          
        if(e.getSource() == btn[press1]) return;
      }
      if(press2 != -1) {          
        if(e.getSource() == btn[press2]) return;
      }  
                  
      for(i = 0; i <= 11; i++) {
        // �����I�����@�Ӧ��ī��s
        if (btn[i].isEnabled()) {
          if(e.getSource() == btn[i]) { 
            p_num++; 
            if (p_num > 2) {
              btn[press1].setIcon(icon[0]);
              btn[press2].setIcon(icon[0]);
              press1 = -1;      // �⦸½�P���s���٭쬰-1
              press2 = -1;
              p_num = 1;
            }
                            
            btn[i].setIcon(icon[data[i]]);     // �Ϥ����s½�P  
            if (p_num == 1) press1 = i;
              if (p_num == 2) press2 = i;
          }
        } 
      }
            
      if (press1 != -1 && press2 != -1) {
        if (Math.abs(data[press1] - data[press2]) == 6) {
          btn[press1].setEnabled(false);     // ������i�P�����s�\��
          btn[press2].setEnabled(false);
          p_num = 0;
          press1 = -1;      // �⦸½�P���s���٭쬰-1
          press2 = -1;
          correct ++;       // �t�令�\���Ʋ֥[1
          // �p�G�t�令�\���Ƶ�������P���@�b�Y�C�����\����
          if (correct == 6) lblPass.setText("���ߦ��\�I");
        }
      }
    }
  }         
} 

public class J7_9_2 {
  public static void main(String[] args)  {
    Ccard frame = new Ccard();
  }
}

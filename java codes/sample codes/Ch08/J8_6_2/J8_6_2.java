import javax.swing.*;
import java.awt.event.*;

class movePic implements Runnable {
  private JLabel lblPic;
  private int wide, pos_y, time;

  movePic(JLabel lbl, int w, int y, int t) {
    lblPic = lbl;
    wide = w;
    pos_y = y;
    time = t;    
  } 
    
  public void run() {
    int pos_x = 0;
    while (true) {
      pos_x += 2;
      lblPic.setLocation(pos_x, pos_y);
      Pause(time);
      if (pos_x >= wide) pos_x = 0;    
    }
  }
    
  void Pause(int msec) {
     try { Thread.sleep(msec); }
     catch(InterruptedException e) {}
  }
}

class Ccard extends JFrame {
  // �H���������ƶü�0��8���ŧi
  int min = 0, max = 8, rnd_no = 9;
  int tot_no = max - min + 1;
  int data[] = new int[tot_no];
  BBKMath OData = new BBKMath();
  // �@���ܼƩΪ���
  int i, j, k;                            // �j���ܼ�
  ImageIcon icon[] = new ImageIcon[9];    // 9�ӹϧΪ���
  JLabel lbl[] = new JLabel[9];           // 9�ө�ϧΪ�����
  ClblPic mouseObj = new ClblPic();       // ��ť�̪���
  int p_num = 0;                          // �O���I�����Ҫ��i��
  int press1 = -1, press2 = -1;           // �O����1,2���I�����Ҫ��s��
  JLabel lblCar = new JLabel(new ImageIcon("fig/car.gif"));
       
  Ccard() {
    OData.GenRnd(data, min, max, rnd_no);  // ����0~8�ü�        
    // 9�ӹϧΪ���
    for (i = 0; i <= 8; i++) {
      icon[i] = new ImageIcon("fig/p_" + i + ".jpg");
    }
    // 9�Ӽ��ҶüƩ��
    k = 0;
    for (i = 0; i <= 2; i++) {
      for (j = 0; j <= 2; j++){
        lbl[k]= new JLabel(icon[data[k]]);
        lbl[k].setBounds(10 + j * 160, 10 + i * 120, 160, 120);
        add(lbl[k]);
        lbl[k].addMouseListener(mouseObj);    // ���U��ť��
        k++;
      }
    }
        
    lblCar.setSize(65, 43);
    add(lblCar);
    int width = 510, c_y = 375, c_time = 25;
    Thread ThCar = new Thread(new movePic(lblCar, width, c_y, c_time));  
    ThCar.start();     
                      
    setTitle("3x3���ϹC��");
    setLayout(null);
    setBounds(100, 100, 510, 460);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }  
 
  // �ƥ�B�z
  class ClblPic extends MouseAdapter {
    public void mouseClicked(MouseEvent e) {
      for(i = 0; i <= 8; i++) {
        if(e.getSource() == lbl[i]) { 
          p_num++; 
          if (p_num == 1) press1 = i;
          if (p_num == 2) press2 = i;
        }    
      }
      // ��i���ҥ洫�Ϥ�
      if (press1 != -1 && press2 != -1) {
        int temp = data[press1];
        data[press1] = data[press2];
        data[press2] = temp;
        lbl[press1].setIcon(icon[data[press1]]);
        lbl[press2].setIcon(icon[data[press2]]);
        p_num=0;
        press1 = -1;      // �⦸�I�����O���٭쬰-1
        press2 = -1;
      }
    }
  }         
} 

public class J8_6_2 {
  public static void main(String[] args)  {
    Ccard frame = new Ccard();
  }
}

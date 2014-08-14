import javax.swing.*;
import java.awt.event.*;

class CFrame extends JFrame implements ActionListener {
  // �@���ܼƩΪ���
  int i, j, k;                             // �j���ܼ�
  ImageIcon icon[] = new ImageIcon[7];     // �ϧΪ���
  JButton btn[] = new JButton[6];          // ��ϧΪ����s
  JButton btnOK = new JButton("�i�H½�P");
  JButton btnNo = new JButton("���i�H½�P");
  CbtnPic mouseObj = new CbtnPic();        // ��ť�̪���
    
  CFrame() {
    // �ϧΫ��s
    icon[0] = new ImageIcon("fig_0.jpg"); // �P�I����
    for (i = 1; i <= 3; i++) {
      icon[i] = new ImageIcon("fig_" + i + ".jpg");
      icon[i + 3] = new ImageIcon("fig_" + i + ".jpg");
    }
    k = 0;
    for (i = 0; i <= 1; i++) {
      for (j = 0; j <= 2; j++){
        btn[k] = new JButton(icon[0]);    // �P�I���ϩ�J���ӫ��s
        btn[k].setBounds(10 + j * 160, 10 + i * 170, 150, 160);
        add(btn[k]);
        k++;
      }
    }
    // ���s
    btnOK.addActionListener(this);    // ���U��ť��
    btnOK.setBounds(120, 350, 120, 25);
    add(btnOK);
    btnNo.addActionListener(this);    // ���U��ť��
    btnNo.setBounds(250, 350, 120, 25);
    add(btnNo);
        
    setTitle("½�P");
    setLayout(null);
    setBounds(100, 100, 500, 420);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }  
 
  // ���s�ƥ�B�z
  public void actionPerformed(ActionEvent e)  {
    if (e.getSource() == btnOK) {
      for(k = 0; k <= 5; k++)
        btn[k].addMouseListener(mouseObj);    // ���U��ť��
    }
    if (e.getSource() == btnNo) {
      for(k = 0; k <= 5; k++)
      btn[k].removeMouseListener(mouseObj); // ������ť��
    }
  }
   
  // ½�P�ƥ�B�z
  class CbtnPic extends MouseAdapter {
    public void mouseClicked(MouseEvent e){
      for(i = 0; i <= 5; i++)
        if(e.getSource() == btn[i])     // �����I�����@�ӫ��s
      btn[i].setIcon(icon[i + 1]);    // �Ϥ����s½�P         
    }
  }
} 

public class J7_7_1 {
  public static void main(String[] args)  {
    CFrame frame = new CFrame();
  }
}

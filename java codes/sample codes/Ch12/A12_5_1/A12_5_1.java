import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class A12_5_1 extends JApplet implements ActionListener {
  A12_5_1 applet, applet1, applet2;
  int now_pic = 0;                         // �ثe�Ϥ��s��
  ImageIcon[] icon = new ImageIcon[6];     // 6�i�Ϥ����}�C
  JButton btnPic = new JButton();          // �Ϥ����s
  JButton btnSend = new JButton("�ǰe");   // �ǰe���s
  TextField txtSend = new TextField();     // ��J�n�ǰe���a��
  JLabel lblPic = new JLabel();            // �����Ϥ������ҥ�
  
  public void init() {
    setLayout(null);
       
    JLabel lblTitle1 = new JLabel("���Ϥ����");
    lblTitle1.setBounds(10, 5, 80, 20);
    add(lblTitle1);
    
    for (int i = 0; i < icon.length ; i++)
      icon[i] = new ImageIcon(getImage(getCodeBase(),"fig/fig_" + i +".jpg"));
    btnPic.setIcon(icon[now_pic]);            // btnPic�Ϥ����s��J�Ϥ�
    btnPic.setBounds(10, 25, 150, 160);
    add(btnPic);
    btnPic.addActionListener(this);
    
    String user = getParameter("user");      // �q<param>���o�ۨ�user�W��  
    JLabel lblUser = new JLabel("�ڬO" + user);
    lblUser.setBounds(10, 190, 80, 20);
    add(lblUser);
    
    JLabel lblSend = new JLabel("�ǰe��G");
    lblSend.setBounds(10, 215, 55, 20);
    add(lblSend);
    txtSend.setBounds(70, 215, 55, 20);
    add(txtSend);
    btnSend.setBounds(130, 215, 60, 20);
    add(btnSend);
    btnSend.addActionListener(this);

    JLabel lblTitle2 = new JLabel("�����Ϥ�");
    lblTitle2.setBounds(170, 5, 80, 20);
    add(lblTitle2);
        
    lblPic.setBounds(170, 25, 150, 160);
    lblPic.setBorder(BorderFactory.createLineBorder(Color.red));
    add(lblPic);
  }
  
  public void actionPerformed(ActionEvent e) {  // ��ť�ƥ�B�z
    if (e.getSource() == btnPic) {
      now_pic ++;                               // �Ϥ��s���[1
      if (now_pic == icon.length) now_pic = 0;  // �s���W�L�q0�}�l
        btnPic.setIcon(icon[now_pic]);          // ��ܹϤ����s�W�Ϥ�
    }
     
    if (e.getSource() == btnSend) {
      applet1 = (A12_5_1)getAppletContext().getApplet("user1");
      applet2 = (A12_5_1)getAppletContext().getApplet("user2");
      String rece_user = txtSend.getText();
      applet = (A12_5_1)getAppletContext().getApplet(rece_user);
      if(applet == applet1) 
        applet1.lblPic.setIcon(icon[now_pic]);
      if(applet == applet2) 
        applet2.lblPic.setIcon(icon[now_pic]);
    }
  }
}
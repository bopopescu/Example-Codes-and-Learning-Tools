import java.awt.event.*;
import javax.swing.*;

public class A12_4_1a extends JApplet implements ActionListener {
  A12_4_1a applet1;
  A12_4_1b applet2;
  ImageIcon icon = new ImageIcon();        // �Ϥ�
  JButton btnPic = new JButton();          // �Ϥ����s
  
  public void init() {
    setLayout(null);
       
    JLabel lblTitle = new JLabel("���Ϥ��ǰe");
    lblTitle.setBounds(10, 5, 80, 20);
    add(lblTitle);
    
    icon = new ImageIcon(getImage(getCodeBase(),"fig_0.jpg"));
    btnPic.setIcon(icon);                 // btnPic���s��J�Ϥ�
    btnPic.setBounds(10, 25, 150, 160);
    add(btnPic);
    btnPic.addActionListener(this);
    
    String user = getParameter("user");      // �q<param>���o�ۨ�user�W��  
    JLabel lblUser = new JLabel("�ڬO" + user);
    lblUser.setBounds(10, 190, 80, 20);
    add(lblUser);
  }
  
  public void actionPerformed(ActionEvent e) { // ��ť�ƥ�B�z
    if (e.getSource() == btnPic) {
      applet2 = (A12_4_1b)getAppletContext().getApplet("user2");
      applet2.lblPic.setIcon(icon);
    }  
  }
}
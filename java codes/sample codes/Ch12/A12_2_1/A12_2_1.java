import javax.swing.*;
import java.awt.event.*;

public class A12_2_1 extends JApplet implements ActionListener {
  JLabel lblCel = new JLabel("�п�J���żСG");
  JTextField txtCel = new JTextField();
  JButton btnChange = new JButton("�����ؤ�");
  JLabel lblFah = new JLabel();
  
  public void init() {
    setLayout(null);
    lblCel.setBounds(20,10,110,20);
    add(lblCel);                     // �[�J���Ҩ�Applet
    txtCel.setBounds(130,10,40,20);
    add(txtCel);                     // �[�J��r�����Applet
    btnChange.setBounds(30,50,120,20);
    add(btnChange);                 // �[�J�R�O���s��Applet
    btnChange.addActionListener(this);   // �[�J�R�O���sListerer
    lblFah.setBounds(20,90,140,20);
    add(lblFah);                      // �[�J���Ҩ�Applet
  }

  public void actionPerformed(ActionEvent e) {
    float f = Integer.parseInt(txtCel.getText());  // ���o���ż�
    f = f * 9 / 5 + 32;                      // �ন�ؤ�ż�
    String txt = "�ؤ�żЬ��G " + f;                          
    lblFah.setText(txt);	                // ��X��ؤ��r���
  }
}
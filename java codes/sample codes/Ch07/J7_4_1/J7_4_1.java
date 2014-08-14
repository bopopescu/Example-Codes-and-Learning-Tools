import javax.swing.*;
import java.awt.event.*;

class CFrame extends JFrame implements ActionListener {
  JLabel lbl1 = new JLabel("1 �W = 3.3 ���褽��");
  JLabel lbl2 = new JLabel("��J�W�ơG");
  JTextField txtInput = new JTextField("0");
  JTextField txtArea = new JTextField();
  JButton btnOK = new JButton("�T�w");
  JButton btnCls = new JButton("�M��");
    
  CFrame() {
    lbl1.setBounds(50,10,200,20);
    add(lbl1);
    lbl2.setBounds(20,50,100,20);
    add(lbl2);
    txtInput.addActionListener(this);
    txtInput.setBounds(90,50,100,20);
    add(txtInput);
    txtArea.setEditable(false);
    txtArea.setBounds(20,80,170,20);
    add(txtArea);
    btnOK.addActionListener(this);
    btnOK.setBounds(20,110,70,20);
    add(btnOK);
    btnCls.addActionListener(this);
    btnCls.setBounds(120,110,70,20);
    add(btnCls);
                
    setTitle("�g�a���n����");
    setLayout(null);
    setBounds(100, 100, 220, 180);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           
  }
    
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnOK || e.getSource() == txtInput) {
      double area;
      area = Double.parseDouble(txtInput.getText()) * 3.3;
      String stArea = String.valueOf(area);
      txtArea.setText("���n���G " + stArea + " ���褽��");         
      //txtArea.setText("���n���G " + area + " ���褽��");
    }
    if (e.getSource() == btnCls) {
      txtInput.setText("0");
      txtArea.setText(""); 
    }
    //txtInput.removeActionListener(this);
  }
}

public class J7_4_1 {
  public static void main(String[] args)  {
    CFrame frame = new CFrame();
  }
}

import javax.swing.*;

class cDialog extends JFrame {
  cDialog() {
    ImageIcon icon = new ImageIcon("pic.jpg");
    String[] options = {"�ӦZ", "�űC", "�ճ����D"};
    int op = JOptionPane.showOptionDialog(null,
             "�]��I�]��I�z�{���@�ɤW�ֳ̺}�G�H", "���D",
             0, 0, icon, options, options[0]);
    int answer = 2;
    JLabel lblAns;
    if (op == answer) 
      lblAns = new JLabel("����F�A�z�n���@�I");
    else 
      lblAns = new JLabel("�����F�I���׬O " + options[answer]);
    add(lblAns);
    
    setTitle("�ﶵ��ܮ�");
    setBounds(50, 50, 250, 100);   
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
}

public class J11_2_3 {
  public static void main(String[] args) {
    cDialog frame = new cDialog();
  }
}
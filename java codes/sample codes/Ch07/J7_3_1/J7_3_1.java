import javax.swing.*;
class CFrame extends JFrame {
  CFrame() {
    JLabel lbl1 = new JLabel("1 �W = 3.3 ���褽��");
    lbl1.setBounds(50, 10, 200, 20);
    add(lbl1);
        
    JLabel lbl2 = new JLabel("��J�W�ơG");
    lbl2.setBounds(20,50,100,20);
    add(lbl2);
       
    setTitle("�g�a���n����");
    setLayout(null);
    setBounds(100, 100, 220, 180);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }   
}

public class J7_3_1 {
  public static void main(String[] args) {
    CFrame frame = new CFrame();
  }
}

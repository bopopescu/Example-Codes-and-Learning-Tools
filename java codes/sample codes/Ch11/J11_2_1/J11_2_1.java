import javax.swing.*;

class cDialog extends JFrame {
  cDialog() {
    int ans = JOptionPane.showConfirmDialog(null,
              "�z�糧�Ѥ��e���s�ơA�ٺ��N�ܡH", "�а�",
              JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    JLabel lblAns;
    if (ans == JOptionPane.YES_OPTION)
      lblAns = new JLabel("�P�±z������A�ڭ̷|�A���A�F�I");
    else
      lblAns = new JLabel("�٬O�n���±z�A�ڭ̷|�n�n��i�I");
    add(lblAns);
    
    setTitle("�T�{��ܮ�");
    setBounds(50, 50, 250, 100);   
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
}

public class J11_2_1 {
  public static void main(String[] args) {
    cDialog myFrame = new cDialog();
  }
}
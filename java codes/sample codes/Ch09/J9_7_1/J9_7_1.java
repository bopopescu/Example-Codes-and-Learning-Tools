import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

class CGFrame extends JFrame implements ActionListener {
  JButton btnCr[] = new JButton[5];    // �إߤ����C����s
  JPanel pane = new JPanel();
  Color cr[] ={Color.red, Color.yellow, Color.green, Color.pink, Color.cyan};
            
  CGFrame() {
    pane.setBounds(20, 20, 200, 200);
    // �]�wpane�e�����ؽu�p�׬� 4 ����
    pane.setBorder(BorderFactory.createLineBorder(Color.blue, 4));
    add(pane);
        
    for (int i = 0; i <= 4; i++) {
      btnCr[i] = new JButton();
      btnCr[i].setBounds(21 + i * 42, 230, 30, 30);
      // �]�w�C����s�ثe�����e�B�Y��
      btnCr[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
      btnCr[i].setBackground(cr[i]);
      add(btnCr[i]);
      btnCr[i].addActionListener(this);
    }
      
    setTitle("�I���C��");
    setLayout(null);
    setBounds(50, 50, 248, 310);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
    
  public void actionPerformed(ActionEvent e) {
    for(int k = 0; k <= 4; k++) {
      // ���N�����C����s�٭쬰�B�Y��	
      btnCr[k].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
      if(e.getSource() == btnCr[k]) { 
        pane.setBackground(cr[k]);
        // �Q�I�����C����s�e�W����	
        btnCr[k].setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
      }    
    }
  }
}

public class J9_7_1 {
  public static void main(String[] args) {
    CGFrame frame = new CGFrame();
  }
}
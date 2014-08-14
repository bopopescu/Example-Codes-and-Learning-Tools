import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.awt.*;

class CMenuFrame extends JFrame implements ActionListener {
  JLabel lbl = new JLabel("��ܤ�r��"); 
  JMenuItem item11, item12, item13;
  
  CMenuFrame() {
    JMenuBar mnuBar = new JMenuBar();
    JMenu menu1 = new JMenu("���e(C)");
    menu1.setMnemonic('C');
    mnuBar.add(menu1);
    item11 = new JMenuItem("��r�@");
    item12 = new JMenuItem("��r�G");
    item13 = new JMenuItem("����(X)");
    item13.setMnemonic('X');
    menu1.add(item11);
    menu1.add(item12);
    menu1.addSeparator();
    menu1.add(item13);
    item11.addActionListener(this);
    item12.addActionListener(this);
    item13.addActionListener(this);
      
    lbl.setBorder(BorderFactory.createLineBorder(Color.black)); 
    lbl.setFont(new Font("�з���", Font.PLAIN, 20));
    
    add(mnuBar, BorderLayout.NORTH);
    add(lbl, BorderLayout.SOUTH);
    setTitle("�\���");
    setBounds(50, 50, 300, 200);   
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
  
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == item11) {
      lbl.setText("���\�O������쩳���H"); 
      lbl.setForeground(Color.blue);
    }
    if (e.getSource() == item12) {
      lbl.setText("���|�O���ɨ�n�����H"); 
      lbl.setForeground(Color.red);
    }
    if (e.getSource() == item13) System.exit(0);
  }  
}

public class J11_1_2 {
  public static void main(String[] args){
    CMenuFrame myFrame = new CMenuFrame();
  }
}
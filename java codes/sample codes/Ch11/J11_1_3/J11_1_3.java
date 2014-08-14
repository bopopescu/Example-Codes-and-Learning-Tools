import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.awt.*;

class CMenuFrame extends JFrame implements ActionListener {
  JLabel lbl = new JLabel("��ܤ�r��"); 
  JMenuItem item11, item12, item13;
  JCheckBoxMenuItem sItem211, sItem212;
  JRadioButtonMenuItem[] sItem22 = new JRadioButtonMenuItem[3];
 
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
    
    JMenu menu2 = new JMenu("�榡");
    mnuBar.add(menu2);
    JMenu sMenu21 = new JMenu("�˦�");
    menu2.add(sMenu21);
    sItem211 = new JCheckBoxMenuItem("����");
    sItem212 = new JCheckBoxMenuItem("����");
    sMenu21.add(sItem211);
    sMenu21.add(sItem212);
    sItem211.addActionListener(this);
    sItem212.addActionListener(this);
    
    menu2.addSeparator(); 
    JMenu sMenu22 = new JMenu("���");
    menu2.add(sMenu22);
    ButtonGroup group = new ButtonGroup();
    sItem22[0] = new JRadioButtonMenuItem("�a��", true);
    sItem22[1] = new JRadioButtonMenuItem("�m��");
    sItem22[2] = new JRadioButtonMenuItem("�a�k");
    for(int i = 0; i < sItem22.length; i++) {
      sItem22[i].addActionListener(this);
      group.add(sItem22[i]);
      sMenu22.add(sItem22[i]);
    }  
    
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
    
    int style = Font.PLAIN;
    if (sItem211.isSelected()) style = Font.ITALIC;
    if (sItem212.isSelected()) style = Font.BOLD;
    if (sItem211.isSelected() & sItem212.isSelected()) 
                               style = Font.BOLD + Font.ITALIC;
    lbl.setFont(new Font("�з���", style, 20)); 

    if (sItem22[0].isSelected()) lbl.setHorizontalAlignment(JLabel.LEFT); 
    if (sItem22[1].isSelected()) lbl.setHorizontalAlignment(JLabel.CENTER); 
    if (sItem22[2].isSelected()) lbl.setHorizontalAlignment(JLabel.RIGHT); 
  }  
}

public class J11_1_3 {
  public static void main(String[] args){
    CMenuFrame myFrame = new CMenuFrame();
  }
}
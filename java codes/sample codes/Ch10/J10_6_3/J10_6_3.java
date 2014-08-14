import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.awt.*;

class BorderFrame extends JFrame implements ActionListener {
  JLabel lbl = new JLabel("���\�O������쩳���H"); 
  Border lineBorder = BorderFactory.createLineBorder(Color.black);
  JRadioButton[] radio = new JRadioButton[3];
  JCheckBox[] check = new JCheckBox[2];
    
  BorderFrame() {
    lbl.setBounds(20, 20, 280, 40);
    lbl.setBorder(BorderFactory.createLineBorder(Color.red)); 
    lbl.setFont(new Font("�з���", Font.PLAIN, 24));
  	add(lbl);
    
    JPanel pane1 = new JPanel();
    pane1.setBounds(20, 80, 120, 110);
    pane1.setBorder(BorderFactory.createTitledBorder(lineBorder,"���"));
    pane1.setLayout(new BoxLayout(pane1, BoxLayout.Y_AXIS));
    add(pane1);
    radio[0] = new JRadioButton("�a��", true);
    radio[1] = new JRadioButton("�m��");
    radio[2] = new JRadioButton("�a�k"); 
    ButtonGroup group = new ButtonGroup();
    for(int i = 0; i < radio.length; i++) {
      radio[i].addActionListener(this);
      group.add(radio[i]);
      pane1.add(radio[i]);
    }  
  
    JPanel pane2 = new JPanel();
    pane2.setBounds(180, 80, 120, 110);
    pane2.setBorder(BorderFactory.createTitledBorder(lineBorder,"�˦�"));
    pane2.setLayout(new BoxLayout(pane2, BoxLayout.Y_AXIS));
    add(pane2);
    check[0] = new JCheckBox("����");
    check[1] = new JCheckBox("����");
    for(int j = 0; j < check.length; j++) {
      check[j].addActionListener(this);
      pane2.add(check[j]);
    }
     
    setTitle("���D�ج[");
    setLayout(null);
    setBounds(50, 50, 330, 240);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    if (radio[0].isSelected()) lbl.setHorizontalAlignment(JLabel.LEFT); 
    if (radio[1].isSelected()) lbl.setHorizontalAlignment(JLabel.CENTER); 
    if (radio[2].isSelected()) lbl.setHorizontalAlignment(JLabel.RIGHT);   
    int style = Font.PLAIN;
    if (check[0].isSelected()) style = Font.ITALIC;
    if (check[1].isSelected()) style = Font.BOLD;
    if (check[0].isSelected() & check[1].isSelected()) style = Font.BOLD + Font.ITALIC;
    lbl.setFont(new Font("�з���", style, 24)); 
  }
}

public class J10_6_3 {
  public static void main(String[] args) {
    BorderFrame frame = new BorderFrame();
  }
}
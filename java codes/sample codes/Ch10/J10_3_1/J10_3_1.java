import java.awt.*;
import javax.swing.*;

class CTextArea extends JFrame {
  CTextArea() {
    JLabel label = new JLabel("�ު��i�աG");
    String txt1 = "�z�n�A�ڬO�ޤp�̡I\n";
    String txt2 = "���~�O�ަ~�A���U��ަ~��j�B�C\n";
    String txt3 = "�ڦ��@�ӥ~���A�s�y�ѽ��j���ӡz�I�z�ݡA�ګӤ��өO�H";
    JTextArea txtPig = new JTextArea(txt1,4,12);
    txtPig.setBorder(BorderFactory.createLineBorder(Color.blue));
    txtPig.setLineWrap(true);
    txtPig.append(txt2);
    txtPig.append(txt3);
        
    JScrollPane scroll = new JScrollPane(txtPig,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
     
    JPanel pane = new JPanel();
    pane.add(label);
    pane.add(scroll);
   
    add(pane);
    setTitle("���ʮe��");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }	
}

public class J10_3_1 {
  public static void main(String[] args) {
    CTextArea frame = new CTextArea();
  }
}
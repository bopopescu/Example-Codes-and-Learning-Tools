import java.awt.*;
import javax.swing.*;

class CTextArea extends JFrame {
  CTextArea() {
    JLabel label = new JLabel("�ު��i�աG");
    String txt1 = "�z�n�A�ڬO�ޤp�̡I\n";
    String txt2 = "���~�O�ަ~�A���U��ަ~��j�B�C\n";
    String txt3 = "�ڦ��@�ӥ~���A�s�y�ѽ��j���ӡz�I�z�ݡA�ګӤ��өO�H";
    JTextArea txtPig = new JTextArea(txt1,5,18);
    txtPig.setBorder(BorderFactory.createLineBorder(Color.blue));
    txtPig.setLineWrap(true);
    txtPig.append(txt2);
    txtPig.append(txt3);
        
    JPanel pane =  new JPanel();
    pane.add(label);
    pane.add(txtPig);
   
    add(pane);
    setTitle("�h���r���");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }	
}

public class J10_2_1 {
  public static void main(String[] args) {
    CTextArea frame = new CTextArea();
  }
}
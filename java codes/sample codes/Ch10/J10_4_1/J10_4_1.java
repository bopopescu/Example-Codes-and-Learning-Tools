import java.awt.*;
import javax.swing.*;

class TabsFrame extends JFrame {
  TabsFrame() {
    JTabbedPane tabPane = new JTabbedPane();
    tabPane.setBounds(10, 10, 220, 180);
    add(tabPane);
    
    String tabTxt1 = "�i��";
    String txt = "�z�n�I�ڬO�ޤp�̡C\n���~�O�ަ~�A���U��ަ~��j�B�C\n�ڦ��@�ӥ~���A�s�y�ѽ��j���ӡz�C�z�ݡA�ګӤ��өO�H";
    JTextArea txtPig = new JTextArea(txt);
    txtPig.setLineWrap(true);
    tabPane.addTab(tabTxt1, txtPig);	
    
    String tabTxt2 = "�ɷ�";
    JLabel lblPig = new JLabel(new ImageIcon("pig.jpg"));
    tabPane.addTab(tabTxt2, lblPig);
   
    setTitle("����");
    setLayout(null);
    setBounds(50, 50, 250, 230);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }	
}

public class J10_4_1 {
  public static void main(String[] args) {
    TabsFrame frame = new TabsFrame();
  }
}
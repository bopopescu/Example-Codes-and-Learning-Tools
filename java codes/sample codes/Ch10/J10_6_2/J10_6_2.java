import javax.swing.*;

class CheckFrame extends JFrame {
  CheckFrame(){
    JPanel pane =  new JPanel();
    add(pane);
    
    JLabel lbl = new JLabel("�п�ܦr���˦��G");
    pane.add(lbl); 
    
    JCheckBox[] check = new JCheckBox[4];
    check[0] = new JCheckBox("�@��", true);
    check[1] = new JCheckBox("����");
    check[2] = new JCheckBox("����");
    check[3] = new JCheckBox("����+����");
    for(int i = 0; i < check.length; i++) 
      pane.add(check[i]);
  
    setTitle("JCheckBox");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }
}

public class J10_6_2 {
  public static void main(String[] args) {
    CheckFrame frame = new CheckFrame();
  }
}
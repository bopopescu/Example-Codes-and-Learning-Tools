import javax.swing.*;
import java.awt.*;


class RadioFrame extends JFrame{
  RadioFrame(){
    JPanel pane =  new JPanel();
    add(pane);
    
    JLabel lbl = new JLabel("�п�ܹ���覡�G");
    pane.add(lbl); 
    
    JRadioButton[] radio = new JRadioButton[3];
    radio[0] = new JRadioButton("�a��", true);
    radio[1] = new JRadioButton("�m��");
    radio[2] = new JRadioButton("�a�k"); 
    ButtonGroup group = new ButtonGroup();
    for(int i = 0; i < radio.length; i++) {
      group.add(radio[i]);
      pane.add(radio[i]);
    }  
  
    setTitle("JRadioButton");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }
}

public class J10_6_1 {
  public static void main(String[] args){
    RadioFrame frame = new RadioFrame();
  }
}
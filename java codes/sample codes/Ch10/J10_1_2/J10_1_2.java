import javax.swing.*;
import java.awt.*;

class CDraw extends JFrame {
  JPanel pane = new JPanel();        // pane��m���s
  String txtDraw[] = {"�e���u", "�e�x��", "�e���", "��߯x", "��߶�"}; 
  JButton btnDw[] = new JButton[txtDraw.length];    // ø�Ϥu����s
   
  CDraw(){
    // ��mø�Ϥu����s�e��
    pane.setBounds(300, 20, 120, 220);
    pane.setBackground(Color.pink);
    pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    add(pane);    
    for(int i = 0; i < txtDraw.length; i++) {
      btnDw[i] = new JButton(txtDraw[i]);
      pane.add(btnDw[i]);  
    }
       
    setTitle("ø�Ϥu��");
    setLayout(null);
    setBounds(50, 50, 450, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
}

public class J10_1_2 {
  public static void main(String[] args){
    CDraw frame = new CDraw();
  }
}
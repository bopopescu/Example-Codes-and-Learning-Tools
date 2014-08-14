import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class CFrame extends JFrame implements ActionListener {
  JButton btnShow, btnCls;
  JPanel pane = new JPanel();    
    
  CFrame() {
    pane.setBounds(0, 0, 140, 71);
    // ��pane�e���]�w����ؽu
    pane.setBorder(BorderFactory.createLineBorder(Color.red)); 
    add(pane);    
        
    btnShow = new JButton("�e�{");
    btnShow.addActionListener(this);
    btnShow.setBounds(25, 80, 70, 20);
    add(btnShow);
        
    btnCls = new JButton("�M��");
    btnCls.addActionListener(this);
    btnCls.setBounds(120, 80, 70, 20);
    add(btnCls);
           
    setTitle("JPanel�e��");
    setLayout(null); 
    setBounds(100, 100, 220, 150);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           
  }
    
  public void actionPerformed(ActionEvent e)  {
    Graphics g = pane.getGraphics();
    if (e.getSource() == btnShow) g.drawString("�μe�A���p�߼e!", 50, 70);
    if (e.getSource()== btnCls) pane.update(g);  // �M���e��
  }
}

public class J9_3_2 {
  public static void main(String[] args)  {
    CFrame frame = new CFrame();
  }
}

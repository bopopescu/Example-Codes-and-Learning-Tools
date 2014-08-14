import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class CGFrame extends JFrame implements ActionListener {
  JButton btnShow, btnCls;
  JPanel pane = new JPanel();    
    
  CGFrame() {
    pane.setBounds(15, 20, 190, 50);
    pane.setBackground(Color.yellow);  // �]�w�e���I����
    add(pane);    

    btnShow = new JButton("�e�{");
    Font f1 = new Font("�s�ө���" ,Font.BOLD, 16);
    btnShow.setFont(f1);
    Color rgb = new Color(255, 0, 0);
    btnShow.setForeground(rgb);        // �]�w����e����
    btnShow.addActionListener(this);
    btnShow.setBounds(20, 80, 80, 25);
    add(btnShow);
        
    btnCls = new JButton("�M��");
    btnCls.setFont(new Font("�s�ө���" ,Font.ITALIC, 16));
    btnCls.setBackground(new Color(255, 255, 0));   // �]�w����I���� 
    btnCls.addActionListener(this);
    btnCls.setBounds(120, 80, 80, 25);
    add(btnCls);
                
    setTitle("�r���C��]�w");
    setLayout(null);
    setBounds(100, 100, 230, 150);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           
  }
    
  public void actionPerformed(ActionEvent e) {
    Graphics g = pane.getGraphics();
    g.setFont(new Font("�з���", Font.ITALIC + Font.BOLD, 20));
    g.setColor(Color.red); 
    if (e.getSource() == btnShow) g.drawString("�j�a�Ӿ� Java2 !", 10, 30);
    if (e.getSource()== btnCls) pane.update(g);   // �M��pane�e��
  }
}

public class J9_4_1 {
  public static void main(String[] args)  {
    CGFrame frame = new CGFrame();
  }
}

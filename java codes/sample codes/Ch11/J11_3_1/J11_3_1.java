import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class cToolBar extends JFrame implements ActionListener {
  JPanel pane = new JPanel(); 
  JButton btn1 = new JButton();
  JButton btn2 = new JButton();
  JTextField txtFile = new JTextField("�T���C");
  
  cToolBar() {
  	JToolBar toolBar = new JToolBar("�u��C", JToolBar.HORIZONTAL);
    ImageIcon icon1 = new ImageIcon("open_file.png");
    ImageIcon icon2 = new ImageIcon("select_color.png");
    btn1 = new JButton(icon1);
    btn2 = new JButton(icon2);
    btn1.addActionListener(this);
    btn2.addActionListener(this); 
    btn1.setToolTipText("�}���ɮ׭���"); 
    btn2.setToolTipText("�}���C�⭱��");     
    toolBar.add(btn1);
    toolBar.add(btn2);
    pane.setLayout(new BorderLayout());
    pane.add(toolBar, BorderLayout.NORTH);
    txtFile.setBackground(Color.yellow);
    pane.add(txtFile ,BorderLayout.SOUTH);
       
    add(pane);
    setTitle("�u��C");
    setBounds(50, 50, 300, 150);   
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  } 

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btn1) {
      JFileChooser chooser = new JFileChooser(); 
      int press = chooser.showOpenDialog(null);      //�}���ɮ�
      if (press == JFileChooser.APPROVE_OPTION) 
        txtFile.setText("�ɮ׬�  " + chooser.getSelectedFile().getPath());
      else
        txtFile.setText("�����}���ɮ�");
    }
    
    if (e.getSource() == btn2) {
      JColorChooser chooser = new JColorChooser();
      Color color = chooser.showDialog(null, "�п�ܩ���", Color.gray);
      pane.setBackground(color); 
      txtFile.setText("�]�w�I����");
    }  
  }
}
 
public class J11_3_1 {
  public static void main(String[] args) {
    cToolBar myFrame = new cToolBar();
  }
}
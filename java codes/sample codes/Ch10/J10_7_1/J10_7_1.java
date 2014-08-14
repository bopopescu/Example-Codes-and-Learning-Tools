import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

class JListFrame extends JFrame implements ListSelectionListener {
  JLabel lblPlace = new JLabel("�~��a�ϡG");
  String[] items = {"�x�_", "���", "�s��", "�]��", "�x��", "����", "���L",
                    "�Ÿq", "�x�n", "����", "�̪F", "�Ὤ", "�x�F", "���"};
  JList list = new JList(items);
  JPanel pane = new JPanel();     
  JTextField texta = new JTextField();
 
  JListFrame() {
    pane.add(lblPlace);
    list.setVisibleRowCount(4);
    JScrollPane scroll = new JScrollPane(list);
    list.addListSelectionListener(this);
    pane.add(scroll);
    add(pane, BorderLayout.NORTH);
   
    add(texta, BorderLayout.SOUTH);      
    
    setTitle("�M��");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
    setBounds(50, 50, 200, 150);
    setVisible(true);   
  }

  public void valueChanged(ListSelectionEvent evt) {
    Object[] values = list.getSelectedValues();
    String text = lblPlace.getText();
    for(int i = 0; i < values.length; i++)
      text += values[i].toString();
    texta.setText(text);
  }
}

public class J10_7_1 {
  public static void main(String[] args) {
    JListFrame frame = new JListFrame();
  }
}
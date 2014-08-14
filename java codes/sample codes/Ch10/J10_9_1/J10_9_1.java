import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class InputFrame extends JFrame {
  // ����JLabel���Ҫ���A���O�m�W�B�~�֡B�ʧO�B����B�Ǿ��B�~��a��
  JLabel lblName, lblAge, lblSex, lblInter, lblAcad, lblPlace;
  JTextField text0 = new JTextField(10);   // �إ�JTextField����  
  String[] checkItem = {"�q��", "�ۺq", "�q�v", "ø��", "�ȹC"}; 
  JCheckBox[] check = new JCheckBox[checkItem.length];
  JTextArea texta = new JTextArea();
 
  InputFrame() {
  	JPanel pane = new JPanel(); 
    pane.setLayout(null); 
    pane.setBackground(Color.LIGHT_GRAY);
    add(pane);
    // JTextField��r��줸��  
    lblName = new JLabel("�m�W�G");
    lblName.setBounds(10, 10, 40, 20);
    pane.add(lblName);
    text0.setBounds(50, 10, 80, 20);
    text0.addActionListener(textfield);
    pane.add(text0);
    // JSpinner�Ʀ�ǦC����
    lblAge = new JLabel("�~�֡G");
    lblAge.setBounds(170, 10, 40, 20);
    pane.add(lblAge);
    JSpinner spin = new JSpinner(new SpinnerNumberModel(20, 1, 100, 1));
    spin.setBounds(210, 10, 80, 20); 
    spin.addChangeListener(spinner);
    pane.add(spin);
    // JRadioButton�ﶵ��s����  
    lblSex = new JLabel("�ʧO�G");
    lblSex.setBounds(10, 40, 40, 20);
    pane.add(lblSex);
    ButtonGroup group = new ButtonGroup();
    JRadioButton rb1 = new JRadioButton("�ӭ�", false); 
    rb1.setBounds(50, 40, 60, 20);
    JRadioButton rb2 = new JRadioButton("���k", false);
    rb2.setBounds(110, 40, 60, 20);
    rb1.setOpaque(false); rb2.setOpaque(false);    // �q�X����
    rb1.addActionListener(radio); rb2.addActionListener(radio);
    group.add(rb1); group.add(rb2);
    pane.add(rb1); pane.add(rb2);
    // JCheckBox�ֹ�������
    lblInter = new JLabel("����G");
    lblInter.setBounds(10, 70, 50, 20);
    pane.add(lblInter);
    for(int i = 0; i < check.length; i++) {
      check[i] = new JCheckBox(checkItem[i]);
      check[i].setBounds(50 + 60 * i, 70, 60, 20);
      check[i].setOpaque(false);
      check[i].addActionListener(checkbox);
      pane.add(check[i]);
    }
    // JComboBox�U�Ԧ��M�椸��
    lblAcad = new JLabel("�Ǿ��G");
    lblAcad.setBounds(10, 100, 50, 20);
    pane.add(lblAcad);
    String[] items_c = {"�դh", "�Ӥh", "�j��", "����", "�ꤤ", "��p"};
    JComboBox c_box = new JComboBox(items_c);
    c_box.setBounds(50, 100, 100, 20);
    c_box.addItemListener(cbo);
    pane.add(c_box);
    // JList�M�椸��
    lblPlace = new JLabel("�~��a�ϡG");
    lblPlace.setBounds(170, 100, 70, 20);
    pane.add(lblPlace); 
    String[] items_p = {"�x�_", "���", "�s��", "�]��", "�x��", "����", "���L",
                    "�Ÿq", "�x�n", "����", "�̪F", "�Ὤ", "�x�F", "���"};
    JList list = new JList(items_p);
    list.setVisibleRowCount(4);
    list.addListSelectionListener(list_p);
    JScrollPane scroll = new JScrollPane(list);
    scroll.setBounds(240, 100, 80, 80); 
    pane.add(scroll);
    // JTextArea��r�ϰ줸��
    texta.setBounds(10, 190, 330, 40);
    texta.setEditable(false);
    pane.add(texta);
   
    setTitle("��J�����X����");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
    setBounds(50, 50, 360, 280);
    setVisible(true);   
  }

  // JTextField�ƥ��ť
  public ActionListener textfield = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      texta.setText(lblName.getText() + text0.getText());
    }
  };
  // JSpinner�ƥ��ť
  public ChangeListener spinner = new ChangeListener() {
    public void stateChanged(ChangeEvent e) {
      texta.setText(lblName.getText() + text0.getText()+ '\n'
                  + lblAge.getText() + ((JSpinner)e.getSource()).getValue());
    }
  };
  // JRadioButton�ƥ��ť
  public ActionListener radio = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      texta.setText(lblName.getText() + text0.getText()+ '\n'
                  + lblSex.getText() + ((JRadioButton)e.getSource()).getText());
    }
  };
  // JCheckBox�ƥ��ť
  public ActionListener checkbox = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      String txt = lblInter.getText();
      for (int i = 0; i < check.length; i++) 
      	if(check[i].isSelected()) txt += check[i].getText();
      texta.setText(lblName.getText() + text0.getText()+ '\n' + txt);
    }
  };
  // JComboBox�ƥ��ť
  public ItemListener cbo = new ItemListener() {
    public void itemStateChanged(ItemEvent e) {
      texta.setText(lblName.getText() + text0.getText()+ '\n' 
                 + lblAcad.getText() + e.getItem());
    }
  }; 
  // JList�ƥ��ť
  public ListSelectionListener list_p = new ListSelectionListener() {
    public void valueChanged(ListSelectionEvent e) {
      Object[] values = ((JList)e.getSource()).getSelectedValues();
      String txtItem = "";
      for(int i = 0; i < values.length; i++)
        txtItem += values[i].toString();
      texta.setText(lblName.getText() + text0.getText()+ '\n'
                  + lblPlace.getText() + txtItem);
    }
  };
}

public class J10_9_1 {
  public static void main(String[] args) {
    InputFrame frame = new InputFrame();
  }
}
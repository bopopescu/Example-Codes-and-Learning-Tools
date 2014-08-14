import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;

class mysqlFrame extends JFrame {
  Connection connection;
  Statement statement;
  ResultSet result;
  String id_get, password_get, dateNow, comm_data;
  int act;       // �O��user���s�A1 = ���U�A 2 = �n�J
  JLabel itemq1, itemq2;
  JTextField id;
  JPasswordField password;
  JButton qb11, qb12;
  JButton qb31, qb32, qb33;
  JPanel panel1, panel2;
  JLabel item7, item8;
   
  Container cont;   
  JLabel item1, item2, item3, item4, item5, item6;
  ButtonGroup btn_group;
  JCheckBox cb1, cb2, cb3, cb4, cb5;
  JRadioButton rb1, rb2;
  JComboBox c_box;
  JTextField text0;
  JSpinner spin;
  JList list;
  String[] c_label = { "�դh", "�Ӥh", "�j��", "����", "�ꤤ", "��p" };
  String[] l_label = { "�x�_", "���", "�s��", "�]��", "�x��", "����", "���L",
                      "�Ÿq", "�x�n", "����", "�̪F", "�Ὤ", "�x�F", "���" };
   	                    
  public mysqlFrame() {
    // ��Ʈw�e�m�@�~
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (Exception e) {
      errorMessage("MySQL�X�ʵ{���w�˥��ѡI");
    }
    try {
      connection = DriverManager.getConnection("jdbc:mysql://localhost/members"
                   +"?user=root&password=mysql");
      statement = connection.createStatement();
    } catch (SQLException e) { errorMessage("MySQL�L�k�ҰʡI");
    } catch (Exception e) { errorMessage("�o�ͨ�L���~�I");
    }
    // �ϥΪ̤����إ�
    cont = getContentPane();  //���o�e��
    // ���o����r��ѥ[�J����ϥ�
    Date date = new Date();
    dateNow = DateFormat.getDateInstance().format(date);
    // �t�m�b���K�X��J���ε��U�B�n�J���s�P�ƥ��ť
    panel1 = new JPanel();  panel1.setBounds( 0, 0, 500, 40);
    panel1.setBackground(Color.LIGHT_GRAY);
    itemq1 = new JLabel("�b���G");
    itemq2 = new JLabel("�K�X�G");
    id = new JTextField(10);
    password = new JPasswordField(10);
    qb11 = new JButton("���U");
    qb12 = new JButton("�n�J");
    qb11.addActionListener(check);
    qb12.addActionListener(check);
    panel1.add(itemq1);  panel1.add(id);
    panel1.add(itemq2);  panel1.add(password);  
    panel1.add(qb11);  panel1.add(qb12); 
    cont.add(panel1);
    // �s�W�����t�m�ӤH��ƶ��ؤΤ��e
    panel2 = new JPanel();  panel2.setBounds( 0, 40, 500, 200); 
    panel2.setLayout(null);
    item1 = new JLabel("�m�W�G"); item1.setBounds( 40, 60, 40, 20);
    item2 = new JLabel("�~�֡G"); item2.setBounds(200, 60, 40, 20);
    item3 = new JLabel("�ʧO�G"); item3.setBounds( 40, 80, 40, 20);
    item4 = new JLabel("����G"); item4.setBounds( 40, 100, 50, 20);
    item5 = new JLabel("�Ǿ��G"); item5.setBounds( 40, 130, 50, 20);
    item6 = new JLabel("�~��a�ϡG"); item6.setBounds(200, 130, 70, 20);
    item7 = new JLabel("�[�J����G"); item7.setBounds(40, 230, 70, 20);
    item8 = new JLabel(); item8.setBounds(110, 230, 80, 20);
    panel2.add(item1);  panel2.add(item2);  panel2.add(item3);
    panel2.add(item4);  panel2.add(item5);  panel2.add(item6);
    panel2.add(item7);  panel2.add(item8);
    // �m�W
    text0 = new JTextField(10);  text0.setBounds(80,  60, 80, 20);
    panel2.add(text0);
    // �~��
    spin = new JSpinner(new SpinnerNumberModel(20, 1, 100, 1));
    spin.setBounds(240, 60, 80, 20);
    panel2.add(spin);
    // �ʧO
    btn_group = new ButtonGroup();
    rb1 = new JRadioButton("�ӭ�", false); rb1.setBounds( 80, 80, 60, 20);
    rb2 = new JRadioButton("���k", false); rb2.setBounds(140, 80, 60, 20);
    btn_group.add(rb1); btn_group.add(rb2);
    panel2.add(rb1); panel2.add(rb2);
    // ����
    cb1 = new JCheckBox("�q��"); cb1.setBounds( 80, 100, 60, 20);
    cb2 = new JCheckBox("�ۺq"); cb2.setBounds(140, 100, 60, 20);
    cb3 = new JCheckBox("�q�v"); cb3.setBounds(200, 100, 60, 20);
    cb4 = new JCheckBox("ø��"); cb4.setBounds(260, 100, 60, 20);
    cb5 = new JCheckBox("�ȹC"); cb5.setBounds(320, 100, 60, 20);
    panel2.add(cb1); panel2.add(cb2); panel2.add(cb3); panel2.add(cb4); panel2.add(cb5);
    // �Ǿ�
    c_box = new JComboBox(c_label);
    c_box.setBounds(80, 130, 100, 20);
    panel2.add(c_box);
    // �~��a��
    list = new JList(l_label);
    JScrollPane s_pane = new JScrollPane(list);
    s_pane.setBounds(270, 130, 80, 80); 
    panel2.add(s_pane);
    // �s�W�U��u�@���s
    qb31 = new JButton("����");  qb31.setBounds( 270, 230, 60, 30);
    qb32 = new JButton("�T�{");  qb32.setBounds( 340, 230, 60, 30);
    qb33 = new JButton("�R��");  qb33.setBounds( 410, 230, 60, 30);
    qb31.addActionListener(reset);
    qb32.addActionListener(submit);
    qb33.addActionListener(delete);
    panel2.add(qb31);  panel2.add(qb32);  panel2.add(qb33);
    cont.add(panel2);
    panel2.setVisible(false);  // �����U�εn�J�e�N�ӤH��ƭ�������

    this.setTitle("�|�����U�n�J�t��");
    this.setSize(500,300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.show();
  }
  
  // ���U�B�n�J���s�ƥ��ť
  public ActionListener check = new ActionListener() {
    public void actionPerformed(ActionEvent a) {
      id_get = id.getText().trim();
      password_get = password.getText().trim();
      // �p�G�b���αK�X���ťիh���B�z
      if (id_get.equals("") | password_get.equals("")) return;
      // �p�G�b���αK�X���W�L10�r�h���ĵ�i�ä��A�B�z
   	  if (id_get.length() >10 | password_get.length() >10) {
   	    warnMessage("�b���αK�X�̦h10�r�I");
   	    return;
   	  }
   	  try {
   	    // �ھڿ�J���b���K�X�d�߸�Ʈw
   	    String comm_data = "SELECT * FROM personal_data WHERE " + 
   	  	       "acc_id='" + id_get + "'";
   	    statement = connection.createStatement(); 
   	    result = statement.executeQuery(comm_data);
   	    if (result.next()) {
   	      // ���ŦX������
   	      if (a.getSource() == qb11) {
   	        // �p�Guser���s�O���U�A�����~��B�z
            warnMessage("�b�����ơI");
            return;
          } else {
            if (password_get.equals(result.getString("password").trim())) {
              // �p�Guser���s�O�n�J�B�K�X���T�A�i�d�ߡB�󥿡B�R�����
              act = 2;   // �n�J�N��
              initialProcess();  // ������ȳB�z
              // ���o��Ʈw���s�ɰO��
              item8.setText(result.getString("date_join")); // �[�J���
   	          text0.setText(result.getString("name"));      // �m�W
   	          spin.setValue(result.getInt("age"));          // �~��
   	          int msex = result.getInt("sex");              // �ʧO, 1=�k, 2=�k
   	          if (msex == 1) rb1.setSelected(true);
   	          else rb2.setSelected(true);
              cb1.setSelected(result.getBoolean("habbit1"));      // ����1
              cb2.setSelected(result.getBoolean("habbit2"));      // ����2
              cb3.setSelected(result.getBoolean("habbit3"));      // ����3
              cb4.setSelected(result.getBoolean("habbit4"));      // ����4
              cb5.setSelected(result.getBoolean("habbit5"));      // ����5
   	          c_box.setSelectedIndex(result.getInt("education")); // �Ǿ��N��0-5
   	          list.setSelectedIndex(result.getInt("home"));       // �~��a�ϥN��0-13               	  	
            } else {
               warnMessage("�K�X���~�I");
               return;
            }
          }
        } else {
          // �䤣��ŦX������
   	      if ( a.getSource() == qb11 ) {
   	        // �p�Guser���s�O���U�A�i�d�ߡB�󥿸��
   	        act = 1;  // ���U�N��
   	        initialProcess();  // ������ȳB�z
            item8.setText(dateNow); // �[�J����Ѩt�δ���
            // �ӤH��Ƥ��e��ȥѨt�δ���
            text0.setText("");
            spin.setValue(20);
   	        rb1.setSelected(false);  rb2.setSelected(false);
   	        cb1.setSelected(false);  cb2.setSelected(false);
   	        cb3.setSelected(false);  cb4.setSelected(false);
   	        cb5.setSelected(false);
   	        c_box.setSelectedIndex(0);
   	        list.setSelectedIndex(0);
          } else {
            // �p�Guser���s�O�n�J�A�����~��B�z
            warnMessage("�b���αK�X���~�I");
            return;
          }
        }
      } catch (Exception e) {
         errorMessage("��Ʈw�o�Ϳ��~�I"); 
      }
    }
  };
  
  // ������ȳB�z
  public void initialProcess() {
    // �W���J�����L�@��
    id.setEnabled(false);
    password.setEnabled(false);
    qb11.setEnabled(false);
    qb12.setEnabled(false); 
    // ��ܤU��ӤH��ƭ���     
    panel2.setVisible(true);  
    if (act == 1) qb33.setEnabled(false);  // �p�G���U�h�R���s�L�@��
  }
  
  // ���������B�z
  public void endProcess() {
    // ��_�W���J�����@��
    id.setEnabled(true);        id.setText("");
    password.setEnabled(true);  password.setText("");
    panel2.setVisible(false);
    qb11.setEnabled(true);
    qb12.setEnabled(true);
    qb33.setEnabled(true);  // ��_�R���s�@��
    return;
  }
  
  // �������s�ƥ��ť
  public ActionListener reset = new ActionListener() {
    public void actionPerformed(ActionEvent a) {
      endProcess();  // �L����ʧ@�A�������������B�z
    }
  };
  
  // �T�{���s�ƥ��ť
  public ActionListener submit = new ActionListener() {
    public void actionPerformed(ActionEvent a) {
      int msex;
      boolean mh1, mh2, mh3, mh4, mh5;
      // ���o�ʧO����sboolean���ର�Ʀr�N��
      if (rb1.isSelected()) msex = 1;
      else msex = 2;
      // ���o����ƿ��sboolean��
      mh1 = cb1.isSelected();
      mh2 = cb2.isSelected();
      mh3 = cb3.isSelected();
      mh4 = cb4.isSelected();
      mh5 = cb5.isSelected();
      if (act == 1) {
        // ���U���p�G��Ʈw�����J�s��Ƶ���
      	comm_data = "INSERT INTO personal_data(" +
                "acc_id,password,date_join,name,sex,age," +
                "habbit1,habbit2,habbit3,habbit4,habbit5,education,home) VALUES('" +
                id_get + "', '" + password_get + "', '" + dateNow + "', '" + 
                text0.getText().trim()+ "', " + msex + ", " + spin.getValue() + ", " +
                mh1 + ", " + mh2 + ", " + mh3 + ", " + mh4 + ", " + mh5 + ", " +
                c_box.getSelectedIndex() + ", " + list.getSelectedIndex() + ")";
      } else {
        // �n�J���p�G��Ʈw����s��Ƥ��e
        comm_data = "UPDATE personal_data SET name='" + text0.getText().trim()+
                "', sex=" + msex + ", age=" + spin.getValue() + 
                ", habbit1=" + mh1 + ", habbit2=" + mh2 + ", habbit3=" + mh3 +
                ", habbit4=" + mh4 + ", habbit5=" + mh5 +
                ", education=" + c_box.getSelectedIndex() +
                ", home=" + list.getSelectedIndex() +
                " WHERE acc_id='" + id_get + "'AND password='" + password_get + "'";
      }
      try {	
	    statement = connection.createStatement(); 
	    statement.execute(comm_data);
	    if (act == 1) correctMessage("�s�|�����U���\�I");
	    else correctMessage("��Ƨ󥿦��\�I");
   	    endProcess();  // ���������B�z
      } catch(SQLException sqlex){
        errorMessage("��Ʈw�o�Ϳ��~�I");
      }
    }
  };
  
  // �R�����s�ƥ��ť
  public ActionListener delete = new ActionListener() {
    public void actionPerformed(ActionEvent a) {
      // �R����ƻݦA�T�{�@��
      JOptionPane option = new JOptionPane("�нT�{�R���Ψ����I",
      JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
      JDialog dialog = option.createDialog(null, "�R���T�{");
      dialog.show();
      Object ans = option.getValue();
      if ( ans !=null ) {
        if ( (Integer) ans == 0 ) {
      	  // ��Ʈw�R���ӵ����
          comm_data = "DELETE FROM personal_data WHERE " + 
   	            "acc_id='" + id_get + "'AND password='" + password_get + "'";
          try {	
	       statement = connection.createStatement(); 
	       statement.execute(comm_data);
	       correctMessage("��ƧR�����\�I");
   	       endProcess();
	      } catch(SQLException sqlex) {
	        errorMessage("��Ʈw�o�Ϳ��~�I");
          }
      	}
      }
    }
  };
  
  // ��Ʈw���ʦ��\��ܭ���
  public void correctMessage(String msg) {
    String message = msg;
    JOptionPane.showMessageDialog(null, message, "��Ʈw����",     JOptionPane.INFORMATION_MESSAGE);
  } 
  
  // ��Ʈw�Y�����~��ܭ���
  public void errorMessage(String msg) {
    String message = msg;
    JOptionPane.showMessageDialog(null, message, "�Y�����~", JOptionPane.ERROR_MESSAGE);
    System.exit(0);
  }
  
  // ��Ʈw���~�T����ܭ���
  public void warnMessage(String msg) {
    String message = msg;
    JOptionPane.showMessageDialog(null, message, "���~�T��", JOptionPane.ERROR_MESSAGE);
    id.setText("");
    password.setText("");
  } 
}

public class J15_5_1 {
  public static void main(String[] args) {
    mysqlFrame frame = new mysqlFrame();
  }
}

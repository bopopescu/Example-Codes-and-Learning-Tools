import java.awt.*;
import javax.swing.*;
import java.sql.*;           // �B�J1

class mysqlFrame {
  Connection connection;
  Statement statement;	  
  
  public  mysqlFrame() {
    try {
      Class.forName("com.mysql.jdbc.Driver");    // �B�J2
    } catch (Exception e) {
      errorMessage("MySQL�X�ʵ{���w�˥��ѡI");
    }
  	// �R����Ʈw
    try {
 	  connection = DriverManager.getConnection("jdbc:mysql://localhost/members1"
                  +"?user=root&password=mysql");     // �B�J3
      statement = connection.createStatement();      // �B�J4
      String deleteDB = "DROP DATABASE members1";    // �B�J6
      statement.execute(deleteDB);             // �B�J6
      JOptionPane.showMessageDialog(null, "��Ʈw�R�����\�I");
      statement.close();                        // �B�J12
      System.exit(0);
    } catch (SQLException e) {
      if ( statement == null ) errorMessage("��Ʈw���s�b�I");
      else errorMessage("MySQL�L�k�ҰʡI");
    } catch (Exception e) {
      errorMessage("�o�ͨ�L���~�I");
    }
  }

  public void errorMessage(String msg) {
    String message = msg;
    JOptionPane.showMessageDialog(null, message, "���~�T��",
                       JOptionPane.ERROR_MESSAGE);
    System.exit(0);
  } 
}

public class J15_3_2 {
  public static void main(String[] args) {
    mysqlFrame frame = new mysqlFrame();
  }
}

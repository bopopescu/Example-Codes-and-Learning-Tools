import java.awt.*;
import javax.swing.*;
import java.sql.*;                // �B�J1

class mysqlFrame {
  Connection connection;
  Statement statement;	  
 
  public mysqlFrame() {
    try {
      Class.forName("com.mysql.jdbc.Driver");         // �B�J2
    } catch (Exception e) {
      errorMessage("MySQL�X�ʵ{���w�˥��ѡI");
    }
  	// �إ߸�Ʈw
    try {
 	  connection = DriverManager.getConnection("jdbc:mysql://localhost/"
                   +"?user=root&password=mysql");     // �B�J3
      statement = connection.createStatement();       // �B�J4  
      String createDB = "CREATE DATABASE members1";   // �B�J5
      statement.executeUpdate(createDB);              // �B�J5
      JOptionPane.showMessageDialog(null, "��Ʈw�إߦ��\�I");
      statement.close();                              // �B�J12
      System.exit(0);
    } catch (SQLException e) {
      if ( statement != null ) errorMessage("��Ʈw�w�s�b�I");
      else errorMessage("MySQL�L�k�ҰʡI");
    } catch (Exception e) {
      errorMessage("�o�ͨ�L���~�I");
    }
  }

  public void errorMessage(String msg) {
    String message = msg;
    JOptionPane.showMessageDialog(null, message, "���~�T��", JOptionPane.ERROR_MESSAGE);
    System.exit(0);
  } 
}

public class J15_3_1 {
  public static void main(String[] args) {
    mysqlFrame frame = new mysqlFrame();
  }
}
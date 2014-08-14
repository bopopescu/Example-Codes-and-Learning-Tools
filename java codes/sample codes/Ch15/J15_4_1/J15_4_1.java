import java.awt.*;
import javax.swing.*;
import java.sql.*;        // �B�J1

class mysqlFrame {
  Connection connection;
  Statement statement;	  
  
  public mysqlFrame() {
    try {
      Class.forName("com.mysql.jdbc.Driver");    // �B�J2
    } catch (Exception e) {
      errorMessage("MySQL�X�ʵ{���w�˥��ѡI");
    }  
    
  	// �إ߸�Ʈw
    try {
 	  connection = DriverManager.getConnection("jdbc:mysql://localhost/"
                  +"?user=root&password=mysql");         // �B�J3
      statement = connection.createStatement();          // �B�J4 
      String createDB = "CREATE DATABASE members";       // �B�J5
      statement.executeUpdate(createDB);                 // �B�J5
    } catch (SQLException e) {
      if ( statement != null ) errorMessage("��Ʈw�w�s�b�I");
      else errorMessage("MySQL�L�k�ҰʡI");
    } catch (Exception e) {
      errorMessage("�o�ͨ�L���~�I");
    }
    // �إ߸�ƪ�
    try {
 	  connection = DriverManager.getConnection("jdbc:mysql://localhost/members"
                   +"?user=root&password=mysql");          // �B�J3
      statement = connection.createStatement();          // �B�J4 
      String createTB = "CREATE TABLE personal_data(";
      createTB += "acc_id     VARCHAR(10) PRIMARY KEY, ";  //�b��
      createTB += "password   VARCHAR(10), ";              //�K�X
      createTB += "date_join  DATE, ";                     //���U���
      createTB += "name       VARCHAR(10), ";              //�m�W
      createTB += "sex        TINYINT, ";                  //�ʧO
      createTB += "age        TINYINT, ";                  //�~��
      createTB += "habbit1    BOOL, ";                     //����1
      createTB += "habbit2    BOOL, ";                     //����2
      createTB += "habbit3    BOOL, ";                     //����3
      createTB += "habbit4    BOOL, ";                     //����4
      createTB += "habbit5    BOOL, ";                     //����5
      createTB += "education  TINYINT, ";                  //�Ǿ�
      createTB += "home       TINYINT)";                   //�~��a��
      statement.executeUpdate(createTB);
      JOptionPane.showMessageDialog(null, "��Ʈw�M��ƪ�إߦ��\�I");
      statement.close();                                // �B�J12
      System.exit(0);
    } catch (SQLException e) {
      if (statement != null ) errorMessage("��ƪ�w�s�b�I");
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

public class J15_4_1 {
  public static void main(String[] args) {
    mysqlFrame frame = new mysqlFrame();
  }
}

  
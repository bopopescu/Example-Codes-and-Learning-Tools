<%@ page contentType="text/html;charset=big5" %>
<%@ page import= "java.sql.*" %>
<html>
<head><title>Ex80_2</title></head><body>
<p align="center">
<font size="5"><b>Sub Page of Ex80_2</b></font>
</p>
<%
  String JDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
  String connectDB="jdbc:odbc:cloudDB09";

  Class.forName(JDriver);
  Connection con = DriverManager.getConnection(connectDB);
  Statement stmt = con.createStatement();

  request.setCharacterEncoding("big5");
  String Number_2 = request.getParameter("number_1");
  String Name_2 = request.getParameter("name_1");
  String Pwd_2 = request.getParameter("pwd_1");
  String Address_2 = request.getParameter("address_1");

  String sql="UPDATE UserList SET " +
                       "�����Ҧr��='" + Number_2 +
                       "', �ϥΪ̱b��='" + Name_2 +
                       "', �ϥΪ̱K�X='" + Pwd_2 +
                       "', �ϥΪ̦a�}='" + Address_2 +
                       "' WHERE �����Ҧr��='" + Number_2 + "';" ;
                      
  if(stmt.executeUpdate(sql) == 1)
      out.print("���\�ק���!!");
  else
      out.print("�ק��ƥ���!!");

  stmt.close();
  con.close();
%>
</body>
</html>
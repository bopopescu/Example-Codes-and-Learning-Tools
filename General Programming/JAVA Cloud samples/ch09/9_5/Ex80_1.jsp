<%@ page contentType="text/html;charset=big5" %>
<%@ page import= "java.sql.*" %>
<html>
<head><title>Ex80_1</title></head><body>
<p align="center">
<font size="5"><b>Sub Page of Ex80_1</b></font>
</p><p align="left">
<B>�ק�U�C���(��J�s����л\�¸��)</B></p>
<%
  String JDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
  String connectDB="jdbc:odbc:cloudDB09";

  Class.forName(JDriver);
  Connection con = DriverManager.getConnection(connectDB);
  Statement stmt = con.createStatement();

  request.setCharacterEncoding("big5");
  String Name = request.getParameter("name");
  String Pwd = request.getParameter("pwd");

 String sql="SELECT *  FROM UserList WHERE �ϥΪ̱b��='" +
                    Name + "'AND �ϥΪ̱K�X='" + Pwd + "';";

  ResultSet rs= stmt.executeQuery(sql);
  ResultSetMetaData rsmd = rs.getMetaData();
  int colCount = rsmd.getColumnCount();

  rs.next();
  out.print("<FORM ACTION=Ex80_2.jsp " +
                  "METHOD=post>");
  out.print("�����Ҧr���G<INPUT TYPE=text NAME= number_1 " +
                             "VALUE=" + rs.getString("�����Ҧr��") + ">(���y�ק�)<BR>");
  out.print("�ϥΪ̱b���G<INPUT TYPE=text NAME= name_1 " +
                             "VALUE=" + Name + "><BR>");
  out.print("�ϥΪ̱K�X�G<INPUT TYPE=text NAME= pwd_1 " +
                             "VALUE=" + Pwd + "><BR>");
  out.print("�ϥΪ̦a�}�G<INPUT TYPE=text NAME= address_1 " +
                             "VALUE=" + rs.getString("�ϥΪ̦a�}") + "> <BR><BR>");
  out.print("<INPUT TYPE=submit VALUE=\"���e\">");
  out.print("<INPUT TYPE=reset VALUE=\"����\">");

  stmt.close();
  con.close();
%>
</body>
</html>
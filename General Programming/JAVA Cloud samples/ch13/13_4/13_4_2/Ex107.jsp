<%@ page contentType="text/html;charset=big5" %>
<%@ page import= "java.sql.*" %>
<%@ page import= "java.io.*" %>
<html>
<head><title>Ex107</title></head><body>
<p align="left">
<font size="5"><b>Sub Page of Ex107 Ū����Ʈw�S�w�d��</b></font>
</p><HR>
<%
  String JDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
  String connectDB="jdbc:odbc:cloudDB13";

  Class.forName(JDriver);
  Connection con = DriverManager.getConnection(connectDB);
  Statement stmt = con.createStatement();

  request.setCharacterEncoding("big5");
  String MsgName = request.getParameter("msgName");
  String sql="SELECT * FROM boardMessage WHERE �W��= '" +
                   MsgName + "';" ;

  if (stmt.execute(sql))   {
      ResultSet rs = stmt.getResultSet();
      while (rs.next()) {
          %>
          �ɶ��G<%= rs.getString("�ɶ�")%><BR>
          �W�١G<%= rs.getString("�W��")%><BR>
          �H�c�G<%= rs.getString("�H�c")%><BR>
          �d���G<BR>
           <%= rs.getString("�d��")%><BR><HR>
           <%
        }
  }
  stmt.close();
  con.close();
%>
</body>
</html>
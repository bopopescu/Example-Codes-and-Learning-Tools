<%@ page contentType= "text/html;charset=big5" %>
<%@ page import= "java.sql.*" %>
<html>
<head><title>Ex82</title></head><body>
<p align="center">
<font size="5"><b>Sub Page of Ex82</b></font>
</p><p align="left">
<%
  session = request.getSession();
  session.setAttribute("ex82", "true");

  String JDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
  String connectDB="jdbc:odbc:cloudDB09";

  Class.forName(JDriver);
  Connection con = DriverManager.getConnection(connectDB);
  Statement stmt = con.createStatement();

  request.setCharacterEncoding("big5");
  String Name = request.getParameter("name");
  String Pwd = request.getParameter("pwd");

  String sql="SELECT * FROM UserList WHERE �ϥΪ̱b��='" +
                    Name + "'AND �ϥΪ̱K�X='" + Pwd + "';";

  ResultSet rs= stmt.executeQuery(sql);
  boolean flag= false;
  while(rs.next()) flag= true;
  if(flag){
      out.print("�b���K�X�L�~");
      out.print("<FORM METHOD=post ACTION=Ex82_2.jsp>");
      out.print("<INPUT TYPE=\"submit\" VALUE=\"go to Sub Page\">");
  }
  else {
      out.print("<p><A HREF=Ex82.html TARGET=");
      out.print("'_top'");
      out.print(">�b���K�X���~!! �Ы����^����</A></p>");
   }

  stmt.close();
  con.close();
%>
</body>
</html>
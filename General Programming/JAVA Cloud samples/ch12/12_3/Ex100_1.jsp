<%@ page contentType= "text/html;charset=big5" %>
<%@ page import= "java.sql.*" %>
<html>
<head><title>Ex100_1</title></head><body>
<p align="center">
<font size="5"><b>Sub Page of Ex100_1 ���b���K�X</b></font>
</p><p align="left">
<%
  session = request.getSession();
  session.setAttribute("ex100", "true");

  String JDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
  String connectDB="jdbc:odbc:cloudDB12";

  Class.forName(JDriver);
  Connection con = DriverManager.getConnection(connectDB);
  Statement stmt = con.createStatement();

  request.setCharacterEncoding("big5");
  String Name = request.getParameter("name");
  String Pwd = request.getParameter("pwd");

  String sql="SELECT * FROM userList WHERE �ϥΪ̱b��='" +
                    Name + "'AND �ϥΪ̱K�X='" + Pwd + "';";

  ResultSet rs= stmt.executeQuery(sql);
  boolean flag= false;
  while(rs.next()) flag= true;
  if(flag){
      out.print("�b���K�X�L�~");
      out.print("<FORM METHOD=post ACTION=Ex100_2.jsp>");
      out.print("<INPUT TYPE=\"submit\" VALUE=\"�~��\">");
  }
  else {
      out.print("<p><A HREF=Ex100.html TARGET=");
      out.print("'_top'");
      out.print(">�b���K�X���~!! �Ы����^����</A></p>");
   }

  stmt.close();
  con.close();
%>
</body>
</html>
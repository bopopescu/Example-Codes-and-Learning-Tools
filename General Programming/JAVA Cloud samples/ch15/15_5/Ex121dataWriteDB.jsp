<%@ page contentType="text/html;charset=big5" %>
<%@ page import= "java.sql.*, java.util.Date" %>
<html>
<head><title>dataWriteDB</title></head><body>
<%
  String JDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
  String connectDB="jdbc:odbc:cloudDB15";

  Class.forName(JDriver);
  Connection con = DriverManager.getConnection(connectDB);
  Statement stmt = con.createStatement();

  request.setCharacterEncoding("big5");
  String chatStr = request.getParameter("chatData");

  session= request.getSession();
  String nameStr= session.getAttribute("loginName").toString();

  if(chatStr=="")  {
     out.print("��ƶ�g������");
     %><br>
     <a href= "01chatPage.jsp" target= "_top">�������s��J</a>
     <%
  } 
  else  {
     String sql= "INSERT INTO chatINFO(�W��, �T��)" +
                      "VALUES('" + nameStr + "','" + chatStr + "')";
                      
    stmt.executeUpdate(sql);
  }
   stmt.close();
   con.close();

  out.print("<FORM ACTION=Ex121dataWriteDB.jsp " +
                  "METHOD=post>");
  out.print("<TEXTAREA NAME= chatData  ROWS=3 COLS=45></TEXTAREA>");
  out.print("<INPUT TYPE=submit VALUE=\"��J�T��\">");
 %>
</body>
</html>
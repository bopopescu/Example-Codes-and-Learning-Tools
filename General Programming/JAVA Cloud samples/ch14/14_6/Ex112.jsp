<%@ page contentType="text/html;charset=big5" %>
<%@ page import= "java.sql.*, java.util.Date" %>
<html>
<head><title>Ex112</title></head><body>
<p align="center">
<font size="5"><b>Sub Page of Ex112 �g�J��Ʈw</b></font>
</p>
<%
  String JDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
  String connectDB="jdbc:odbc:cloudDB14";

  Class.forName(JDriver);
  Connection con = DriverManager.getConnection(connectDB);
  Statement stmt = con.createStatement();

  request.setCharacterEncoding("big5");
  String MsgName = request.getParameter("msgName");
  String EMail = request.getParameter("eMail");
  String Data = request.getParameter("data");

  if(MsgName=="" || EMail=="" || Data=="")  {
     out.print("��ƶ�g������");
     %><br>
     <a href= "Ex112.html" target= "_top">�����^����</a>
     <%
  } 
  else {
     Date msgDate= new Date();
     String dateStr= msgDate.toLocaleString();

     String sql="INSERT INTO articleINFO(�ɶ�, �W��," +
                       "�H�c, �峹) VALUES ('" +
                        dateStr + "','" + MsgName + "','" +
                        EMail + "','" + Data + "')" ;
                      
    stmt.executeUpdate(sql);
    out.print("���\�����峹�g�J��Ʈw");

     stmt.close();
     con.close();
  }
   %>
</body>
</html>
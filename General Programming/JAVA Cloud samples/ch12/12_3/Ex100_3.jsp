<%@ page contentType="text/html;charset=big5" %>
<%@ page import= "java.sql.*, java.util.Date" %>
<html>
<head><title>Ex100_3</title></head><body>
<p align="center">
<font size="5"><b>Sub Page of Ex100_3 �T���g�J��Ʈw</b></font>
</p>
<%
  String JDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
  String connectDB="jdbc:odbc:cloudDB12";

  Class.forName(JDriver);
  Connection con = DriverManager.getConnection(connectDB);
  Statement stmt = con.createStatement();

  request.setCharacterEncoding("big5");
  String dataStr = request.getParameter("data");

  Date T = new Date();
  String timeStr= T.toLocaleString();

  int year = (T.getYear() + 1900);
  int month = T.getMonth() + 1;
  int date = T.getDate();
  int hours = T.getHours();
  int minutes = T.getMinutes();
  int seconds = T.getSeconds();

  String timeKey= String.format("%02d:%02d:%02d:%02d:%02d:%02d", year, month, date, hours, minutes, seconds);
                         
  String sql="INSERT INTO instantNews(�ɶ�����, �ɶ�, �T��)" +
                "VALUES ('" + timeKey + "','" + timeStr + "','" + dataStr  + "')" ;

  session = request.getSession();
  if(session.getAttribute("ex100") == "true") {
       out.print("���������X�k�{�Һ���" + "<br>");
       out.print(timeStr);
       stmt.executeUpdate(sql);
       out.print("���\�����T����J");
  }
  else 
       out.print("���������D�k�{�Һ����L�k����" + "<br>");

  stmt.close();
  con.close();
%>
</body>
</html>
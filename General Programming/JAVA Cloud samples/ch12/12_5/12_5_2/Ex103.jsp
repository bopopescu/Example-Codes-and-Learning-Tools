<%@ page contentType="text/html;charset=big5" %>
<%@ page import= "java.sql.*" %>
<%@ page import= "java.io.*" %>
<html>
<head><title>Ex103</title></head><body>
<p align="left">
<font size="5"><b>Page of Ex103 �]���O���ݰT��</b></font>
</p><HR>
<%
  String JDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
  String connectDB="jdbc:odbc:cloudDB12";
  StringBuffer sb = new StringBuffer();

  Class.forName(JDriver);
  Connection con = DriverManager.getConnection(connectDB);
  Statement stmt = con.createStatement();

  request.setCharacterEncoding("big5");
  String StartTime = request.getParameter("startTime");
  String EndTime = request.getParameter("endTime");

  String sql="SELECT �ɶ�, �T��  FROM instantNews WHERE �ɶ�����>='" +
                    StartTime + "' AND �ɶ�����<='" +  EndTime + "';";

  if (stmt.execute(sql))   {
      ResultSet rs = stmt.getResultSet();
      while (rs.next()) {
          sb.append( rs.getString("�ɶ�"));
          sb.append( rs.getString("�T��"));
          sb.append("!!      ");
      }
  }
  String Info= sb.toString();
  %>
  <b><font color="#FF0000"><marquee scrolldelay="120" loop="5"        bgcolor="#00FFFF"><%= Info%>��</marquee></font></b>
  <%
  stmt.close();
  con.close();
%>
</body>
</html>
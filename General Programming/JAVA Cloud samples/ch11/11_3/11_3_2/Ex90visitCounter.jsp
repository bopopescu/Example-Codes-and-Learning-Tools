<%@ page contentType="text/html;charset=big5" %>
<%@ page import= "java.sql.*" %>
<%@ page import= "java.io.*" %>
<html>
<head><title>Ex90visitCounter</title></head><body>
<p align="left">
<font size="5"><b>Page of Ex90visitCounter</b></font>
</p>
<%
  String JDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
  String connectDB="jdbc:odbc:cloudDB11";
  StringBuffer sb = new StringBuffer();

  Class.forName(JDriver);
  Connection con = DriverManager.getConnection(connectDB);
  Statement stmt = con.createStatement();

//Ū�����x�s�����X�H��------------------------------------------------
  String sql1= "SELECT count FROM visitCounter WHERE workPointer=0";
  if (stmt.execute(sql1))   {
       ResultSet rs = stmt.getResultSet();
       while (rs.next())  {
           Object obj = rs.getObject(1);
            sb.append(obj.toString());
        }
    }

//�N���X�H���[1�A�x�s-------------------------------------------------
  String result= sb.toString();
  int numInt= Integer.parseInt(result) + 1;
  String sql2= "UPDATE visitCounter SET " +
                       "workPointer= " + 0 + "," +
                       "count= " + numInt + 
                        " WHERE workPointer=  0";
  stmt.executeUpdate(sql2);


  stmt.close();
  con.close();

  out.print("���������X�H���G "  + numInt);
%>
</body>
</html>
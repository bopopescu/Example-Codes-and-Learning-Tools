<%@ page contentType="text/html;charset=big5" %>
<%@ page import= "java.sql.*, java.util.Date" %>
<html>
<head><title>Ex113</title></head><body>
<p align="center">
<font size="5"><b>Page of Ex113 �^���N���g�J��Ʈw</b></font>
</p>
<%
  String JDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
  String connectDB="jdbc:odbc:cloudDB14";

  Class.forName(JDriver);
  Connection con = DriverManager.getConnection(connectDB);
  Statement stmt = con.createStatement();

  request.setCharacterEncoding("big5");
  String indexStr= request.getParameter("respIndex");
  String nameStr = request.getParameter("respName");
  String emailStr = request.getParameter("respEmail");
  String dataStr = request.getParameter("respData");

  if(indexStr=="" || nameStr=="" || emailStr=="" || dataStr=="")  {
     out.print("��ƶ�g������");
     %><br>
     <a href= "Ex113printArticle.jsp" target= "_top">�����^����</a>
     <%
  } 
  else {
     Date respDate= new Date();
     String dateStr= respDate.toLocaleString();

     String sql="INSERT INTO ResponseInfo(��峹�s��, �^���ɶ�," +
                       "�^���̦W��, �^���̫H�c, �^���̷N��) VALUES (" +
                        indexStr + ",'" + dateStr + "','" + nameStr + "','" +
                        emailStr + "','" + dataStr + "')" ;
                      
    stmt.executeUpdate(sql);
    out.print("���\�����^���N����J��Ʈw");

     stmt.close();
     con.close();
  }
   %>
</body>
</html>
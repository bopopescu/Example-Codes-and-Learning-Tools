<%@ page contentType="text/html;charset=big5" %>
<%@ page import= "java.sql.*, java.util.Date" %>
<html>
<head><title>Ex113</title></head><body>
<p align="center">
<font size="5"><b>Page of Ex113 ��J�^���N��</b></font>
</p>
<%
  String JDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
  String connectDB="jdbc:odbc:cloudDB14";

  Class.forName(JDriver);
  Connection con = DriverManager.getConnection(connectDB);
  Statement stmt = con.createStatement();

  request.setCharacterEncoding("big5");
  String indexStr = request.getParameter("postIndex");

  String sql="SELECT * FROM articleINFO WHERE �s��= " +
                      indexStr + ";" ;

  if (stmt.execute(sql))   {
      ResultSet rs = stmt.getResultSet();
      while (rs.next()) {
        String timeStr= rs.getString("�ɶ�");
        String nameStr= rs.getString("�W��");
        String emailStr= rs.getString("�H�c");
        String articalStr= rs.getString("�峹");

        out.print("�s���G" + indexStr + "<BR>");
        out.print("�ɶ��G" + timeStr + "<BR>");
        out.print("�W�١G" + nameStr + "<BR>");
        out.print("�H�c�G" + emailStr + "<BR>");
        out.print("�峹<BR>" + articalStr + "<BR><HR>");
      }
      stmt.close();
      con.close();
  }
  out.print("<FORM ACTION=Ex113writeDatabase.jsp " +
                  "METHOD=post>");
  out.print("��峹�s���G<INPUT TYPE=text NAME=respIndex " +
                  "VALUE=" + indexStr + "><BR>");
  out.print("�^���̦W�١G<INPUT TYPE=text NAME=respName " +
                  "SIZE=" + 10 + "><BR>");
  out.print("�^���̫H�c�G<INPUT TYPE=text NAME=respEmail " +
                  "SIZE=" + 20 + "></p><p>");
  out.print("�^���̷N���G(50�t�H��)<BR>" + 
             "<TEXTAREA NAME=respData  ROWS=3 COLS=45></TEXTAREA>");
     %>
</p><p>
<INPUT TYPE="submit" VALUE="���e">
<INPUT TYPE="reset" VALUE="����"> 
 
</body>
</html>
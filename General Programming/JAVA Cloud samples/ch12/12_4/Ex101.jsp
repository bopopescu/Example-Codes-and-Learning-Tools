<%@ page contentType="text/html;charset=big5" %>
<%@ page import= "java.sql.*" %>
<%@ page import= "java.io.*" %>
<html>
<head><title>Ex101</title></head><body>
<p align="left">
<font size="5"><b>Sub Page of Ex101 ���ݰT��</b></font>
</p>
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

  if (stmt.execute(sql))
      {
        ResultSet rs = stmt.getResultSet();
        ResultSetMetaData md = rs.getMetaData();
        int colCount = md.getColumnCount();
        sb.append("<TABLE CELLSPACING=10><TR>");
        for (int i = 1; i <= colCount; i++)
        sb.append("<TH>" + md.getColumnLabel(i));
        while (rs.next())
            {
              sb.append("<TR>");
              for (int i = 1; i <= colCount; i++)
                   {
                     sb.append("<TD>");
                     Object obj = rs.getObject(i);
                     if (obj != null)
                          sb.append(obj.toString());
                      else
                          sb.append("&nbsp;");
                    }
              }
              sb.append("</TABLE>\n");
            }
        else
             sb.append("<B>Update Count:</B> " +
                                 stmt.getUpdateCount());

  String result= sb.toString();
  out.print(result);

  stmt.close();
  con.close();
%>
</body>
</html>
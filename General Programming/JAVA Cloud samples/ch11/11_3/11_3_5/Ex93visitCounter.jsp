<%@ page contentType= "text/html;charset=big5" %>
<%! int count= 0; %>
<html>
<head><title>Ex93visitCounter</title></head><body>
<p align="center">
<font size="5"><b>Page of Ex93visitCounter</b></font>
</p>
<%
  count = count + 1;
  out.print("�������X�H��: " + count);
%>
</body>
</html>
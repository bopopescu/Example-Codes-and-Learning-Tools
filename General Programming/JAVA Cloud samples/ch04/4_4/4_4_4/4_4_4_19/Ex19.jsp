<%@ page contentType= "text/html;charset=big5" %>
<%@ page import= "java.util.*" %>
<% Date T= new Date(); %>
<html>
<head><title>Ex19</title></head><body>

<%
 response.addIntHeader("refresh", 5);

  out.print("This is the page of Ex19" + "<br>");
  out.print("" + "<br>");

  out.print("�������C5����@��" + "<br>");
  out.print("" + "<br>");

  out.print("�{�b�ɶ�: " + T);
%>
</body>
</html>
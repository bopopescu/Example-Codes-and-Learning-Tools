<%@ page contentType= "text/html;charset=big5" %>
<html>
<head><title>Ex82_2</title></head><body>
<%
  request.setCharacterEncoding("big5");
  out.print("This is the Sub Page of Ex82_2" + "<br>");
  out.print("" + "<br>");

  session = request.getSession();

  if(session.getAttribute("ex82") == "true")
       out.print("���������X�k�{�Һ���" + "<br>");
   else
       out.print("���������D�k�{�Һ���" + "<br>");
%>
</body>
</html>
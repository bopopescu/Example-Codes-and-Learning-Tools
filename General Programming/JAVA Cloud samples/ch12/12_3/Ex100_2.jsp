<%@ page contentType= "text/html;charset=big5" %>
<html>
<head><title>Ex100_2</title></head><body>
<%
  request.setCharacterEncoding("big5");
  out.print("Sub Page of Ex100_2 ��J�Y�ɰT��" + "<br>");
  out.print("" + "<br>");

  session = request.getSession();

  if(session.getAttribute("ex100") == "true") {
       out.print("���������X�k�{�Һ���" + "<br>");
       out.print("<FORM ACTION = Ex100_3.jsp " +
                       "METHOD = post>");
       out.print("��J�Y�ɰT���G" + "<br>");
       out.print("<TEXTAREA NAME = data " +
                       "ROWS = 3 COLS= 40>" + "</TEXTAREA>" + "<br>");
       out.print("<INPUT TYPE = submit VALUE = \"���e\">");
       out.print("<INPUT TYPE = reset VALUE = \"����\">");
  }
   else
       out.print("���������D�k�{�Һ����L�k����" + "<br>");
%>
</body>
</html>
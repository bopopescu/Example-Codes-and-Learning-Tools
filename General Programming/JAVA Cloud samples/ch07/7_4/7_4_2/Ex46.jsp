<%@ page contentType= "text/html;charset=big5" %>
<%@ page import = "java.io.*" %>
<html>
<head><title>Ex46</title></head><body>
<%
  request.setCharacterEncoding("big5");
  String val_fOld = request.getParameter("f_Old");
  String val_fNew = request.getParameter("f_New");

  File fNew = new File(val_fNew);
  if (fNew.createNewFile()) 
      out.print("���\�إ߷s�ɮ�" + val_fNew +"<br>");

  BufferedReader bfOld = new BufferedReader(new FileReader(val_fOld));
  BufferedWriter bfNew = new BufferedWriter(new FileWriter(val_fNew));
  int msg;
  while ((msg = bfOld.read()) != -1) 
        bfNew.write((char)msg);

  out.print("���\���m�ɮ�" + "<br>");
  bfOld.close();
  bfNew.close();

  File fOld = new File(val_fOld);
  if (fOld.delete()) 
      out.print("���\�R�����ɮ�" + val_fOld + "<br>");
%>
</body>
</html>
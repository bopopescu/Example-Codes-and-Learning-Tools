<%@ page contentType= "text/html;charset=big5" %>
<%@ page import = "java.io.*" %>
<html>
<head><title>Ex48</title></head><body>
<%
  request.setCharacterEncoding("big5");
  String val_fA = request.getParameter("f_A");
  String val_fB = request.getParameter("f_B");
  String val_fC = request.getParameter("f_C");

  File file_C = new File(val_fC);
  if (file_C.createNewFile())
      out.print("���\�إ߷s�ɮ�C: " + val_fC + "<br>");

  int msgInt_A, msgInt_B;
  char msgCh_A, msgCh_B;
  String msgStr_A= "", msgStr_B= "";

  BufferedReader bfRead_A = new BufferedReader(new FileReader(val_fA));
  BufferedReader bfRead_B = new BufferedReader(new FileReader(val_fB));

  while ((msgInt_A = bfRead_A.read()) != -1) {
        msgCh_A = (char)msgInt_A;
        msgStr_A = msgStr_A + msgCh_A;
  }
  out.print("���\Ū���ɮ�A" + "<br>");

  while ((msgInt_B = bfRead_B.read()) != -1) {
        msgCh_B = (char)msgInt_B;
        msgStr_B = msgStr_B + msgCh_B;
  }
  out.print("���\Ū���ɮ�B" + "<br>");

  bfRead_A.close();
  bfRead_B.close();

  BufferedWriter bfWrite_C = new BufferedWriter(new FileWriter(val_fC));

  bfWrite_C.write(msgStr_A + msgStr_B);
  out.print("���\�ǻ��ɮ�A,B���ɮ�C" + "<br>");

  bfWrite_C.close();
%>
</body>
</html>
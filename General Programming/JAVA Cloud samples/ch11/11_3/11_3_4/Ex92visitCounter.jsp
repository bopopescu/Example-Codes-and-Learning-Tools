<%@ page contentType="text/html; charset=Big5" %>
<%@ page import = "java.io.*" %>
<html>
<head><title>Ex92visitCounter</title></head><body>
<p align="left">
<font size="5"><b>Page of Ex92visitCounter</b></font>
</p>
<%
request.setCharacterEncoding("big5");

String fCount = request.getRealPath("/fileVisitor.txt");

//Ū���ɮפ����x�s�����X�H��
BufferedReader br = new BufferedReader(new FileReader(fCount));
String numStr = br.readLine();
br.close(); 

//�N���X�H���[1�A�x�s�J�ɮ�
if (session.isNew()) {
   int numInt = Integer.parseInt(numStr) + 1;
   numStr = String.valueOf(numInt);
   BufferedWriter bw = new BufferedWriter(new FileWriter(fCount));
   bw.write(String.valueOf(numStr));
   bw.close();
}

//�ϥιϧμƦr�L�X���X�H��
out.print("���������X�H���G " + "<br>");
for(int i = 0; i < numStr.length(); i++)  {
    %>
    <img src = "./images/<%= numStr.charAt(i) %>.gif"></img>
    <%
}
%>
</body>
</html>

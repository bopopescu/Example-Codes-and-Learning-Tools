<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>

<%!
   HttpSession hs;
   Integer cn;
   Date dt;
   String str1, str2;
%>
<%
   // ���osession
   hs = request.getSession(true);
   cn = (Integer) hs.getAttribute("count");
   dt = (Date) hs.getAttribute("date");

   // �]�w����
   if(cn == null){
   cn = new Integer(1);
      dt = new Date();
      str1 = "�o�O�z���즸�y�X�C";
      str2 = "";
   }
   else{
      cn = new Integer(cn.intValue() + 1);
      dt = new Date();
      str1 = "�o�O�z����" + cn + "���y�X�C";
      str2 = "(�W���O�b�G" + dt + ")";
   }

   // �]�wSession
   hs.setAttribute("count", cn);
   hs.setAttribute("date", dt);
%>
<html>
<head>
<title>�d��</title>
</head>
<body>
<center>

<h2>�w��</h2>
<%= str1 %><br/>
<%= str2 %><br/>
�п�ܤ@��ӫ~�C<br/>
<a href="car1.html">�T��</a><br/>
<a href="car2.html">�d��</a><br/>
<a href="car3.html">�Ԩ�</a><br/>
</center>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String tmp = request.getParameter("cars");
    String carname = new String(tmp.getBytes("ISO-8859-1"), "UTF-8");
%>
<html>
<head>
<title>�d��</title>
</head>
<body>
<center>

<%
   if(carname.length() != 0){
%>

<h2><%= carname %></h2>
�P�±z�ʶR�����q��
<%= carname %>
�C<br/>

<%
   }
   else{
%>

<h2>���~</h2>
�п�J�ӫ~�W�١C<br/>

<%
   }
%>

</center>
</body>
</html>
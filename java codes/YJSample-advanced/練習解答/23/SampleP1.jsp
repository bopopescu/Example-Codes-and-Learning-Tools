<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String tmp = request.getParameter("name");
    String name = new String(tmp.getBytes("8859_1"), " UTF-8");
%>

<html>
<head>
<title><%= name %></title>
</head>
<body>
<center>
<h2>�w��</h2>
�w����{�A
<%= name%>
���͡C<br/>
</center>
</body>
</html>
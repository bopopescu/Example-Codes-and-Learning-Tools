<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="cb" class="mybeans.CarBean" scope="request"/>

<html>
<head>
<title>�d��</title>
</head>
<body>
<center>
<h2>�P��</h2>
�P�±z�ʶR�����q��
<jsp:getProperty name="cb" property="cardata"/>
�C<br/>
</center>
</body>
</html>
<%@ page contentType="text/html;charset=big5"
    import="java.util.*"
%>
 <html>
  <head>
   <title>�޲z�H��</title>
   <link href="css/generalstyle.css" type="text/css" rel="stylesheet">
  </head>
 <body>
   <%@ include file="admintop.jsp" %>
	<br><br><br>
	<center>
	 <font color="red" size="4.5">
     <% 
        request.setCharacterEncoding("gbk");
        String message=(String)request.getAttribute("msg");
     	if(message!=null)
     	{//��X����
     		out.println(message);
     	}
      %>
	</center>
 </body>
</html>

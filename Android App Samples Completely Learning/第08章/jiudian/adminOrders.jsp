<%@ page contentType="text/html;charset=big5"
    import="java.util.*"%>
 <html>
  <head>
   <title>�q��޲z</title>
   <link href="css/generalstyle.css" type="text/css" rel="stylesheet">
   <script language="JavaScript">
    function check()
    {
       if(document.searchOrder.oid.value=="")
       {
         alert("�п�J�q�渹�d�ߡI�I�I");
         searchOrder.oid.focus();
         return false;
       }
       document.searchOrder.submit();
    }
   </script>
  </head>
 <body>
   <%@ include file="admintop.jsp" %>	
   <hr width="100%"></hr><br>
   <table align="center" border="0" width="80%">
    <tr>
       
	  <form name="searchOrder" action="OrderServlet" method="post">
	   <td align="right">�q��s��:	 
	    <input type="hidden" name="action" value="query">
	    <input type="text" name="oid">
	    <input type="button" value="�d��" onClick="check()">
	   </td>
	  </tr>
	  </form>
   </table><br>
   <table align="center" width="60%">
   <tr>
   <td align="left"><a href=OrderServlet?action=allOrders&&condition=2>�w�B�z</a></td>
   <td align="center"><a href=OrderServlet?action=allOrders&&condition=1>�Ҧ��q��</a></td>
   <td align="right"><a href=OrderServlet?action=allOrders&&condition=3>���B�z</a></td>
   </tr>
   </table><br>
	   <%Vector<String[]> list = //�o��q��C��
          (Vector<String[]>)request.getAttribute("list");
       if(list==null||list.size()==0){//�C������
	     out.println("<center>");
	     out.println("<font color=red size=5>�S���q��</font>");
	     out.println("<br><br><a href="+
	       "OrderServlet?action=allOrders&&condition=1>��^</a></center>");
	    }
	    else{%>
   <table align="center" width="70%" cellspacing="1" bgcolor="black">
	 <tr width="60%" height="30" bgcolor="white">
	   <th>�s��</th>   <th>�U�q�H</th>   <th>����ɶ�</th> 
	   <th>���A</th>   <th>�B�z�H</th>	 <th>�Ա�</th>
	   <th>�Ƶ�</th>   <th>�B�z�q��</th>
	 </tr>
	   <%int color = 0;
	  	 for(int i=0;i<list.size();i++){ 
		   String[] s = list.get(i);%>
     <tr bgcolor=<%= color%2==0?"eeffee":"ffeeee" %> height="40">
	   <td align="center"><%= s[0] %></td>  <td align="center"><%= s[1] %></td>
	   <td align="center"><%= s[2] %></td>  <td align="center"><%= s[4] %></td>
	   <td align="center"><%= s[3] %></td>
	   <td align="center">
	    <a target="blank" href=OrderServlet?action=ListDetail&&oid=<%= s[0] %>>�q��Ա�</a>	   
	   </td>   
	   <form action="OrderServlet" method="post">
	   <td align="center">
	      <input type="text" name="reason" size="10" value=<%= s[5] %>>
	   </td><td align="center">
	     <select name="ostatus">
	     <option selected>�w�q���\</option>
	     <option>�w�q����</option>
	     <option>�w�q��</option>
	     </select>
	     <input type="hidden" name="action" value="dealOrder">
	     <input type="hidden" name="oid" value=<%= s[0] %>>
	     <input type="submit" value="����">
	   </td>
	   </form>	  
	</tr> <%color++;}%>
   </table><%}%>
 </body>
</html>

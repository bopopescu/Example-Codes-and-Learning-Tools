<%@ page contentType="text/html;charset=big5"
    import="java.util.*,wyf.wyy.*"%>
 <html>
  <head>
   <title>���պ޲z</title>
   <link href="css/generalstyle.css" type="text/css" rel="stylesheet">
  </head>
 <body>
    <%@ include file="admintop.jsp" %>
    
    <hr width="100%"></hr><br>
    <table align="center" width="80%">
    <tr>
    <td align="right" >
    <a href=addGroup.jsp>�K�[����>></a>
    </td>
    </tr>
    </table>
    <% Vector<String []> vgroup = DB.getGroup();
       int color=0;//���ܨC���C�� 
     %>
    <table align="center" width="90%" cellspacing="1" bgcolor="black">
     <tr bgcolor="white">
       <th>�W��</th>  <th>�Ϥ�URL</th>  <th>�y�z</th>
       <th>�W�h</th>  <th>�ק�/�R��</th>    
     </tr>     
      <%for(String []s:vgroup){%>
      <tr bgcolor=<%= color%2==0?"eeffee":"ffeeee" %>>
        <td align="center"><%= s[0] %></td>
        <td align="center"><%= s[1] %></td>
        <td align="center"><%= s[2] %></td>
        <td align="center"><%= s[4] %></td> 
        <td align="center">
         <a href=ListServlet?action=editGroup&&gId=<%= s[3] %>>�ק�/�R��</a>
        </td>
       </tr>
     <%color++;}
      %>
    </table><br>
    
 </body>
</html>

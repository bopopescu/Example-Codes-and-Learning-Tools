<%@ page contentType="text/html;charset=big5"
    import="java.util.*,wyf.wyy.*"
%>
 <html>
  <head>
   <title>�޲z���޲z</title>
   <link href="css/generalstyle.css" type="text/css" rel="stylesheet">
  </head>
 <body>
    <%@ include file="admintop.jsp" %>
    <hr width="100%"></hr><br>
    <%
       Vector<String []> vadmin = DB.getAdminInfo();
       int color=0;//���ܨC���C�� 
     %>
    <table align="center" width="90%" cellspacing="1" bgcolor="black">
     <tr bgcolor="white">
       <th>�޲z��ID</th> <th>�޲z���ŧO</th>
       <th>�R��</th>   
     </tr>
      <%
      for(String []s:vadmin)
      {
     %>
      <tr bgcolor=<%= color%2==0?"eeffee":"ffeeee" %>>
        <td align="center"><%= s[0] %></td>
        <% 
        if(s[1].equals("1"))
        {
        %>
        <td align="center">�W�ź޲z��</td>
        <%
        }
        else
        {
         %>
        <td align="center">���q�޲z��</td>
        <% } %>        
        <td align="center">
         <a href=ListServlet?action=deleteAdmin&&adname=<%= s[0] %>>�R��</a>
        </td>
       </tr>
     <%
         color++;
       }
      %>
    </table >
    <table align="center" border="0" width="80%">
     <tr>
      <td align="left"><a href=addAdmin.jsp><<�K�[�޲z��</a></td>
      <td align="right"><a href=adResetPwd.jsp>���m�޲z���K�X>></a></td>
     </tr>
    </table>
 </body>
</html>

<%@ page contentType="text/html;charset=big5"%>
<link href="css/generalstyle.css" type="text/css" rel="stylesheet"><br>

<table align=center border="0" width="80%" height=20 >
  <tr align="center">
    <td height=15 colspan="5">
	  <font color="#5e82e9" size="6">�L��s���w�q�޲z</font>
	</td>
  </tr>
  <tr>
   <td align="right" colspan="5">
  	<%String adname = (String)session.getAttribute("adname");
  	  if(adname!=null){
  	   out.println("�޲z��"+adname+"�z�n");
  	   }%>
    </td>
  </tr>
</table>
<table align="center" border="0" width="80%" bgcolor="#92cfeb">
  <tr>
   <td><a href="adindex.jsp">�n��</a></td>
   <td><a href=RegAndLoginServlet?action=adlogout>���P</a></td>
   <td><a href=adminChangePwd.jsp>�ק�K�X</a></td>
   <td><a href=ListServlet?action=admanage>�޲z���޲z</a></td>
   <td><a href=ListServlet?action=adminGroup>���պ޲z</a></td>
   <td><a href=ListServlet?action=adminList&&gId=0>�귽�޲z</a></td>
   <td><a href=OrderServlet?action=allOrders&&condition=1>�q��޲z</a></td>
  </tr>
</table>

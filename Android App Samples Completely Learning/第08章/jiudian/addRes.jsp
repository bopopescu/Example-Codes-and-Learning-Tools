<%@ page contentType="text/html;charset=big5"
    import="java.util.*,wyf.wyy.*"
%>
 <html>
  <head>
   <title>�K�[�귽</title>
   <link href="css/generalstyle.css" type="text/css" rel="stylesheet">
   <script language="JavaScript">
    function check3()
    {
       if(document.addRes.rgid.value=="")
       {
         alert("�s�����šI�I�I");
         addRes.rgid.focus();
         return false;
       }
       if(document.addRes.rlevel.value=="")
       {
         alert("�W�欰��");
         addRes.rlevel.focus();
         return false;
       }
       if(document.addRes.rmoney.value=="")
       {
         alert("���欰�šI�I�I");
         addRes.rmoney.focus();
         return false;
       }
       if(document.addRes.rdetail.value=="")
       {
         alert("�y�z���šI�I�I");
         addRes.rdetail.focus();
         return false;
       }
       document.addRes.submit();
    }
   </script>
  </head>
 <body>
   <%@ include file="adminRestop.jsp" %>
   <center>
    <font color="red" size="4">�Х��T��g�귽���H��</font>
   </center><br>
   <table align="center" border="0" width="60%">
    <form name="addRes" action="ListServlet" method="post">
     <tr bgcolor="b9fbfa">
      <td align="right" width="20%">�s��:</td>
      <td><input type="text" name="rgid"></td>
     </tr>
     <tr>
      <td align="right">����:</td>
      <td>
       <select name="rgroup">
		<% 
		   for(String s[]:vgroup)
		   {
		 %>        
             <option value=<%= s[3] %>><%= s[0] %></option>
   		<%
   		   }
   		 %>
       </select>          
      </td>
     </tr>
     <tr bgcolor="b9fbfa">
      <td align="right">�W��:</td>
      <td><input type="text" name="rlevel"></td>
     </tr>
     <tr>
      <td align="right">����/�ɬq:</td>
      <td><input type="text" name="rmoney"></td>
     </tr>
     <tr bgcolor="b9fbfa">
       <td align="right">�y�z:</td>
       <td>
         <textarea rows=4 cols=40 name="rdetail"></textarea>
       </td>
     </tr>
     <tr>
      <td align="right">���A:</td>
      <td>
       <select name="rstatus">
        <option>�Ŷ�</option>
        <option>����</option>               
       </select>
      </td>
     </tr>
     <tr bgcolor="b9fbfa"><td></td>
      <td align="left">
        <input type="hidden" name="action" value="addRes">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" value="�O�s" onClick="check3()">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="reset" value="���m">
       </td>
     </tr>
    </form>
   </table>
 </body>
</html>

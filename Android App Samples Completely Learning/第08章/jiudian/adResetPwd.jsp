<%@ page contentType="text/html;charset=big5"
    import="java.util.*,wyf.wyy.*"
%>
 <html>
  <head>
   <title>���m�޲z���K�X</title>
   <link href="css/generalstyle.css" type="text/css" rel="stylesheet">
   <script language="javascript">
    function check()
    { 
      if(document.resetPwd.adname.value=="")
      {
         alert("�ж�g�n���W�I�I�I");
         resetPwd.adname.focus();
         return false;
      }
      if(document.resetPwd.adpwd.value=="")
      {
         alert("�ж�g�n���K�X�I�I�I");
         resetPwd.adpwd.focus();
         return false;
      }
      if(document.resetPwd.repwd.value=="")
      {
         alert("�Э��ƱK�X�I�I�I");
         resetPwd.repwd.focus();
         return false;
      }
      if(document.resetPwd.adpwd.value!=document.resetPwd.repwd.value)
       {
         alert("�⦸�K�X��J���@�ˡA���ˬd��J�I�I�I");
         resetPwd.adpwd.value="";
         resetPwd.repwd.value="";
         resetPwd.adpwd.focus();
         return false;
       }
       document.resetPwd.submit();
    }
   </script>
  </head>
 <body>
    <%@ include file="admintop.jsp" %>
    <hr width="100%"></hr><br>
    <table align="center" border="0" width="40%">
     <form name="resetPwd" action="ListServlet" method="post">
      <tr bgcolor="eeffee">
       <td align="right">�n���W</td>
       <td><input type="text" name="adname" size="20"></td>
      </tr>
      <tr bgcolor="ffeeff">
       <td align="right">�n���K�X</td>
       <td><input type="password" name="adpwd" size="20"></td>
      </tr>
      <tr bgcolor="eeffee">
       <td align="right">���ƱK�X</td>
       <td><input type="password" name="repwd" size="20"></td>
      </tr>
      <tr bgcolor="ffeeff">
       <td></td>
       <td>
        <input type="hidden" name="action" value="resetPwd">
        <input type="button" value="���m" onClick="check()">
       </td>
      </tr>
     </form>
    </table>    
 </body>
</html>

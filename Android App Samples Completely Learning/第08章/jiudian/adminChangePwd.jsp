<%@ page contentType="text/html;charset=big5"
    import="java.util.*,wyf.wyy.*"
%>
 <html>
  <head>
   <title>�޲z���ק�K�X</title>
   <link href="css/generalstyle.css" type="text/css" rel="stylesheet">
   <script language="javascript">
    function check()
    { 
      if(document.changePwd.adname.value=="")
      {
         alert("�ж�g�n���W�I�I�I");
         changePwd.adname.focus();
         return false;
      }
      if(document.changePwd.adpwd.value=="")
      {
         alert("�ж�g�n���K�X�I�I�I");
         changePwd.adpwd.focus();
         return false;
      }
      if(document.changePwd.newPwd.value=="")
      {
         alert("�ж�g�s�K�X�I�I�I");
         changePwd.newPwd.focus();
         return false;         
      }
      if(document.changePwd.newPwd.value!=document.changePwd.reNewPwd.value)
       {
         alert("�⦸�K�X��J���@�ˡA���ˬd��J�I�I�I");
         changePwd.newPwd.value="";
         changePwd.reNewPwd.value="";
         changePwd.newPwd.focus();
         return false;
       }
       document.changePwd.submit();
    }
   </script>
  </head>
 <body>
    <%@ include file="admintop.jsp" %>
    <hr width="100%"></hr><br>
    <table align="center" border="0" width="40%">
     <form name="changePwd" action="ListServlet" method="post">
      <tr bgcolor="eeffee">
       <td align="right">�n���W</td>
       <td><input type="text" name="adname" size="20"></td>
      </tr>
      <tr bgcolor="ffeeff">
       <td align="right">�n���K�X</td>
       <td><input type="password" name="adpwd" size="20"></td>
      </tr>
      <tr bgcolor="eeffee">
       <td align="right">�s�K�X</td>
       <td><input type="password" name="newPwd" size="20"></td>
      </tr>
      <tr bgcolor="ffeeff">
       <td align="right">�A����J�s�K�X</td>
       <td><input type="password" name="reNewPwd" size="20"></td>
      </tr>
      <tr bgcolor="eeffee">
       <td></td>
       <td>
        <input type="hidden" name="action" value="changePwd">
        <input type="button" value="�ק�" onClick="check()">
       </td>
      </tr>
     </form>
    </table>    
 </body>
</html>

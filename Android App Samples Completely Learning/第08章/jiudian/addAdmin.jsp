<%@ page contentType="text/html;charset=big5"
    import="java.util.*,wyf.wyy.*"
%>
 <html>
  <head>
   <title>�K�[�޲z��</title>
   <link href="css/generalstyle.css" type="text/css" rel="stylesheet">
   <script language="javascript">
    function check()
    { 
      if(document.addAdmin.adname.value=="")
      {
         alert("�ж�g�n���W�I�I�I");
         addAdmin.adname.focus();
         return false;
      }
      if(document.addAdmin.adpwd.value=="")
      {
         alert("�ж�g�n���K�X�I�I�I");
         addAdmin.adpwd.focus();
         return false;
      }
      if(document.addAdmin.repwd.value=="")
      {
         alert("�Э��ƱK�X�I�I�I");
         addAdmin.repwd.focus();
         return false;
      }
      if(document.addAdmin.adpwd.value!=document.addAdmin.repwd.value)
       {
         alert("�⦸�K�X��J���@�ˡA���ˬd��J�I�I�I");
         addAdmin.adpwd.value="";
         addAdmin.repwd.value="";
         addAdmin.adpwd.focus();
         return false;
       }
       document.addAdmin.submit();
    }
   </script>
  </head>
 <body>
    <%@ include file="admintop.jsp" %>
    <hr width="100%"></hr><br>
    <table align="center" border="0" width="40%">
     <form name="addAdmin" action="ListServlet" method="post">
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
        <input type="hidden" name="action" value="addAdmin">
        <input type="button" value="�K�[" onClick="check()">
       </td>
      </tr>
     </form>
    </table>    
 </body>
</html>

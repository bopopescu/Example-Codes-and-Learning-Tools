<%@ page contentType="text/html;charset=big5"
    import="java.util.*,wyf.wyy.*"
%>
<html>
  <head>
   <title>���պ޲z</title>
   <script language="JavaScript">
    function check()
    {
      if(document.addGroup.gName.value=="")
      {
         alert("�ж�g���զW�I�I�I");
         addGroup.gName.focus();
         return false;
      }
      if(document.addGroup.gImg.value=="")
      {
         alert("�ж�g�Ϥ�URL�I�I�I");
         addGroup.gImg.focus();
         return false;
      }
      if(document.addGroup.gDetail.value=="")
      {
         alert("�ж�g�y�z�H���I�I�I");
         addGroup.gDetail.focus();
         return false;
      }
      if(document.addGroup.gOrderDet.value=="")
      {
         alert("�ж�g�w�q���@�ǳW�h�I�I�I");
         addGroup.gOrderDet.focus();
         return false;
      }
      document.addGroup.submit();
    }    
   </script>
  </head>
 <body>
   <%@ include file="admintop.jsp" %><br>   
    <%
      Vector<String> ginfo = 
      	 (Vector<String>)request.getAttribute("ginfo");
 	%>
   <center>
    <font color="BLUE" size="5.5">�л{�u���T��g���իH��
   </center>
   <table align="center" border="0" width="60%">
    <form name="addGroup" action="ListServlet" method="post">
     <tr bgcolor="eedbf8">
      <td align="right" width="20%">�զW:</td>
      <td><input type="text" name="gName"></td>
     </tr>
     
     <tr>
      <td align="right">�Ϥ�URL:</td>
      <td><input type="text" name="gImg"></td>
     </tr>
     
     <tr bgcolor="eedbf8">
      <td align="right">�y�z:</td>
      <td>
        <textarea rows=6 cols=50 name="gDetail"></textarea>
      </td>
     </tr>
     <tr>
      <td align="right">�W�h:</td>
      <td>
        <textarea rows=6 cols=50 name="gOrderDet"></textarea>
      </td>
     </tr>
     <tr bgcolor="eedbf8"><td></td>
      <td align="left">
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <input type="hidden" name="action" value="addGroup"> 
       <input type="button" value="�K�[" onClick="check()">
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <input type="reset" value="���m">
      </td>
     </tr>
    </form>
   </table><br>
 </body>
</html>

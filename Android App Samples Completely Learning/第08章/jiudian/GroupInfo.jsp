<%@ page contentType="text/html;charset=big5"
    import="java.util.*,wyf.wyy.*"
%>
<html>
  <head>
   <title>���պ޲z</title>
   <script language="JavaScript">
    function check()
    {
      if(document.groInfo.gNameAfter.value=="")
      {
         alert("�ж�g���զW�I�I�I");
         groInfo.gNameAfter.focus();
         return false;
      }
      if(document.groInfo.gImg.value=="")
      {
         alert("�ж�g�Ϥ�URL�I�I�I");
         groInfo.gImg.focus();
         return false;
      }
      if(document.groInfo.gDetail.value=="")
      {
         alert("�ж�g�y�z�H���I�I�I");
         groInfo.gDetail.focus();
         return false;
      }
      if(document.groInfo.gOrderDet.value=="")
      {
         alert("�ж�g�w�q���@�ǳW�h�I�I�I");
         groInfo.gOrderDet.focus();
         return false;
      }
      document.groInfo.action.value="changeGroup";
      document.groInfo.submit();
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
    <font color="black" size="5.5"><%= ginfo.get(1) %>�H��
   </center>
   <table align="center" border="0" width="60%">
    <form name="groInfo" action="ListServlet" method="post">
     <tr bgcolor="ffeeee">
      <td align="right" width="20%">�զW:</td>
      <td><input type="text" name="gNameAfter" value=<%= ginfo.get(1) %>></td>
     </tr>
     
     <tr>
      <td align="right">�Ϥ�URL:</td>
      <td><input type="text" name="gImg" value=<%= ginfo.get(3) %>></td>
     </tr>
     
     <tr bgcolor="ffeeee">
      <td align="right">�y�z:</td>
      <td>
        <textarea rows=6 cols=50 name="gDetail"><%= ginfo.get(4) %></textarea>
      </td>
     </tr>
     <tr>
      <td align="right">�W�h:</td>
      <td>
        <textarea rows=6 cols=50 name="gOrderDet"><%= ginfo.get(2) %></textarea>
      </td>
     </tr>
     <tr bgcolor="ffeeee"><td></td>
      <td align="left">
       <input type="hidden" name="action" value="deleteGroup">
       <input type="hidden" name="gId" value=<%= ginfo.get(0) %> >
       <input type="hidden" name="gNameBefor" value=<%= ginfo.get(1) %>>       
       <input type="button" value="�O�s" onClick="check()">
       &nbsp&nbsp&nbsp&nbsp
       <input type="submit" value="�R��">
       <font color="red" size="2">*�R�����աA�N�|�R�����դU�Ҧ����귽
      </td>
     </tr>
    </form>
   </table><br>
 </body>
</html>

<%@ page contentType="text/html;charset=big5"
    import="java.util.*,wyf.wyy.*"
%>
 <html>
  <head>
   <title>�귽�޲z</title>
   <link href="css/generalstyle.css" type="text/css" rel="stylesheet">
   <script language="JavaScript">
    function check2()
    {
       if(document.resInfo.rgidAfter.value=="")
       {
         alert("�s�����šI�I�I");
         resInfo.rgidAfter.focus();
         return false;
       }
       if(document.resInfo.rlevel.value=="")
       {
         alert("�W�欰��");
         resInfo.rlevel.focus();
         return false;
       }
       if(document.resInfo.rmoney.value=="")
       {
         alert("���欰�šI�I�I");
         resInfo.rmoney.focus();
         return false;
       }
       if(document.resInfo.rdetail.value=="")
       {
         alert("�y�z���šI�I�I");
         resInfo.rdetail.focus();
         return false;
       }
       document.resInfo.action.value="changeRes";
       document.resInfo.submit();
    }
   </script>
  </head>
 <body>
   <%@ include file="adminRestop.jsp" %>	
    <%
      Vector<String[]> rinfo = 
      	 (Vector<String[]>)request.getAttribute("rinfo");
 	  String []s = rinfo.get(0);
 	%>
   <table align="center" border="0" width="60%">
    <form name="resInfo" action="ListServlet" method="post">
     <tr bgcolor="ffeeee">
      <td align="right" width="20%">�s��:</td>
      <td><input type="text" name="rgidAfter" value=<%= s[0] %>></td>
     </tr>
     <tr>
      <td align="right">����:</td>
      <td>
       <select name="rgroup">
        <%
         for(String ss[]:vgroup)
         {
           if(ss[0].equals(s[7]))
           {
         %>           
             <option selected value=<%= ss[3] %>><%= ss[0] %></option>
         <%
           }
           else
           {
           %>
             <option value=<%= ss[3] %>><%= ss[0] %></option>
           <%
           }
          }
          %>
       </select>          
      </td>
     </tr>
     
     <tr bgcolor="ffeeee">
      <td align="right">�W��:</td>
      <td><input type="text" name="rlevel" value=<%= s[1] %>></td>
     </tr>
     <tr>
      <td align="right">����/�ɬq:</td>
      <td><input type="text" name="rmoney" value=<%= s[2] %>></td>
     </tr>
     <tr bgcolor="ffeeee">
       <td align="right">�y�z:</td>
       <td>
         <textarea rows=4 cols=40 name="rdetail"><%= s[3] %></textarea>
       </td>
     </tr>
     <tr>
      <td align="right">���A:</td>
      <td>
       <select name="rstatus">
        <option>�Ŷ�</option>
        <%
        if(s[4].equals("����"))
        {
        %>
        <option selected>����</option>
        <%
        }
        else
        {
        %>
        <option>����</option>
        <% 
        }
         %>                
       </select>
      </td>
     </tr>
     <tr bgcolor="ffeeee"><td></td>
      <td align="left">
       <input type="hidden" name="action" value="deleteRes">
       <input type="hidden" name="rid" value=<%= s[6] %>>
       <input type="hidden" name="rgidBefor" value=<%= s[0] %>>       
       <input type="button" value="�O�s" onClick="check2()">
       &nbsp&nbsp&nbsp&nbsp
       <input type="submit" value="�R��">
       <font color="red" size="2">*�R���귽���e�Х��T�{�Ӹ귽�S���q��B�B��Ŷ����A
      </td>
     </tr>
    </form>
   </table><br>
 </body>
</html>

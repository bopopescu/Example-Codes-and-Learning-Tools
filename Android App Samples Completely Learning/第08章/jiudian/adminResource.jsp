<%@ page contentType="text/html;charset=big5"
    import="java.util.*,wyf.wyy.*"%>
 <html>
  <head>
   <title>�귽�޲z</title>
   <link href="css/generalstyle.css" type="text/css" rel="stylesheet">
  </head>
 <body>
    <%@ include file="adminRestop.jsp" %>
	<% Vector<String> list = (Vector<String>)session.getAttribute("list");
	   int group = Integer.parseInt(list.get(0));
	   String gName = list.get(1);//�o����զW
	   String cpStr=request.getParameter("cp");
	   int currPage=1;//�]�m�q�{��e�����Ĥ@��
	   if(cpStr!=null){
	 	currPage=Integer.parseInt(cpStr.trim());//�o���e��
	    }
	   int span=5;//�C����ܰO�����Ƭ�5��
	   int totalPage=DB.getTotal(span,group);//�o���`����
     %>
   <%@ include file="adminFenYe.jsp" %>
 </body>
</html>

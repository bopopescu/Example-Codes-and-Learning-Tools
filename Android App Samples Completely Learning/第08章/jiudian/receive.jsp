<%@ page
 contentType="text/html;charset=big5"
 import="java.io.*,javax.servlet.*,wyf.wyy.MyConverter,wyf.wyy.DB"
 %>
 
<% 
   String param1=request.getParameter("params1").trim();
   String param2=request.getParameter("params2").trim();
   
   String uname=MyConverter.unescape(param1);
   String pwd=MyConverter.unescape(param2);
   
   String sqla="select pwd from user where uname='"+uname+"'";
   if(DB.isExist(sqla)){
				String sql = "select pwd from user where uname='"+uname+"'";
				String password=DB.getInfo(sql).trim();//�q�ƾڮw�o��K�X
				if(pwd.equals(password)){
				   out.println(MyConverter.escape("�n�����\"));	
				}
				else{
				   out.println(MyConverter.escape("�n������"));	 
				}
	}
   else
   {
       out.println(MyConverter.escape("�Τᤣ�s�b�A�Э��s��J"));
   }

%>

 
 

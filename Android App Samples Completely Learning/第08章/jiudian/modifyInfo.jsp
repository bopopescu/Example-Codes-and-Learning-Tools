<%@ page
 contentType="text/html;charset=big5"
 import="java.io.*,javax.servlet.*,wyf.wyy.MyConverter,wyf.wyy.DB,java.util.*"
 %>
 <%
   String param1=request.getParameter("param1").trim();//�m�W
   String param2=request.getParameter("param2").trim();//�K�X
   String param3=request.getParameter("param3").trim();//�����
   String param4=request.getParameter("param4").trim();//�u��m�W
   String param5=request.getParameter("param5").trim();//�ʧO
   String param6=request.getParameter("param6").trim();//�q�l�l�c
   String uname=MyConverter.unescape(param1);
   String mm=MyConverter.unescape(param2);
   String telnum=MyConverter.unescape(param3);
   String realname=MyConverter.unescape(param4);
   String sex=MyConverter.unescape(param5);
   String email=MyConverter.unescape(param6);
   Vector<String> userInfo=new Vector<String>(); 
   String sqla="update user set pwd='"+mm+"',telNum='"+telnum+"',realName='"+
   realname+"',gender='"+sex+"',email='"+email+"' where uname='"+uname+"'";
   if(DB.updatea(sqla)){
	 out.println(MyConverter.escape("��s���\�A�Э��s�n���I"));
	 	//System.out.println("��s���\�I�I�I");
	}
   else
   {
   //System.out.println("��s���ѡI�I�I�I");
   }
%>

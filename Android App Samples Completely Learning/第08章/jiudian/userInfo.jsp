<%@ page
 contentType="text/html;charset=big5"
 import="java.io.*,javax.servlet.*,wyf.wyy.MyConverter,wyf.wyy.DB,java.util.*"
 %>
<%
   String param1=request.getParameter("param1").trim();
      //System.out.println(",�Τ�ƾڤw�g�Ǧ^�Ȥ�ݡI");
   String uname=MyConverter.unescape(param1);
   Vector<String> userInfo=new Vector<String>(); 
   userInfo=DB.getUserInfo(uname);
   StringBuffer msg=new StringBuffer();
   for(String s:userInfo)
   {
 	msg.append(s);
 	msg.append("|");
   }
   String s=msg.toString();
   out.println(MyConverter.escape(s));
   //System.out.println(s+",�Τ�ƾڤw�g�Ǧ^�Ȥ�ݡI");
%>

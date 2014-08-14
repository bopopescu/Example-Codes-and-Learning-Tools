import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sample4 extends HttpServlet
{
   public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException
   {
      try{
         // ���oSession
         HttpSession hs = request.getSession(true);	
         Integer cn = (Integer) hs.getAttribute("count");	
         Date dt = (Date) hs.getAttribute("date");	

         String str1, str2;

         // �]�w����
         if(cn == null){
            cn = new Integer(1);
            dt = new Date();
            str1 = "�o�O�z���즸�y�X�C";
            str2 = "";
         }
         else{
            cn = new Integer(cn.intValue() + 1);
            dt = new Date();
            str1 = "�o�O�z����" + cn + "���y�X�C";
            str2 = "(�W���O�b�G" + dt + ")";
         }
     
         // �]�wSession
         hs.setAttribute("count", cn);
         hs.setAttribute("date", dt);

         // �]�w���e����
         response.setContentType("text/html; charset=UTF-8");

         // ��XHTML���
         PrintWriter pw = response.getWriter();
         pw.println("<html>\n" +
                    "<head><title>�d��</title></head>\n" +
                    "<body><center>\n" +
                    "<h2>�w����{</h2>" +
                    "<hr />\n" +
                    str1 + "<br/>\n" +
                    str2 + "<br/>\n" +
                    "�п�ܤ@��ӫ~�C<br/>\n" +
                    "<br/>\n" +
                    "<a href=\"../car1.html\">�T��</a><br/>\n" +
                    "<a href=\"../car2.html\">�d��</a><br/>\n" +
                    "<a href=\"../car3.html\">�Ԩ�</a><br/>\n" +
                    "</center></body>\n" +
                    "</html>\n");
        }
        catch(Exception e){    
          e.printStackTrace();
       }
   } 
}
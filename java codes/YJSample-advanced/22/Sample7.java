import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sample7 extends HttpServlet
{
   public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException
   {
      try{
         // �]�w���e����
         response.setContentType("text/html; charset=UTF-8");

         // ��XHTML���
         PrintWriter pw = response.getWriter();
         pw.println("<br/>\n" +
                    "�п�ܤ@��ӫ~�C<br/>\n" +
                    "<a href=\"../car1.html\">�T��</a><br/>\n" +
                    "<a href=\"../car2.html\">�d��</a><br/>\n" +
                    "<a href=\"../car3.html\">�Ԩ�</a><br/>\n");
       }
       catch(Exception e){    
          e.printStackTrace();
       }
   } 
}
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sample1 extends HttpServlet
{
   public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException
   {
      try{
         // �]�w���e����
         response.setContentType("text/html; charset=UTF-8");

         // ���o�ɶ����
         Date dt = new Date(); 
         
         // ��XHTML���
         PrintWriter pw = response.getWriter();
         pw.println("<html>\n" +
                    "<head><title>�d��</title></head>\n" +
                    "<body><center>\n" +
                    "<h2>�w����{</h2>" +
                    "<hr/>\n" +
                    "�{�b�O" + dt + "�C<br/>\n" +
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
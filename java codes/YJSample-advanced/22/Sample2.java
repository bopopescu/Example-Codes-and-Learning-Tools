import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sample2 extends HttpServlet
{
   public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException
   {
      try{
         // ���o�����
         String tmp = request.getParameter("cars");
         String carname = new String(tmp.getBytes("ISO-8859-1"), "UTF-8");

         // �]�w���e����
         response.setContentType("text/html; charset=UTF-8");

         // ��XHTML���
         PrintWriter pw = response.getWriter();
         pw.println("<html>\n" +
                    "<head><title>\n" + carname + "</title></head>\n" +
                    "<body><center>\n" +
                    "<h2>\n" + carname + "</h2>\n" + 
		    "�P�±z�ʶR�����q��" + carname + "�C<br/>\n" +
                    "</center></body>\n" +
                    "</html>\n");
       }
       catch(Exception e){
          e.printStackTrace();
       }
   } 
}
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SampleP1 extends HttpServlet
{
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
   throws ServletException
   {
      try{
         // ���o�����
         String tmp = request.getParameter("name");
         String name = new String(tmp.getBytes("8859_1"), "UTF-8");

         // �]�w���e����
         response.setContentType("text/html; charset=UTF-8");

         // ��XHTML���
         PrintWriter pw = response.getWriter();
         if(name.length() != 0){
            pw.println("<html>\n" +
                       "<head><title>\n" + name + "</title></head>\n" +
                       "<body><center>\n" +
                       "<h2>�w��</h2>\n" +
					   "�w����{�A" +
                       name + "���͡C<br/>\n" +
                       "</center></body>\n" +
                      "</html>\n");
          }
          else{
             pw.println("<html>\n" +
                        "<head><title></title></head>\n" +
                        "<body><center>\n" +
                        "<h2>���~</h2>\n" +
                        "�п�J�z���W�r�C<br/>\n" +
                        "</center></body>\n" +
                        "</html>\n");
          }
       }
       catch(Exception e){
          e.printStackTrace();
       }
   } 
}
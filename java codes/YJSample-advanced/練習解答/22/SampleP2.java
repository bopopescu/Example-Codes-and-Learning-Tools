import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SampleP2 extends HttpServlet
{
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
   throws ServletException
   {
      try{
         // ���o�����
         String tmp = request.getParameter("cars");
         String carname = new String(tmp.getBytes("8859_1"), "UTF-8");
      
         // �]�w���e����
         response.setContentType("text/html; charset=UTF-8");

         // ��XHTML���
         PrintWriter pw = response.getWriter();
         if(carname.length() == 0){
             pw.println("<html>\n" +
                        "<head><title>���~</title></head>\n" +
                        "<body><center>\n" +
                        "<h2>���~</h2>\n" +
                        "�п�ܤ@�����~�C<br/>\n" +
                        "</center></body>\n" +
                        "</html>\n");
          }
         else if(carname.equals("�p�{��")){
            pw.println("<html>\n" +
                       "<head><title>\n" + carname + "</title></head>\n" +
                       "<body><center>\n" +
                       "<h2>\n" + carname + "</h2>\n" + "�L�k�ʶR" +
                       carname + "�C<br/>\n" +
                       "</center></body>\n" +
                       "</html>\n");
          }
          else {
            pw.println("<html>\n" +
                       "<head><title>\n" + carname + "</title></head>\n" +
                       "<body><center>\n" +
                       "<h2>\n" + carname + "</h2>\n" + "�P�±z�ʶR" +
                       carname + "�C<br/>\n" +
                       "</center></body>\n" +
                       "</html>\n");
          }
       }
       catch(Exception e){    
          e.printStackTrace();
       }
   } 
}
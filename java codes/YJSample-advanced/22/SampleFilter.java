import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SampleFilter implements Filter 
{
   public void doFilter(ServletRequest request,
                        ServletResponse response,
                        FilterChain chain)
               throws IOException, ServletException
   {
      // �]�w���e����
      response.setContentType("text/html; charset=UTF-8");

      // ��XHTML���
      PrintWriter pw = response.getWriter();
      pw.println("<html>\n" +
                 "<head><title>�d��</title></head>\n" +
                 "<body><center>\n" +
                 "<h2>�z�n</h2>" +
                 "<hr/>\n");

      chain.doFilter(request, response);

      pw.println("<hr/>���´f�U�C\n" +
                 "</center></body>\n" +
                 "</html>\n");

   }
   public void init(FilterConfig filterConfig){}
   public void destroy(){} 
}
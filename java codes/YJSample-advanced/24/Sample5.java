import mybeans.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sample5 extends HttpServlet
{
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
   throws ServletException
   {
      try{
         // ���oServlet Context
         ServletContext sc = getServletContext();

         // �إ�Bean
         CarDBBean cb = new CarDBBean();
      
         // �]�w�b�ШD��
         request.setAttribute("cb", cb);

         // ���ШD
         sc.getRequestDispatcher("/Sample5.jsp")
           .forward(request, response);
      }
      catch(Exception e){    
         e.printStackTrace();
      }
   } 
}
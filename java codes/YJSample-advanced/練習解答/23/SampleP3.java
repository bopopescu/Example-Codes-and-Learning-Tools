import mybeans.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SampleP3 extends HttpServlet
{
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
   throws ServletException
   {
      try{
         // ���o�����
         String tmp = request.getParameter("cars");
         String carname = new String(tmp.getBytes("8859_1"), "UTF-8");
         
         // �إ�Bean
         CarBean cb = new CarBean();
         cb.setCarname(carname);
         cb.makeCardata();
      
         // �]�w�ШD
         request.setAttribute("cb", cb);

         // ���oServlet Context
         ServletContext sc = getServletContext();

         // ���ШD
         if(carname.length() == 0){
            sc.getRequestDispatcher("/error.html")
              .forward(request, response);
         }
         else if(carname.equals("�p�{��")){
            sc.getRequestDispatcher("/SampleP3T.jsp")
              .forward(request, response);
         }
         else{
            sc.getRequestDispatcher("/SampleP3.jsp")
              .forward(request, response);
         }
      }
      catch(Exception e){    
         e.printStackTrace();
      } 
   } 
}
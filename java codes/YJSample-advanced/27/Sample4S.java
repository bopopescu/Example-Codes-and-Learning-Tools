import java.io.*;
import java.net.*;

public class Sample4S
{
   public static final int PORT = 10000;

   public static void main(String[] args)
   {
      Sample4S sm = new Sample4S();

      try{
         ServerSocket ss = new ServerSocket(PORT);

         System.out.println("���b���ݡC");
         while(true){
               Socket sc = ss.accept();
               System.out.println("�w��C");
                   
               PrintWriter pw = new PrintWriter
                  (new BufferedWriter
                  (new OutputStreamWriter(sc.getOutputStream())));
               pw.println("�o�̬O���A���C");
               pw.flush();
               pw.close();

               sc.close();
          }
      }
      catch(Exception e){
         e.printStackTrace();
      }
   }
}
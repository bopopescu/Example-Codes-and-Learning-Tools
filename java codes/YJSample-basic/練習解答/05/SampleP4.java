import java.io.*;

class SampleP4
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("�п�J0��10��������ơC");

      BufferedReader br =
       new BufferedReader(new InputStreamReader(System.in));

      String str = br.readLine();
      int res = Integer.parseInt(str);

      if(res >= 0 && res <= 10){
         System.out.println("���T���סC");
      }
      else{
         System.out.println("�����F�C");
      }
   }
}
import java.io.*;

class Sample4
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("�п�J�r��C");

      BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));

      String str = br.readLine();
      
      System.out.println("��~��J���r��O�G" + str);
   }
}

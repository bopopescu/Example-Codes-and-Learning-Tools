import java.io.*;

class Sample3
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("�п�J��ơC");

      BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));

      String str = br.readLine();
      int res = Integer.parseInt(str);

      if(res == 1){
         System.out.println("��J���O1�C");
      }
      else{
         System.out.println("��J���O1�H�~���Ʀr�C");
      }
   }
}

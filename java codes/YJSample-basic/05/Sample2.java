import java.io.*;

class Sample2
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
         System.out.println("��ܪ��O1�C");
      }

      System.out.println("�����B�z�C");
   }
}

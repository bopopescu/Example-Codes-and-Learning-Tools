import java.io.*;

class Sample5
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("�п�J�@�Ӿ�ơC");

      BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));

      String str = br.readLine();

      int num = Integer.parseInt(str);

      System.out.println("�z��J���Ʀr�O�G" + num);
   }
}

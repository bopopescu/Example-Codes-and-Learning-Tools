import java.io.*;

class Sample6
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("�п�J��Ӿ�ơC");

      BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));
      
      String str1 = br.readLine();
      String str2 = br.readLine();

      int num1 = Integer.parseInt(str1);
      int num2 = Integer.parseInt(str2);

      System.out.println("�Ĥ@�ӿ�J���O" + num1 + "�C");
      System.out.println("�ĤG�ӿ�J���O" + num2 + "�C");
   }
}

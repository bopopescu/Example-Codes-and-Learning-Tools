import java.io.*;

class Sample3
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("�п�J�r��C");

      BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));

      String str1 = br.readLine();

      System.out.println("�п�J�n�˯�����r�C");

      String str2 = br.readLine();
      char ch = str2.charAt(0);

      int num = str1.indexOf(ch);

      if(num != -1)
         System.out.println(str1 + "����" + (num+1) + "�Ӧr�N�O�u" + ch +"�v�C");
      else
         System.out.println(str1 + "���S���u" + ch + "�v�o�Ӧr�C");
   }
}
import java.io.*;

class SampleP5
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("�п�J�����M�魫�G");

      BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));
      
      String str1 = br.readLine();
      String str2 = br.readLine();

      double num1 = Double.parseDouble(str1);
      double num2 = Double.parseDouble(str2);

      System.out.println("�����O" + num1 + "�C");
      System.out.println("�魫�O" + num2 + "�C");
   }
}

import java.io.*;

class Sample3
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("�п�J��Ӿ�ơG");

      BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));

      String str1 = br.readLine();
      String str2 = br.readLine();

      int num1 = Integer.parseInt(str1);
      int num2 = Integer.parseInt(str2);

      System.out.println("�ۥ[�����G�O" + (num1+num2) + "�C");
   }
}

import java.io.*;

class SampleP3
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("�п�J�r��C");

      BufferedReader br =
       new BufferedReader(new InputStreamReader(System.in));

      String str1 = br.readLine();

      System.out.println("�ХH��ƨӿ�Ja�����J��m�C");

      String str2 = br.readLine();
      int num = Integer.parseInt(str2);

      StringBuffer str3 = new StringBuffer(str1);
      str3.insert(num, 'a');

      System.out.println("�r���ܦ�" + str3 + "�F�C");
   }
}

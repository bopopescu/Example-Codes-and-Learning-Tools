import java.io.*;

class SampleP2
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("�п�J�r��C");

      BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));

      String str1 = br.readLine();
      StringBuffer str2 = new StringBuffer(str1);
      str2.reverse();

      System.out.println("��" + str1 + "�ϹL�Ӽg�N�|�ܦ�" + str2 + "�C");
   }
}

import java.io.*;

class Sample2
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("�п�J�^��r�C");

      BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));

      String str = br.readLine();

      String stru = str.toUpperCase();
      String strl = str.toLowerCase();

      System.out.println("�ഫ���j�g�ɬ�" + stru + "�C");
      System.out.println("�ഫ���p�g�ɬ�" + strl + "�C");
   }
}

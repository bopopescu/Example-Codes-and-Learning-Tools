import java.io.*;

class SampleP3
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("�п�J����Ϊ�����G");

      BufferedReader br =
       new BufferedReader(new InputStreamReader(System.in));

      String str = br.readLine();

      int width = Integer.parseInt(str);

      System.out.println("����Ϊ����n�O" + (width*width) +"�C");
   }
}
import java.io.*;

class SampleP2
{
   public static void main(String[] args)throws IOException
   {
      System.out.println("�п�J�Ҹժ����Z�C(��J0�N����)");

      BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));

      int num = 0; 
      int sum = 0;

      do{
         String str = br.readLine();
         num = Integer.parseInt(str);
         sum += num;
      }while(num != 0);

      System.out.println("�`����" + sum + "���C");
   }
}

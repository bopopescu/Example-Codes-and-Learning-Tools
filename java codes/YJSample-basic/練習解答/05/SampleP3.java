import java.io.*;

class SampleP3
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

      if(num1 < num2){
         System.out.println(num1 + "��" + num2 + "�ӱo�p�C");
      }
      else if(num1 > num2){
         System.out.println(num1 + "��" + num2 + "�ӱo�j�C");
      }
      else{
         System.out.println("��ӼƭȬO�ۦP���C");
      }
   }
}

import java.io.*;

class Sample10
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("�п�J���Z����(1��5)�C");

      BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));

      String str = br.readLine();
      int res = Integer.parseInt(str);

      switch(res){
         case 1:
         case 2:
            System.out.println("�٭n�A�[�j��I");
            break;
         case 3:
         case 4:
            System.out.println("�N�o�˫O���U�h�a�C");
            break;
         case 5:
            System.out.println("�A���u�q�F�C");
            break;
         default:
            System.out.println("�п�J1��5�����Z����C");
            break;
      }
   }
}

import java.io.*;

class SampleP5
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("�п�J���Z�C");

      BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));

      String str = br.readLine();
      int res = Integer.parseInt(str);

      switch(res){
         case 1:
            System.out.println("�Ы��R�[�o�a�I");
            break;
         case 2:
            System.out.println("�ЦA�[�o�@�I�I");
            break;
         case 3:
            System.out.println("�z�٥i�H�@�o��n�I");
            break;
         case 4:
            System.out.println("�F�o�n�I");
            break;
         case 5:
            System.out.println("�z�u�O���u�q�F�I");
            break;
      }
   }
}

import java.io.*;

class Sample6
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("�п�Ja��b�C");

      BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));

      String str = br.readLine();
      char res = str.charAt(0);

      switch(res){
         case 'a':
            System.out.println("��J���Oa�C");
            break;
         case 'b':
            System.out.println("��J���Ob�C");
            break;
         default:
            System.out.println("�п�Ja��b�C");
            break;
      }
   }
}

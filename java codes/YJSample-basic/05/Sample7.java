import java.io.*;

class Sample7
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("�аݧA�O�k�ͶܡH");
      System.out.println("�п�JY��N�C");

      BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));

      String str = br.readLine();
      char res = str.charAt(0);

      if(res == 'Y' || res == 'y'){
         System.out.println("�A�O�k�ͰڡI");
      }
      else if(res == 'N' || res == 'n'){
         System.out.println("�p�O�k�ͰڡI");
      }
      else{
         System.out.println("�п�JY��N�C");
      }
   }
}

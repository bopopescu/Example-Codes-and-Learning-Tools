import java.io.*;

class Sample6
{
   public static void main(String[] args)
   {
      System.out.println("�п�J�r��C");

      try{
         BufferedReader br =
          new BufferedReader
           (new InputStreamReader(System.in));

         String str = br.readLine();
         System.out.println("�w��J�r��" + str + "�C");

      }
      catch(IOException e){
         System.out.println("��J��X���~�C");
      }
   }
}


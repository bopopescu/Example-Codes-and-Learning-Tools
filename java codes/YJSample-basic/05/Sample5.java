import java.io.*;

class Sample5
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("�п�J��ơC");

      BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));

      String str = br.readLine();
      int res = Integer.parseInt(str);

      switch(res){
         case 1:
            System.out.println("��J���O1�C");
            break;
         case 2:
            System.out.println("��J���O2�C");
            break;
         default:
            System.out.println("�п�J1��2�C");
            break;
       }
   }
}

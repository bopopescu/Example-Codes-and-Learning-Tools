import java.io.*;

class Sample9
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("�аݭn�b�ĴX���B�z�����j��O�H(1��10)");

      BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));

      String str = br.readLine();
      int res = Integer.parseInt(str);

      for(int i=1; i<=10; i++){
         System.out.println("��" + i + "�����B�z�C");
         if(i == res)
            break; 
      }
   }
}

import java.io.*;

public class Sample1
{
   public static void main(String[] args)
   {
      if(args.length != 1){
         System.out.println("�Ѽƪ��ƶq����C");
         System.exit(1);
      }

      try{
         File fl = new File(args[0]);
         System.out.println("�ɮצW�٬�" + fl.getName() + "�C");
         System.out.println("������|��" + fl.getAbsolutePath() + "�C");
         System.out.println("�ɮפj�p��" + fl.length() + "�줸�C");
      }   
      catch(Exception e){
         e.printStackTrace();
      }
   }
}
import java.io.*;

public class Sample2
{
   public static void main(String[] args)
   {
      if(args.length != 2){
         System.out.println("�Ѽƪ��ƶq����C");
         System.exit(1);
      }

      try{
         File fl1 = new File(args[0]);
         File fl2 = new File(args[1]);

         System.out.println("�ܧ�e���ɮצW�٬�" + fl1.getName() + "�C");

         boolean res = fl1.renameTo(fl2);

         if(res == true){
            System.out.println("�ɮצW���ܧ�F�C");
            System.out.println("�ܧ�᪺�ɮצW�٬�" + fl2.getName() + "�C");
         }
         else{
            System.out.println("�S��k�ܧ��ɮצW�١C");
         }
      }
      catch(Exception e){
         e.printStackTrace();
      }
   }
}
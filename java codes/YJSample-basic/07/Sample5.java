public class Sample5
{
   public static void main(String[] args)
   {
      int[] test1;
      test1 = new int[3];
      System.out.println("�ŧi�}�Ctest1�C");
      System.out.println("�T�O�}�C�����C");

      test1[0] = 80;
      test1[1] = 60;
      test1[2] = 22;

      int[] test2;
      System.out.println("�ŧi�}�Ctest2�C");

      test2 = test1;
      System.out.println("�Ntest1���w��test2�C");

      for(int i=0; i< test1.length; i++){
         System.out.println("test1�ҫ�����" + (i+1) + "�ӤH�����ƬO" + test1[i] + "���C");
      }

      for(int i=0; i< test2.length; i++){
         System.out.println("test2�ҫ�����" + (i+1) + "�ӤH�����ƬO" + test2[i] + "���C");
      }
   }
}

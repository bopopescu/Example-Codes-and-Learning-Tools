class Sample3
{
   public static void main(String[] args)
   {
      try{
         int test[];
         test = new int[5];

         System.out.println("�N�ȫ��w��test[10]�C");

         test[10] = 80;
         System.out.println("�N80���w��test[10]�C");

      }
      catch(ArrayIndexOutOfBoundsException e){

          System.out.println("�W�L�}�C���d��F�C");

      }
      finally{

          System.out.println("�̫�@�w�|����o�ӳB�z�C");
      }
      System.out.println("���Q�a���槹���F�C");
   }
}
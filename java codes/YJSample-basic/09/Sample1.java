// ���l���O
class Car
{
   int num;
   double gas;

   void show()
   {
      System.out.println("�����O" + num + "�C");
      System.out.println("�T�o�q�O" + gas + "�C");
   }
}

class Sample1
{
   public static void main(String[] args)
   {
      Car car1 = new Car();

      car1.num = 1234;
      car1.gas = 20.5;

      car1.show();
   }
}

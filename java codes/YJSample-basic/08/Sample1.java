// ���l���O
class Car
{
   int num;
   double gas;
}

class Sample1
{
   public static void main(String[] args)
   {
      Car car1;
      car1 = new Car();

      car1.num = 1234;
      car1.gas = 20.5;

      System.out.println("�����O" + car1.num + "�C");
      System.out.println("�T�o�q�O" + car1.gas + "�C");
   }
}
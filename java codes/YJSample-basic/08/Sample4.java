// ���l���O
class Car
{
   int num;
   double gas;

   void setNum(int n)
   {
      num = n;
      System.out.println("�N�����]��" + num + "�C");
   }

   void setGas(double g)
   {
      gas = g;
      System.out.println("�N�T�o�q�]��" + gas + "�C");
   }

   void show()
   {
      System.out.println("�����O" + num + "�C");
      System.out.println("�T�o�q�O" + gas + "�C");
   }
}

class Sample4
{
   public static void main(String[] args)
   {
      Car car1 = new Car();

      car1.setNum(1234);
      car1.setGas(20.5);
   }
}

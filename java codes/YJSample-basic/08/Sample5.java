// ���l���O
class Car
{
   int num;
   double gas;

   void setNumGas(int n, double g)
   {
      num = n;
      gas = g;
      System.out.println("�N�����]��" + num + "�A�T�o�q�]��" + gas + "�C");
   }

   void show()
   {
      System.out.println("�����O" + num + "�C");
      System.out.println("�T�o�q�O" + gas + "�C");
   }
}

class Sample5
{
   public static void main(String[] args)
   {
      Car car1 = new Car();

      int number = 1234;
      double gasoline = 20.5;

      car1.setNumGas(number, gasoline);
   }
}

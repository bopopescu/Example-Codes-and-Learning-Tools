// ���l���O
class Car
{
   int num;
   double gas;

   int getNum()
   {
      System.out.println("�լd�����C");
      return num;
   }

   double getGas()
   {
      System.out.println("�լd�T�o�q�C");
      return gas;
   }

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

class Sample6
{
   public static void main(String[] args)
   {
      Car car1 = new Car();

      car1.setNumGas(1234, 20.5);

      int number = car1.getNum();
      double gasoline = car1.getGas();

      System.out.println("�լd�˫~���ɱo��");
      System.out.println("�����O"+ number + "�A�T�o�q�O" + gasoline + "�C");
   }
}
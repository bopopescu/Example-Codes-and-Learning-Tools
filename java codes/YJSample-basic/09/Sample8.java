// ���l���O
class Car
{
   public static int sum = 0;

   private int num;
   private double gas;

   public Car()
   {
      num = 0;
      gas = 0.0;
      sum++;
      System.out.println("�Ͳ��F���l�C");
   }
   public void setCar(int n, double g)
   {
      num = n;
      gas = g;
      System.out.println("�N�����]��" + num + "�A�T�o�q�]��" + gas + "�C");
   }
   public static void showSum()
   {
      System.out.println("���l�`�@��" + sum + "�x�C");
   }
   public void show()
   {
      System.out.println("�����O" + num + "�C");
      System.out.println("�T�o�q�O" + gas + "�C");
   }
}

class Sample8
{
   public static void main(String[] args)
   {
      Car.showSum();

      Car car1 = new Car();
      car1.setCar(1234, 20.5);

      Car.showSum();

      Car car2 = new Car();
      car2.setCar(4567, 30.5);

      Car.showSum();
   }
}
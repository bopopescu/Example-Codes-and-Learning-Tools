// ���l���O
class Car
{
   private int num;
   private double gas;

   public Car()
   {
      num = 0;
      gas = 0.0;
      System.out.println("�Ͳ��F���l�C");
   }
   public Car(int n, double g)
   {
      num = n;
      gas = g;
      System.out.println("�Ͳ��F������" + num + "�A�T�o�q��" + gas + "�����l�C");
   }
   public void show()
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
      car1.show();

      Car car2 = new Car(1234,25.0);
      car2.show();
   }
}
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
   public void setCar(int n, double g)
   {
      num = n;
      gas = g;
      System.out.println("�N�����]��" + num + "�A�T�o�q�]��" + gas + "�C");
   }
   public void show()
   {
      System.out.println("�����O" + num + "�C");
      System.out.println("�T�o�q�O" + gas + "�C");
   }
}

class Sample7
{
   public static void main(String[] args)
   {
      Car car1;
      System.out.println("�ŧicar1�C");
      car1 = new Car();
      car1.setCar(1234,20.5);

      Car car2;
      System.out.println("�ŧicar2�C");

      car2 = car1;
      System.out.println("�Ncar1���w��car2�C");

      System.out.print("car1��");
      car1.show();
      System.out.print("car2��");
      car2.show();

      System.out.println("����car1��������ơC");
      car1.setCar(2345, 30.5);

      System.out.print("car1��");
      car1.show();
      System.out.print("car2��");
      car2.show();
   }
}

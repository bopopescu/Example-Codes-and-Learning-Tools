// ���l���O
class Car
{
   private int num;
   private double gas;

   public void setCar(int n)
   {
      num = n;
      System.out.println("�N�����]��" + num + "�C");
   }
   public void setCar(double g)
   {
      gas = g;
      System.out.println("�N�T�o�q�]��" + gas + "�C");
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

class Sample3
{
   public static void main(String[] args)
   {
      Car car1 = new Car();

      car1.setCar(1234, 20.5);
      car1.show();

      System.out.println("�u�ܧ󨮸��C");
      car1.setCar(2345);
      car1.show();

      System.out.println("�u�ܧ�T�o�q�C");
      car1.setCar(30.5);
      car1.show();
   }
}

// ���l���O
class Car
{
   protected int num;
   protected double gas;
   
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

// �ɨ����O
class RacingCar extends Car
{
   private int course;
   
   public RacingCar()
   {
      course = 0;
      System.out.println("�Ͳ��F�ɨ��C");
   }
   public void setCourse(int c)
   {
      course = c;
      System.out.println("�N�ɨ��s���]��" + course + "�C");
   }
   public void show()
   {
      System.out.println("�ɨ��������O" + num + "�C");
      System.out.println("�T�o�q�O" + gas + "�C");
      System.out.println("�ɨ��s���O" + course + "�C");
   }
}

class Sample5
{
   public static void main(String[] args)
   {
      Car car1;
      car1 = new RacingCar();

      car1.setCar(1234, 20.5);

      car1.show();
   }
}
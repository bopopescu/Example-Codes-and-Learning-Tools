// ��q�u�����O
abstract class Vehicle
{
   protected int speed;
   public void setSpeed(int s)
   {
     speed = s;
     System.out.println("�N�t�׳]��" + speed + "�F�C");
   }
   abstract void show();
}

// ���l���O
class Car extends Vehicle
{
   private int num;
   private double gas;
   
   public Car(int n, double g)
   {
      num = n;
      gas = g;
      System.out.println("�Ͳ��F������" + num + "�A�T�o�q��" + gas+ "�����l�C");
   }
   public void show()
   {
      System.out.println("�����O" + num + "�C");
      System.out.println("�T�o�q�O" + gas + "�C");
      System.out.println("�t�׬O" + speed + "�C");
   }
}

// �������O
class Plane extends Vehicle
{
   private int flight;

   public Plane(int f)
   {
      flight = f;
      System.out.println("�Ͳ��F" + flight + "�Z���������C");
   }
   public void show()
   {
      System.out.println("�������Z���O" + flight + "�C");
      System.out.println("�t�׬O" + speed + "�C");
   }
}

class Sample2
{
   public static void main(String[] args)
   {
      Vehicle[] vc;
      vc = new Vehicle[2];

      vc[0] = new Car(1234, 20.5);
      vc[1] = new Plane(232);

      for(int i=0; i<vc.length; i++){
          if(vc[i] instanceof Car)
            System.out.println("��" + (i+1) + "�Ӫ���OCar���O�C");
          else
            System.out.println("��" + (i+1) + "�Ӫ��󤣬OCar���O�C");
      }
   }
}

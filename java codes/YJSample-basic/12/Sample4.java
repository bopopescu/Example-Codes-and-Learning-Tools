// ��q�u�㤶��
interface iVehicle
{
   void vShow();
}
// ���Ƥ���
interface iMaterial
{
   void mShow();
}
// ���l���O
class Car implements iVehicle,iMaterial
{
   private int num;
   private double gas;
   
   public Car(int n, double g)
   {
      num = n;
      gas = g;
      System.out.println("�Ͳ��F������" + num + "�A�T�o�q��" + gas + "�����l�C");
   }
   public void vShow()
   {
      System.out.println("�����O" + num + "�C");
      System.out.println("�T�o�q�O" + gas + "�C");
   }
   public void mShow()
   {
      System.out.println("���l������O�K�C");
   }
}

class Sample4
{
   public static void main(String[] args)
   {
      Car car1 = new Car(1234, 20.5);
      car1.vShow();
      car1.mShow();
   }
}

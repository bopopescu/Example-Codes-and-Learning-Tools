// ���l���O
class Car
{
   private int num;
   private double gas;
   private String name;

   public Car()
   {
      num = 0;
      gas = 0.0;
      name = "�S���W��";
      System.out.println("�Ͳ��F���l�C");
   }
   public void setCar(int n, double g)
   {
      num = n;
      gas = g;
      System.out.println("�N�����]��" + num + "�A�T�o�q�]��" + gas + "�C");
   }
   public void setName(String nm)
   {
      name = nm;
      System.out.println("�N���W�]��" + name + "�C");
   }
   public void show()
   {
      System.out.println("�����O" + num + "�C");
      System.out.println("�T�o�q�O" + gas + "�C");
      System.out.println("���W�O" + name + "�C");
   }
}

class Sample8
{
   public static void main(String[] args)
   {
      Car car1;
      car1 = new Car();

      car1.show();

      int number = 1234;
      double gasoline = 20.5;
      String str = "1����";

      car1.setCar(number, gasoline);
      car1.setName(str);

      car1.show();
   }
}

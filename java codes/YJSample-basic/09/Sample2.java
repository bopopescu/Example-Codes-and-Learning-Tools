// ���l���O
class Car
{
   private int num;
   private double gas;
   public void setNumGas(int n, double g)
   {
      if(g > 0 && g < 1000){
         num = n;
         gas = g;
         System.out.println("�N�����]��" + num + "�A�T�o�q�]��" + gas + "�C");
       }
       else{
         System.out.println(g + "���O���T���T�o�q�C");
         System.out.println("�L�k�ܧ�T�o�q�C");
       }
   }
   public void show()
   {
      System.out.println("�����O" + num + "�C");
      System.out.println("�T�o�q�O" + gas + "�C");
   }
}

class Sample2
{
   public static void main(String[] args)
   {
      Car car1 = new Car();

      // �L�k�i��o�˪��s���Ȃ�܂��B
      //car1.num = 1234;
      //car1.gas = -10.0;

      car1.setNumGas(1234, 20.5);
      car1.show();

      System.out.println("���w�����T���T�o�q(-10.0)�ݬ�...�C");

      car1.setNumGas(1234, -10.0);
      car1.show();
   }
}

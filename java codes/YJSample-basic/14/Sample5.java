class CarException extends Exception
{
}
// �T�����O
class Car
{
   private int num;
   private double gas;
   
   public Car()
   {
      num = 0;
      gas = 0.0;
      System.out.println("�w�Ͳ��F�T���C");
   }
   public void setCar (int n, double g) throws CarException
   {
     if(g < 0)
     {
        CarException e = new CarException();
        throw e;
     }
     else{
        num = n;
        gas = g;
        System.out.println("�N�����]��" + num + "�A�T�o�q�]��" + gas + "�C");
     }
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
      try{
         car1.setCar(1234, -10.0);
      }
      catch(CarException e){
         System.out.println("�ߥX" + e + "�F�C");
      }
      car1.show();
   }
}

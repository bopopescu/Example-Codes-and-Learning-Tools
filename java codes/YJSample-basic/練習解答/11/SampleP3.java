class A
{
   A()
   {
      System.out.println("�o�OA���L�Ѽƫغc���C");
   }
   A(int a)
   {
      System.out.println("�o�OA����Ѽƫغc���C");
   }
}
class B extends A
{
   B()
   {
      System.out.println("�o�OB���L�Ѽƫغc���C");
   }
   B(int b)
   {
      super(b);
      System.out.println("�o�OB����Ѽƫغc���C");
   }
}

class SampleP3
{
   public static void main(String[] args)
   {
      B b1 = new B();
      B b2 = new B(10);
   }
}
class A
{
   A()
   {
      System.out.println("�o�O�S���Ѽƪ��غc���C");
   }
   A(int a)
   {
      this();
      System.out.println("�o�O�@�ӰѼƪ��غc���C");
   }
}
class SampleP4
{
   public static void main(String[] args)
   {
      A a1 = new A();
      A a2 = new A(10);
   }
}
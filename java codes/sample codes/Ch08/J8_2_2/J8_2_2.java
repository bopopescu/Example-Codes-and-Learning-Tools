public class J8_2_2 {
  public static void main (String[] args) {
    int n1 = 12, n2 = 2, n3;
    try {
      n3 = n1 / n2;
      System.out.println("�۰����G : " + n3);
    }
    catch(Exception e) {
      System.out.println("���~���� : ");
      System.out.println(e);
    }
    finally {
      System.out.println("���� finally �ԭz");
    }
  }
}
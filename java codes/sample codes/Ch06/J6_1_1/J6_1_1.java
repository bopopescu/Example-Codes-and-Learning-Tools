class CRectangle {
  int high;
  int wide;
  int area = 0;
  
  int Perimeter(int h, int w) {
    high = h;
    wide = w;
    return (high + wide) * 2; 
  }
  void CalArea() {
    area = high * wide;
  }
}

public class J6_1_1 {
  public static void main(String[] args) {
    CRectangle rec1;
    rec1 = new CRectangle();
    rec1.high =20;
    rec1.wide =15;
    rec1.CalArea();
    int r1_area = rec1.area;
    int r1_peri;
    r1_peri = rec1.Perimeter(20, 15);
    System.out.println("*** �x�Τ@ ***");
    System.out.println("���� = " + rec1.high);
    System.out.println("�e�� = " + rec1.wide);
    System.out.println("���n = " + r1_area);
    System.out.println("�P�� = " + r1_peri); 
  }
}
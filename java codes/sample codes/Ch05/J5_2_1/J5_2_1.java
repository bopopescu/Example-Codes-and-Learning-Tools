public class J5_2_1 {
  static void Change(int x,int y){
    int t;
    t = x;
    x = y;
    y = t;
    System.out.println("Change ��k  : ");
    System.out.println("x = " + x +"      y = " + y); 
  }  
        
  public static void main (String[] args) {
    int x = 3, y = 5;
    System.out.println("main ��k -- �I�s Change ��k�e : ");
    System.out.println("x = " + x +"      y = " + y);  
    Change(x,y); 
    System.out.println("main ��k -- �I�s Change ��k�� : ");
    System.out.println("x = " + x +"      y = " + y);    
  }
}
import java.io.*;
public class J2_6_1 {
  public static void main(String[] args) throws IOException {
    BufferedReader keyin;
    keyin = new BufferedReader(new InputStreamReader(System.in));
    double w1, w2, ave;
    System.out.print("��J 1 ���魫�G ");
    String st = keyin.readLine(); 
    w1 = Double.parseDouble(st);
    System.out.print("��J 2 ���魫�G ");
    w2 = Double.parseDouble(keyin.readLine());
    ave = (w1 + w2 ) / 2;
    String st_ave = String.valueOf(ave);
    System.out.print("��H�����魫 �G " + st_ave);
    //System.out.print("��H�����魫 �G " + ave);
  }
}        

import java.io.*;
public class J2_4_4 {
  public static void main(String[] args) throws IOException {
    BufferedReader keyin;
    keyin = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("��J��Ʀr��G ");
    String st = keyin.readLine();
    int num = Integer.parseInt(st);
    System.out.println("��ܾ�ƼƭȡG " + num);
  }
}
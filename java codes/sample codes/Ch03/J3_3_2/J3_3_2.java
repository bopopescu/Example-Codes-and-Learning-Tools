import java.io.*;
public class J3_3_2 {
  public static void main(String[] args) throws IOException {
    BufferedReader keyin = new BufferedReader(
                           new InputStreamReader(System.in));
    System.out.print("�п�J�p�{����p�������ơG");
    float km = Float.parseFloat(keyin.readLine());
    int money = 75;
    km -= 1.5; 
    while (km > 0){
      money += 5;
      km -= 0.3;
    }
    System.out.println("����(��)�G" + money);
  }
}
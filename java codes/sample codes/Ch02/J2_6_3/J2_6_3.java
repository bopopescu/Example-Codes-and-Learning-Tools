import java.io.*;
public class J2_6_3 {
  public static void main(String[] args) throws IOException {
    BufferedReader keyin = new BufferedReader(
                           new InputStreamReader(System.in));
    System.out.print("��J�f�~�W�١G ");        
    String goods_name = keyin.readLine();
    System.out.print("��J����G ");
    float price = Float.parseFloat(keyin.readLine());
    System.out.print("��J�ƶq�G ");
    int num = Integer.parseInt(keyin.readLine());
    float money = price * num;
    
    System.out.println("\n�f�~�W��\t���\t�ƶq\t���B");
    System.out.println("========================================");
    System.out.println(goods_name + "\t\t" + price + "\t" + num + "\t" + money);
  }
}        

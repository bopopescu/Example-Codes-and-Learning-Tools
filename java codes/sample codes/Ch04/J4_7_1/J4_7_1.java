import java.util.*;
public class J4_7_1 {
  public static void main (String[] args) {
    String str1 = "�T�o,28.7,40";
    StringTokenizer str2 = new StringTokenizer(str1, ",");
    String name;
    int num;
    double price, money;
    System.out.println(str2.countTokens() + " �����");
    name = str2.nextToken();
    price = Double.parseDouble(str2.nextToken());
    num = Integer.parseInt(str2.nextToken());
    money = price * num;
    System.out.println("�~�W : " + name);
    System.out.println("��� : " + price);
    System.out.println("�ƶq : " + num);
    System.out.println("���B : " + money);  
  }
}

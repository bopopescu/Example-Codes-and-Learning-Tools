import java.io.*;
public class J2_6_2 {
  public static void main(String[] args) throws IOException {
    String goods_name;    // �f�~�W���ܼ�
    float price, money;   // ����ܼ�, ���B�ܼ�
    int num;              // �ƶq�ܼ�
    BufferedReader keyin; // ��J��y����
    keyin = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("��J�f�~�W�١G ");        
    goods_name = keyin.readLine();
    System.out.print("��J����G ");
    price = Float.parseFloat(keyin.readLine());
    System.out.print("��J�ƶq�G ");
    num = Integer.parseInt(keyin.readLine());
    money = price * num;
    System.out.println("");
    System.out.println("�f�~�W�١G " + goods_name);
    System.out.println("����G " + price + "��");
    System.out.println("�ƶq�G " + num);
    System.out.println("���B�G " + money + "��");
  }
}        

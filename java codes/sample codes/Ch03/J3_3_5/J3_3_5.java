import java.io.*;
public class J3_3_5 {
  public static void main(String[] args) throws IOException {
    BufferedReader keyin = new BufferedReader(
                           new InputStreamReader(System.in));
    int count = 0;
    String pw = "Java2";
    boolean pass = false;
    do {
      count++;
      System.out.print("�п�J�K�X�G");
      String pw_keyin = keyin.readLine();
      if (pw_keyin.equals(pw)) {
        System.out.println("�K�X���T�A�w����{");
        pass = true;
        break;
      } else {
        System.out.println("��" + count + "���K�X��J���~�I\n");
      }    
    } while (count < 3);
    if (!pass) System.out.println("�T���K�X��J���~�A�ڵ��ϥΡI");
  }
}
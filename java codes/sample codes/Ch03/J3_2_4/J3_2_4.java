import java.io.*;
public class J3_2_4 {
  public static void main(String[] args) throws IOException {
    BufferedReader keyin = new BufferedReader(
                           new InputStreamReader(System.in));
    System.out.print("�п�J���ơG");
   	int score = Integer.parseInt(keyin.readLine());
    if (score >= 80) {
      System.out.println("���Z�ܦn�A���~��O���I");
    } else if (score >= 60) {
      System.out.println("���Z���q�A�n�h�h�[�o�I");
    } else { 
      System.out.println("���Z����A���Ϭٮ��@�I");
    }
  }
}
import java.io.*;
public class J5_4_2 {
  public static void main (String[] args) throws IOException{
    BufferedReader keyin = new BufferedReader(
                           new InputStreamReader(System.in));
    int i, score = 0;
    for(i = 1; i <= 2; i++) {
      System.out.println("\n��" + i + "�D" );
      int num1 = (int)(Math.random() * 11) + 10;
      int num2 = (int)(Math.random() * 11);
      int right = num1 * num2;
      System.out.print(num1 + " x " + num2 + " = ");
      int ans = Integer.parseInt(keyin.readLine());
      if(ans == right){
        System.out.println("����F�I");
        score += 50;
      } else {
        System.out.println("�����F �I");
        System.out.println("���T���� : " + right);                 
      }
    }
    System.out.println("\n�`�� = " + score);
  }
}
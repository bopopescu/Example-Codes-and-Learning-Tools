import java.io.*;
public class J3_4_1 {
  public static void main(String[] args) throws IOException {
    BufferedReader keyin = new BufferedReader(
                           new InputStreamReader(System.in));
    int num = 38;
    boolean guessOK=false;
    System.out.println("*****  �q�Ʀr�C��  *****\n");
    do {
      System.out.print("�бq 0~99 ���q�@�ӼƦr�G ");
      int guess = Integer.parseInt(keyin.readLine());
      if (guess == num) {
        guessOK = true;
        System.out.println("�z�I�n�ΰڡA�A�ש�q��F�I");
      } else {
        if (guess > num) {
          System.out.println("�Ӥj�F�I�A�q�p�@�I�I\n");
        } else {
          System.out.println("�Ӥp�F�I�A�q�j�@�I�I\n");
        }    
      }   
    } while(!guessOK);
  }
}        

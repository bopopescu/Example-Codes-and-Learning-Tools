import java.io.*;
public class J4_8_1 {
  public static void main (String[] args) throws IOException {
    BufferedReader keyin = new BufferedReader(
                           new InputStreamReader(System.in));
    String[] prob = {"2 + 6 = ( )","_ you a girl?",
                     "�ɫe _ _ ���A�ìO�a�W���C"};
    String[] right = {"8", "Are", "����"};
    String ans; 
    int i, j, count = 0;
    for(i = 0; i < prob.length; i++) {
      System.out.println("\n��" + (i+1) + "�D" );
      System.out.println(prob[i]);
      System.out.print("��J���� : ");
      ans = keyin.readLine();
      if(ans.equals(right[i])) {
        System.out.println("����F�I");
        count++;
      } else {
        System.out.println("�����F �I");
        System.out.println("���T���� : " + right[i]);                 
      }
    }
               
    switch(count) {
      case 0 :
      case 1 : System.out.println("\n�[�o�I");
               break;
      case 2 : System.out.println("\n�L���I");
               break;
      case 3 : System.out.println("\n����A�@�ŴΡI");
    }         
  }
}
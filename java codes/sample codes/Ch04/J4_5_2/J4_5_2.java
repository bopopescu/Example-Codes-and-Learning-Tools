import java.io.*;
public class J4_5_2 {
  public static void main (String[] args) throws IOException {
    BufferedReader keyin = new BufferedReader(
                           new InputStreamReader(System.in));
    int[] account = {18,14,12,16,10};
    String[] name = {"����","�i�T","���G","���|","�G�@"}; 
    int i, num, search_num;
    System.out.println("\n �u �� �j �M �k" );
    System.out.print(" �� �J �s �� : " );
    search_num = Integer.parseInt(keyin.readLine());
    num = -1;
    for(i = 0; i < account.length; i++) {
      if(account[i] == search_num) {
        num = i;  
        break;
      }
    }
    if (num == -1)
      System.out.println("\n �d �L �� �s �� " );
    else {
      System.out.println("\n �s��\t�m�W");
      System.out.println(" " + account[num] + "\t" + name[num]);
    }
  }
}
import java.io.*;
public class J3_2_5 {
  public static void main(String[] args) throws IOException {
    char ch;
    System.out.print("�п�Ja�Bb�䤤�@�Ӧr���G");
    ch = (char)System.in.read();
    switch (ch) {
      case 'A':
      case 'a':
           System.out.println(ch + " ---> apple");
           break;
      case 'B':
      case 'b':
           System.out.println(ch + " ---> book");
           break;
      default:
           System.out.println("��J���~�I");
    }
  }
}
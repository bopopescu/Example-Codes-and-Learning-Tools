import java.io.*;

public class J14_3_1 {
  public static void main(String[] args) {
    try {
      String filename = "student.txt";
      FileWriter fWrite = new FileWriter(filename);
      BufferedWriter fOut = new BufferedWriter(fWrite);
      fOut.write("���@, 85, 90");   // �g�J�@�����
      fOut.newLine();               // ����r��
      fOut.write("�i�T, 65, 67");  
      fOut.newLine();
      fOut.flush();                 // �M�z�w�İ�
      fWrite.close();               // �����ɮ�
    }
    catch (IOException e) {
      System.out.println("�ɮ׳B�z���~!!");
    } 
  }
}
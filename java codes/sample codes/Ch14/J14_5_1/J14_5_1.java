import java.io.*;
import javax.swing.*;
public class J14_5_1 {
  public static void main(String[] args) {
    String data;
    try {
      // �إ��ɮת���,�Y���O�}���ɮ� poem1.txt �P poem3.txt
      String fname1 = "poem1.txt";
      BufferedReader fIn1 = new BufferedReader(new FileReader(fname1));
      String fname3 = "poem3.txt";
      BufferedWriter fOut3= new BufferedWriter(new FileWriter(fname3));

      // Ū�� poem1.txt �ɮ׸��,�N����Ʀs�� poem3.txt
      do {
        data = fIn1.readLine();
        if(data == null) break;
        fOut3.write(data);
        fOut3.newLine();
      } while (true);
      fIn1.close();      // ����poem1.txt�ɮ�

      // �إ��ɮת���,�Y�}���ɮ� poem2.txt 
      String fname2 = "poem2.txt";
      BufferedReader fIn2 = new BufferedReader(new FileReader(fname2));

      // Ū�� poem2.txt �ɮ׸��,�N����Ʊ���s�� poem3.txt
      do {
        data = fIn2.readLine();
	     if(data == null) break;
        fOut3.write(data);
        fOut3.newLine();
      } while (true);
      
      fIn2.close();      // ����poem2.txt�ɮ�
      fOut3.close();     // ����poem3.txt�ɮ�
    }
    catch (IOException e) {
      JOptionPane.showMessageDialog(null,"�ɮ׳B�z���~!!");
    }
  }
}


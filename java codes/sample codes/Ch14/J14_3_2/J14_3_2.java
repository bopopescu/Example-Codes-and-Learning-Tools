import java.io.*;

public class J14_3_2 {
  public static void main(String[] args) {
    String data;
    try {
      FileReader fRead = new FileReader("..\\J14_3_1\\student.txt");
      BufferedReader fIn = new BufferedReader(fRead);
	  do {
        data = fIn.readLine();    // Ū���@��r����
		if(data == null) break;     // �Y���Ū�������A�����j��
		System.out.println(data);   // ��ܩ�Ū�������
	  } while (true);
	  fRead.close();               // �����ɮ�
	}
	catch (IOException e) {
      System.out.println("�ɮ׳B�z���~!!");
	}
  }
}
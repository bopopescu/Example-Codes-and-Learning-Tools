import java.io.*;

public class J14_4_1 {
  public static void main(String[] args) {
    String fname = "mountain.jpg";   // �h�C���ɮצW��
    try {
      // ���o��J��y	
      FileInputStream fIn = new FileInputStream (fname);
      byte data[] = new byte[fIn.available()]; // ���o�ɮת��j�p
	  fIn.read(data);              // ��J��y�s��}�C
	  fIn.close();                 // ������J��y 
	  
	  // ���o��X��y
	  FileOutputStream fOut = new FileOutputStream ("�ƻs-" + fname);
	  fOut.write(data);            // ��X��y���o�}�C���
	  System.out.println("�ɮפw�ƻs....");
	  fOut.close();                // ������X��y 
	}
	catch (IOException e) {
      System.out.println("�ɮ׳B�z���~!!");
	}
  }
}
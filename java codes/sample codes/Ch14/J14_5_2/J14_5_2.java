import java.util.*;
import java.io.*;
import javax.swing.*;

public class J14_5_2 {
  public static void main(String[] args) {
    String data, fName = "";
    StringTokenizer str;
    String name;
    int chi, eng;
    double avg;

    // ����L��J��Ƽg�J���ɮפ� 
    try {
      fName = JOptionPane.showInputDialog("��J�s�ɸ��|");
      BufferedWriter fOut = new BufferedWriter(new FileWriter(fName, true));
      while(true){
        data = JOptionPane.showInputDialog("��J�ǥͪ����\n�m�W,���,�^��\n(����������<<Enter>>)");
        if(data.equals("")) break;
        fOut.write(data);
        fOut.newLine();
      }
      fOut.close();
    }
    catch (Exception e) { 
      JOptionPane.showMessageDialog(null, "�s�ɸ��|���~!!");
      System.exit(0);
    }
  
    // ���ɮ�Ū�X�����ܿù��W��
    try {
      BufferedReader fIn = new BufferedReader(new FileReader(fName));
      System.out.println("�m�W\t���\t�^��\t����");
      do {
        data = fIn.readLine();
        if(data == null || data.equals("")) break;
        str = new StringTokenizer(data, ",");   // �H","���ѥX�l�r��
        name = str.nextToken( );
        chi = Integer.parseInt(str.nextToken( ));
        eng = Integer.parseInt(str.nextToken( ));
        avg = (chi + eng) / 2.0;
        System.out.println(name + "\t" + chi + "\t" + eng + "\t" + avg );
      } while (true);
      fIn.close();
    }
    catch (IOException e) {
      JOptionPane.showMessageDialog(null,"�ɮ׳B�z���~!!");
    }
  }
}
import java.util.*;
import java.io.*;
public class J5_5_1 {
  public static void main(String[] argv) throws IOException {
    long t1, t2, tot_t;
    Date date_time1 = new Date();
    Calendar date_time2 = Calendar.getInstance();
    t1 = System.currentTimeMillis();
    System.out.println("\n�}�l : " + t1 + "�@��\n");
    System.out.println(date_time1);
    System.out.println(date_time2.get(Calendar.YEAR) + "�~");
    System.out.println(date_time2.get(Calendar.MONTH)+1 + "��");
    System.out.println(date_time2.get(Calendar.DAY_OF_MONTH) + "��");
    System.out.println(date_time2.get(Calendar.HOUR_OF_DAY) + "��");
    System.out.println(date_time2.get(Calendar.MINUTE) + "��");
    System.out.println(date_time2.get(Calendar.SECOND) + "��");
    System.out.println(date_time2.get(Calendar.MILLISECOND) + "�@��");

    System.out.println("\n�� << Enter >> �������ծɶ�"); 
    int p = System.in.read();
    t2 = System.currentTimeMillis();
    System.out.println("\n���� : " + t2 + "�@��");
    tot_t = t2 - t1;
    System.out.println("\n���� : " + (double)tot_t / 1000 + "��");
  }
}

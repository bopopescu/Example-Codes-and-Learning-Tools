import java.util.*;
import java.text.*;
public class J5_6_5 {
  public static void main (String[] args) {
    long sum = 0;
    for(int i = 0; i <= 4; i++){
      Date date1 = new Date();
      DateFormat df = new SimpleDateFormat("yyyy�~ M��dd�� E a hh�I mm�� ss��");
      System.out.println(df.format(date1)); 
      for(long j = 0; j <= 100000000; j++){
        sum++;
      }
    }
    System.out.println("sum = " + sum); 
  }
}
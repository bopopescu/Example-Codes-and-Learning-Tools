public class J3_2_3 {
  public static void main (String[] args){
    int eng = 80, computer = 91;
   	double avg;
   	avg = (double)(eng + computer) / 2;
    System.out.println("�������Z = " + avg);
    System.out.println("�q������ = " + computer);
    if (avg >= 60) {
      if (computer >= 90) {
        System.out.println("���ߡI�A�w�F�����зǡA�B�i�O�e��T�t");
      } else {
        System.out.println("���ߡI�A�w�F�����з�"); 
      }
    } else { 
      System.out.println("��p�A�A���F�����зǡI");
    }
  }
}
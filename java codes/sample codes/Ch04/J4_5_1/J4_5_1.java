public class J4_5_1 {
  public static void main (String[] args){
    int[] a = {18,14,12,16,10};
    int i, j, k, t;
    System.out.print(" �� �� �e : " );
    for(i = 0; i < a.length; i++){
      System.out.print(a[i] + "  ");
    }
    System.out.println();
 
    for(i = 3; i >= 0; i--) {
      for(j = 0; j <= i; j++)
        if(a[j] > a[j+1]) {
          t = a[j+1];
          a[j+1] = a[j];
          a[j] = t;
        }        
            
      System.out.print(" ��  " + (4 - i) + " �� : ");
      for(k = 0; k < a.length; k++){
        System.out.print(a[k] + "  ");
      }
      System.out.println();
    }
  }
}
public class J5_8_1 {
  static int Max(int[] data) {
    int max;
    max = data[0];
    for(int i = 1; i < data.length - 1; i++) {
      if(data[i] >= max) 
        max = data[i];            
    }
    return(max);
  }

  public static void main (String[] args){
    int[] A,B;
    A = new int[]{2,4,10,6,8};
    B = new int[]{7,15,3};
    int max_A, max_B, max_num;
    max_A = Max(A);
    System.out.println("A ���̤j��" +  " : " + max_A); 
    max_B = Max(B);
    System.out.println("B ���̤j��" +  " : " + max_B); 
    if(max_A >= max_B)
      max_num = max_A;
    else
      max_num = max_B;
    System.out.println("A �P B ���̤j��" +  " : " + max_num);
  }
}
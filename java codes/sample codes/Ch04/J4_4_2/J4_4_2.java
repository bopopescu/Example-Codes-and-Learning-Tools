public class J4_4_2 {
  public static void main (String[] args){
    String[][] data;
    data = new String[][] {{"�����q","�ժ�","�q�u"},{"������","�a��"},
                           {"���R��","�u��","�߮v","����"}}; 
    int i,j;
    for(i = 0; i < data.length; i++){
      System.out.print(data[i][0] + " : ");
      for(j = 1; j < data[i].length; j++){
        if(j == data[i].length - 1)
          System.out.print(data[i][j]);
        else
          System.out.print(data[i][j] + " , ");
      }
      System.out.println();
    }
  }
}
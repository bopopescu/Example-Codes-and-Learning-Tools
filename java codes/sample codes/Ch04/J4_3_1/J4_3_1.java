public class J4_3_1 {
  public static void main (String[] args){
    int i, j, i_max, j_max;
    String[] name = {"���Ѥ�","���߼w","�`�p"};
    int[][] salary = {{21000,5000,1200,0},{45000,120,4500,0},{0,0,0,0}}; 
    i_max = salary.length;
    j_max = salary[0].length;
    for(i = 0; i < i_max-1; i++)
      salary[i][j_max-1] = salary[i][0] + salary[i][1] - salary[i][2];

    for(j = 0; j < j_max; j++)
      for(i = 0; i < i_max-1; i++)
        salary[i_max-1][j] += salary[i][j];
   
    System.out.println("\n\t�m�W\t���~\t�[�Z�O\t�Ұ��O\t��o���B" );
    for(i = 0; i < i_max; i++) {
      System.out.print("\t" + name[i]);             
      for(j = 0; j < j_max; j++)
        System.out.print("\t" + salary[i][j] + " ");
      System.out.println();
    }
  }
}
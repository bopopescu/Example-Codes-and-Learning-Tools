import java.sql.*;
import java.util.Scanner;

public class Store_Main {
  public static void main(String[] args) {
    // ��Ʈw�W��
    String db_name="store";
	CStore_Operation DataManager = new CStore_Operation(db_name);
    boolean exit = false;
    Scanner reader = new Scanner(System.in); 
    while(!exit) {
      System.out.println("=========================================="); 
      System.out.print("1. �s�W  2. �R�� 3.�����f�~ 4. ����  : "); 
      int choice = reader.nextInt(); 
      System.out.println("==========================================");                     
      switch(choice) {
        case 1:     
             // ��J�f�~���
             System.out.println("1. �s�W�f�~ ");
		     System.out.print("��J�f�~�s�X : ");
             reader = new Scanner(System.in); 		 
		     String code= reader.nextLine();
		     System.out.print("��J�f�~��� : ");		 
		     float price= reader.nextFloat();
		     System.out.print("��J�f�~�ƶq : ");	 
		     int num = reader.nextInt();
		     DataManager.InsertDB(code,price,num);
             break;
 		case 2: 
		     // �R���f�~���
             System.out.println("2. �R���f�~ ");	
		     System.out.print("��J�f�~�s�X : ");
		     reader = new Scanner(System.in); 		 
		     String search_code2= reader.nextLine();    
             DataManager.DeleteDB(search_code2);
             break;
        case 3:
             // ��X�����f�~���
             System.out.println("3. �����f�~��� ");
             System.out.println("\n�s�X\t���\t�ƶq\t���B");
             DataManager.Display();
             break;
        case 4:
             exit = true;
             break;
      }
    }
  }
}

import java.io.*;
public class J6_5_2 {
  public static void main(String[] args) throws IOException {
    BufferedReader keyin = new BufferedReader(
                           new InputStreamReader(System.in));
    System.out.print("�п�J�Ǵ����Z�G ");
    int s = Integer.parseInt(keyin.readLine());
    System.out.println();      
        
    String stu_pass;
    String stu_lever;
    CStudent stu = new CStudent();
    stu_pass = stu.Pass(s);
    stu_lever = stu.Lever(s);
    System.out.println(stu_pass + "�A" + stu_lever + "\n"); 
  }
}

// �w�qIPass����
interface IPass {
  int score = 60;
  String Pass(int s);
}

// �w�qILever����
interface ILever {
  int high = 80;
  int low = 60;
  String Lever(int s);
}

// �w�qCStudent���O��@IPass�����PILever����
class CStudent implements IPass, ILever {
  String passOK;
  public String Pass(int s) {
    if (s >= score)
      passOK = "�ή�";
    else
      passOK = "���ή�";
    return passOK;
  }
  String leverN;

  public String Lever(int s) {
    if (s >= high) 
      leverN = "��{�u��";
    if ((s >= low) && (s < high)) 
      leverN = "�t�j�H�N";
    if (s < low) 
      leverN = "���ݥ[�j";
    return leverN;   
  }
}


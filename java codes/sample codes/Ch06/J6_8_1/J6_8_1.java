import java.io.*;
public class J6_8_1 {
  public static void main(String[] args) throws IOException {
    BufferedReader keyin = new BufferedReader(
                           new InputStreamReader(System.in));
    System.out.print("�п�J�Ǵ����Z�G");
    int s = Integer.parseInt(keyin.readLine());
               
    CCollage pass1 = new CCollage();
    pass1.score = s;
    pass1.Pass();
    System.out.println(pass1.title + pass1.passOK); 
                
    CGraduate pass2 = new CGraduate();
    pass2.score = s;
    pass2.Pass();
    System.out.println(pass2.title + pass2.passOK);   
  }
}

// �w�q���Z�q�L��IPass����
interface IPass {
  int s1 = 60;  // �j�Ǥή榨�Z
  int s2 = 70;  // ��s�Ҥή榨�Z
  void Pass();
}

// �w�qCCollage���O��@IPass����
class CCollage implements IPass {
  int score;
  String title = "�j�Ǧ��Z�A";
  String passOK;
  public void Pass() {
    if (score >= s1)
      passOK = "�ή�";
    else
      passOK = "���ή�";
  }
}

//�w�qCGraduate���O��@IPass����
class CGraduate implements IPass {
  int score;
  String title = "��s�Ҧ��Z�A";
  String passOK;
  public void Pass() {
    if (score >= s2)
      passOK = "�ή�";
    else
      passOK = "���ή�";
  }
}


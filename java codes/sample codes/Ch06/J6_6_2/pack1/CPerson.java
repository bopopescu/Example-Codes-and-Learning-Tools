package pack1;
public class CPerson {
  private String name, sex;
  public void SetName(String st1) {
    name = st1;
  }
  public void SetSex(String st2) {
    sex = st2;
  }
  public void GetWelcome() {
    System.out.print(name);
    if(sex.equals("�k"))
      System.out.println("�p�j�A�w����{�I"); 
    else
      System.out.println("���͡A�w����{�I");
  }
}

class CDogKind {
  private String kind;
  void SetKind(String k) {
    kind = k;
  }
  void Show() {
    System.out.println("�d�������G" + kind);
  }
}

class CDog extends CDogKind {
  private String name;
  private int age;
  void SetName(String n) {
    name = n;
  }
  void SetAge(int a) {
    age = a;
  }
  void Show() {
    System.out.println("�d���W�r�G" + name);
    System.out.println("�d���~�֡G" + age);
  }
}   
  
public class J6_4_2 {
  public static void main(String[] args) {
    CDog dog = new CDog();
    dog.SetKind("�̮�|");
    dog.SetName("���B");
    dog.SetAge(2);
    dog.Show();
  }
}
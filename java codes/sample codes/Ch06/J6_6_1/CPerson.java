class CPerson {
  private String name, sex;
  void SetName(String st1) {
    name = st1;
  }
  void SetSex(String st2) {
    sex = st2;
  }
  void GetWelcome() {
    System.out.print(name);
    if(sex.equals("�k"))
      System.out.println("�p�j�A�w����{�I"); 
    else
      System.out.println("���͡A�w����{�I");
  }
}
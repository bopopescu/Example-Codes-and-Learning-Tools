import javax.swing.*;

class cDialog {
  cDialog() {
    String s_wide = JOptionPane.showInputDialog("�п�J�x�μe�סG", "10");
    String s_high = JOptionPane.showInputDialog("�п�J�x�ΰ��סG", "5");
    String unit = JOptionPane.showInputDialog("�п�J���׳��G", "����");
    int area = Integer.parseInt(s_wide) * Integer.parseInt(s_high);
    String result = "�x�έ��n���G " + area + " ����" + unit;
    JOptionPane.showMessageDialog(null, result, "���⵲�G",
                                  JOptionPane.INFORMATION_MESSAGE);
    System.exit(0);
  }
}

public class J11_2_2 {
  public static void main(String[] args) {
    cDialog myDialog = new cDialog();
  }
}
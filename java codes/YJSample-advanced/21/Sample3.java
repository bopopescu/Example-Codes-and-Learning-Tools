import java.awt.*;
import javax.swing.*;

public class Sample3 extends JApplet
{
   private JLabel lb;
   private JTable tb;
   private JScrollPane sp;

   public void init()
   {
      // �ǳƸ��
      String[] colname = {"�W��", "����", "���"};
      String[][] data = {
         {"�T��", "1200��","10-01"},
         {"�d��", "2400��","10-05"},
         {"�Ԩ�", "3600��","10-06"},
         {"�p�{��", "2500��","10-10"},
         {"�]��", "2600��","10-11"},
         {"�g�A��", "300��","10-12"},
         {"�}��", "800��","10-15"},
         {"�T����", "600��","10-18"},
         {"����", "15000��","10-19"},
         {"���ɾ�", "3500��","10-21"},
         {"���b", "32800��","10-22"}
      };

      // �إߤ���
      lb = new JLabel("�w����{�C");
      tb = new JTable(data, colname);
      sp = new JScrollPane(tb);

      // �s�W��e����
      add(lb, BorderLayout.NORTH);
      add(sp, BorderLayout.CENTER);
    }
}
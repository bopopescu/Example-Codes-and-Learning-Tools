import java.awt.*;
import javax.swing.*;

public class Sample3 extends JApplet
{
   private JLabel lb;
   private JButton bt;

   public void init()
   {
      // �إߤ���
      lb= new JLabel("�w����{�C");
      bt = new JButton("�ʶR");

      // �s�W��e����
      add(lb, BorderLayout.NORTH);
      add(bt, BorderLayout.SOUTH);
   }
}

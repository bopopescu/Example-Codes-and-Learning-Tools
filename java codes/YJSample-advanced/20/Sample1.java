import java.awt.*;
import javax.swing.*;

public class Sample1 extends JApplet
{
   private JLabel lb;
   private JButton bt;

   public void init()
   {
      // �إߤ���
      lb = new JLabel("�w����{�C");
      bt = new JButton("�ʶR");

      // �]�w�e��
      setLayout(new BorderLayout());

      // �s�W��e����
      add(lb, BorderLayout.NORTH);
      add(bt, BorderLayout.SOUTH);
   }
}
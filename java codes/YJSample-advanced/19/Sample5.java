import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample5 extends JApplet
{
   private JLabel lb;

   public void init()
   {
      // �إߤ���
      lb = new JLabel("�w����{�C");

      // �s�W��e����
      add(lb, BorderLayout.NORTH);

      // �n����ť��
      addMouseListener(new SampleMouseListener());
   }

   // ��ť�����O
   class SampleMouseListener extends MouseAdapter
   {
      public void mouseClicked(MouseEvent e)
      {
         lb.setText("���´f�U�C");
      }
   }
}

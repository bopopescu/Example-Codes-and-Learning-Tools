import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample6 extends JApplet
{
   private JLabel lb;

   public void init()
   {
      // �إߤ���
      lb = new JLabel("�z�n�C");

      // �s�W��e����
      add(lb, BorderLayout.NORTH);

      // �n����ť��
      addMouseListener(new SampleMouseListener());
   }

   // ��ť�����O
   class SampleMouseListener extends MouseAdapter
   {
      public void mouseEntered(MouseEvent e)
      {
         lb.setText("�w����{�C");
      }
      public void mouseExited(MouseEvent e)
      {
         lb.setText("�z�n�C");
      }
   }
}
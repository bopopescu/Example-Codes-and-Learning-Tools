import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SampleP2 extends JApplet
{
   private JButton bt;

   public void init()
   {
      // �إߤ���
      bt = new JButton("�w��C");

      // �s�W��e����
      add(bt, BorderLayout.NORTH);

      // �n����ť��
      bt.addMouseListener(new SampleMouseListener());
   }

   // ��ť�����O
   class SampleMouseListener extends MouseAdapter
   {
      public void mouseEntered(MouseEvent e)
      {
         bt.setText("�w����{�C");
      }
      public void mouseExited(MouseEvent e)
      {
         bt.setText("�w��C");
      }
   }
}
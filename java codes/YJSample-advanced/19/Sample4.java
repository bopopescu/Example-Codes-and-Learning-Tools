import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample4 extends JApplet
{
   private JLabel lb;
   private JButton bt;

   public void init()
   {
      // �إߤ���
      lb = new JLabel("�w����{�C");
      bt = new JButton("�ʶR");

      // �s�W��e����
      add(lb, BorderLayout.NORTH);
      add(bt, BorderLayout.SOUTH);

      // �n����ť��
      bt.addActionListener(new SampleActionListener());
   }

   // ��ť�����O
   class SampleActionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         lb.setText("���´f�U�C");
      }
   }
}

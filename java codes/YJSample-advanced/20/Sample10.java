import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample10 extends JApplet
{
   private JLabel lb;
   private JPanel pn;
   private JButton bt;
   private Icon ic;

   public void init()
   {
      // �إߤ���
      lb = new JLabel("�w����{�C");
      pn = new JPanel();
      bt = new JButton("�ʶR");
      ic = new ImageIcon(getImage(getDocumentBase(), "car.gif"));

      // �]�w����
      bt.setIcon(ic);

      // �s�W��e����
      pn.add(bt);
      add(lb, BorderLayout.NORTH);
      add(pn, BorderLayout.SOUTH);

      // �n����ť��
      bt.addActionListener(new SampleActionListener());
   }

   // ��ť�����O
   class SampleActionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         lb.setText("���´f�U�C");
         bt.setEnabled(false);
      }
   }
}
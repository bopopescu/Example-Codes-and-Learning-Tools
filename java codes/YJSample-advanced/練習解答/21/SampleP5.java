import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SampleP5 extends JApplet
{
   private JLabel  lb;
   private JPanel pn;
   private JButton bt;

   public void init()
   {
      // �إߤ���
      lb= new JLabel("�w����{�C");
      pn = new JPanel();
      bt = new JButton("�ʶR");

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
         String title = "�P�¥d"; 
         String msg = "�D�`�P�±z���ʶR�C";
         int type = JOptionPane.INFORMATION_MESSAGE;

         JOptionPane.showMessageDialog(getContentPane(), msg, title, type);
      }
   }
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample10 extends JApplet
{
   private JLabel lb;
   private JPanel pn;
   private JButton bt;

   public void init()
   {
      // �إߤ���
      lb = new JLabel("�w����{�C");
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
         Container cnt = getContentPane();

         String title1 = "�T�{"; 
         String msg1 = "�u���n�ʶR�ܡH";
         int type1 = JOptionPane.YES_NO_OPTION;

         String title2 = "�ʶR"; 
         String msg2 = "���´f�U�C";
         int type2 = JOptionPane.INFORMATION_MESSAGE;

         int res =  JOptionPane.showConfirmDialog(cnt, msg1, title1, type1);
         if(res == JOptionPane.YES_OPTION)
            JOptionPane.showMessageDialog(cnt, msg2, title2, type2);
      }
   }
}
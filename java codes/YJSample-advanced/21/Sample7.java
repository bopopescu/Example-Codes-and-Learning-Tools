import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample7 extends JApplet
{
   private JLabel lb;
   private JMenuBar mb;
   private JMenu[] mn = new JMenu[4];
   private JMenuItem[] mi = new JMenuItem[6];

   public void init()
   {
      // �إߤ���
      lb = new JLabel("�w����{�C");
      mb = new JMenuBar();
   
      mn[0] = new JMenu("�D�ﶵ1"); 
      mn[1] = new JMenu("�D�ﶵ2"); 
      mn[2] = new JMenu("�l�ﶵ1"); 
      mn[3] = new JMenu("�l�ﶵ2"); 

      mi[0] = new JMenuItem("�T��");
      mi[1] = new JMenuItem("�d��");
      mi[2] = new JMenuItem("�Ԩ�");
      mi[3] = new JMenuItem("�p�{��");
      mi[4] = new JMenuItem("�]��");
      mi[5] = new JMenuItem("�g�A��");

      // �s�W��e����
      mn[0].add(mi[0]);
      mn[0].add(mi[1]);

      mn[2].add(mi[2]);
      mn[2].add(mi[3]);

      mn[3].add(mi[4]);
      mn[3].add(mi[5]);

      mn[1].add(mn[2]);
      mn[1].addSeparator();
      mn[1].add(mn[3]);

      mb.add(mn[0]);
      mb.add(mn[1]);

      add(mb, BorderLayout.NORTH);
      add(lb, BorderLayout.CENTER);

      // �n����ť��
      for(int i=0; i<mi.length; i++)
      {
         mi[i].addActionListener(new SampleActionListener()); 
      }
   }

   // ��ť�����O
   class SampleActionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         JMenuItem tmp =(JMenuItem) e.getSource();
         String str = tmp.getText();
         lb.setText("�z��ܤF" + str + "�C");
      }
   }
}
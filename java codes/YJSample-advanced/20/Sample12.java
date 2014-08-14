import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample12 extends JApplet
{
   private JLabel lb;
   private JPanel pn;
   private JRadioButton rb1, rb2, tmp;
   private ButtonGroup bg;

   public void init()
   {
      // �إߤ���
      lb  = new JLabel("�w����{�C");
      pn = new JPanel();
      rb1 = new JRadioButton("�T��", true);
      rb2 = new JRadioButton("�d��", false);
      bg  = new ButtonGroup();

      // �s�W����s�s�դ�
      bg.add(rb1);
      bg.add(rb2);

      // �s�W��e����
      pn.add(rb1);
      pn.add(rb2);
      add(lb, BorderLayout.NORTH);
      add(pn, BorderLayout.SOUTH);

      // �n����ť��
      rb1.addActionListener(new SampleActionListener());
      rb2.addActionListener(new SampleActionListener());
   }

   // ��ť�����O
   class SampleActionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         tmp = (JRadioButton) e.getSource(); 
         lb.setText("�z��ܤF" + tmp.getText() + "�C");
      }
   }
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SampleP1 extends JApplet
{
   private JLabel lb;
   private JPanel pn;
   private JRadioButton rb1, rb2, rb3, tmp;
   private ButtonGroup bg;

   public void init()
   {
      // �إߤ���
      lb  = new JLabel("�w����{�C");
      pn = new JPanel();
      rb1 = new JRadioButton("��", true);
      rb2 = new JRadioButton("��", false);
      rb3 = new JRadioButton("��", false);
      bg  = new ButtonGroup();

      // �]�w����
      lb.setOpaque(true);
      lb.setBackground(Color.yellow);

      // �s�W����s�s�դ�
      bg.add(rb1);
      bg.add(rb2);
      bg.add(rb3);

      // �s�W��e����
      pn.add(rb1);
      pn.add(rb2);
      pn.add(rb3);
      add(lb, BorderLayout.NORTH);
      add(pn, BorderLayout.SOUTH);

      // �n����ť��
      rb1.addActionListener(new SampleActionListener());
      rb2.addActionListener(new SampleActionListener());
      rb3.addActionListener(new SampleActionListener());
   }

   // ��ť�����O
   class SampleActionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
          tmp = (JRadioButton) e.getSource(); 
          if(tmp == rb1)
             lb.setBackground(Color.yellow);
          else if(tmp == rb2)
             lb.setBackground(Color.red);
          else if(tmp == rb3)
             lb.setBackground(Color.blue);
      }
   }
}
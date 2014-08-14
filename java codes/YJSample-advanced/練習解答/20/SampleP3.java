import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SampleP3 extends JApplet
{
   private JLabel lb;
   private JPanel pn;
   private JRadioButton rb1, rb2, rb3, tmp;
   private ButtonGroup bg;

   public void init()
   {
      // �إߤ���
      lb = new JLabel("Hello!");
      pn = new JPanel();
      rb1 = new JRadioButton("���q", true);
      rb2 = new JRadioButton("����", false);
      rb3 = new JRadioButton("����", false);
      bg  = new ButtonGroup();

      // �]�w����
      lb.setFont(new Font("Serif", Font.PLAIN, 20));
      lb.setHorizontalAlignment(JLabel.CENTER);

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
             lb.setFont(new Font("Serif", Font.PLAIN, 20));
          else if(tmp == rb2)
             lb.setFont(new Font("Serif", Font.BOLD, 20));
          else if(tmp == rb3)
             lb.setFont(new Font("Serif", Font.ITALIC, 20));
      }
   }
}
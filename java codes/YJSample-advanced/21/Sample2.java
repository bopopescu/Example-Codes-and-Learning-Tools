import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample2 extends JApplet
{
   private JLabel lb;
   private JComboBox cb;

   public void init()
   {
      // �ǳƸ��
      String[] str = {"�T��", "�d��", "�Ԩ�",
                      "�p�{��", "�]��", "�g�A��"};

      // �إߤ���
      lb = new JLabel("�w����{�C");
      cb = new JComboBox(str);

      // �s�W��e����
      add(lb, BorderLayout.NORTH);
      add(cb, BorderLayout.SOUTH);

      // �n����ť��
      cb.addActionListener(new SampleActionListener()); 
   }

   // ��ť�����O
   class SampleActionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         JComboBox tmp = (JComboBox) e.getSource();
         String str = (String) tmp.getSelectedItem();
         lb.setText("�z��ܤF" + str + "�C");
      }
   }
}
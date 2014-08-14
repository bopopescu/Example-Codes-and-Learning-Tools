import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Sample1 extends JApplet
{
   private JLabel lb;
   private JList lst;
   private JScrollPane sp;

   public void init()
   {
      // �ǳƸ��
      String[] str = {"�T��", "�d��", "�Ԩ�",
                      "�p�{��", "�]��", "�g�A��",
                      "�}��", "�T����", "������",
                      "����", "���ɾ�", "���b"};

      // �إߤ���
      lb = new JLabel("�w����{�C");
      lst = new JList(str);
      sp = new JScrollPane(lst);

      // �s�W��e����
      add(lb, BorderLayout.NORTH);
      add(sp, BorderLayout.CENTER);

      // �n����ť��
      lst.addListSelectionListener(new SampleListSelectionListener()); 
   }

   // ��ť�����O
   class SampleListSelectionListener implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent e)
      {
         JList tmp = (JList) e.getSource();
         String str = (String) tmp.getSelectedValue();
         lb.setText("�z��ܤF" + str + "�C");
      }
   }
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample11 extends JFrame
{
   private JLabel lb;
   private JList lst;
   private JScrollPane sp;

   public static void main(String[] args)
   {
      Sample11 sm = new Sample11();
   }
   public Sample11()
   {
      // �]�w���D
      super("�d��");

      // �ǳƸ��
      String str[] = {"�T��", "�d��", "�Ԩ�",
                      "�p�{��", "�]��", "�g�A��",
                      "�}��", "�T����", "������",
                      "����", "���ɾ�", "���b",};

      // �إߤ���
      lb = new JLabel("�w����{�C");
      lst = new JList(str);
      sp = new JScrollPane(lst);

      // �s�W��e����
      add(lb, BorderLayout.NORTH);
      add(sp, BorderLayout.CENTER);

      // �n����ť��
      addWindowListener(new SampleWindowListener());

      // �]�w�ج[
      setSize(200, 200);
      setVisible(true);
   }

   // ��ť�����O
   class SampleWindowListener extends WindowAdapter
   {
      public void windowClosing(WindowEvent e)
      {
         System.exit(0);
      }
   }
}
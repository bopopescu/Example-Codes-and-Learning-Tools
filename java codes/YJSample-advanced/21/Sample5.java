import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample5 extends JApplet
{
   private JList lst;
   private JScrollPane sp;
   private JButton bt;
   private JPanel pn;

   public void init()
   {
      // �ǳƸ��
      String[] str = {"�T��", "�d��", "�Ԩ�",
                      "�p�{��", "�]��", "�g�A��",
                      "�}��", "�T����", "������",
                      "����", "���ɾ�", "���b"};

      // �إߤ���
      lst = new JList(str);
      sp = new JScrollPane(lst);
      bt = new JButton("�ܧ��˵�");
      pn = new JPanel();

      // �s�W��e����
      pn.add(bt);
      add(sp, BorderLayout.CENTER);
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
         try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            SwingUtilities.updateComponentTreeUI(cnt);
         }
         catch(Exception ex){
            ex.printStackTrace();
         }
      }
   }
}
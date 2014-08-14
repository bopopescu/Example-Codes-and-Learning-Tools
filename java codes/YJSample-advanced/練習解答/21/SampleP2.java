import java.util.*;
import java.text.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class SampleP2 extends JApplet
{
   private JLabel lb;
   private JList lst;
   private JScrollPane sp;

   public void init()
   {
      // �إߤ���
      lb = new JLabel("��ܤ���C");
      lst = new JList(new MyListModel());
      sp = new JScrollPane(lst);

      // �s�W��e����
      add(lb, BorderLayout.NORTH);
      add(sp, BorderLayout.SOUTH);

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
         lb.setText("�O" + str + "�a�C");
      }
   }

   // �ҫ����O
   class MyListModel extends AbstractListModel
   {
       DateFormat df;

       public MyListModel()
       {
           df = new SimpleDateFormat("yyyy/MM/dd");
       }
       public int getSize()
       {
          return 50;
       }
       public Object getElementAt(int index)
       {
           Calendar cl = Calendar.getInstance(); 
           cl.setTime(new Date());
           cl.add(Calendar.DATE, index);

           String str = df.format(cl.getTime());
           return str;
       }
   }
}
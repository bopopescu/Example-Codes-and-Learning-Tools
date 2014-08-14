import java.util.*;
import java.text.*;
import java.awt.*;
import javax.swing.*;

public class SampleP1 extends JApplet
{
   private JLabel lb;
   private JList lst;
   private JScrollPane sp;

   public void init()
   {
      // �إߤ���
      lb = new JLabel("�w����{�C");
      lst = new JList(new MyListModel());
      sp = new JScrollPane(lst);

      // �s�W��e����
      add(lb, BorderLayout.NORTH);
      add(sp, BorderLayout.SOUTH);
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
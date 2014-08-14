import java.util.*;
import java.text.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class Sample4 extends JApplet
{
   private JLabel lb;
   private JTable tb;
   private JScrollPane sp;

   public void init()
   {
      // �إߤ���
      lb = new JLabel("�w����{�C");
      tb = new JTable(new MyTableModel());
      sp = new JScrollPane(tb);

      // �s�W��e����
      add(lb, BorderLayout.NORTH);
      add(sp, BorderLayout.CENTER);
    }

   // �ҫ����O
   class MyTableModel extends AbstractTableModel
   {
       DateFormat df;

       public MyTableModel()
       {
           df = new SimpleDateFormat("yyyy/MM/dd");
       }
       public int getRowCount() 
       {
          return 50;
       }
       public int getColumnCount() 
       {
          return 2;
       }
       public Object getValueAt(int row, int column) 
       {
          Calendar cl = Calendar.getInstance(); 
          cl.setTime(new Date());
          cl.add(Calendar.DATE, row);

          switch(column){
             case 0:
               return (df.format(cl.getTime()));
             case 1:
                if(cl.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) 
                   return "�����";
                else 
                   return "�}����";      
             default:
                return "";
           }
       }
       public String getColumnName(int column) 
       {
         switch(column){
             case 0:
                return "���";
             case 1:
                return "�}������";
             default:
                return "";
          }
       }
   }
}
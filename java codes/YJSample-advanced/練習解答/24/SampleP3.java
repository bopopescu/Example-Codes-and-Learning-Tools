import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class SampleP3 extends JFrame
{
   private JTable tb;
   private JScrollPane sp;

   public static void main(String[] args)
   {
      SampleP3 sm = new SampleP3();
   }
   public SampleP3()
   {
      // �]�w���D
      super("�d��");

      // �إߤ���
      tb = new JTable();
      sp = new JScrollPane(tb);

      // �s�W��e����
      add(sp);

      // �n����ť��
      addWindowListener(new SampleWindowListener());

      // �]�w�ج[
      setSize(300, 300);
      setVisible(true);

      try{
         // �ǳƳs�u
         String url = "jdbc:derby:fooddb;create=true";
         String usr = "";
         String pw = "";

         // �s�����Ʈw
         Connection cn = DriverManager.getConnection(url, usr, pw);

         // �ǳƬd��
         Statement st = cn.createStatement();
         String qry = "SELECT * FROM ���G��ƪ�";

         // �i��d��
         ResultSet rs = st.executeQuery(qry);
         tb.setModel(new MyTableModel(rs));

         // �����s�u
         rs.close();
         st.close();
         cn.close();
      }
      catch(Exception e){
         e.printStackTrace();
      }
   }

   // ��ť�����O
   class SampleWindowListener extends WindowAdapter
   {
      public void windowClosing(WindowEvent e)
      {
         System.exit(0);
      }
   }

   // �ҫ����O
   class MyTableModel extends AbstractTableModel
   {
      private ArrayList<String> colname;
      private ArrayList<ArrayList> data;

      public MyTableModel(ResultSet rs)
      {
         try{

            // ���o���ƶq
            ResultSetMetaData rm = rs.getMetaData();
            int cnum = rm.getColumnCount();
            colname = new ArrayList<String>(cnum);

            // ���o���W��
            for(int i=1; i<=cnum; i++){
               colname.add(rm.getColumnName(i));
            }

            // ���o�C
            data = new ArrayList<ArrayList>(); 
            while(rs.next()){
               ArrayList<String> rowdata = new ArrayList<String>();
               for(int i=1; i<=cnum; i++){
                  rowdata.add(rs.getObject(i).toString());
               }
               data.add(rowdata);
            }
         }
         catch(Exception e){
            e.printStackTrace();
         }
      }
      public int getRowCount() 
      {
         return data.size();
      }
      public int getColumnCount() 
      {
         return colname.size();
      }
      public Object getValueAt(int row, int column) 
      {
         ArrayList rowdata = (ArrayList) (data.get(row));
         return rowdata.get(column);
      }
      public String getColumnName(int column) 
      {
         return (String) colname.get(column);
      }
   }
}
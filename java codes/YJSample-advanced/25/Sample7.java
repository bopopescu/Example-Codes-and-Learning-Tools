import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample7 extends JFrame
{
   private JLabel[] lb;
   private Icon ic;
   private JScrollPane[] sp; 
   private JTabbedPane tp;

   public static void main(String[] args)
   {
      Sample7 sm = new Sample7();
   }
   public Sample7()
   {
      // �]�w���D
      super("�d��");

      // �إߤ���
      File fl = new File(".");
      File[] fls = fl.listFiles(new MyFileFilter());

      lb = new JLabel[fls.length];
      sp = new JScrollPane[fls.length];
      tp = new JTabbedPane();

      for(int i=0; i<fls.length; i++){
         ic = new ImageIcon(fls[i].getName());
         lb[i] = new JLabel(ic);
         sp[i] = new JScrollPane(lb[i]);
      }

      // �s�W��e����
      for(int i=0; i<fls.length; i++){
         tp.add(fls[i].getName(), sp[i]);
      }         
      add(tp, BorderLayout.CENTER);

      // �n����ť��
      addWindowListener(new SampleWindowListener());

      // �]�w�ج[
      setSize(300, 200);
      setVisible(true);
   }

   // �L�o�����O
   class MyFileFilter implements FilenameFilter
   {
      public boolean accept(File f, String n)
      {
         if(n.toLowerCase().endsWith(".jpg")){
            return true;
         }
         return false;
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
}

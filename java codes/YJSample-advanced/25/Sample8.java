import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample8 extends JFrame
{
   private JTextArea[] ta; 
   private JScrollPane[] sp; 
   private JPanel pn;
   private JDesktopPane dp;
   private JInternalFrame[] itf;

   public static void main(String[] args)
   {
      Sample8 sm = new Sample8();
   }
   public Sample8()
   {
      // �]�w���D
      super("�d��");

      // �إߤ���
      dp = new JDesktopPane();

      File fl = new File(".");
      File[] fls = fl.listFiles(new MyFileFilter());

      try{
         ta = new JTextArea[fls.length];
         sp = new JScrollPane[fls.length];
         itf = new JInternalFrame[fls.length];

         for(int i=0; i<fls.length; i++){
            ta[i] = new JTextArea();
            sp[i] = new JScrollPane(ta[i]);
            itf[i] = new JInternalFrame(fls[i].getName(), true, true, true, true);
            BufferedReader br = new BufferedReader(new FileReader(fls[i]));
            ta[i].read(br, null);
            br.close();
         }

         // �s�W��e����
         for(int i=0; i<fls.length; i++){
            itf[i].add(sp[i]);
            dp.add(itf[i]);
            itf[i].setLocation(i*10,i*10);
            itf[i].setSize(300,200);
            itf[i].setVisible(true);
         }         
         add(dp, BorderLayout.CENTER);

         // �n����ť��
         addWindowListener(new SampleWindowListener());

         // �]�w�ج[
         setSize(500, 400);
         setVisible(true);
      }  
      catch(Exception e){
         e.printStackTrace();
      }
   }
   // �L�o�����O
   class MyFileFilter implements FilenameFilter
   {
      public boolean accept(File f, String n)
      {
         if(n.toLowerCase().endsWith(".java")){
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

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample11 extends JFrame
{
   private JPanel pn;
   private JList ls;
   private JScrollPane sp;
   private JButton bt;

   public static void main(String[] args)
   {
      Sample11 sm = new Sample11();
   }
   public Sample11()
   {
      // �]�w���D
      super("�d��");

      // �إߤ���
      File fl = new File(".");
      File[] fls = fl.listFiles(new MyFileFilter());
      String[] st = new String[fls.length];
      for(int i=0; i<fls.length; i++){
         st[i] = fls[i].getName();
      }

      ls = new JList(st);
      sp = new JScrollPane(ls);
      bt = new JButton("�Ұ�");
      pn = new JPanel();

      // �s�W��e����
      pn.add(bt);

      add(sp, BorderLayout.CENTER);
      add(pn, BorderLayout.SOUTH);

      // �n����ť��
      bt.addActionListener(new SampleActionListener());
      addWindowListener(new SampleWindowListener());

      // �]�w�ج[
      setSize(350, 300);
      setVisible(true);
  }

   // ��ť�����O
   class SampleActionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try{
            if(e.getSource() == bt){
               Desktop dp = Desktop.getDesktop();
                  dp.open(new File((String)ls.getSelectedValue()));
            }
         }
         catch(IOException ex){
            System.out.println("");
         }
      }
   }
   class SampleWindowListener extends WindowAdapter
   {
      public void windowClosing(WindowEvent e)
      {
         System.exit(0);
      }
   }

   // �L�o�����O
   class MyFileFilter implements FileFilter
   {
      public boolean accept(File f)
      {
         if(f.isFile()){
            return true;
         }
         return false;
      }
   }
}
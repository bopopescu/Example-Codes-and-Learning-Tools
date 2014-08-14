import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SampleP2 extends JFrame
{
   private JPanel pn;
   private JLabel lb;
   private JTextArea ta;
   private JScrollPane sp;
   private JButton bt1, bt2;

   public static void main(String[] args)
   {
      SampleP2 sm = new SampleP2();
   }
   public SampleP2()
   {
      // �]�w���D
      super("�d��");

      // �إߤ���
      lb = new JLabel("�п���ɮסC");
      ta = new JTextArea();
      sp = new JScrollPane(ta);

      pn = new JPanel();
      bt1 = new JButton("Ū��");
      bt2 = new JButton("�x�s");

      // �s�W��e����
      pn.add(bt1);
      pn.add(bt2);

      add(lb, BorderLayout.NORTH);
      add(sp, BorderLayout.CENTER);
      add(pn, BorderLayout.SOUTH);

      // �n����ť��
      bt1.addActionListener(new SampleActionListener());
      bt2.addActionListener(new SampleActionListener());
      addWindowListener(new SampleWindowListener());

      // �]�w�ج[
      setSize(600, 400);
      setVisible(true);
  }

   // ��ť�����O
   class SampleActionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         Container cnt = getContentPane();
         JFileChooser fc = new JFileChooser();
         fc.setFileFilter(new MyFileFilter());
         try{
            if(e.getSource() == bt1){
               int res = fc.showOpenDialog(cnt);
               if(res == JFileChooser.APPROVE_OPTION){       
                  File fl = fc.getSelectedFile();
                  BufferedReader br = new BufferedReader(new FileReader(fl));
                  ta.read(br, null);
                  br.close();
               }
            }
            else if(e.getSource() == bt2){
               int res = fc.showSaveDialog(cnt);
               if(res == JFileChooser.APPROVE_OPTION){       
                  File fl = fc.getSelectedFile();
                  BufferedWriter bw = new BufferedWriter(new FileWriter(fl));
                  ta.write(bw);
                  bw.close();
              }
            }
         }
         catch(Exception ex){
            ex.printStackTrace();
         }
      }
      // �L�o�����O
      class MyFileFilter extends javax.swing.filechooser.FileFilter
      {
         public boolean accept(File f)
         {
            if(f.isDirectory()){
               return true;
            }

            String fn = f.getName();
            if(fn.toLowerCase().endsWith(".java")){
               return true;
            }
            return false;
         }
         public String getDescription()
         {
            return "java�ɮ�";
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
}
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;

public class Sample4C extends JFrame
{
   public static final String HOST = "localhost";
   public static final int PORT = 10000;

   private JTextArea ta;
   private JPanel pn;
   private JButton bt;

   public static void main(String[] args)
   {
      Sample4C sm = new Sample4C();
   }
   public Sample4C()
   {
      // �]�w���D
      super("�d��");

      // �إߤ���
      ta = new JTextArea();
      pn = new JPanel();
      bt = new JButton("�s�u");
 
      // �s�W��e����
      pn.add(bt);
      add(ta, BorderLayout.CENTER);
      add(pn, BorderLayout.SOUTH);

      // �n����ť��
      bt.addActionListener(new SampleActionListener());
      addWindowListener(new SampleWindowListener());

      // �]�w�ج[
      setSize(300, 200);
      setVisible(true);
   }

   // ��ť�����O
   class SampleActionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try{
            Socket sc = new Socket(HOST, PORT);
            BufferedReader  br = new BufferedReader
                 (new InputStreamReader(sc.getInputStream()));
            String str = br.readLine();
            ta.setText(str);
            br.close();
            sc.close();
         }
         catch(Exception ex){
            ex.printStackTrace();
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
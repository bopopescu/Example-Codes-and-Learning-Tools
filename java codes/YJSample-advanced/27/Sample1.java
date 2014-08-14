import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample1 extends JFrame
{
   private JTextField tf;
   private JEditorPane ep;
   private JScrollPane sp;
   private JPanel pn;
   private JButton bt;

   public static void main(String[] args)
   {
      Sample1 sm = new Sample1();
   }
   public Sample1()
   {
      // �]�w���D
      super("�d��");

      // �إߤ���
      tf = new JTextField();
      ep = new JEditorPane();
      sp = new JScrollPane(ep);
      pn = new JPanel();
      bt = new JButton("Ū�J");

      // �s�W��e����
      pn.add(bt);

      add(tf, BorderLayout.NORTH);
      add(sp, BorderLayout.CENTER);
      add(pn, BorderLayout.SOUTH);

      // �n����ť��
      bt.addActionListener(new SampleActionListener());
      addWindowListener(new SampleWindowListener());

      // �]�w�ج[
      setSize(300, 300);
      setVisible(true);

      tf.requestFocus();
  }

   // ��ť�����O
   class SampleActionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try{
            URL url = new URL(tf.getText());
            ep.setPage(url);
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
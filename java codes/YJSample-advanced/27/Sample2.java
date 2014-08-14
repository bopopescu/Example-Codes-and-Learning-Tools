import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;

public class Sample2 extends JFrame
{
   private JLabel lb1, lb2;
   private JTextField tf1, tf2;

   public static void main(String[] args)
   {
      Sample2 sm = new Sample2();
   }
   public Sample2()
   {
      // �]�w���D
      super("�d��");

      try{
         InetAddress ia = InetAddress.getLocalHost();

         // �إߤ���
         lb1 = new JLabel("�D���W��");
         lb2 = new JLabel("IP��}");
         tf1 = new JTextField(ia.getHostName());    
         tf2 = new JTextField(ia.getHostAddress()); 

         // �]�w�e��
         setLayout(new GridLayout(2, 2));

         // �s�W��e����
         add(lb1);
         add(tf1);
         add(lb2);
         add(tf2);

         // �n����ť��
         addWindowListener(new SampleWindowListener());

         // �]�w�ج[
         pack();
         setVisible(true);

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
}
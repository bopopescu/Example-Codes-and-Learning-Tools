import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;

public class Sample3 extends JFrame
{
   private JLabel lb1, lb2, lb3;
   private JTextField tf1, tf2, tf3;
   private JButton bt;
   private JPanel pn1, pn2, pn3;

   public static void main(String[] args)
   {
      Sample3 sm = new Sample3();
   }
   public Sample3()
   {
      // �]�w���D
      super("�d��");

      // �إߤ���
      lb1 = new JLabel("��J�D��");
      lb2 = new JLabel("�D���W��");
      lb3 = new JLabel("IP��}");
      tf1 = new JTextField();    
      tf2 = new JTextField(); 
      tf3 = new JTextField(); 
      pn1 = new JPanel();
      pn2 = new JPanel();
      pn3 = new JPanel();
      bt = new JButton("�j�M");

      // �]�w�e��
      pn1.setLayout(new GridLayout(1, 2));
      pn2.setLayout(new GridLayout(2, 2));

      // �s�W��e����
      pn1.add(lb1);
      pn1.add(tf1);
      pn2.add(lb2);
      pn2.add(tf2);
      pn2.add(lb3);
      pn2.add(tf3);
      pn3.add(bt);

      add(pn1, BorderLayout.NORTH);
      add(pn2, BorderLayout.CENTER);
      add(pn3, BorderLayout.SOUTH);

      // �n����ť��
      bt.addActionListener(new SampleActionListener());

      addWindowListener(new SampleWindowListener());

      // �]�w�ج[
      setSize(300, 200);
      setVisible(true);

      tf1.requestFocus();
   }

   // ��ť�����O
   class SampleActionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try{
            InetAddress ia = InetAddress.getByName(tf1.getText());
            tf2.setText(ia.getHostName());    
            tf3.setText(ia.getHostAddress());
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
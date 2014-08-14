import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;

public class SamplePC extends JFrame
{
   private JLabel lb1, lb2;
   private JTextField tf1, tf2;
   private JTextArea ta;
   private JPanel pn1, pn2;
   private JButton bt;

   public static void main(String[] args)
   {
      SamplePC sm = new SamplePC();
   }
   public SamplePC()
   {
      // �]�w���D
      super("�d��");

      // �إߤ���
      lb1 = new JLabel("�D��");
      lb2 = new JLabel("��");
      tf1 = new JTextField();
      tf2 = new JTextField();
      ta = new JTextArea();
      pn1 = new JPanel();
      pn2 = new JPanel();
      bt = new JButton("�s�u");
 
      // �]�w�e��
      pn1.setLayout(new GridLayout(2,2));

      // �s�W��e����
      pn1.add(lb1);
      pn1.add(tf1);
      pn1.add(lb2);
      pn1.add(tf2);
      pn2.add(bt);

      add(pn1, BorderLayout.NORTH);
      add(ta, BorderLayout.CENTER);
      add(pn2, BorderLayout.SOUTH);

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
            InetAddress ia = InetAddress.getByName(tf1.getText());
            String host = ia.getHostName();   
            int port = Integer.parseInt(tf2.getText());

            Socket sc = new Socket(host, port);
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
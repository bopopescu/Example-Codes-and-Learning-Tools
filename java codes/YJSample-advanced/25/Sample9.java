import java.io.*;
import java.util.regex.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Sample9 extends JFrame
{
   private JPanel pn;
   private JLabel lb1, lb2, lb3;
   private JTextArea ta;
   private JTextField tf1, tf2;
   private JButton bt;
   private JScrollPane sp;

   public static void main(String[] args)
   {
      Sample9 sm = new Sample9();
   }
   public Sample9()
   {
      // �]�w���D
      super("�d��");

      // �إߤ���
      lb1 = new JLabel("�п�J��r�C");
      lb2 = new JLabel("���N�e");
      lb3 = new JLabel("���N��");
      ta = new JTextArea();
      sp = new JScrollPane(ta);

      pn = new JPanel();
      bt = new JButton("���N");
      tf1 = new JTextField();
      tf2 = new JTextField();

      // �]�w�e��
      pn.setLayout(new GridLayout(1,5));

      // �s�W��e����
      pn.add(lb2);
      pn.add(tf1);
      pn.add(lb3);
      pn.add(tf2);
      pn.add(bt);

      add(lb1, BorderLayout.NORTH);
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
         if(e.getSource() == bt){
            Pattern pn = Pattern.compile(tf1.getText());
            Matcher mt = pn.matcher(ta.getText());
            ta.setText(mt.replaceAll(tf2.getText()));
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

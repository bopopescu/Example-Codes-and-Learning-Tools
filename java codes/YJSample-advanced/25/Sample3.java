import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample3 extends JFrame
{
   private JPanel pn1, pn2;
   private JLabel lb1, lb2, lb3, lb4;
   private JButton bt;

   public static void main(String[] args)
   {
      Sample3 sm = new Sample3();
   }
   public Sample3()
   {
      // �]�w���D
      super("�d��");

      // �إߤ���
      lb1 = new JLabel("�п���ɮסC");
      lb2 = new JLabel();
      lb3 = new JLabel();
      lb4 = new JLabel();

      pn1 = new JPanel();
      pn2 = new JPanel();
      bt = new JButton("���");

      // �]�w�e��
      pn1.setLayout(new GridLayout(3,1));

      // �s�W��e����
      pn1.add(lb2);
      pn1.add(lb3);
      pn1.add(lb4);
      pn2.add(bt);

      add(lb1, BorderLayout.NORTH);
      add(pn1, BorderLayout.CENTER);
      add(pn2, BorderLayout.SOUTH);

      // �n����ť��
      bt.addActionListener(new SampleActionListener());
      addWindowListener(new SampleWindowListener());

      // �]�w�ج[
      setSize(300, 300);
      setVisible(true);
  }

   // ��ť�����O
   class SampleActionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         Container cnt = getContentPane();

         JFileChooser fc = new JFileChooser();
         int res = fc.showOpenDialog(cnt);
       
         if(res == JFileChooser.APPROVE_OPTION){
            File fl = fc.getSelectedFile();
            lb2.setText("�ɦW�O" + fl.getName() + "�C");
            lb3.setText("������|�O" + fl.getAbsolutePath() + "�C");
            lb4.setText("�j�p�O" + fl.length() + "�C");
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
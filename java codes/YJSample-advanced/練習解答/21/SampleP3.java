import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SampleP3 extends JFrame
{
   private JLabel lb;
   private JPanel pn;
   private JButton bt;

   public static void main(String[] args)
   {
      SampleP3 sm = new SampleP3();
   }
   public SampleP3()
   {
      // �]�w���D
      super("�d��");

      // �إߤ���
      lb = new JLabel("�w����{�C");
      pn = new JPanel();
      bt = new JButton("�ʶR");

      // �s�W��e����
      pn.add(bt);
      add(lb, BorderLayout.NORTH);
      add(pn, BorderLayout.SOUTH);

      // �n����ť��
      addWindowListener(new SampleWindowListener());
      bt.addActionListener(new SampleActionListener());

      // �]�w�ج[
      setSize(200, 200);
      setVisible(true);
   }

   // ��ť�����O
   class SampleWindowListener extends WindowAdapter
   {
      public void windowClosing(WindowEvent e)
      {
         System.exit(0);
      }
   }
   class SampleActionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         lb.setText("���´f�U�C");
      }
   }
}
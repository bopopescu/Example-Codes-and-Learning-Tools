import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample13 extends JApplet
{
   private JLabel lb;
   private JTextField tf;

   public void init()
   {
      // �إߤ���
      lb = new JLabel("�п�ܡC");
      tf = new JTextField();

      // �s�W��e����
      add(lb, BorderLayout.NORTH);
      add(tf, BorderLayout.SOUTH);

      // �n����ť��
      tf.addActionListener(new SampleActionListener());
  }

   // ��ť�����O
   class SampleActionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         JTextField tmp = (JTextField) e.getSource(); 
         lb.setText("�n���" + tmp.getText() + "��a�I");
      }
   }
}
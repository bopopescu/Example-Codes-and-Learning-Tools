import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SampleP3 extends JApplet
{
   private JLabel lb1, lb2;
   private String str;

   public void init()
   {
      // �إߤ���
      lb1 = new JLabel("�Ы��U��L����C");
      lb2 = new JLabel();

      // �s�W��e����
      add(lb1, BorderLayout.NORTH);
      add(lb2, BorderLayout.CENTER);

      // �n����ť��
      addKeyListener(new SampleKeyListener());
   }

   // ��ť�����O
   class SampleKeyListener extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {
         char c = e.getKeyChar();
         lb2.setText("�O" + c + "�a�C");
      }
   }
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample7 extends JApplet
{
   private JLabel lb1, lb2;

   public void init()
   {
      // �إߤ���
      lb1 = new JLabel("�п�ܤ�V��C");
      lb2 = new JLabel();

      // �s�W��e����
      add(lb1, BorderLayout.NORTH);
      add(lb2, BorderLayout.SOUTH);

      // �n����ť��
      addKeyListener(new SampleKeyListener());
   }

   // ��ť�����O
   class SampleKeyListener extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {
         String str;
         int k = e.getKeyCode();
         switch(k){
            case KeyEvent.VK_UP:
              str = "�W"; break;
            case KeyEvent.VK_DOWN:
              str = "�U"; break;
            case KeyEvent.VK_LEFT:
              str = "��"; break;
            case KeyEvent.VK_RIGHT:
              str = "�k"; break;
            default:
              str = "�䥦����";
         }
         lb2.setText(str + "�C");
      }
   }
}
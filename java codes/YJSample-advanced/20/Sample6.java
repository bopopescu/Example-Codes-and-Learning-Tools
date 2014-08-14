import java.awt.*;
import javax.swing.*;

public class Sample6 extends JApplet
{
   private JLabel[] lb = new JLabel[3];

   public void init()
   {
      // �إߤ���
      for(int i=0; i<lb.length; i++){ 
         lb[i] = new JLabel("�zı�o�T��" + i + "���˩O�H");
      }

      // �]�w����
      lb[0].setForeground(Color.black);
      lb[1].setForeground(Color.black);
      lb[2].setForeground(Color.black);

      lb[0].setBackground(Color.white);
      lb[1].setBackground(Color.gray);
      lb[2].setBackground(Color.white);

      lb[0].setOpaque(true);
      lb[1].setOpaque(true);
      lb[2].setOpaque(true);

      lb[0].setHorizontalAlignment(JLabel.LEFT);
      lb[1].setHorizontalAlignment(JLabel.CENTER);
      lb[2].setHorizontalAlignment(JLabel.RIGHT);

      lb[0].setVerticalAlignment(JLabel.TOP);
      lb[1].setVerticalAlignment(JLabel.CENTER);
      lb[2].setVerticalAlignment(JLabel.BOTTOM);

      // �]�w�e��
      setLayout(new GridLayout(3, 1, 3, 3)); 

      // �b�e�����s�W����
      for(int i=0; i<lb.length; i++){ 
         add(lb[i]);
      }
   }
}
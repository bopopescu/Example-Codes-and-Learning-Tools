import java.awt.*;
import javax.swing.*;

public class Sample7 extends JApplet
{
   private JLabel[] lb = new JLabel[3];
   private Icon ic;

   public void init()
   {
      // �إߤ���
      for(int i=0; i<lb.length; i++){ 
         lb[i] = new JLabel("�zı�o�T��" + i + "���˩O�H");
      }
      ic = new ImageIcon(getImage(getDocumentBase(), "car.gif"));

      // �]�w����
      lb[0].setIcon(ic);
      lb[1].setIcon(ic);
      lb[2].setIcon(ic);

      lb[0].setHorizontalTextPosition(JLabel.LEFT);
      lb[1].setHorizontalTextPosition(JLabel.CENTER);
      lb[2].setHorizontalTextPosition(JLabel.RIGHT);

      lb[0].setVerticalTextPosition(JLabel.TOP);
      lb[1].setVerticalTextPosition(JLabel.CENTER);
      lb[2].setVerticalTextPosition(JLabel.BOTTOM);

      // �]�w�e��
      setLayout(new GridLayout(3, 1)); 

      // �s�W��e����
      for(int i=0; i<lb.length; i++){ 
         add(lb[i]);
      }
   }
}
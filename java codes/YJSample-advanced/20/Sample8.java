import java.awt.*;
import javax.swing.*;

public class Sample8 extends JApplet
{
   private JLabel[] lb = new JLabel[3];
   private Icon ic;

   public void init()
   {
      // �إߤ���
      for(int i=0; i<lb.length; i++){ 
         lb[i] = new JLabel("This is a Car.");
      }
      ic = new ImageIcon(getImage(getDocumentBase(), "car.gif"));

      // �]�w����
      lb[0].setIcon(ic);
      lb[1].setIcon(ic);
      lb[2].setIcon(ic);

      lb[0].setFont(new Font("SansSerif", Font.BOLD, 12));
      lb[1].setFont(new Font("Helvetica", Font.BOLD, 14));
      lb[2].setFont(new Font("Century", Font.BOLD, 16));

      // �]�w�e��
      setLayout(new GridLayout(3, 1)); 

      // �s�W��e����
      for(int i=0; i<lb.length; i++){ 
         add(lb[i]);
      }
   }
}
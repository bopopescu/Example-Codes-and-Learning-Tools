import java.awt.*;
import javax.swing.*;

public class Sample4 extends JApplet
{
   private JButton[] bt = new JButton[6];

   public void init()
   {
      // �إߤ���
      for(int i=0; i<bt.length; i++){ 
         bt[i] = new JButton(Integer.toString(i));
      }

      // �]�w�e��
      setLayout(new GridLayout(2, 3)); 

      // �s�W��e����
      for(int i=0; i<bt.length; i++){ 
         add(bt[i]);
      }
   }
}
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Sample9 extends JApplet
{
   private JLabel lb[] = new JLabel[3];
   Icon ic;

   public void init()
   {
      // �إߤ���
      for(int i=0; i<lb.length; i++){ 
         lb[i] = new JLabel("�zı�o�T��" + i + "���˩O�H");
      }
      ic = new ImageIcon(getImage(getDocumentBase(), "br.gif"));

      // �]�w����
      lb[0].setBorder(new BevelBorder(BevelBorder.RAISED));
      lb[1].setBorder(new EtchedBorder());
      lb[2].setBorder(new MatteBorder(5, 5, 5, 5, ic));

      // �]�w�e��
      setLayout(new GridLayout(3, 1, 5, 5)); 

      // �s�W��e����
      for(int i=0; i<lb.length; i++){ 
         add(lb[i]);
      }
   }
}

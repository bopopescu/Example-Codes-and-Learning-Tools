import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class SampleP2 extends JApplet
{
   private JLabel lb;
   private JCheckBox cb;
   private Icon ic;

   public void init()
   {
      // �إߤ���
      lb = new JLabel("�o�O���l�C");
      cb = new JCheckBox("��ܼv��");
      ic = new ImageIcon(getImage(getDocumentBase(), "car.gif"));

      // �]�w����
      lb.setBorder(new LineBorder(Color.blue, 10));

      // �s�W��e����
      add(lb, BorderLayout.CENTER);
      add(cb, BorderLayout.SOUTH);

      // �n����ť��
      cb.addItemListener(new SampleItemListener());
   }
   // ��ť�����O
   class SampleItemListener implements ItemListener
   {
      public void itemStateChanged(ItemEvent e)
      {
         if(e.getStateChange() == ItemEvent.SELECTED)
         {
            lb.setIcon(ic);
         }
         else if(e.getStateChange() == ItemEvent.DESELECTED)
         {
            lb.setIcon(null);
         }
      }
   }
}
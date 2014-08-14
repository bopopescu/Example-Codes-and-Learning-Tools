import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample11 extends JApplet
{
   private JLabel lb;
   private JPanel pn;
   private JCheckBox ch1, ch2, tmp;

   public void init()
   {
      // �إߤ���
      lb = new JLabel("�w����{�C");
      pn = new JPanel();
      ch1 = new JCheckBox("�T��");
      ch2 = new JCheckBox("�d��");

      // �s�W��e����
      pn.add(ch1);
      pn.add(ch2);
      add(lb, BorderLayout.NORTH);
      add(pn, BorderLayout.SOUTH);

      // �n����ť��
      ch1.addItemListener(new SampleItemListener());
      ch2.addItemListener(new SampleItemListener());
   }

   // ��ť�����O
   class SampleItemListener implements ItemListener
   {
      public void itemStateChanged(ItemEvent e)
      {
         if(e.getStateChange() == ItemEvent.SELECTED){
            tmp = (JCheckBox) e.getSource(); 
            lb.setText("��ܤF" + tmp.getText() + "�C");
         }
         else if(e.getStateChange() == ItemEvent.DESELECTED){
            tmp = (JCheckBox) e.getSource(); 
            lb.setText("���F" + tmp.getText() + "�C");
         }
      }
   }
}

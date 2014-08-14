import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sample8 extends JApplet
{
   private JLabel lb;
   private JButton bt[] = new JButton[3];
   private JToolBar tl;
   private Icon ic;

   public void init()
   {
      // �إߤ���
      lb = new JLabel("�w����{�C");
      tl = new JToolBar();
      ic = new ImageIcon(getImage(getDocumentBase(), "car.gif"));
   
      for(int i=0; i<bt.length; i++){
         bt[i] = new JButton(ic); 
      }

      // �]�w����
      for(int i=0; i<bt.length; i++){
         bt[i].setToolTipText((i+1) + "����"); 
      }

      // �s�W��u��C�W
      tl.add(bt[0]);
      tl.add(bt[1]);
      tl.addSeparator();
      tl.add(bt[2]);

      // �s�W��e���W
      add(tl, BorderLayout.NORTH);
      add(lb, BorderLayout.CENTER);

      // �n����ť��
      for(int i=0; i<bt.length; i++){
         bt[i].addActionListener(new SampleActionListener()); 
      }
   }

   // ��ť�����O
   class SampleActionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         int num = 0;
         JButton tmp = (JButton) e.getSource();

         if(tmp == bt[0])
             num = 1;
         else if(tmp == bt[1])
             num = 2;
         else if(tmp == bt[2])
             num = 3;

         lb.setText("�z��ܤF" + num + "�����C");
      }
   }
}
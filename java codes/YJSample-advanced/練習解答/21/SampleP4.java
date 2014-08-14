import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SampleP4 extends JFrame
{
   JLabel lb;
   JMenuBar mb;
   JMenu mn[] = new JMenu[4];
   JMenuItem mi[] = new JMenuItem[6];

   public static void main(String[] args)
   {
        SampleP4 sm = new SampleP4();
   }
   public SampleP4()
   {
      // �]�w���D
      super("�d��");

      // �إߤ���
      lb = new JLabel("�w����{�C");
      mb = new JMenuBar();
   
      mn[0] = new JMenu("�D���1"); 
      mn[1] = new JMenu("�D���2"); 
      mn[2] = new JMenu("�l���1"); 
      mn[3] = new JMenu("�l���2"); 

      mi[0] = new JMenuItem("�T��");
      mi[1] = new JMenuItem("�d��");
      mi[2] = new JMenuItem("�Ԩ�");
      mi[3] = new JMenuItem("�p�{��");
      mi[4] = new JMenuItem("�]��");
      mi[5] = new JMenuItem("�g�A��");

      mn[0].add(mi[0]);
      mn[0].add(mi[1]);

      mn[2].add(mi[2]);
      mn[2].add(mi[3]);

      mn[3].add(mi[4]);
      mn[3].add(mi[5]);

      mn[1].add(mn[2]);
      mn[1].addSeparator();
      mn[1].add(mn[3]);

      mb.add(mn[0]);
      mb.add(mn[1]);

      // �s�W��e����
      add(mb, BorderLayout.NORTH);
      add(lb, BorderLayout.CENTER);

      // �n����ť��
      for(int i=0; i<mi.length; i++){
         mi[i].addActionListener(new SampleActionListener()); 
      }
      addWindowListener(new SampleWindowListener());

      // �]�w�ج[
      setSize(200, 200);
      setVisible(true);
   }

   // ��ť�����O
   class SampleActionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         JMenuItem tmp =(JMenuItem) e.getSource();
         String str = tmp.getText();
         lb.setText("�O" + str + "�a�C");
      }
   }
   class SampleWindowListener extends WindowAdapter
   {
      public void windowClosing(WindowEvent e)
      {
         System.exit(0);
      }
   }
}
import java.io.*;
import java.util.regex.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Sample10 extends JFrame
{
   private JPanel pn;
   private JLabel lb;
   private JTextArea ta;
   private JTextField tf;
   private JButton bt;
   private JScrollPane sp;

   public static void main(String[] args)
   {
      Sample10 sm = new Sample10();
   }
   public Sample10()
   {
      // �]�w���D
      super("�d��");

      // �إߤ���
      lb = new JLabel("�п�J��r�C");
      ta = new JTextArea();
      sp = new JScrollPane(ta);

      pn = new JPanel(new GridLayout(1,2));
      bt = new JButton("�j�M");
      tf = new JTextField();

      // �s�W��e����
      pn.add(tf);
      pn.add(bt);

      add(lb, BorderLayout.NORTH);
      add(sp, BorderLayout.CENTER);
      add(pn, BorderLayout.SOUTH);

      // �n����ť��
      bt.addActionListener(new SampleActionListener());
      addWindowListener(new SampleWindowListener());

      // �]�w�ج[
      setSize(300, 300);
      setVisible(true);
  }

   // ��ť�����O
   class SampleActionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try{
            if(e.getSource() == bt){
               Highlighter hl = ta.getHighlighter();            
               hl.removeAllHighlights();
               Pattern pn = Pattern.compile(tf.getText());
               Matcher mt = pn.matcher(ta.getText());

               while(mt.find()){
                  hl.addHighlight(mt.start(), mt.end(), new DefaultHighlighter.DefaultHighlightPainter(null));
               }
            }
         }
         catch(Exception ex){
            ex.printStackTrace();
         }
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
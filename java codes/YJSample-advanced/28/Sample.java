import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Sample extends JFrame
{
   private JMenuBar mb;
   private JMenu[] mn = new JMenu[3];
   private JMenuItem[] mi = new JMenuItem[6];
   private SamplePanel sp;

   private ArrayList<Shape> shapeList;    // �ϧΦC��
   private int currentShape;    // ��ܹϧ�
   private Color currentColor;  // ����C��
  
   public static void main(String args[])
   {
      Sample sm = new Sample();
   }
   public Sample()
   {
      // �]�w���D
      super("�d��");

      // �إߤ���
      mb = new JMenuBar();
   
      mn[0] = new JMenu("�ɮ�"); 
      mn[1] = new JMenu("�]�w"); 
      mn[2] = new JMenu("�ϧ�"); 

      mi[0] = new JMenuItem("�}������");
      mi[1] = new JMenuItem("�x�s�ɮ�");
      mi[2] = new JRadioButtonMenuItem("�|����");
      mi[3] = new JRadioButtonMenuItem("����");
      mi[4] = new JRadioButtonMenuItem("���u");
      mi[5] = new JMenuItem("�C��");

      sp = new SamplePanel();

      // �s�W��e����
      mn[0].add(mi[0]);
      mn[0].add(mi[1]);

      mn[2].add(mi[2]);
      mn[2].add(mi[3]);
      mn[2].add(mi[4]);

      mn[1].add(mn[2]);
      mn[1].add(mi[5]);

      mb.add(mn[0]);
      mb.add(mn[1]);

      ButtonGroup bg = new ButtonGroup();
      bg.add(mi[2]);
      bg.add(mi[3]);
      bg.add(mi[4]);

      add(sp, BorderLayout.CENTER);
      add(mb, BorderLayout.NORTH);

      // �n����ť��
      for(int i=0; i<mi.length; i++)
      {
         mi[i].addActionListener(new SampleActionListener()); 
      }
      addWindowListener(new SampleWindowListener());

      // �i���l��
      shapeList = new ArrayList<Shape>();
      currentShape = Shape.RECT;
      currentColor = Color.blue;
      mi[2].setSelected(true);

      // �]�w�ج[
      setSize(600, 400);
      setVisible(true);
   }

   // ��ť�����O
   class SampleWindowListener extends WindowAdapter
   {
      public void windowClosing(WindowEvent e)
      {
         System.exit(0);
      }
   }      
   class SampleActionListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         Container cnt = getContentPane();
         JFileChooser fc = new JFileChooser();
         fc.setFileFilter(new MyFileFilter());
         try{
            // Ū�J�ɮ�
            if(e.getSource() == mi[0]){
               int res = fc.showOpenDialog(cnt);
               if(res == JFileChooser.APPROVE_OPTION){       
                  File fl = fc.getSelectedFile();
                  ObjectInputStream oi = new ObjectInputStream(new FileInputStream(fl));
                  Shape tmp = null;
                  shapeList.clear();
                  try{
                     while((tmp = (Shape)oi.readObject()) != null){
                        shapeList.add(tmp);
                     }
                  }catch(EOFException ex){}                  
                  oi.close();
               }
            }
            // ��X�ɮ�
            else if(e.getSource() == mi[1]){
               int res = fc.showSaveDialog(cnt);
               if(res == JFileChooser.APPROVE_OPTION){       
                  File fl = fc.getSelectedFile();
                  ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(fl));
                  for(int i=0; i < shapeList.size(); i++){ 
                    oo.writeObject(shapeList.get(i));
                  }
                 oo.close();
               }
            }
            // �]�w�|����
            else if(e.getSource() == mi[2]){
               currentShape = Shape.RECT;
            }
            // �]�w����
            else if(e.getSource() == mi[3]){
               currentShape = Shape.OVAL;
            }
            // �]�w���u
            else if(e.getSource() == mi[4]){
               currentShape = Shape.LINE;
            }
            // ����C�⪺��ܵe��
            else if(e.getSource() == mi[5]){
               currentColor = JColorChooser.showDialog(cnt, "����C��", Color.black);
            }
         }
         catch(Exception ex){
            ex.printStackTrace();
         }
      }
      // �L�o�����O
      class MyFileFilter extends javax.swing.filechooser.FileFilter
      {
         public boolean accept(File f)
         {
            if(f.isDirectory()){
               return true;
            }
            String fn = f.getName();
            if(fn.toLowerCase().endsWith(".g")){
               return true;
            }
            return false;
          }
          public String getDescription()
          {
            return "�Ϲ���";
          }
      }
   }
      
   // ���O���O
   class SamplePanel extends JPanel
   {
      SamplePanel()
      {
          setOpaque(false);
          SampleMouseListener ml= new SampleMouseListener();
          addMouseListener(ml);
      }
      public void update(Graphics g)
      {
      }
      public void paint(Graphics g)
      {
         for(int i=0; i < shapeList.size(); i++){
            // �q�C�����X�ϧΪ���
            Shape  sh = (Shape) shapeList.get(i);
            // �H�ϧΪ���ۤv�Ӷi��yø
            sh.draw(g);
         }
      }
      // ��ť�����O
      class SampleMouseListener extends MouseAdapter
      {
         public void mousePressed(MouseEvent e)
         {
            // �إ߹ϧΪ���
            Shape sh = null;
            if(currentShape == Shape.RECT){
                sh = new Rect();
            }
            else if(currentShape == Shape.OVAL){
                sh = new Oval();
            }
            else if(currentShape == Shape.LINE){
                sh = new Line();
            }
            // �]�w�ϧΪ����C��
            sh.setColor(currentColor);
            // �]�w�ϧΪ��󪺮y��
            sh.setStartPoint(e.getX(), e.getY());
            sh.setEndPoint(e.getX(), e.getY());
            // �N�ϧΪ���s�W��C������
            shapeList.add(sh);
            // ���s�yø���O
            repaint();
         }
         public void mouseReleased(MouseEvent e)
         {
            // �q�C�����ݨ��X�ϧΪ���
            Shape sh = (Shape)shapeList.get(shapeList.size()-1);
            // �]�w�ϧΪ��󪺵����y��
            sh.setEndPoint(e.getX(), e.getY());
            // ���s�yø���O
            repaint();
         }
      }
   }
}
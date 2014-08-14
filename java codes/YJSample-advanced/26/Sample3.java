import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Sample3 extends JFrame
{
   private JTree tr;
   private JScrollPane sp;

   public static void main(String[] args)
   {
      Sample3 sm = new Sample3();
   }
   public Sample3() 
   {
      // �]�w���D
      super("�d��");

      // Ū�JXML���
      try
      {
         DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         DocumentBuilder db = dbf.newDocumentBuilder();
         Document doc = db.parse(new BufferedInputStream(new FileInputStream(new File("Sample.xml"))));

         // �إߤ���
         tr = new JTree(new MyTreeModel(doc));
         sp = new JScrollPane(tr);
 
         // �s�W��e����
         add(sp);

         // �n����ť��
         addWindowListener(new SampleWindowListener());

         // �]�w�ج[
         setSize(300, 300);
         setVisible(true);
      } 
      catch(Exception ex){
         ex.printStackTrace();
      }
   }

   // �ҫ����O
   class MyTreeModel implements TreeModel
   {
      private Document doc;

      public MyTreeModel(Document d)
      {
         doc = d;
      }
      public Object getRoot() 
      {
         return (new XMLNode(doc.getDocumentElement()));
      }
      public Object getChild(Object parent, int index) 
      {
         XMLNode node = (XMLNode) parent;
         NodeList children = node.getChildNodes();
         return new XMLNode(children.item(index));
      }
      public int getChildCount(Object parent) 
      {
         XMLNode node = (XMLNode) parent;
         NodeList children = node.getChildNodes();
         return children.getLength();
      }
      public int getIndexOfChild(Object parent, Object child) 
      {
         XMLNode node = (XMLNode) parent;
         NodeList children = node.getChildNodes();
         for(int i=0; i<children.getLength(); i++){
            if(child == children.item(i)){
               return i;
            }
         }
         return -1;
      }
      public boolean isLeaf(Object node) 
      {
         XMLNode n = (XMLNode) node;
         NodeList children = n.getChildNodes();
         if(children.getLength() == 0) 
            return  true;
         else
            return false;
      }
      public void addTreeModelListener(TreeModelListener l){}
      public void removeTreeModelListener(TreeModelListener l){}
      public void valueForPathChanged(TreePath path ,Object newValue){}

      // �`�I���O
      class XMLNode
      {
         Node node;
         public XMLNode(Node n)
         {
            node = n;
         }
         public String toString()
         {
            int type = node.getNodeType();
            switch(type){
               case Node.ELEMENT_NODE:
                 return node.getNodeName();
            }
            return node.getNodeValue();
         }
         public NodeList getChildNodes()
         {
            return node.getChildNodes(); 
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
import java.sql.*;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;

public class Sample5
{
   public static void main(String[] args)
   {
      try{
         // �ǳƳs�u
         String url = "jdbc:derby:cardb;create=true";
         String usr = "";
         String pw = "";

         // �s�����Ʈw
         Connection cn = DriverManager.getConnection(url, usr, pw);

         // �ǳƬd��
         DatabaseMetaData dm = cn.getMetaData();
         ResultSet tb = dm.getTables(null, null, "���l��ƪ�", null);

         Statement st = cn.createStatement();

         String qry1 = "CREATE TABLE ���l��ƪ�(�s�� int, �W�� varchar(50))";
         String[] qry2 = {"INSERT INTO ���l��ƪ� VALUES (2, '�T��')",
                          "INSERT INTO ���l��ƪ� VALUES (3, '�d��')",
                          "INSERT INTO ���l��ƪ� VALUES (4, '�Ԩ�')"};
         String qry3 = "SELECT * FROM ���l��ƪ�";

         if(!tb.next()){
            st.executeUpdate(qry1);
            for(int i=0; i<qry2.length; i++){
               st.executeUpdate(qry2[i]);
            }
         }        

         // �i��d��
         ResultSet rs = st.executeQuery(qry3);

         // ���o���
         ResultSetMetaData rm = rs.getMetaData();
         int cnum = rm.getColumnCount();

         // �i��DOM���ǳ�
         DocumentBuilderFactory dbf
            = DocumentBuilderFactory.newInstance();
         DocumentBuilder db
            = dbf.newDocumentBuilder();

         // �إ߷s���
         Document doc = db.newDocument();

         // �إ߮ڤ���
         Element root = doc.createElement("cars");
         doc.appendChild(root);

         // �إߤ���
         while(rs.next()){
            Element car = doc.createElement("car");
            root.appendChild(car);
            for(int i=1; i<=cnum; i++){
               Element elm = doc.createElement(rm.getColumnName(i).toString());
               Text txt = doc.createTextNode(rs.getObject(i).toString());
               elm.appendChild(txt);
               car.appendChild(elm);
            }
         }

         // ��X���
         TransformerFactory tff
            = TransformerFactory.newInstance();
         Transformer tf
            = tff.newTransformer();
         tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
         tf.transform(new DOMSource(doc), new StreamResult("result.xml"));
         System.out.println("��X��result.xml�F�C");

         // �����s�u
         rs.close();
         st.close();
         cn.close();
      }
      catch(Exception e){
         e.printStackTrace();
      }
   }
}
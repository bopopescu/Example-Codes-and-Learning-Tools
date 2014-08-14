import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;

public class SampleP2
{
   public static void main(String[] args) throws Exception
   {
      // �i��DOM���ǳ�
      DocumentBuilderFactory dbf
         = DocumentBuilderFactory.newInstance();
      DocumentBuilder db
         = dbf.newDocumentBuilder();

      // �إ߷s���
      Document doc = db.newDocument();

      // �s�W�ڤ���
      Element root = doc.createElement("���G�C��");
      doc.appendChild(root);

      // �s�W����
      Element fruit = doc.createElement("���G");
      root.appendChild(fruit);

      Element elm1 = doc.createElement("�W��");
      Text txt1 = doc.createTextNode("��l");
      elm1.appendChild(txt1);
      fruit.appendChild(elm1);

      Element elm2 = doc.createElement("�i�f���a");
      Text txt2 = doc.createTextNode("�C�s�ө�");
      elm2.appendChild(txt2);
      fruit.appendChild(elm2);

      // ��X���
      TransformerFactory tff
         = TransformerFactory.newInstance();
      Transformer tf
         = tff.newTransformer();
      tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      tf.transform(new DOMSource(doc), new StreamResult("result.xml"));
      System.out.println("��X��result.xml�F�C");
   }
}
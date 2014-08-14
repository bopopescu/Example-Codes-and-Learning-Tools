import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;

public class Sample4
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

      // �إ߮ڤ���
      Element root = doc.createElement("���l�C��");
      doc.appendChild(root);

      // �ǳ�CSV���
      BufferedReader br = new BufferedReader(new FileReader("Sample.csv"));

      // �x�sCSV��󪺼��D�C
      ArrayList<String> colname = new ArrayList<String>();
      String line = br.readLine();
      StringTokenizer stt = new StringTokenizer(line, ",");
      while(stt.hasMoreTokens()){
         colname.add(stt.nextToken());
      }

      // �ഫCSV���
      while((line = br.readLine()) != null){
         StringTokenizer std = new StringTokenizer(line, ",");
         Element car = doc.createElement("���l");
         root.appendChild(car);

         for(int i=0; i<colname.size(); i++){
            Element elm = doc.createElement((String)colname.get(i));
            Text txt = doc.createTextNode(std.nextToken());
            elm.appendChild(txt);
            car.appendChild(elm);
         }

      }
      br.close();

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
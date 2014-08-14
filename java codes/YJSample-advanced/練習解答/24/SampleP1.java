import java.sql.*;

public class SampleP1
{
   public static void main(String[] args)
   {
      try{
         // �ǳƳs�u
         String url = "jdbc:derby:fooddb;create=true";
         String usr = "";
         String pw = "";

         // �s�����Ʈw
         Connection cn = DriverManager.getConnection(url, usr, pw);

         // �ǳƬd��
         DatabaseMetaData dm = cn.getMetaData();
         ResultSet tb = dm.getTables(null, null, "���G��ƪ�", null);

         Statement st = cn.createStatement();

         String qry1 = "CREATE TABLE ���G��ƪ�(�s�� int, �W�� varchar(50), �c�橱 varchar(50))";
         String[] qry2 = {"INSERT INTO ���G��ƪ� VALUES (1,'��l','�C�s�ө�')",
                          "INSERT INTO ���G��ƪ� VALUES (2,'ī�G','�F�ʥ���')",
                          "INSERT INTO ���G��ƪ� VALUES (3,'����','�a��ө�')",
                          "INSERT INTO ���G��ƪ� VALUES (4,'���','�F�ʥ���')",
                          "INSERT INTO ���G��ƪ� VALUES (5,'���l','�C�s�ө�')",
                          "INSERT INTO ���G��ƪ� VALUES (6,'�ߤl','���ئʳf')",
                          "INSERT INTO ���G��ƪ� VALUES (7,'��l','���ئʳf')",
                          "INSERT INTO ���G��ƪ� VALUES (8,'���','���ðө�')",
                          "INSERT INTO ���G��ƪ� VALUES (9,'�U�l','�C�s�ө�')",
                          "INSERT INTO ���G��ƪ� VALUES (10,'���','�F�ʥ���')"};
         String qry3 = "SELECT * FROM ���G��ƪ�";

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
         while(rs.next()){
            for(int i=1; i<=cnum; i++){
                System.out.print(rm.getColumnName(i) + ":"+ rs.getObject(i) + "  ");
            }
            System.out.println("");
         }

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
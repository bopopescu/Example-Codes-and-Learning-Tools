package mybeans;
import java.util.*;
import java.io.*;
import java.sql.*;

public class CarDBBean implements Serializable
{
   private ArrayList<String> colname;
   private ArrayList<ArrayList> data;

   public CarDBBean()
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
                          "INSERT INTO ���l��ƪ� VALUES (3, '�Ԩ�')",
                          "INSERT INTO ���l��ƪ� VALUES (4, '�d��')"};
         String qry3 = "SELECT * FROM ���l��ƪ�";

         if(!tb.next()){
            st.executeUpdate(qry1);
            for(int i=0; i<qry2.length; i++){
               st.executeUpdate(qry2[i]);
            }
         }

         // �i��d��
         ResultSet rs = st.executeQuery(qry3);

         // ���o����
         ResultSetMetaData rm = rs.getMetaData();
         int cnum = rm.getColumnCount();
         colname = new ArrayList<String>(cnum);

         // ���o���W��
         for(int i=1; i<=cnum; i++){
            colname.add(rm.getColumnName(i).toString());
         }

         // ���o�C
         data = new ArrayList<ArrayList>(); 
         while(rs.next()){
            ArrayList<String> rowdata = new ArrayList<String>();
            for(int i=1; i<=cnum; i++){
                rowdata.add(rs.getObject(i).toString());
            }
            data.add(rowdata);
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
   public ArrayList getData() 
   {
      return data;
   }
   public ArrayList getColname() 
   {
      return colname;
   }
}
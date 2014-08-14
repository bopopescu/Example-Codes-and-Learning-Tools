import java.sql.*;

public class Sample3
{
   public static void main(String args[])
   {
      if(args.length != 2){
         System.out.println("�Ѽƪ��ƶq����C");
         System.exit(1);
      }

      try{
         // �ǳƳs�u
         String url = "jdbc:derby:cardb;create=true";
         String usr = "";
         String pw = "";

         // �s�����Ʈw
         Connection cn = DriverManager.getConnection(url, usr, pw);

         // �ǳƬd��
         Statement st = cn.createStatement();
         String qry1 = "INSERT INTO ���l��ƪ� VALUES (" + args[0] + ", '" + args[1] + "')";
         String qry2 = "SELECT * FROM ���l��ƪ�";

         // �i��d��
         st.executeUpdate(qry1);
         ResultSet rs = st.executeQuery(qry2);

         // ���o���
         ResultSetMetaData rm = rs.getMetaData();
         int cnum = rm.getColumnCount();

         while(rs.next()){
            for(int i=1; i<=cnum; i++){
               System.out.print(rm.getColumnName(i) +  ":" + rs.getObject(i) + "  ");
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
package wyf.wyy;
import java.sql.*;
import javax.sql.*;
import java.util.*;
import java.text.*;
public class Order_DB{
	private static Connection con=null;//�n��Connection�ޥ�
	private static Statement stat=null;//�n��Statement�ޥ�
	private static ResultSet rs=null;//�n��ResultSet�ޥ�
	public static boolean isOrdered(String rgid1){//�P�_�귽�O�_�Q�w�w��
		boolean b = false;//��l��false�A�Y�S���Q�w�q
		try{
			String ostatus = new String("�w�w��".getBytes(),"iso8859-1");
			String rstatus = new String("����".getBytes(),"iso8859-1");
			String rgid = new String(rgid1.getBytes(),"iso8859-1");
			con = DB.getCon();
			stat = con.createStatement();
			//�d�ݷ�ergid�������귽���S�����b�w�q��
			rs = stat.executeQuery("select rgid from oinfo where ostatus='"+
								ostatus+"' and rgid='"+rgid+"'");
			if(rs.next()) {b = true;}
			//�d�ݷ�e�귽�O�_�B��Ŷ����A
			rs = stat.executeQuery("select rgid from resource where rstatus='"+
								rstatus+"' and rgid='"+rgid+"'");
			if(rs.next()) {b = true;}
		}
		catch(Exception e){e.printStackTrace();}
		finally{DB.closeCon();}//�����ƾڮw�s��
		return b;//��^���G
	}
	public static Vector<String []> getOrderedDay(String rgid1){
		Vector<String []> v = new Vector<String []>();
		try{
			String ostatus = new String("�w�q���\".getBytes(),"iso8859-1");
			String rgid = new String(rgid1.getBytes(),"iso8859-1");
			con = DB.getCon();//�o��ƾڮw�s��
			stat = con.createStatement();//�Ыػy�y�ﹳ
			//�d�ݷ�ergid�������귽�w�g�Q�w�q���C��]���ӤC�Ѥ����^
			rs = stat.executeQuery("select ftime,etime from oinfo where ostatus='"+
								ostatus+"' and rgid='"+rgid+"'");
			while(rs.next()){				
				String []s =new String[2];
				//�o��}�l�M�����ɶ�
				s[0] = new String(rs.getString(1).getBytes("iso8859-1"),"big5");
				s[1] = new String(rs.getString(2).getBytes("iso8859-1"),"big5");
				java.util.Date etime = Order_DB.chageStringToDate(s[1]);
				java.util.Date now = new java.util.Date();
				//�p�G�����ɶ��b��e�ɶ�����A�h���ӤC�Ѥ������w�g�Q�q�����
				if(etime.after(now)) {v.add(s);}
			}
		}
		catch(Exception e)	{e.printStackTrace();}
		finally {DB.closeCon();}//�����ƾڮw�s��
		return v;//��^���G
	}
	public static java.util.Date chageStringToDate(String s){
		java.util.Date da = null;//�n������ﹳ�ޥ�
		if(s!=null){
			String p = "-|:| "; //�Ω��������h��
			String[] d = s.split(p);//�o�����᪺�r�Ŧ�Ʋ�			
			int[] date = new int[d.length];//�N�r�Ŭ�Ʋ��ഫ��int���Ʋ�
			for(int i=0;i<d.length;i++)
			{date[i]=Integer.parseInt(d[i]);}
			//�ե�java.util.Date���c�y���c�y�@�Ӥ���ﹳ
			da = new java.util.Date(date[0]-1900,date[1]-1,date[2],date[3],date[4]);
		}	
		return da;//��^����ﹳ
	}
/*	public static int addOrder(String user,Vector<String[]> OrderList)
	{
		int i = 0;
		int orid = DB.getId("oinfo","orid");//�o��q����Ӫ��D��ID+1��
		int oid = DB.getId("olist","oid");//�o��q����D��ID+1��
		try{			
			con = DB.getCon();
			stat = con.createStatement();
			//�o������H���զ��q��			
			java.util.Date d = new java.util.Date();
			String otime = d.toLocaleString();
			con.setAutoCommit(false);//�T�Φ۰ʴ���A�}�l�@�Өư�
			String sqla = "insert into olist(oid,oname,otime) values"+
					"("+oid+",'"+user+"','"+otime+"')";
			String sql = new String(sqla.getBytes(),"iso8859-1");			
			stat.executeUpdate(sql);
			//�o��q����ӫH��
			Vector<String> sqlb = new Vector<String>();			
			for(String []s:OrderList){												
				String rgid = s[0];//�o��ҭq�귽��
				//�o��}�l�M�����ɶ�
				String ftime = s[2]; String etime = s[3];
				String sqlc = "insert into oinfo(orid,oid,rgid,ftime,etime) values"+
						"("+orid+","+oid+",'"+rgid+"','"+ftime+"','"+etime+"')";
				String sqld = new String(sqlc.getBytes(),"iso8859-1");
				stat.executeUpdate(sqld);//�����s
				orid++;//�D��ۥ[�A�@���U�@���O�����D��
			}
			con.commit();//�N�ưȴ���
			con.setAutoCommit(true);//��_�۰ʴ���Ҧ�
		}
		catch(Exception e){
			e.printStackTrace();
			i = -1;
			try{con.rollback();}//�X�{���~�A�o�_�^�u�ާ@
			catch(Exception ea)	{e.printStackTrace();}
		}
		finally	{DB.closeCon();}
		return i;//��^���浲�G,-1�N����
	}*/
		public static int addOrder(String user,Vector<String[]> OrderList)
	{
		int i = 0;
		int orid = DB.getId("oinfo","orid");//�o��q����Ӫ��D��ID+1��
		int oid = DB.getId("olist","oid");//�o��q����D��ID+1��
		try{			
			con = DB.getCon();
			stat = con.createStatement();
			//�o������H���զ��q��			
			//java.util.Date d = new java.util.Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String otime = df.format(new java.util.Date());
			//String otime = d.toLocaleString();
			con.setAutoCommit(false);//�T�Φ۰ʴ���A�}�l�@�Өư�
			String sqla = "insert into olist(oid,oname,otime) values"+  
					"("+oid+",'"+user+"','"+otime+"')";//�q��s�� �Τ�W �U�w�ɶ�
			String sql = new String(sqla.getBytes(),"iso8859-1");			
			stat.executeUpdate(sql);
			//�o��q����ӫH��
			Vector<String> sqlb = new Vector<String>();			
			for(String []s:OrderList){												
				String rgid = s[0];//�o��ҭq�귽��
				//�o��}�l�M�����ɶ�
				String ftime = s[1]; String etime = s[2];
				String sqlc = "insert into oinfo(orid,oid,rgid,ftime,etime) values"+
						"("+orid+","+oid+",'"+rgid+"','"+ftime+"','"+etime+"')";
				String sqld = new String(sqlc.getBytes(),"iso8859-1");
				stat.executeUpdate(sqld);//�����s
				orid++;//�D��ۥ[�A�@���U�@���O�����D��
			}
			con.commit();//�N�ưȴ���
			con.setAutoCommit(true);//��_�۰ʴ���Ҧ�
		}
		catch(Exception e){
			e.printStackTrace();
			i = -1;
			try{con.rollback();}//�X�{���~�A�o�_�^�u�ާ@
			catch(Exception ea)	{e.printStackTrace();}
		}
		finally	{DB.closeCon();}
		return i;//��^���浲�G,-1�N����
	}
	public static Vector<String []> getOrderList(String sqla){//�o��Τ�w����q��
		Vector<String []> v = new Vector<String[]>();//�Ыت�^�V�q
		try{
			con = DB.getCon();//�o��ƾڮw�s��
			stat = con.createStatement();//�Ыػy�y�ﹳ		
			String sql = new String(sqla.getBytes(),"iso8859-1");//��X
			rs = stat.executeQuery(sql);//����d��
			while(rs.next()){//�M�����G��
				String s[] = new String[6];
				for(int i=0;i<s.length;i++){//��H���i����
					s[i] = new String(rs.getString(i+1).getBytes("iso8859-1"),"big5");
				}				
				v.add(s);//�N�q��H���K�[�i��^�V�q
			}
		}
		catch(Exception e) {e.printStackTrace();}
		finally	{DB.closeCon();}
		return v;
	}
	public static Vector<String []> getOrderDetail(String oid){//�o��Y�@�q��Ա�
		Vector<String []> v = new Vector<String[]>();
		try{
			con = DB.getCon();//�o��ƾڮw�s��
			stat = con.createStatement();//�Ыػy�y�ﹳ
			rs = stat.executeQuery("select rgid,ftime,etime,ostatus from oinfo"+
						" where oid='"+oid+"'");//����d�߱o�쵲�G��			
			while(rs.next()){//�M�����G��
				String s[] = new String[5];
				for(int i=0;i<s.length-1;i++){//��X
					s[i] = new String(rs.getString(i+1).getBytes("iso8859-1"),"big5");
				}										
				v.add(s);//�N�H���K�[���^�V�q
			}
			for(String[] s:v){//�o����զW
				String rgid = new String(s[0].getBytes(),"iso8859-1");
				rs = stat.executeQuery("select gName from rgroup where gId=("+
							"select rgroup from resource where rgid='"+rgid+"')");
				rs.next();
				s[4] = new String(rs.getString(1).getBytes("iso8859-1"),"big5");
			}
		}
		catch(Exception e) {e.printStackTrace();}
		finally	{DB.closeCon();}
		return v;
	}
		public static Vector<String []> getOrderListThree(String sqla){//�o��Τ�w����q��䤤���T��
		Vector<String []> v = new Vector<String[]>();//�Ыت�^�V�q
		try{
			con = DB.getCon();//�o��ƾڮw�s��
			stat = con.createStatement();//�Ыػy�y�ﹳ		
			String sql = new String(sqla.getBytes(),"iso8859-1");//��X
			rs = stat.executeQuery(sql);//����d��
			while(rs.next()){//�M�����G��
				String s[] = new String[3];
				for(int i=0;i<s.length;i++){//��H���i����
					s[i] = new String(rs.getString(i+1).getBytes("iso8859-1"),"big5");
				}				
				v.add(s);//�N�q��H���K�[�i��^�V�q
			}
		}
		catch(Exception e) {e.printStackTrace();}
		finally	{DB.closeCon();}
		return v;
	}
}
}

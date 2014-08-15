package wyf.wyy;
import javax.naming.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;
public class DB
{
	private static Connection con=null;//�n��Connection�ޥ�
	private static Statement stat=null;//�n��Statement�ޥ�
	private static ResultSet rs=null;//�n��ResultSet�ޥ�
//*****************************�ƾڮw�s���M�����ާ@*************************
	public static Connection getCon(){//�o��ƾڮw�s������k	
		try{			
			 Context initial = new InitialContext();//�o��W�U��ޥ�
			 DataSource ds = //�o��DataSource�ޥ�
				    (DataSource)initial.lookup("java:comp/env/jdbc/jiudian");
			 con = ds.getConnection();//�o��ƾڮw�s��
		}
		catch(Exception e)
		{e.printStackTrace();}
		return con;//��^�ƾڮw�s��
		
		/*try{
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost/test";
		con = DriverManager.getConnection(url,"root","123");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			}catch(SQLException e1){
				e1.printStackTrace();
				}
	
		return con;*/
	}
	public static void closeCon(){//�����ƾڮw�s����k
		try	{
			  if(rs!=null){rs.close();}
			  if(stat!=null){stat.close();}
			  if(con!=null){con.close();}
		}
		catch(Exception e)
		{e.printStackTrace();}
	}
//*******************����ժ��ާ@******************************************
	public static Vector<String[]> getGroup(){
		Vector<String[]> v =new Vector<String[]>();//�Ыت�^�V�q�ﹳ
		try{
			 con = DB.getCon();//�o��ƾڮw�s��
			 stat = con.createStatement();//�Ыػy�y�ﹳ
			 String sql = "select gName,gImg,gDetail,gId,gOrderDet from rgroup";
			 rs = stat.executeQuery(sql);
			 while(rs.next()){//�M�����G���o����իH��		    
			    String group[] = new String[5];
			    for(int i=0;i<group.length;i++){
			      group[i] = //�N�H���K�[��Ʋ�
			    	new String(rs.getString(i+1).getBytes("iso8859-1"),"big5");
			    }			
				v.add(group);//�N�H���ƲղK�[���^���V�q��
			}
		}
		catch(Exception e)
		{e.printStackTrace();}
		finally
		{DB.closeCon();}	
		return v;
	}
	public static Vector<String> getGroupInfo(int gId){
		Vector<String> v =new Vector<String>();//�Ыت�^�H���V�q	
		try{
			 con = DB.getCon();//�o��ƾڮw�s��
			 stat = con.createStatement();//�Ыػy�y�ﹳ
			 String sql = "select gId,gName,gOrderDet,gImg,gDetail from"+
			  				" rgroup where gId="+gId;
			 rs = stat.executeQuery(sql);//����SQL�d��
			 if(rs.next()){//�N���G���H���K�[���^�V�q��			  
				v.add(new String(rs.getString(1).getBytes("iso8859-1"),"big5"));
				v.add(new String(rs.getString(2).getBytes("iso8859-1"),"big5"));
				v.add(new String(rs.getString(3).getBytes("iso8859-1"),"big5"));
				v.add(new String(rs.getString(4).getBytes("iso8859-1"),"big5"));
				v.add(new String(rs.getString(5).getBytes("iso8859-1"),"big5"));								
			 }
		}
		catch(Exception e){e.printStackTrace();}
		finally	{DB.closeCon();}//�����ƾڮw�s��
		return v;//��^���իH��
	}
	public static String getOrderDet(int gId)
	{
		String msg="";
		try
		{
			con=DB.getCon();
			stat=con.createStatement();
			String sql="select gOrderDet from rgroup where gId="+gId;
			rs=stat.executeQuery(sql);
			if(rs.next())
			{
				msg=new String(rs.getString(1).getBytes("iso8859-1"),"big5");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DB.closeCon();
		}
		return msg;
	}
	
//******************�����ާ@**************************************************
public static int getTotal(int span,int group){
	int result=0;//��l�ƪ�^����
	String sql = "";//�n��sql�ޥ�
	try{
		con = DB.getCon();
		stat = con.createStatement();
		//�o������O�����`����
		if(group==0)//0�N��Ҧ�����
		{sql = "select count(*) from resource";}
		else{
			sql = "select count(*) from resource "+"where rgroup='"+group+"'";
		}
		rs = stat.executeQuery(sql);//����sql�y�y			
	    rs.next();
	    int rows=rs.getInt(1);//�o��O������
	    result=rows/span+((rows%span==0)?0:1);//�p��X�`����
	}
	catch(Exception e){e.printStackTrace();}
	finally{DB.closeCon();}//�����ƾڮw�s��		
	return result;//��^���G
}
public static Vector<String[]> getResource(String rlevel)
	{
		Vector<String[]> v=new Vector<String[]>();
		
		String sql;
		try
		{
			con=DB.getCon();
			stat=con.createStatement();
			String rlevell = new String(rlevel.getBytes("big5"),"iso8859-1");
			sql="select rgid,rlevel,rmoney,rstatus from resource where rlevel='"+rlevell+"'";
			rs=stat.executeQuery(sql);
            while(rs.next()){//�M�����G���o����իH��		    
			    String group[] = new String[4];
			    for(int i=0;i<group.length;i++){
			      group[i] = //�N�H���K�[��Ʋ�
			    	new String(rs.getString(i+1).getBytes("iso8859-1"),"big5");
			    }			
				v.add(group);//�N�H���ƲղK�[���^���V�q��
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DB.closeCon();
		}
		return  v;
	}
	public static Vector<String[]> getResource1()
	{
		Vector<String[]> v=new Vector<String[]>();
		try
		{
			con=DB.getCon();
			stat=con.createStatement();
			String sql="select rgroup,rgid,rlevel,rmoney,rstatus from resource";
			rs=stat.executeQuery(sql);
            while(rs.next()){//�M�����G���o����իH��		    
			    String group[] = new String[4];
			    for(int i=0;i<group.length;i++){
			      group[i] = //�N�H���K�[��Ʋ�
			    	new String(rs.getString(i+2).getBytes("iso8859-1"),"big5");
			    }			
				v.add(group);//�N�H���ƲղK�[���^���V�q��
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DB.closeCon();
		}
		return  v;
	}
	
public static Vector<String[]> getPageContent(int page,int span,int group){
	Vector<String[]> v = new Vector<String[]>();//�n����^�V�q���X
	String sql = "";//�n��sql�y�y�ޥ�
	int startRow = 	(page-1)*span;//�p��X�_�l�O�����
	try{
		con = DB.getCon();
		stat = con.createStatement();
		if(group==0){//group�ѼƬ��s�A�h�O��Ҧ������նi��������
			sql = "select rgid,rlevel,rmoney,rdetail,rstatus,rid,gName from "+
			       "resource,rgroup where resource.rgroup=rgroup.gId order "+
			        "by rgroup, rgid, rid";
		}
		else{//�������նi��������
			sql = "select rgid,rlevel,rmoney,rdetail,rstatus,rid,gName "+
			 	   "from resource,rgroup where resource.rgroup=rgroup.gId "+
			 	   "and rgroup='"+group+"' order by rgid";
		}
		rs = stat.executeQuery(sql);//����sql�y�y�A���쵲�G��
		if(startRow!=0)//�p�G���椣�O�Ĺs��
		{rs.absolute(startRow);}//���G���u�ʨ�_�l��
		int c=0;//����Ū�����O������
		while(c<span&&rs.next()){//�q����Ū�C����ܪ��O������
			String s[] = new String[7];
			for(int i=0;i<s.length;i++){
		      s[i] = //�M�����G���N�H���K�[��Ʋ�
		    	new String(rs.getString(i+1).getBytes("iso8859-1"),"big5");
		    }							
			v.add(s);//�N�ƲղK�[���^�V�q
			c++;
		}
	}
	catch(Exception e){e.printStackTrace();}
	finally{DB.closeCon();}//�����ƾڮw�s��	
	return v;//��^���G
}
//*******************�o��Y�i��Y�@�C���̤j�Ȩå[1***************************
	public static int getId(String table,String row){//�o��@�Ӫ�D��ID+1��
		int id = 0;
		try	{
			con = DB.getCon();//�o��ƾڮw�s��
			stat = con.createStatement();//�Ыػy�y�ﹳ
			rs = stat.executeQuery("select count(*) from "+table);
			rs.next();
			if(rs.getInt(1)==0)	{ id = 1; }//�p�G���S���O���A�h�Nid�m��1
			else{
				rs = stat.executeQuery("select max("+row+") from "+table);
				rs.next();
				id = Integer.parseInt(rs.getString(1))+1;//�N��ȥ[�@
			}						
		}
		catch(Exception e){e.printStackTrace();}
		finally	{DB.closeCon();}//�����ƾڮw�s��
		return id;//��^���G
	}
//********************�Y���O���O�_�s�b**************************************
	public static boolean isExist(String sqla){//�d�ݦ����O���O�_�s�b
		boolean flag = false;			
		try{			
			String sql = new String(sqla.getBytes("big5"),"iso8859-1");//��X	
			con = DB.getCon();//�o��ƾڮw�s��
			stat = con.createStatement();//�Ыػy�y�ﹳ
			rs = stat.executeQuery(sql);//����d��
			if(rs.next()) {flag = true;}
		}
		catch(Exception e)	{e.printStackTrace();}
		finally	{DB.closeCon();}//�����ƾڮw�s��		
		return flag;//��^���G
	}
//*********************��s�ƾڮw*****************************************
	public static int update(String sqla){
		int changedCount=0;
		try{
			String sql = new String(sqla.getBytes(),"iso8859-1");//��X
			con = DB.getCon();//�o��ƾڮw�s��
			stat = con.createStatement();//�Ыػy�y�ﹳ
			changedCount = stat.executeUpdate(sql);//�i���s
		}
		catch(Exception e)	{e.printStackTrace();}
		finally {DB.closeCon();}//�����ƾڮw�s�� 		
		return changedCount;//��^��s�O������
	}
	public static boolean update(String sqla,String sqlb){//�ưȳB�z
		boolean b = false;//�O���O�_��s���\
		try{
			con = DB.getCon();//�o��ƾڮw�s��
			con.setAutoCommit(false);//�T��۰ʴ���A�}�l�@�Өư�
			stat = con.createStatement();
			String sql = new String(sqla.getBytes(),"iso8859-1");//��X
			stat.executeUpdate(sql);//�����s
			sql = new String(sqlb.getBytes(),"iso8859-1");//��X
			stat.executeUpdate(sql);//�����s			
			con.commit();//�N�ưȴ���			
			con.setAutoCommit(true);//��_�۰ʴ���Ҧ�
			b = true;//�]�m��s���\
		}
		catch(Exception e){
			e.printStackTrace();
			try{
				con.rollback();//�X�{���D�A�ưȦ^�u
				b = false;
			}
			catch(Exception ea){ea.printStackTrace();}
		}
		finally{DB.closeCon();}//�����ƾڮw�s��
		return b;//��^��s���\�Ϊ̥��Ѽлx
	}
		public static boolean updatea(String sqla)//�ڲK�[��
	{
		boolean b=false;
		try{
			String sql = new String(sqla.getBytes(),"iso8859-1");//��X
			con = DB.getCon();//�o��ƾڮw�s��
			con.setAutoCommit(false);//�T��۰ʴ���A�}�l�@�Өư�
			stat = con.createStatement();//�Ыػy�y�ﹳ
			stat.executeUpdate(sql);//�i���s
			con.commit();//�N�ưȴ���			
			con.setAutoCommit(true);//��_�۰ʴ���Ҧ�
			b = true;//�]�m��s���\
		}
		catch(Exception e){
			e.printStackTrace();
			try{
				con.rollback();//�X�{���D�A�ưȦ^�u
				b = false;
			}
			catch(Exception ea){ea.printStackTrace();}
		}
		finally{DB.closeCon();}//�����ƾڮw�s��
		return b;//��^��s���\�Ϊ̥��Ѽлx
	}
//********************�ھڤ@��SQL�o��ƾڮw���H��****************************
	public static String getInfo(String sqla){
		String Info=null;
		try{			
			String sql = new String(sqla.getBytes(),"iso8859-1");//SQL��X
			con = DB.getCon();//�o��ƾڮw�s��
			stat = con.createStatement();//�Ыػy�y�ﹳ
			rs = stat.executeQuery(sql);//����d��
			if(rs.next())
			{Info=new String(rs.getString(1).getBytes("iso8859-1"),"big5");}
		}
		catch(Exception e)	{e.printStackTrace();}
		finally {DB.closeCon();}		
		return Info;
	}
	public static String getDetail(String rgid)
	{
		String s=null;
		try
		{
			String sql="select rdetail from resource where rgid="+rgid;
			con=DB.getCon();
			stat=con.createStatement();
			rs=stat.executeQuery(sql);
			if(rs.next())
			{
				s=new String(rs.getString(1).getBytes("iso8859-1"),"big5");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DB.closeCon();
		}
		return s;
	}
	
//***********************�o��Τ᪺�ԲӫH��********************************
	public static Vector<String> getUserInfo(String uname1){
		Vector<String> userInfo=new Vector<String>();
		try{
			String uname = new String(uname1.getBytes("big5"),"iso8859-1");//��X
			con = DB.getCon();//�o��ƾڮw�s��
			stat = con.createStatement();//�Ыػy�y�ﹳ
			rs = stat.executeQuery("select pwd,telNum,realName,gender,"+
									"email from user where uname='"+uname+"'");
			if(rs.next()){//�N�Τ�H���K�[��V�q��
			    userInfo.add(new String(rs.getString(1).getBytes("iso8859-1"),"big5"));
				userInfo.add(new String(rs.getString(2).getBytes("iso8859-1"),"big5"));
				userInfo.add(new String(rs.getString(3).getBytes("iso8859-1"),"big5"));
				userInfo.add(new String(rs.getString(4).getBytes("iso8859-1"),"big5"));
				userInfo.add(new String(rs.getString(5).getBytes("iso8859-1"),"big5"));
			}
		}
		catch(Exception e) {e.printStackTrace();}
		finally	{DB.closeCon();}//�����ƾڮw�s��		
		return userInfo;//��^�Τ�H��
	}
//******************�o��귽���ԲӫH��***************************
	public static Vector<String[]> getResInfo(String sqla){
		Vector<String []> v = new Vector<String[]>();
		try{
			con = DB.getCon();//�o��ƾڮw�s��
			stat = con.createStatement();//�Ыػy�y�ﹳ			
			String sql = new String(sqla.getBytes(),"iso8859-1");//��X
			rs = stat.executeQuery(sql);//����d��
			while(rs.next()){
				String s[] = new String[8];
				for(int i=0;i<s.length-1;i++){//�N�귽�H���K�[��Ʋ�
					s[i] = new String(rs.getString(i+1).getBytes("iso8859-1"),"big5");
				}				
				v.add(s);//�N�ƲղK�[���^�V�q��
			}
			for(String s[]:v){//�ھڤ���ID�o����զW				
				String sqlb = "select gName from rgroup where gId='"+s[5]+"'";
				rs = stat.executeQuery(sqlb);
				rs.next();//���G����ЦV�Ჾ�@��
				s[7] = new String(rs.getString(1).getBytes("iso8859-1"),"big5");
			}
		}
		catch(Exception e){e.printStackTrace();}
		finally{DB.closeCon();}//�����ƾڮw�s��
		return v;//��^�d�ߵ��G
	}
	
//*****************�o��޲z���ԲӫH��********************************
	public static Vector<String[]> getAdminInfo(){
		Vector<String[]> v = new Vector<String[]>();
		try{
			con = DB.getCon();//�o��ƾڮw�s��
			stat = con.createStatement();//�Ыػy�y�ﹳ
			rs = stat.executeQuery("select adname,adlevel from adinfo");//����d��
			while(rs.next()){
				String s[] = new String[2];
				s[0] = new String(rs.getString(1).getBytes("iso8859-1"),"big5");
				s[1] = new String(rs.getString(2).getBytes("iso8859-1"),"big5");				
				v.add(s);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		finally{DB.closeCon();}		
		return v;
	}
		public static boolean isDelete(String rid1){
		boolean b = false;
		int count=0;
		try{
			String orid=new String(rid1.getBytes("iso8859-1"),"big5");
			System.out.println(orid);
			String sqla="delete from olist where oid="+orid;
			String sqlb="delete from oinfo where orid="+orid;
		    count+=stat.executeUpdate(sqla);
		    count+=stat.executeUpdate(sqlb);
		    if(count==2){b=true;}
		}
		catch(Exception e){e.printStackTrace();}
		finally{DB.closeCon();}
		return b;
	}
}

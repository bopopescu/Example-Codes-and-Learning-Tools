package com.bn.reader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLDBUtil 
{
	static SQLiteDatabase sld;
	public static void createOrOpenDatabase()//�ЫةΥ��}�ƾڮw
	{
		try
    	{
	    	sld=SQLiteDatabase.openDatabase
	    	(
	    			"/data/data/com.bn.reader/recordself", //�ƾڮw�Ҧb���|
	    			null, 							//��Фu�t
	    			SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY //Ū�g�B�Y���s�b�h�Ы�
	    	);	
	    	
	    	String sql1="create table if not exists BookRecord"+
					"("+
					"rid INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"path varchar(50),"+
					"data blob"+
					");";
	    	String sql2="create table if not exists BookMark"+
					"("+
					"mid INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"ridfk INTEGER,"+
					"bmname varchar(50),"+
					"page INTEGER"+
					");";
	    	String sql3="create table if not exists LastTimePage"+
	    			"("+
	    			"lid INTEGER PRIMARY KEY AUTOINCREMENT,"+
	    			"path varchar(50),"+
	    			"page INTEGER,"+
	    			"fontsize INTEGER"+
	    			");";
	    	sld.execSQL(sql1);//�Ыت�
	    	sld.execSQL(sql2);
	    	sld.execSQL(sql3);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
	
	//�����ƾڮw����k
    public static void closeDatabase()
    {
    	try
    	{
	    	sld.close();     		
    	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    } 

	//��^���w�ѩҦ�������
	public static List<BookMark> getBookmarkList(String path)
	{
		List<BookMark> al=new ArrayList<BookMark>();
		Cursor cur=null;
		try
		{
			createOrOpenDatabase();//���}�ƾڮw			
			String sql="select page,bmname from BookRecord,BookMark"
						+" where path='"+path+"' and rid=ridfk order by page";
			cur=sld.rawQuery(sql, new String[]{});
			while(cur.moveToNext())
		    {		
				int page=cur.getInt(0);
				String bmname=cur.getString(1);
		    	al.add(new BookMark(bmname,page));//�N���ƩM���ҦW�r�s�J��������
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cur.close();
			closeDatabase(); //�����ƾڮw
		}
		return al;
	}
	//��^���w�Ѫ��̫�@�Ӯ��Ҫ�����
	public static int getLastBookmarkPage(String path)
	{
		int page=0;
		Cursor cur=null;
		try
		{
			createOrOpenDatabase();//���}�ƾڮw			
			String sql="select page from BookRecord,BookMark"
						+" where path='"+path+"' and rid=ridfk order by page";
			cur=sld.rawQuery(sql, new String[]{});
			
			cur.moveToLast();
			page=cur.getInt(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cur.close();
			closeDatabase(); //�����ƾڮw
		}
		return page;
	}
	
	
	
	
	
	//��^�C�@���ѩ�Ū�쪺�Ѫ�����
	public static int getLastTimePage(String path)
	{
		int page=0;
		Cursor cur=null;
		try
		{
			createOrOpenDatabase();//���}�ƾڮw			
			String sql="select page from LastTimePage"
						+" where path='"+path+"'";
			cur=sld.rawQuery(sql, new String[]{});
			cur.moveToNext();	
			page=cur.getInt(0);

			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cur.close();
			closeDatabase(); //�����ƾڮw
		}
		return page;
	}
	
	//��^�C�@���ѩ�Ū�쪺�Ѫ��r��j�p
	public static int getLastTimeFontSize(String path)
	{
		int fontsize=0;
		Cursor cur=null;
		try
		{
			createOrOpenDatabase();//���}�ƾڮw			
			String sql="select fontsize from LastTimePage"
						+" where path='"+path+"'";
			cur=sld.rawQuery(sql, new String[]{});
			cur.moveToNext();	
			fontsize=cur.getInt(0);

			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cur.close();
			closeDatabase(); //�����ƾڮw
		}
		return fontsize;
	}
	
	
	
	
	//�VlastTime�����J�ƾ�
	public static void lastTimeInsert(String path,int page,int fontsize)
	{
		Cursor cur=null;
		try
		{
			createOrOpenDatabase();//���}�ƾڮw
			//���q�ƾڮw���d�߫��w���W�٬O�_�s�b
			String sql="select path from LastTimePage where path='"+path+"'";//�d��O�_�s�b���w���|���ƾ�
			cur=sld.rawQuery(sql, new String[]{});
			if(cur.moveToNext())
			{//�Y�w�g�s�b�h��s
				sql="update LastTimePage set page=?,fontsize=? where path='"+path+"'";
				sld.execSQL(sql,new Object[]{page,fontsize});
			}
			else
			{//�Y���s�b�h���J
	            sql="insert into LastTimePage(path,page,fontsize)values(?,?,?)";    		
	    		sld.execSQL(sql,new Object[]{path,page,fontsize});   
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			cur.close();
        	closeDatabase(); //�����ƾڮw
		}
	}
	
	//�VBookRecord�����J�ƾ�
	public static void recordInsert(String path,byte[] rdata)
	{
		Cursor cur=null;
		try
		{
			createOrOpenDatabase();//���}�ƾڮw
			//���q�ƾڮw���d�߫��w���W�٬O�_�s�b
			String sql="select path from BookRecord where path='"+path+"'";//�d��O�_�s�b���w���|���ƾ�
			cur=sld.rawQuery(sql, new String[]{});
			if(cur.moveToNext())
			{//�Y�w�g�s�b�h��s
				sql="update BookRecord set data=? where path='"+path+"'";
				sld.execSQL(sql,new Object[]{rdata});
			}
			else
			{//�Y���s�b�h���J
				//���J�p��p��ID,�p��p���W��,�p��p���`�q,�p��p���H�ơA�p��p��blod
	            sql="insert into BookRecord(path,data)values(?,?)";    		
	    		sld.execSQL(sql,new Object[]{path,rdata});   
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cur.close();
        	closeDatabase(); //�����ƾڮw
		}
	}
	
	//������w�W�٪��O���ƾڡ]�o�쪺�OHashMap��byte�ƾڡ^
	public static byte[] selectRecordData(String path)
	{
		byte[] data=null;
		Cursor cur=null;

		try
		{
			createOrOpenDatabase();//���}�ƾڮw			
			String sql="select path,data from BookRecord where path='"+path+"'";
			cur=sld.rawQuery(sql, new String[]{});
			if(cur.moveToNext())
		    {
				data=cur.getBlob(1);	
		    }		
		}
		catch(Exception e)
		{
			e.printStackTrace();  
		}
		finally
		{
			cur.close();
        	closeDatabase(); //�����ƾڮw
		}
		
		return data;
	}
	
	//�P�_�O�Ĥ@�����}�o�����٬O��N�����}����
	public static boolean judgeIsWhichTime(String path)
	{
		Cursor cur=null;
		try
		{
			createOrOpenDatabase();//���}�ƾڮw			
			String sql="select path from BookRecord where path='"+path+"'";
			cur=sld.rawQuery(sql, new String[]{});
			if(cur.moveToNext())//�p�G�s�b���|�A�h�O��N�����}�o����
		    {
				return false;
		    }
			else
			{
				return true;//�_�h�O��1�����}�o����	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();  
		}
		finally
		{
			cur.close();
        	closeDatabase(); //�����ƾڮw
		}
		return false;	
	}
	
	//�R��BookMark�������u�ѦW�v���O��
	public static void deleteOneBookMark(String name)
	{
		try
		{
			createOrOpenDatabase();//���}�ƾڮw			
			String sql="delete from BookMark where bmname='"+name+"'";
			sld.execSQL(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();  
		}
		finally
		{
        	closeDatabase(); //�����ƾڮw
		}
	}
	//�R����e�o���Ѫ���������
	//��^���w�ѩҦ�������
	public static void deleteAllBookMark(String path)
	{	
		Cursor cur=null;
		try
		{
			createOrOpenDatabase();//���}�ƾڮw			
			String sql="select rid from BookRecord,BookMark"
						+" where path='"+path+"' and rid=ridfk";
			cur=sld.rawQuery(sql, new String[]{});
		    cur.moveToNext();
			int rid=cur.getInt(0);//�o����Ҥ�ridfk����
			
			String sql2="delete from BookMark where ridfk='"+rid+"'";
			sld.execSQL(sql2);//�M�ŷ�e�o���Ѫ������O��
		    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cur.close();
        	closeDatabase(); //�����ƾڮw
		}
	}
	//�P�_��e�o���ѬO�_�s�b����
	public static boolean judgeHaveBookMark(String path)
	{	
		Cursor cur=null;
		try
		{
			createOrOpenDatabase();//���}��?�p?			
			String sql="select page from BookRecord,BookMark"
						+" where path='"+path+"' and rid=ridfk";
			cur=sld.rawQuery(sql, new String[]{});
			if(cur.moveToNext())//�p�G�Ѥ��s�b���ҡA��^true
		    {
				return true;
		    }
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cur.close();
			closeDatabase(); //�����ƾڮw
		}
		return false;		
	}
	//�VBookMark�����J�ƾ�
	public static void bookMarkInsert(String name,int page)
	{
		Cursor cur=null;
		try
		{
			createOrOpenDatabase();//���}�ƾڮw
			//�d�߷�epath������rid
			String sql="select rid from BookRecord where path='"+Constant.FILE_PATH+"'";
			cur=sld.rawQuery(sql, new String[]{});
			cur.moveToNext();
			int rid=cur.getInt(0);
			
			//���q�ƾڮw���d�߫��w���W�٬O�_�s�b
			sql="select bmname from BookMark where bmname='"+name+"'";
			cur=sld.rawQuery(sql, new String[]{});
			if(cur.moveToNext())
			{//�Y�w�g�s�b�h��s
				sql="update BookMark set page=?,ridfk=? where bmname='"+name+"'";
				sld.execSQL(sql,new Object[]{page,rid});
			}
			else
			{//�Y���s�b�h���J
	            sql="insert into BookMark(bmname,page,ridfk)values(?,?,?)";    		
	    		sld.execSQL(sql,new Object[]{name,page,rid});  		
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			cur.close();
        	closeDatabase(); //�����ƾڮw
		}
	}
	//�qhashMap��Ƭ�byte
	public static byte[] fromListRowNodeListToBytes(HashMap<Integer,ReadRecord> map)
	{
		byte[] result=null;		
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		try 
		{
			ObjectOutputStream oout=new ObjectOutputStream(baos);
			oout.writeObject(map);
			result=baos.toByteArray();
			oout.close();
			baos.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	//�qbyte��Ƭ�hashMap
	@SuppressWarnings("unchecked")
	public static HashMap<Integer,ReadRecord> fromBytesToListRowNodeList(byte[] data)
	{
		HashMap<Integer,ReadRecord> result=null;
		try
		{			
			ByteArrayInputStream bais=new ByteArrayInputStream(data);			
			ObjectInputStream oin=new ObjectInputStream(bais);			
			result=(HashMap<Integer,ReadRecord>)oin.readObject();
			oin.close();
			bais.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
}


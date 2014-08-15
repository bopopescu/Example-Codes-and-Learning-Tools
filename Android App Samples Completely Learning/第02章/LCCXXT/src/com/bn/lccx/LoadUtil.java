package com.bn.lccx;


import java.util.Vector;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LoadUtil {
	
	public static SQLiteDatabase createOrOpenDatabase()//�s���ƾڮw
	{		
		SQLiteDatabase sld=null;
		try{
			sld=SQLiteDatabase.openDatabase//�s���óЫؼƾڮw�A�p�G���s�b�h�Ы�
			(
					"/data/data/com.bn.lccx/mydb", 
					null, 
					SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return sld;//��^�ӳs��
	}
	
	
	
	public static void createTable(String sql){//�Ыت�
		SQLiteDatabase sld=createOrOpenDatabase();//�s���ƾڮw
		try{
			sld.execSQL(sql);//����SQL�y�y
			sld.close();//�����s��
		}catch(Exception e){
			
			
		}		
	}
	
	public static boolean insert(String sql)//���J�ƾ�
	{
		SQLiteDatabase sld=createOrOpenDatabase();//�s���ƾڮw
		try{
			sld.execSQL(sql);
			
			sld.close();
			return true;
		}catch(Exception e){
			return false;			
		}
		
	}
	
	public  static Vector<Vector<String>>  query(String sql)//�d��
	{
		Vector<Vector<String>> vector=new Vector<Vector<String>>();//�s�ئs��d�ߵ��G���V�q
		SQLiteDatabase sld=createOrOpenDatabase();//�o��s���ƾڮw���s��
		
		
		try{			
			Cursor cur=sld.rawQuery(sql, new String[]{});//�o�쵲�G��
			
			while(cur.moveToNext())//�p�G�s�b�U�@��
			{
				Vector<String> v=new Vector<String>();
				int col=cur.getColumnCount();		//�N���J�V�q		
				for( int i=0;i<col;i++)
				{
					v.add(cur.getString(i));					
				}				
				vector.add(v);         		
			}
			cur.close();//�������G��
			sld.close();//�����s��
		}catch(Exception e)
		{
			e.printStackTrace();		
		}
		return vector;
	}
	
		
	
	
	//�d��Y�����g�L���Ҧ�����
	public static Vector<Vector<String>> getInfo(String tname)
	{
		//�d��Y�C���g�L������
		String sql = "select Sname,Rarrivetime,Rstarttime "+
							"from station,"+
							"(select Sid,Rid,Rarrivetime,Rstarttime "+
							"from relation where Tid="+
							"(select Tid from train "+
							"where Tname='"+tname+"')) a "+
							"where a.Sid=station.Sid order by Rid";	
		//�o��ŦX�n�D����
		Vector<Vector<String>> vtemp = query(sql);	
		
		return vtemp;
		
	}
	//�����d��
	public static Vector<Vector<String>> getSameVector(String start,String end)
	{
		//�d�䨮�W,�l�o��,���I���M������
		String sql = "select Tname,Tstartstation,Tterminus,Ttype "+
						"from train where Tid in "+
						"(select Tid from relation where Sid in "+
						"(select Sid from station where Sname='"+start+"') "+
						"and Tid in "+
						"(select Tid from relation where Sid in "+
						"(select Sid from station where Sname='"+end+"')))";
		//�o�즳�������H����Vector
		Vector<Vector<String>> temp = query(sql);
		//�d��X�o���M�����}�����ɶ�
		String sql1 = "select Sname,Rstarttime from station,relation"+
						" where Sname='"+start+"' and "+
						"station.Sid=relation.Sid and "+
						"relation.Tid in "+
						"(select Tid from relation where Sid in"+
						"(select Sid from station where Sname='"+start+"') "+
						"and Tid in"+
						"(select Tid from relation where Sid in "+
						"(select Sid from station where Sname='"+end+"')))";
		//�d����I���M�����쯸�ɶ�
		String sql2 = "select Sname,Rarrivetime from station,relation"+
						" where Sname='"+end+"' and "+
						"station.Sid=relation.Sid and "+
						"relation.Tid in "+
						"(select Tid from relation where Sid in"+
						"(select Sid from station where Sname='"+start+"') "+
						"and Tid in"+
						"(select Tid from relation where Sid in "+
						"(select Sid from station where Sname='"+end+"')))";
		//�o�즳�����������H��
		Vector<Vector<String>> temp1 = query(sql1);		
		Vector<Vector<String>> temp2 = query(sql2);			
		//�N�d�ߵ��G�զX��@�_	
		temp = combine(temp,temp1,temp2);		
		return temp;		
	}
	
	//�զX�V�q
	public static  Vector<Vector<String>> combine(Vector<Vector<String>> temp,Vector<Vector<String>> temp1,Vector<Vector<String>> temp2)
	{//�N�o�T��Vector�զX���@��	
	for(int i=0;i<temp.size();i++)
	{
		Vector<String> v1 = temp.get(i);
		if(i<temp1.size())
		{
			Vector<String> v2 = temp1.get(i);
			//�NV2�����������[��V1����
			for(int j=0;j<v2.size();j++)
			{
				v1.add(v2.get(j));
			}				
		}
		else{
		//�S�����Y�ɲK�[��
			v1.add("");
			v1.add("");
		}	
	}
		for(int i=0;i<temp.size();i++)
		{
			Vector<String> v1 = temp.get(i);
			if(i<temp2.size())
			{
				Vector<String> v2 = temp2.get(i);
				//�NV2�����������[��V1����
				for(int j=0;j<v2.size();j++)
				{
					v1.add(v2.get(j));
				}				
			}
			else
			{
				//�S�����Y�ɲK�[��
				v1.add("");
			}
		
		}
	return temp;
	}

	//�Y���������p�A��l���M�������٦��������ɶ�
	public static Vector<Vector<String>> trainSearch(String tname)
	{//�����d��		
		
		
		String sql =//�d�䨮�W,�l�o��,���I���M������
			"select Tname,Tstartstation,Tterminus,Ttype "+
									"from train where Tname='"+tname+"'";
								
		String sql1 = //�d��X�o���M�����}�����ɶ�	
			"select Tstartstation,Rstarttime from train,relation "+
								"where train.Tid=relation.Tid and "+
								"Tname='"+tname+"' and relation.Sid="+
								"(select Sid from station "+
								"where Sname=train.Tstartstation)";

		
		
		String sql2 = //�d����I���M�����쯸�ɶ�Rarrivetime
			"select Tterminus,Rarrivetime from train,relation "+
								"where train.Tid=relation.Tid and "+
								"Tname='"+tname+"' and relation.Sid="+
								"(select Sid from station "+
								"where Sname=train.Tterminus)";
		
		Vector<Vector<String>> temp = query(sql);//�o�쨮�W,�l�o��,���I���M��������Vector
		
		Vector<Vector<String>> temp1 = query(sql1);//�o��X�o���M�����}���ɶ���vector
				
		Vector<Vector<String>> temp2 = query(sql2);//�o����I���M�����쯸�ɶ���vector
		temp = combine(temp,temp1,temp2);
		
		return temp;
	}
	
	public static Vector<Vector<String>> stationSearch(String station)////�ھڨ����W�r�d�߸g�L�������Ҧ���
	{//�����d��	�A�o��g�L�C�@�������쯸�ɶ��M�X�Ԯɶ�
		
		
		//�d�ߦ����������H��
		String sql = "select Tname,Tstartstation,Tterminus,Ttype "+
								"from train where Tid in "+
								"(select Tid from relation where Sid in "+
								"(select Sid from station where "+
								"Sname='"+station+"'))";
//		//�d�ߥX�o���ΥX�o�ɶ�
		String sql1 = "select '"+station+"',Rstarttime from relation "
								+"where Sid = "+	
								"(select Sid from station where "+
								"Sname='"+station+"')";
		

		
		String sql2 = "select '"+station+"',Rarrivetime from relation "
		+"where Sid = "+	
		"(select Sid from station where "+
		"Sname='"+station+"')";
		
		//�o�즳���H�����V�q
		Vector<Vector<String>> temp = query(sql);
		
		//�o��X�o���M�����}���ɶ���vector
		Vector<Vector<String>> temp1 = query(sql1);
		
		//�o����I���M�����쯸�ɶ���vector
		Vector<Vector<String>> temp2 = query(sql2);
		
		
		//�N�T��Vector�զX�b�@�_		
		temp = combine(temp,temp1,temp2);
		return temp;
	}
	public  static  Vector<Vector<String>>  Zjzquery(String start,String zjz,String end)//�d�ߡA���௸�d�ߪ�
	{
		Vector<Vector<String>> vector=getSameVector(start,zjz);//����B�A���d�X�_�I���줤�௸�A�M��A�d�X���௸����I�������Y�i
		Vector<Vector<String>> vector2=getSameVector(zjz,end);
		if(vector.size()==0||vector2.size()==0)//�p�G�Y�@�ӵL���G�A�h�����L�Ӥ��௸������
		{
			
			vector.clear();
			vector2.clear();//�M�ŦV�q���ƾ�
		}
		
		for(int i=0;i<vector2.size();i++)
		{
			vector.add(vector2.get(i));
		}//�_�h�N�ĤG�ӦV�q�����ƾڲK�[��Ĥ@�ӦV�q��
		
		return vector;//��^
	}

	//�d�ߨ䴡�J����ID���̤j��
	public static int getInsertId(String name,String tid)
	{
		int id = 0;
		String sql = "select Max("+tid+") from "+name;		
			
			SQLiteDatabase sld=createOrOpenDatabase();
			
			
			try{			
				Cursor cur=sld.rawQuery(sql, new String[]{});
			
			//�d�ݵ��G��			
			if(cur.moveToNext())
			{
				id = cur.getInt(0);
			}
			//�������G��,�y�y�γs��
			cur.close();
			sld.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		id++;
		return id;
	}

}


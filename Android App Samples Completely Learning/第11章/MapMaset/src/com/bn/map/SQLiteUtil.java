package com.bn.map;

import java.util.Vector;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteUtil 
{
	static SQLiteDatabase sld;
	//�ЫةΥ��}�ƾڮw����k
    public static void createOrOpenDatabase()
    {
    	try
    	{
	    	sld=SQLiteDatabase.openDatabase
	    	(
	    			"/data/data/com.bn.map/mydb", //��e���ε{�ǥu��b�ۤv���]�U�Ыؼƾڮw
	    			null, 								//CursorFactory
	    			SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY //Ū�g�B�Y���s�b�h�Ы�
	    	);
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
    //�ت�
    public static void createTable(String sql)
    {
    	createOrOpenDatabase();//���}�ƾڮw
    	try
    	{
        	sld.execSQL(sql);//�ت�
    	}
		catch(Exception e)
		{
            e.printStackTrace();
		}
    	closeDatabase();//�����ƾڮw
    }
  //���J�O������k
    public static void insert(String sql)
    {
    	createOrOpenDatabase();//���}�ƾڮw
    	try
    	{
        	sld.execSQL(sql);
    	}
		catch(Exception e)
		{
            e.printStackTrace();
		}
		closeDatabase();//�����ƾڮw
    }
    //�R���O������k
    public static  void delete(String sql)
    {
    	createOrOpenDatabase();//���}�ƾڮw
    	try
    	{
        	sld.execSQL(sql);
      	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		closeDatabase();//�����ƾڮw
    }
    //�ק�O������k
    public static void update(String sql)
    {   
    	createOrOpenDatabase();//���}�ƾڮw
    	try
    	{
        	sld.execSQL(sql);    	
    	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		closeDatabase();//�����ƾڮw
    }
    //�d�ߪ���k
    public static Vector<Vector<String>> query(String sql)
    {
    	createOrOpenDatabase();//���}�ƾڮw
    	Vector<Vector<String>> vector=new Vector<Vector<String>>();//�s�ئs��d�ߵ��G���V�q
    	try
    	{
           Cursor cur=sld.rawQuery(sql, new String[]{});
        	while(cur.moveToNext())
        	{
        		Vector<String> v=new Vector<String>();
        		int col=cur.getColumnCount();		//��^�C�@�泣�h�֦r�q
        		for( int i=0;i<col;i++)
				{
					v.add(cur.getString(i));					
				}				
				vector.add(v);
        	}
        	cur.close();		
    	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		closeDatabase();//�����ƾڮw
		return vector;
    }  
}


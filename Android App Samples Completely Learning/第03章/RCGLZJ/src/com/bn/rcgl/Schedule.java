package com.bn.rcgl;

import static com.bn.rcgl.Constant.getNowDateString;
import static com.bn.rcgl.Constant.getNowTimeString;
import static com.bn.rcgl.DBUtil.getSNFromPrefs;
import android.util.Log;

public class Schedule 
{
	private int sn;//�C�@�Ӥ�{�����@�ӿW�@�L�G��sn�X�A�b�ƾڮw�����D��
	private String date1;//��{���
	private String time1;//��{�ɶ�
	private String date2;//�x�����
	private String time2;//�x���ɶ�
	private String type;//��{����
	private String title;//��{���D
	private String note;//��{�Ƶ�
	private boolean timeSet;//��{�O�_�]�m����ɶ�
	private boolean alarmSet;//��{�O�_�]�m�x��
	
	
	//�Ыطs��{�ɪ��{�ɼƾڡA�u�ݭn�~���T�ӼƾڡA�ΨӦb���i�J�s�ؤ�{�ɭ����~����q�{�]�m����e���
	public Schedule(int y,int m,int d)
	{
		sn=0;
		date1=toDateString(y,m,d);
		time1=toTimeString(8,0);//�ɶ��q�{8�I
		
		date2=null;
		time2=null;
		
		title="";
		note="";
		type="";
		
		timeSet=true;
		alarmSet=false;
				
	}
	
	//���c�y�����q�ƾڮwŪ����{�ﹳ�ɥ�
	public Schedule(int sn,String date1,String time1,String date2,String time2,String title,String note,String type,String timeSet,String alarmSet)
	{
		this.sn=sn;
		this.date1=date1;
		this.time1=time1;
		this.date2=date2;
		this.time2=time2;
		this.title=title;
		this.note=note;
		this.type=type;
		this.timeSet=Boolean.parseBoolean(timeSet);
		this.alarmSet=Boolean.parseBoolean(alarmSet);
	}
	
	public int getYear()//��o�~
	{
		String[] date=date1.split("/");
		int tmp=Integer.valueOf(date[0]);
		return tmp;
	}
	
	public int getMonth()//��o��
	{
		String[] date=date1.split("/");
		int tmp=Integer.valueOf(date[1]);
		return tmp;
	}
	
	public int getDay()//��o��
	{
		String[] date=date1.split("/");
		int tmp=Integer.valueOf(date[2]);
		return tmp;
	}
	
	public int getHour()//��o��
	{
		String[] time=time1.split(":");
		int tmp=Integer.valueOf(time[0]);
		return tmp;
	}
	
	public int getMinute()//��o��
	{
		String[] time=time1.split(":");
		int tmp=Integer.valueOf(time[1]);
		return tmp;
	}
	
	public int getAYear()//��o�x�����~
	{
		String[] date=date2.split("/");
		int tmp=Integer.valueOf(date[0]);
		return tmp;
	}
	
	public int getAMonth()//��o�x����
	{
		String[] date=date2.split("/");
		int tmp=Integer.valueOf(date[1]);
		return tmp;
	}
	
	public int getADay()//��o�x����
	{
		String[] date=date2.split("/");
		int tmp=Integer.valueOf(date[2]);
		return tmp;
	}
	
	public int getAHour()//��o�x����
	{
		String[] time=time2.split(":");
		int tmp=Integer.valueOf(time[0]);
		return tmp;
	}
	
	public int getAMin()//��o�x����
	{
		String[] time=time2.split(":");
		int tmp=Integer.valueOf(time[1]);
		return tmp;
	}
	
	public void setType(String s)//�]�m����
	{
		this.type=s;
	}
	
	public String getType()//��o����
	{
		return type;
	}
	
	public void setTitle(String s)//�]�m���D
	{
		this.title=s;
	}
	
	public String getTitle()//��o���D
	{
		return title;
	}
	
	public void setNote(String s)//�]�m�Ƶ�
	{
		this.note=s;
	}
	
	public String getNote()//��o�Ƶ�
	{
		return note;
	}
	
	public void setTimeSet(boolean b)//�]�m�O�_�]�m����ɶ���������
	{
		this.timeSet=b;
		if(!timeSet)//�p�G��false�����S���]�m����ɶ��A�h����ɶ��q�{����ѳ̫�@����
		{
			time1="23:59";
		}
	}
	
	public boolean getTimeSet()//�o��O�_�]�F�ɶ�
	{
		return timeSet;
	}
	
	public void setAlarmSet(boolean b)//�]�m�O�_�]�m�x����������
	{
		this.alarmSet=b;
		if(!timeSet)//�p�G��false�����S���]�m�x���A�h�x���mnull
		{
			date2=null;
			time2=null;
		}
	}
	
	public boolean getAlarmSet()//�o��O�_�]�m�F�x��
	{
		return alarmSet;
	}
	
	public void setDate1(String y,String m,String d)//�]�m��{����A�ഫ��YYYY/MM/DD
	{
		StringBuffer sb=new StringBuffer();
		sb.append(y);
		sb.append("/");
		sb.append(m);
		sb.append("/");
		sb.append(d);
		date1=sb.toString();
	}
	
	public String getDate1()//�o���{���
	{
		return date1;
	}
	
	public void setTime1(String h,String m)//�]�m��{�ɶ��A�ഫ��HH:MM
	{
		StringBuffer sb=new StringBuffer();
		sb.append(h);
		sb.append(":");
		sb.append(m);
		time1=sb.toString();
	}
	
	public String getTime1()//��o��{�ɶ�
	{
		return time1;
	}
	
	public void setDate2(String y,String m,String d)//�]�m�x�����
	{
		StringBuffer sb=new StringBuffer();
		sb.append(y);
		sb.append("/");
		sb.append(m);
		sb.append("/");
		sb.append(d);
		date2=sb.toString();
	}
	
	public String getDate2()//�o��x�����
	{
		return date2;
	}
	
	public void setTime2(String h,String m)//�]�m�x���ɶ�
	{
		StringBuffer sb=new StringBuffer();
		sb.append(h);
		sb.append(":");
		sb.append(m);
		time2=sb.toString();
	}
	
	public String getTime2()//�o��x���ɶ�
	{
		return time2;
	}	
	
	public void setSn(int sn)//�]�msn�X 
	{
		this.sn = sn;
	}

	public int getSn() //�o��sn�X
	{
		return sn;
	}

	public static String toDateString(int y,int m,int d)//�R�A��k�A��int�����~����ഫ��YYYY/MM/DD
	{
		StringBuffer sb = new StringBuffer();
		sb.append(y);
		sb.append("/");
		sb.append(m<10?"0"+m:""+m);
		sb.append("/");
		sb.append(d<10?"0"+d:""+d);
		return sb.toString();
	}
	
	public String toTimeString(int h,int m)//��int�����ɤ��ഫ��HH:MM
	{
		StringBuffer sb = new StringBuffer();
		sb.append(h<10?"0"+h:""+h);
		sb.append(":");
		sb.append(m<10?"0"+m:""+m);
		return sb.toString();
	}
		
	public String typeForListView()//�Ψӱo��b�D�ɭ���ListView����ܪ������榡
	{
		StringBuffer sbTmp=new StringBuffer();
		sbTmp.append("[");
		sbTmp.append(type);
		sbTmp.append("]");
		return sbTmp.toString();
	}
	
	public String dateForListView()//�Ψӱo��b�D�ɭ���ListView����ܪ�����榡
	{
		StringBuffer sbTmp=new StringBuffer();
		sbTmp.append(date1);
		sbTmp.append("   ");
		return sbTmp.toString();
	}
	
	public String timeForListView()//�Ψӱo��b�D�ɭ���ListView����ܪ��ɶ��榡
	{
		if(!timeSet)
		{
			return "- -:- -   ";
		}
		StringBuffer sbTmp=new StringBuffer();
		sbTmp.append(time1);
		sbTmp.append("   ");
		return sbTmp.toString();
	}
	
	public boolean isPassed()//����{�]�m�ɶ��P��e�ɶ��ۤ�A�P�_��{�O�_�w�L��
	{
		String nowDate=getNowDateString();
		String nowTime=getNowTimeString();
		String schDate=date1;
		String schTime=timeSet?time1:"23:59";//�p�G��{�S���]�m�ɶ��A�h�{���L�F���23:59�A�]�N�O��F�ĤG�Ѥ~�L��
							
		if(nowDate.compareTo(schDate)>0||(nowDate.compareTo(schDate)==0&&nowTime.compareTo(schTime)>0))
		{
			return true;
		}
		return false;
	}
	
	public String toInsertSql(RcActivity father)//���schedule�ﹳ�s�J�ƾڮw�ɪ�sql�y�y
	{
		StringBuffer sb = new StringBuffer();
		sb.append("insert into schedule values(");
		sn=getSNFromPrefs(father);
		sb.append(sn);
		sb.append(",'");
		sb.append(date1);
		sb.append("','");
		sb.append(time1);
		sb.append("','");
		sb.append(date2);
		sb.append("','");
		sb.append(time2);
		sb.append("','");
		sb.append(title);
		sb.append("','");
		sb.append(note);
		sb.append("','");
		sb.append(type);
		sb.append("','");
		sb.append(timeSet);
		sb.append("','");
		sb.append(alarmSet);
		sb.append("')");	
		Log.d("toInsertSql",sb.toString());
		return sb.toString();
	}
	
	public String toUpdateSql(RcActivity father)//���schedule�ﹳ��s�ɪ�sql�y�y
	{
		int preSn=sn;//�O�����e��sn
		StringBuffer sb = new StringBuffer();
		sb.append("update schedule set sn=");
		sn=getSNFromPrefs(father);//�����s��sn
		sb.append(sn);
		sb.append(",date1='");
		sb.append(date1);
		sb.append("',time1='");
		sb.append(time1);
		sb.append("',date2='");
		sb.append(date2);
		sb.append("',time2='");
		sb.append(time2);
		sb.append("',title='");
		sb.append(title);
		sb.append("',note='");
		sb.append(note);
		sb.append("',type='");
		sb.append(type);
		sb.append("',timeset='");
		sb.append(timeSet);
		sb.append("',alarmset='");
		sb.append(alarmSet);
		sb.append("' where sn=");
		sb.append(preSn);
		Log.d("toUpdateSql",sb.toString());
		return sb.toString();
	}
}

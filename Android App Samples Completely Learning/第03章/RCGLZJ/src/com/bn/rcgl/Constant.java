package com.bn.rcgl; 

import java.util.Calendar;  
public class Constant 
{
	final static int DIALOG_SET_SEARCH_RANGE=1;//�]�m�j������d���ܮ�
	final static int DIALOG_SET_DATETIME=2;//�]�m����ɶ���ܮ�
	final static int DIALOG_SCH_DEL_CONFIRM=3;//��{�R���T�{
	final static int DIALOG_CHECK=4;//�d�ݤ�{
	final static int DIALOG_ALL_DEL_CONFIRM=5;//�R�������L����{
	final static int DIALOG_ABOUT=6;//�����ܮ�
	 
	final static int MENU_HELP=1;//������U  
	final static int MENU_ABOUT=2;//�������
	
	public static enum WhoCall
	{//�P�_�ֽեΤFdialogSetRange�A�H�M�w���ӱ����gone�Ϊ�visible 
		SETTING_ALARM,//��ܳ]�m�x�� ���s
		SETTING_DATE,//��ܳ]�m������s
		SETTING_RANGE,//��ܳ]�m��{�d��d����s
		NEW,//��ܷs�ؤ�{���s
		EDIT,//��ܭק��{���s
		SEARCH_RESULT//��ܬd����s
	}
	
	public static enum Layout
	{
		WELCOME_VIEW,
		MAIN,//�D�ɭ�
		SETTING,//��{�]�m
		TYPE_MANAGER,//�����޲z
		SEARCH,//�d��
		SEARCH_RESULT,//�d�䵲�G�ɭ�
		HELP,//���U�ɭ�
		ABOUT
	}
	
	public static String getNowDateString()//��o��e�����k���ഫ�榡YYYY/MM/DD
	{
		Calendar c=Calendar.getInstance();
		String nowDate=Schedule.toDateString(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH));
		return nowDate;
		
	}
	public static String getNowTimeString()//��o��e�ɶ��A���ഫ���榡HH:MM
	{
		Calendar c=Calendar.getInstance();
		int nowh=c.get(Calendar.HOUR_OF_DAY);
		int nowm=c.get(Calendar.MINUTE);
		String nowTime=(nowh<10?"0"+nowh:""+nowh)+":"+(nowm<10?"0"+nowm:""+nowm);
		return nowTime;
	}
}

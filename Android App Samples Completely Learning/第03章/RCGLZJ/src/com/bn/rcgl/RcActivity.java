package com.bn.rcgl;

import java.util.ArrayList;
import java.util.Calendar;
import com.bn.rcgl.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import static com.bn.rcgl.Constant.*;
import static com.bn.rcgl.DBUtil.*;

public class RcActivity extends Activity 
{
	String[] defultType=new String[]{"�|ĳ","�Ƨ�","�ݿ�"};//�n�󪺤T�Ӥ���R�����q�{����
	Dialog dialogSetRange;//��{�d��ɳ]�m����_�l�d�򪺹�ܮ�
	Dialog dialogSetDatetime;//�s�ةέק��{�ɳ]�m����M�ɶ�����ܮ�
	Dialog dialogSchDelConfirm;//�R����{�ɪ��T�{��ܮ�
	Dialog dialogCheck;//�D�ɭ����d�ݤ�{�ԲӤ��e����ܮ�
	Dialog dialogAllDelConfirm;//�R�������L����{�ɪ��T�{��ܮ�
	Dialog dialogAbout;//�����ܮ�
	static ArrayList<String> alType=new ArrayList<String>();//�s�x�Ҧ���{������arraylist
	static ArrayList<Schedule> alSch=new ArrayList<Schedule>();//�s�x�Ҧ�schedule��H��ArrayList
	Schedule schTemp;//�{�ɪ�schedule
	ArrayList<Boolean> alSelectedType=new ArrayList<Boolean>();//�O���d��ɭ��������e��checkbox���A��
	String rangeFrom=getNowDateString();//�d���{�ɳ]�m���_�l����A�q�{��e���
	String rangeTo=rangeFrom;//�d���{�ɳ]�m���פ����A�q�{��e���
	Layout curr=null;//�O����e�ɭ����T�|����
	WhoCall wcSetTimeOrAlarm;//�ΨӧP�_�եήɶ������ܮت����s�O�]�m�ɶ��٬O�]�m�x��,�H�K����ܮؤ����@�Ǳ���ӳ]�m��visible�٬Ogone
	WhoCall wcNewOrEdit;//�ΨӧP�_�եΤ�{�s��ɭ����O�s�ؤ�{���s�٬O�b�ק��{���s�A�H�K�]�m�������ɭ����D
	int sel=0;
	/*�{�ɰO���s�ؤ�{�ɭ��̪�����spinner��position�A�]���]�m�ɶ�����ܮ�cancel��
	     �^��s�ؤ�{�ɭ��ɷ|��s�Ҧ�����Aspinner���H�襤�����ؤ]�|�^���q�{*/ 
	Handler hd=new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
				case 0:
					gotoMain();
				break;
			}
		}
	};
	@Override    
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//�L���D
        goToWelcomeView();
    }
    //�w��ɭ�
    public void goToWelcomeView()
    {
    	MySurfaceView mview=new MySurfaceView(this);
    	getWindow().setFlags//����
    	(
    			WindowManager.LayoutParams.FLAG_FULLSCREEN, 
    			WindowManager.LayoutParams.FLAG_FULLSCREEN
    	);
    	setContentView(mview);
    	curr=Layout.WELCOME_VIEW;
    }
    //===================================�D�ɭ�start===========================================
    public void gotoMain()//��l�ƥD�ɭ�
    {
    	getWindow().setFlags
    	(//�D����
    			WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, 
    			WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN
    	);	
    	setContentView(R.layout.main);
    	curr=Layout.MAIN;
    	sel=0;
    	
    	final ArrayList<Boolean> alIsSelected=new ArrayList<Boolean>();//�O��ListView�������襤�F���лx��
    	
    	final ImageButton bEdit=(ImageButton)findViewById(R.id.ibmainEdit);//�ק��{���s
    	final ImageButton bCheck=(ImageButton)findViewById(R.id.ibmainCheck);//�d�ݤ�{�ԲӤ��e�����s
    	final ImageButton bDel=(ImageButton)findViewById(R.id.ibmainDel);//�R����e�襤��{�����s
    	ImageButton bNew=(ImageButton)findViewById(R.id.ibmainNew);//�s�ؤ�{���s
    	ImageButton bDelAll=(ImageButton)findViewById(R.id.ibmainDelAll);//�R���Ҧ��L����{���s
    	ImageButton bSearch=(ImageButton)findViewById(R.id.ibmainSearch);//�d���{���s
    	final ListView lv=(ListView)findViewById(R.id.lvmainSchedule);//��{�C��
        
        bCheck.setEnabled(false);//�o�T�ӫ��s���O���D�ɭ�����{�d�ݡB��{�ק�B��{�R��,
    	bEdit.setEnabled(false);//�q�{�]�����i�Ϊ��A
    	bDel.setEnabled(false);
        
    	alSch.clear();//�q�ƾڮwŪ�����e�M�Ŧs�x��{��arraylist
		loadSchedule(this);//�q�ƾڮw��Ū����{
		loadType(this);//�q�ƾڮw��Ū������
		
        if(alSch.size()==0)//�p�G�S�������{�A�h�R�������L����{���s�]���T��
        {
        	bDelAll.setEnabled(false);
        }
        else
        {
        	bDelAll.setEnabled(true);
        }
        
        alIsSelected.clear();
    	
        for(int i=0;i<alSch.size();i++)//�����]�m��false�A�Y�S���@���襤
    	{
    		alIsSelected.add(false);
    	}
        
        //�H�U�OListView�]�m
        lv.setAdapter
        (
        		new BaseAdapter()
        		{
					@Override
					public int getCount() 
					{
						return alSch.size();
					}
					@Override
					public Object getItem(int position) 
					{
						return alSch.get(position);
					}
					@Override
					public long getItemId(int position) 
					{
						return 0;
					}
					@Override
					public View getView(int position, View convertView, ViewGroup parent) 
					{
						LinearLayout ll=new LinearLayout(RcActivity.this);
						ll.setOrientation(LinearLayout.VERTICAL);
						ll.setPadding(5, 5, 5, 5);
						
						LinearLayout llUp=new LinearLayout(RcActivity.this);
						llUp.setOrientation(LinearLayout.HORIZONTAL);
						LinearLayout llDown=new LinearLayout(RcActivity.this);
						llDown.setOrientation(LinearLayout.HORIZONTAL);
						
						//ListView�����TextView
						TextView tvDate=new TextView(RcActivity.this);
						tvDate.setText(alSch.get(position).getDate1()+"   ");
						tvDate.setTextSize(17);
						tvDate.setTextColor(Color.parseColor("#129666"));
						llUp.addView(tvDate);
						
						//ListView�ɶ�TextView
						TextView tvTime=new TextView(RcActivity.this);
						tvTime.setText(alSch.get(position).timeForListView());
						tvTime.setTextSize(17);
						tvTime.setTextColor(Color.parseColor("#925301"));
						llUp.addView(tvTime);
						
						//�Y��{�w�L���A�h����M�ɶ��C��B�I����]�m���L�����C��
						if(alSch.get(position).isPassed())
						{
							tvDate.setTextColor(getResources().getColor(R.color.passedschtext));
							tvTime.setTextColor(getResources().getColor(R.color.passedschtext));
							ll.setBackgroundColor(getResources().getColor(R.color.passedschbg));
						}
						//�p�G�Ӷ��Q�襤�F�A�I����]�m���襤���I����
						if(alIsSelected.get(position))
						{
							ll.setBackgroundColor(getResources().getColor(R.color.selectedsch));
						}
						//�p�G���x���A�h�[�W�x�����ϼ�
						if(alSch.get(position).getAlarmSet())
						{
							ImageView iv=new ImageView(RcActivity.this);
							iv.setImageDrawable(getResources().getDrawable(R.drawable.alarm));
							iv.setLayoutParams(new LayoutParams(20, 20));
							llUp.addView(iv);
						}
						//��{����TextView
						TextView tvType=new TextView(RcActivity.this);
						tvType.setText(alSch.get(position).typeForListView());
						tvType.setTextSize(17);
						tvType.setTextColor(Color.parseColor("#b20000"));
						llDown.addView(tvType);
						//��{���DTextView
						TextView tvTitle=new TextView(RcActivity.this);
						tvTitle.setText(alSch.get(position).getTitle());
						tvTitle.setTextSize(17);
						tvTitle.setTextColor(Color.parseColor("#000000"));
						llDown.addView(tvTitle);
						ll.addView(llUp);
						ll.addView(llDown);
						return ll;
					}
		        }
        );
        lv.setOnItemClickListener
        (
        		new OnItemClickListener()
        		{
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
					{
						bCheck.setEnabled(true);//�o�T�ӫ��s���O���D�ɭ�����{�d�ݡB��{�ק�B��{�R��,
				    	bEdit.setEnabled(true);//�p�G�Τ�b��{�C���襤�F�Y�Ӥ�{�ɡA�]���i�Ϊ��A
				    	bDel.setEnabled(true);
				    	
						schTemp=alSch.get(arg2);//�襤�Ӷ��خɡA��Ӷ��عﹳ�ᵹschTemp
						
						//��лx������]��false��A���e�襤�����������лx��]��true
						for(int i=0;i<alIsSelected.size();i++)
						{
							alIsSelected.set(i,false);
						}
						alIsSelected.set(arg2,true);
					}
		        }
        );
        
        //bNew�]�m
        bNew.setOnClickListener
        (
        		new OnClickListener()
		        {
		
					@Override
					public void onClick(View v) {
						Calendar c=Calendar.getInstance();
						int t1=c.get(Calendar.YEAR);
						int t2=c.get(Calendar.MONTH)+1;
						int t3=c.get(Calendar.DAY_OF_MONTH);
						schTemp=new Schedule(t1,t2,t3);//�{�ɷs�ؤ@�Ӥ�{��H�A�~���]����e���
						wcNewOrEdit=WhoCall.NEW;//�եΤ�{�s��ɭ����O�s�ث��s
						gotoSetting();//�h��{�s��ɭ�
					}        	
		        }
        );       
        //bEdit�]�m
        bEdit.setOnClickListener
        (
        		new OnClickListener()
		        {
					@Override
					public void onClick(View v) {
						wcNewOrEdit=WhoCall.EDIT;//�եΤ�{�s��ɭ����O�ק���s
						gotoSetting();//�h��{�s��ɭ�
					}        	
		        }
        ); 
        
        //�R���襤����{���s
        bDel.setOnClickListener
        (
        		new OnClickListener()
		        {
					@Override
					public void onClick(View v) {
						showDialog(DIALOG_SCH_DEL_CONFIRM);
					}
		        }
        );
        
        //�R���Ҧ��L����{���s
        bDelAll.setOnClickListener
        (
        		new OnClickListener()
		        {
					@Override
					public void onClick(View v) {
						showDialog(DIALOG_ALL_DEL_CONFIRM);
					}
		        }
        );
        
        //��{�d����s
        bSearch.setOnClickListener
        (
        		new OnClickListener()
		        {
					@Override
					public void onClick(View v) {
						gotoSearch();
					}
		        }
        );
        
      //��{�d�ݫ��s
        bCheck.setOnClickListener
        (
        		new OnClickListener()
		        {
					@Override
					public void onClick(View v) {
						showDialog(DIALOG_CHECK);
					}
		        }
        ); 
    }
    //===================================��{�s��ɭ�start=====================================
    public void gotoSetting()//��l�Ʒs�ؤ�{�ɭ�
    {
    	setContentView(R.layout.newschedule);
    	curr=Layout.SETTING;
    	
    	TextView tvTitle=(TextView)findViewById(R.id.tvnewscheduleTitle);
    	if(wcNewOrEdit==WhoCall.NEW)
    	{
    		tvTitle.setText("�s�ؤ�{");
    	}
    	else if(wcNewOrEdit==WhoCall.EDIT)
    	{
    		tvTitle.setText("�ק��{");
    	}
    	final Spinner spType=(Spinner)findViewById(R.id.spxjrcType);
    	Button bNewType=(Button)findViewById(R.id.bxjrcNewType);
    	final EditText etTitle=(EditText)findViewById(R.id.etxjrcTitle);
    	final EditText etNote=(EditText)findViewById(R.id.etxjrcNote);
    	TextView tvDate=(TextView)findViewById(R.id.tvnewscheduleDate);
    	Button bSetDate=(Button)findViewById(R.id.bxjrcSetDate);
    	TextView tvTime=(TextView)findViewById(R.id.tvnewscheduleTime);
    	TextView tvAlarm=(TextView)findViewById(R.id.tvnewscheduleAlarm);
    	final Button bSetAlarm=(Button)findViewById(R.id.bxjrcSetAlarm);
    	Button bDone=(Button)findViewById(R.id.bxjrcDone);
    	Button bCancel=(Button)findViewById(R.id.bxjrcCancel);
		
		etTitle.setText(schTemp.getTitle());
		etNote.setText(schTemp.getNote());
		tvDate.setText(schTemp.getDate1());
		tvTime.setText(schTemp.getTimeSet()?schTemp.getTime1():"�L����ɶ�");
		tvAlarm.setText(schTemp.getAlarmSet()?schTemp.getDate2()+"  "+schTemp.getTime2():"�L�x��");
		
		//����spinner�]�m
		spType.setAdapter
		(
				new BaseAdapter()
				{
					@Override
					public int getCount() 
					{
						return alType.size();
					}
		
					@Override
					public Object getItem(int position) 
					{
						return alType.get(position);
					}
					@Override
					public long getItemId(int position) {return 0;}
		
					@Override
					public View getView(int position, View convertView, ViewGroup parent) 
					{
						LinearLayout ll=new LinearLayout(RcActivity.this);
						ll.setOrientation(LinearLayout.HORIZONTAL);	
						TextView tv=new TextView(RcActivity.this);
						tv.setText(alType.get(position));
						tv.setTextSize(17);
						tv.setTextColor(R.color.black);
						return tv;
					}			
				}
		);
		spType.setSelection(sel);

		//�s�ؤ�{�������s
		bNewType.setOnClickListener
		(
				new OnClickListener()
				{
					@Override
					public void onClick(View v) {
						schTemp.setTitle(etTitle.getText().toString());//�N�w�g��J��title�Mnote�s�JschTemp�A�H����^�ɳQ�M��
						schTemp.setNote(etNote.getText().toString());
						sel=spType.getSelectedItemPosition();//�s�xspType����e���
						gotoTypeManager();//�i�J��{�����޲z�ɭ�
					}
				}
		);
		
		//
		bSetDate.setOnClickListener
		(
				new OnClickListener()
				{

					@Override
					public void onClick(View v) {
						schTemp.setTitle(etTitle.getText().toString());//�N�w�g��J���D�D�M�Ƶ��s�JschTemp�A�H���]�m���ɶ��ξx����^�ɳQ�M��
						schTemp.setNote(etNote.getText().toString());
						sel=spType.getSelectedItemPosition();
						wcSetTimeOrAlarm=WhoCall.SETTING_DATE;//�եγ]�m����ɶ���ܮت��O�]�m��{������s
						showDialog(DIALOG_SET_DATETIME);
					}
				}
		);
		bSetAlarm.setOnClickListener
		(
				new OnClickListener()
				{
					@Override
					public void onClick(View v) {
						schTemp.setTitle(etTitle.getText().toString());//�N�w�g��J���D�D�M�Ƶ��s�JschTemp�A�H���]�m���ɶ��ξx����^�ɳQ�M��
						schTemp.setNote(etNote.getText().toString());
						sel=spType.getSelectedItemPosition();
						wcSetTimeOrAlarm=WhoCall.SETTING_ALARM;//�եγ]�m����ɶ���ܮت��O�]�m�x�����s
						showDialog(DIALOG_SET_DATETIME);
					}
				}
		);
		
		//�������s�]�m
		bDone.setOnClickListener(
			new OnClickListener()
			{
				@Override
				public void onClick(View v) {
					//���s�ت���{�ɶ��M��e�ɶ�����ݬO�_�L��
					if(schTemp.isPassed())
					{
						Toast.makeText(RcActivity.this, "����ЫعL����{", Toast.LENGTH_SHORT).show();
						return;
					}
					
					if(schTemp.getAlarmSet())//�p�G�]�m�F�x���A�h�ˬd�x���ɶ��O�_�X�z
					{
						//�p�G��{����b�x��������e,
						//�Ϊ̦b��{�ɶ��w�]�m���e���U�A��{����M�x������ۦP�A���O��{�ɶ��b�x���ɶ����e�A
						//�u�X����
						if(schTemp.getDate1().compareTo(schTemp.getDate2())<0||
								schTemp.getTimeSet()&&
								schTemp.getDate1().compareTo(schTemp.getDate2())==0&&
								schTemp.getTime1().compareTo(schTemp.getTime2())<0)
						{
							Toast.makeText(RcActivity.this,"�x���ɶ�����b��{�ɶ�����", Toast.LENGTH_SHORT).show();
							return;
						}
					}
					
					String title=etTitle.getText().toString().trim();
					if(title.equals(""))//�p�G��{���D�S����J�A�q�{�����R�W
					{
						title="���R�W";
					}
					schTemp.setTitle(title);
					String note=etNote.getText().toString();
					schTemp.setNote(note);
					String type=(String) spType.getSelectedItem();
					schTemp.setType(type);
					
			    	if(wcNewOrEdit==WhoCall.NEW)//�p�G��e�ɭ��O�s�ؤ�{�A�եδ��J��{��k
			    	{
			    		insertSchedule(RcActivity.this);
			    	}
			    	else if(wcNewOrEdit==WhoCall.EDIT)//�p�G��e�ɭ��O�ק��{�A�եΧ�s��{��k
			    	{
			    		updateSchedule(RcActivity.this);
			    	}
					
					gotoMain();
				}
				
			}
		);
		//�������s�]�m
		bCancel.setOnClickListener
		(
			new OnClickListener()
			{
				@Override
				public void onClick(View v) {					
					gotoMain();
				}
				
			}
		);
    }
    //===================================�����޲z�ɭ�start=====================================
	public void gotoTypeManager()
	{
		setContentView(R.layout.typemanager);
		curr=Layout.TYPE_MANAGER;
		final ListView lvType=(ListView)findViewById(R.id.lvtypemanagerType);//�C��C�X�Ҧ��w������
		final EditText etNew=(EditText)findViewById(R.id.ettypemanagerNewType);//��J�s�����W�٪�TextView
		final Button bNew=(Button)findViewById(R.id.btypemanagerNewType);//�s���������s
		final Button bBack=(Button)findViewById(R.id.btypemanagerBack);//��^�W�@�����s
		
		bBack.setOnClickListener
		(
				new OnClickListener()
				{
					@Override
					public void onClick(View v) {
						gotoSetting();
					}
				}
		);
		
		lvType.setAdapter
		(
				new BaseAdapter()
				{
					@Override
					public int getCount() 
					{
						return alType.size();
					}
					@Override
					public Object getItem(int position) 
					{
						return alType.get(position);
					}
					@Override
					public long getItemId(int position) 
					{
						return 0;
					}
					@Override
					public View getView(final int position, View convertView, ViewGroup parent) 
					{
						LinearLayout ll=new LinearLayout(RcActivity.this);
						ll.setOrientation(LinearLayout.HORIZONTAL);
						ll.setGravity(Gravity.CENTER_VERTICAL);
						TextView tv=new TextView(RcActivity.this);
						tv.setText(alType.get(position));
						tv.setTextSize(17);
						tv.setTextColor(Color.BLACK);
						tv.setPadding(20, 0, 0, 0);
						ll.addView(tv);
						
						//�n��۱a����������R���A��L�۫������᭱�K�[�@�Ӭ��e�ΨӧR���۫�����
						if(position>=defultType.length)
						{
							ImageButton ib=new ImageButton(RcActivity.this);
							ib.setBackgroundDrawable(RcActivity.this.getResources().getDrawable(R.drawable.cross));
							ib.setLayoutParams(new LayoutParams(24, 24));
							ib.setPadding(20, 0, 0, 0);
							
							ib.setOnClickListener
							(
									new OnClickListener()
									{
										@Override
										public void onClick(View v) 
										{
											deleteType(RcActivity.this,lvType.getItemAtPosition(position).toString());
											loadType(RcActivity.this);
											gotoTypeManager();
										}
									}
							);
							ll.addView(ib);
						}
						return ll;
					}
				}
		);

		bNew.setOnClickListener
		(
				new OnClickListener()
				{
					@Override
					public void onClick(View v) {
						String newType=etNew.getText().toString().trim();
						if(newType.equals(""))
						{
							Toast.makeText(RcActivity.this, "�����W�٤��ର�šC", Toast.LENGTH_SHORT).show();
							return;
						}
						insertType(RcActivity.this,newType);
						gotoTypeManager();
					}
				}
		);
	}
    //===================================�d��ɭ�start=========================================
    public void gotoSearch()
    {
		setContentView(R.layout.search);
    	curr=Layout.SEARCH;
    	final Button bChange=(Button)findViewById(R.id.bsearchChange);//���ܬd��d����s
		final Button bSearch=(Button)findViewById(R.id.bsearchGo);//�}�l�d��
		final Button bCancel=(Button)findViewById(R.id.bsearchCancel);//����
		final CheckBox cbDateRange=(CheckBox)findViewById(R.id.cbsearchDateRange);//�d��O�_����d��CheckBox
		final CheckBox cbAllType=(CheckBox)findViewById(R.id.cbsearchType);//�O�_�b�b�Ҧ��������d�䪺CheckBox
		final ListView lv=(ListView)findViewById(R.id.lvSearchType);//�Ҧ������C�blv��
		final TextView tvFrom=(TextView)findViewById(R.id.tvsearchFrom);//�d��_�l�ɴ���tv
		final TextView tvTo=(TextView)findViewById(R.id.tvsearchTo);////�d��פ�ɴ���tv
		tvFrom.setText(rangeFrom);
		tvTo.setText(rangeTo);
		
		final ArrayList<String> type=getAllType(RcActivity.this);//����w�s��{�����Ҧ������M�Τ�۫ت��Ҧ�����
		
		alSelectedType.clear();
		for(int i=0;i<type.size();i++)//�q�{���Ҧ������]�m���A��false
		{
			alSelectedType.add(false);
		}
		
		cbDateRange.setOnCheckedChangeListener
		(
				new OnCheckedChangeListener()
				{//�ھڬO�_�������d��CheckBox�M�w������d�򪺫��s�O�_�i��
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						bChange.setEnabled(isChecked);
					}
				}
		);
		
		//�]�m�u�b�����������j���v��CheckBox���ܪ��A�ɪ��欰
		cbAllType.setOnCheckedChangeListener
		(
				new OnCheckedChangeListener()
				{
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						for(int i=0;i<type.size();i++)//�襤�u�����襤�v���listview�̪��Ҧ������᭱��checkbox�]���襤���A
						{
							alSelectedType.set(i, isChecked);
						}
						lv.invalidateViews();//��sListView??
					}
				}
		);
		
		bChange.setOnClickListener
		(
				new OnClickListener()
				{
					@Override
					public void onClick(View v) {
						showDialog(DIALOG_SET_SEARCH_RANGE);
					}
				}
		);
		
		lv.setAdapter
		(
				new BaseAdapter()
				{
					@Override
					public int getCount() 
					{
						return type.size();
					}
		
					@Override
					public Object getItem(int position) 
					{
						return type.get(position);
					}
					@Override
					public long getItemId(int position) 
					{
						return 0;
					}
		
					@Override
					public View getView(final int position, View convertView, ViewGroup parent) {
						LinearLayout ll=new LinearLayout(RcActivity.this);
						ll.setOrientation(LinearLayout.HORIZONTAL);	
						ll.setGravity(Gravity.CENTER_VERTICAL);
						LinearLayout llin=new LinearLayout(RcActivity.this);
						llin.setPadding(20, 0, 0, 0);
						ll.addView(llin);
						CheckBox cb=new CheckBox(RcActivity.this);
						cb.setButtonDrawable(R.drawable.checkbox);
						llin.addView(cb);
						cb.setChecked(alSelectedType.get(position));//��ArrayList�̭��s�x�����A�]�mCheckBox���A
						
						cb.setOnCheckedChangeListener
						(
								new OnCheckedChangeListener()
								{
									@Override
									public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) 
									{
										alSelectedType.set(position, isChecked);//����ArrayList�̭�������mboolean��
									}
								}
						);
						
						TextView tv=new TextView(RcActivity.this);
						tv.setText(type.get(position));
						tv.setTextSize(18);
						tv.setTextColor(R.color.black);
						ll.addView(tv);
						return ll;
					}			
				}	
		);
		
		bSearch.setOnClickListener
		(
				new OnClickListener()
				{
					@Override
					public void onClick(View v) 
					{ 
						//�p�G�S���@�������Q�襤�h����
						boolean tmp=false;
						for(boolean b:alSelectedType)
						{
							tmp=tmp|b;
						}
						if(tmp==false)
						{
							Toast.makeText(RcActivity.this, "�Цܤֿ襤�@������", Toast.LENGTH_SHORT).show();
							return;
						}
						
						searchSchedule(RcActivity.this,type);
						gotoSearchResult();
					}
				}
		);
		
		bCancel.setOnClickListener
		(
				new OnClickListener()
				{
					@Override
					public void onClick(View v) 
					{
						gotoMain();
					}
				}
		);
    }
    //===================================�d�䵲�G�ɭ�start=====================================
    public void gotoSearchResult()//�Ӭɭ��M�D�ɭ����F�֤F�X�ӫ��s��L�����@��
    {
    	setContentView(R.layout.searchresult);
    	curr=Layout.SEARCH_RESULT;

    	sel=0;
    	
    	final ImageButton bCheck=(ImageButton)findViewById(R.id.ibsearchresultCheck);
    	final ImageButton bEdit=(ImageButton)findViewById(R.id.ibsearchresultEdit);
    	final ImageButton bDel=(ImageButton)findViewById(R.id.ibsearchresultDel);
    	ImageButton bBack=(ImageButton)findViewById(R.id.ibsearchresultBack);
    	ListView lv=(ListView)findViewById(R.id.lvsearchresultSchedule);
        
        bCheck.setEnabled(false);
    	bEdit.setEnabled(false);
    	bDel.setEnabled(false);
        
        
        //�H�U�O�d�䵲�G��ListView�]�m
        lv.setAdapter
        (
        		new BaseAdapter()
		        {
					@Override
					public int getCount() 
					{
						return alSch.size();
					}
		
					@Override
					public Object getItem(int position) 
					{
						return alSch.get(position);
					}
		
					@Override
					public long getItemId(int position) 
					{
						return 0;
					}
		
					@Override
					public View getView(int position, View convertView, ViewGroup parent) 
					{
						LinearLayout ll=new LinearLayout(RcActivity.this);
						ll.setOrientation(LinearLayout.VERTICAL);
						ll.setPadding(5, 5, 5, 5);
						
						LinearLayout llUp=new LinearLayout(RcActivity.this);
						llUp.setOrientation(LinearLayout.HORIZONTAL);
						LinearLayout llDown=new LinearLayout(RcActivity.this);
						llDown.setOrientation(LinearLayout.HORIZONTAL);
								
						TextView tvDate=new TextView(RcActivity.this);
						tvDate.setText(alSch.get(position).getDate1()+"   ");
						tvDate.setTextSize(17);
						tvDate.setTextColor(Color.parseColor("#129666"));
						llUp.addView(tvDate);
						
						TextView tvTime=new TextView(RcActivity.this);
						tvTime.setText(alSch.get(position).timeForListView());
						tvTime.setTextSize(17);
						tvTime.setTextColor(Color.parseColor("#925301"));
						llUp.addView(tvTime);
						
						if(alSch.get(position).isPassed())//�Y��{�w�L���A�h����M�ɶ��C��B�I�����ܦ�
						{
							tvDate.setTextColor(Color.parseColor("#292929"));
							tvTime.setTextColor(Color.parseColor("#292929"));
							ll.setBackgroundColor(Color.parseColor("#818175"));
						}
						
						if(alSch.get(position).getAlarmSet())
						{
							ImageView iv=new ImageView(RcActivity.this);
							iv.setImageDrawable(getResources().getDrawable(R.drawable.alarm));
							iv.setLayoutParams(new LayoutParams(20, 20));
							llUp.addView(iv);
						}
						
						TextView tvType=new TextView(RcActivity.this);
						tvType.setText(alSch.get(position).typeForListView());
						tvType.setTextSize(17);
						tvType.setTextColor(Color.parseColor("#b20000"));
						llDown.addView(tvType);
						
						TextView tvTitle=new TextView(RcActivity.this);
						tvTitle.setText(alSch.get(position).getTitle());
						tvTitle.setTextSize(17);
						tvTitle.setTextColor(Color.parseColor("#000000"));
						llDown.addView(tvTitle);
		
						
						ll.addView(llUp);
						ll.addView(llDown);
						return ll;
					}
		        }
        );
        
        lv.setOnItemClickListener
        (
        		new OnItemClickListener()
        		{
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
					{
				        bCheck.setEnabled(true);
				    	bEdit.setEnabled(true);
				    	bDel.setEnabled(true);
						schTemp=alSch.get(arg2);//�襤�Y�Ӷ��خɡA��Ӷ��عﹳ�ᵹschTemp
					}
		        }
        );
        
        //�ק��{���s�]�m
        bEdit.setOnClickListener
        (
        		new OnClickListener()
		        {
		
					@Override
					public void onClick(View v) {
						wcSetTimeOrAlarm=WhoCall.EDIT;
						gotoSetting();
					}        	
		        }
        ); 
        //�R���襤��{���s�]�m
        bDel.setOnClickListener
        (
        		new OnClickListener()
		        {
					@Override
					public void onClick(View v) {
						showDialog(DIALOG_SCH_DEL_CONFIRM);
					}
		        }
        );
        
        //�d���{���s�]�m
        bBack.setOnClickListener
        (
        		new OnClickListener()
		        {
					@Override
					public void onClick(View v) 
					{
						gotoSearch();
					}
		        	
		        }
        );
        
      //�d�ݤ�{���s�]�m
        bCheck.setOnClickListener
        (
        		new OnClickListener()
		        {
					@Override
					public void onClick(View v) {
						showDialog(DIALOG_CHECK);
					}
		        }
        );
    }
	//=========================���U�ɭ�start==================================
	public void gotoHelp()
	{ 
		getWindow().setFlags//����
    	(
    			WindowManager.LayoutParams.FLAG_FULLSCREEN, 
    			WindowManager.LayoutParams.FLAG_FULLSCREEN
    	);
		setContentView(R.layout.help);
		curr=Layout.HELP;
		Button bBack=(Button)this.findViewById(R.id.bhelpback);
		bBack.setOnClickListener
		(
				new OnClickListener()
				{
					@Override
					public void onClick(View v) 
					{
						gotoMain();
					}
					
				}
		);
	}
	//�Ыع�ܮ�
    @Override
	public Dialog onCreateDialog(int id) 
    {
    	Dialog dialog=null;
    	switch(id)
    	{
    		case DIALOG_SET_SEARCH_RANGE:
    			AlertDialog.Builder b=new AlertDialog.Builder(this); 
  			  	b.setItems(null,null);
  			  	dialogSetRange=b.create();
  			  	dialog=dialogSetRange;	
    		break;
    		
    		case DIALOG_SET_DATETIME:
    			AlertDialog.Builder abSetDatetime=new AlertDialog.Builder(this); 
    			abSetDatetime.setItems(null,null);
    			dialogSetDatetime=abSetDatetime.create();
  			  	dialog=dialogSetDatetime;	
    		break;
    		
    		case DIALOG_SCH_DEL_CONFIRM:
    			AlertDialog.Builder abSchDelConfirm=new AlertDialog.Builder(this); 
    			abSchDelConfirm.setItems(null,null);
    			dialogSchDelConfirm=abSchDelConfirm.create();
  			  	dialog=dialogSchDelConfirm;	
    		break;
    		
    		case DIALOG_CHECK:
    			AlertDialog.Builder abCheck=new AlertDialog.Builder(this); 
    			abCheck.setItems(null,null);
    			dialogCheck=abCheck.create();
  			  	dialog=dialogCheck;	
    		break;
    		
    		case DIALOG_ALL_DEL_CONFIRM:
    			AlertDialog.Builder abAllDelConfirm=new AlertDialog.Builder(this); 
    			abAllDelConfirm.setItems(null,null);
    			dialogAllDelConfirm=abAllDelConfirm.create();
  			  	dialog=dialogAllDelConfirm;	
    		break;
    		
    		case DIALOG_ABOUT:
    			AlertDialog.Builder abAbout=new AlertDialog.Builder(this); 
    			abAbout.setItems(null,null);
    			dialogAbout=abAbout.create();
  			  	dialog=dialogAbout;	
    		break;
    	}
		return dialog;
	}
    //�C���u�XDialog��ܮخɧ�s��ܮت����e
	@Override
	public void onPrepareDialog(int id,Dialog dialog) 
	{
		super.onPrepareDialog(id, dialog);
    	switch(id)
    	{
			case DIALOG_SET_SEARCH_RANGE://�]�m�j���d���ܮ�		
				dialog.setContentView(R.layout.dialogsetrange);
				//year month day�᭱�O1���������_�l�ɶ��]�m�A2�������פ�ɶ��]�m�AP���plus�[���AM���minus�ظ�
				final ImageButton bYear1P=(ImageButton)dialog.findViewById(R.id.bdialogsetrangeYear1P);
				final ImageButton bYear1M=(ImageButton)dialog.findViewById(R.id.bdialogsetrangeYear1M);
				final ImageButton bMonth1P=(ImageButton)dialog.findViewById(R.id.bdialogsetrangeMonth1P);
				final ImageButton bMonth1M=(ImageButton)dialog.findViewById(R.id.bdialogsetrangeMonth1M);
				final ImageButton bDay1P=(ImageButton)dialog.findViewById(R.id.bdialogsetrangeDay1P);
				final ImageButton bDay1M=(ImageButton)dialog.findViewById(R.id.bdialogsetrangeDay1M);
				final EditText etYear1=(EditText)dialog.findViewById(R.id.etdialogsetrangeYear1);
				final EditText etMonth1=(EditText)dialog.findViewById(R.id.etdialogsetrangeMonth1);
				final EditText etDay1=(EditText)dialog.findViewById(R.id.etdialogsetrangeDay1);
				
				final ImageButton bYear2P=(ImageButton)dialog.findViewById(R.id.bdialogsetrangeYear2P);
				final ImageButton bYear2M=(ImageButton)dialog.findViewById(R.id.bdialogsetrangeYear2M);
				final ImageButton bMonth2P=(ImageButton)dialog.findViewById(R.id.bdialogsetrangeMonth2P);
				final ImageButton bMonth2M=(ImageButton)dialog.findViewById(R.id.bdialogsetrangeMonth2M);
				final ImageButton bDay2P=(ImageButton)dialog.findViewById(R.id.bdialogsetrangeDay2P);
				final ImageButton bDay2M=(ImageButton)dialog.findViewById(R.id.bdialogsetrangeDay2M);
				final EditText etYear2=(EditText)dialog.findViewById(R.id.etdialogsetrangeYear2);
				final EditText etMonth2=(EditText)dialog.findViewById(R.id.etdialogsetrangeMonth2);
				final EditText etDay2=(EditText)dialog.findViewById(R.id.etdialogsetrangeDay2);
				
				Button bSetRangeOk=(Button)dialog.findViewById(R.id.bdialogsetrangeOk);
				Button bSetRangeCancel=(Button)dialog.findViewById(R.id.bdialogsetrangeCancel);
				
				//��YYYY/MM/DD�榡���~�������X��,�åB�����ܦ~��骺TextView��
				String[] from=splitYMD(rangeFrom);
				String[] to=splitYMD(rangeTo);
				
				etYear1.setText(from[0]);
				etMonth1.setText(from[1]);
				etDay1.setText(from[2]);
				etYear2.setText(to[0]);
				etMonth2.setText(to[1]);
				etDay2.setText(to[2]);
				
				
				bYear1P.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int year=Integer.parseInt(etYear1.getText().toString().trim());
								year++;
								etYear1.setText(""+year);
							}
						}
				);
				bYear1M.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int year=Integer.parseInt(etYear1.getText().toString().trim());
								year--;
								etYear1.setText(""+year);
							}
						}
				);
				bMonth1P.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int month=Integer.parseInt(etMonth1.getText().toString().trim());
								if(++month>12)
								{
									month=1;
								}
								etMonth1.setText(month<10?"0"+month:""+month);
							}
						}
				);
				bMonth1M.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int month=Integer.parseInt(etMonth1.getText().toString().trim());
								if(--month<1)
								{
									month=12;
								}
								etMonth1.setText(month<10?"0"+month:""+month);
							}
						}
				);
				
				bDay1P.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int year=Integer.parseInt(etYear1.getText().toString().trim());
								int month=Integer.parseInt(etMonth1.getText().toString().trim());
								int day=Integer.parseInt(etDay1.getText().toString().trim());
								if(++day>getMaxDayOfMonth(year,month))
								{
									day=1;
								}
								etDay1.setText(day<10?"0"+day:""+day);
							}
						}
				);
				bDay1M.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int year=Integer.parseInt(etYear1.getText().toString().trim());
								int month=Integer.parseInt(etMonth1.getText().toString().trim());
								int day=Integer.parseInt(etDay1.getText().toString().trim());
								if(--day<1)
								{
									day=getMaxDayOfMonth(year,month);
								}
								etDay1.setText(day<10?"0"+day:""+day);
							}
						}
				);
				//================���νu�A�H�W���]�m�_�l�ɶ������s��ť�A�@�U���]�m�פ�ɶ������s��ť==================
				bYear2P.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int year=Integer.parseInt(etYear2.getText().toString().trim());
								year++;
								etYear2.setText(""+year);
							}
						}	
				);
				bYear2M.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int year=Integer.parseInt(etYear2.getText().toString().trim());
								year--;
								etYear2.setText(""+year);
							}
						}
				);
				bMonth2P.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int month=Integer.parseInt(etMonth2.getText().toString().trim());
								if(++month>12)
								{
									month=1;
								}
								etMonth2.setText(month<10?"0"+month:""+month);
							}
						}
				);
				bMonth2M.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int month=Integer.parseInt(etMonth2.getText().toString().trim());
								if(--month<1)
								{
									month=12;
								}
								etMonth2.setText(month<10?"0"+month:""+month);
							}
						}
				);
				
				bDay2P.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int year=Integer.parseInt(etYear2.getText().toString().trim());
								int month=Integer.parseInt(etMonth2.getText().toString().trim());
								int day=Integer.parseInt(etDay2.getText().toString().trim());
								if(++day>getMaxDayOfMonth(year,month))
								{
									day=1;
								}
								etDay2.setText(day<10?"0"+day:""+day);
							}
						}
				);
				bDay2M.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int year=Integer.parseInt(etYear2.getText().toString().trim());
								int month=Integer.parseInt(etMonth2.getText().toString().trim());
								int day=Integer.parseInt(etDay2.getText().toString().trim());
								if(--day<1)
								{
									day=getMaxDayOfMonth(year,month);
								}
								etDay2.setText(day<10?"0"+day:""+day);
							}
						}
				);
				
				bSetRangeOk.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int year1=Integer.parseInt(etYear1.getText().toString().trim());
								int month1=Integer.parseInt(etMonth1.getText().toString().trim());
								int day1=Integer.parseInt(etDay1.getText().toString().trim());
								int year2=Integer.parseInt(etYear2.getText().toString().trim());
								int month2=Integer.parseInt(etMonth2.getText().toString().trim());
								int day2=Integer.parseInt(etDay2.getText().toString().trim());
								
								if(day1>getMaxDayOfMonth(year1,month1)||day2>getMaxDayOfMonth(year2,month2))
								{
									Toast.makeText(RcActivity.this, "����]�m���~", Toast.LENGTH_SHORT).show();
									return;
								}
								rangeFrom=Schedule.toDateString(year1, month1, day1);
								rangeTo=Schedule.toDateString(year2, month2, day2);
								if(rangeFrom.compareTo(rangeTo)>0)
								{
									Toast.makeText(RcActivity.this, "�_�l�������j��פ���", Toast.LENGTH_SHORT).show();
									return;
								}
								dialogSetRange.cancel();
								gotoSearch();
							}
						}
				);
				
				//�I�����h��ܮ�����
				bSetRangeCancel.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								dialogSetRange.cancel();
							}
						}
				);

			break;
			
			case DIALOG_SET_DATETIME://�]�m�ɶ������ܮ�
				dialog.setContentView(R.layout.dialogdatetime);
				final ImageButton bYearP=(ImageButton)dialog.findViewById(R.id.bdialogdatetimeYearP);
				final ImageButton bYearM=(ImageButton)dialog.findViewById(R.id.bdialogdatetimeYearM);
				final ImageButton bMonthP=(ImageButton)dialog.findViewById(R.id.bdialogdatetimeMonthP);
				final ImageButton bMonthM=(ImageButton)dialog.findViewById(R.id.bdialogdatetimeMonthM);
				final ImageButton bDayP=(ImageButton)dialog.findViewById(R.id.bdialogdatetimeDayP);
				final ImageButton bDayM=(ImageButton)dialog.findViewById(R.id.bdialogdatetimeDayM);
				final ImageButton bHourP=(ImageButton)dialog.findViewById(R.id.bdialogdatetimeHourP);
				final ImageButton bHourM=(ImageButton)dialog.findViewById(R.id.bdialogdatetimeHourM);
				final ImageButton bMinP=(ImageButton)dialog.findViewById(R.id.bdialogdatetimeMinP);
				final ImageButton bMinM=(ImageButton)dialog.findViewById(R.id.bdialogdatetimeMinM);
				final EditText etYear=(EditText)dialog.findViewById(R.id.etdialogdatetimeYear);
				final EditText etMonth=(EditText)dialog.findViewById(R.id.etdialogdatetimeMonth);
				final EditText etDay=(EditText)dialog.findViewById(R.id.etdialogdatetimeDay);
				final EditText etHour=(EditText)dialog.findViewById(R.id.etdialogdatetimeHour);
				final EditText etMin=(EditText)dialog.findViewById(R.id.etdialogdatetimeMin);
				final CheckBox cbSetTime=(CheckBox)dialog.findViewById(R.id.cbdialogdatetimeSettime);
				final CheckBox cbSetAlarm=(CheckBox)dialog.findViewById(R.id.cbdialogdatetimeSetAlarm);
				Button bSetDateOk=(Button)dialog.findViewById(R.id.bdialogdatetimeOk);
				Button bSetDateCancel=(Button)dialog.findViewById(R.id.bdialogdatetimeCancel);
				
				LinearLayout llSetTime=(LinearLayout)dialog.findViewById(R.id.lldialogdatetimeSetTime);
				LinearLayout llCheckBox=(LinearLayout)dialog.findViewById(R.id.lldialogdatetimeCheckBox);
				LinearLayout llAlarmCheckBox=(LinearLayout)dialog.findViewById(R.id.lldialogdatetimeAlarmCheckBox);
				
				if(wcSetTimeOrAlarm==WhoCall.SETTING_DATE)//�p�G�O�]�m������s�եΪ�����ܮ�
				{
					llSetTime.setVisibility(LinearLayout.VISIBLE);//�]�m����ɶ���LinearLayout��ܥX��
					llCheckBox.setVisibility(LinearLayout.VISIBLE);//�O�_�]�m����ɶ���CheckBox��ܥX��
					llAlarmCheckBox.setVisibility(LinearLayout.GONE);//�O�_�]�m�x����CheckBox�����
					
					//��schTemp����year month day��ܦbEditText��
					etYear.setText(""+schTemp.getYear());
					etMonth.setText(schTemp.getMonth()<10?"0"+schTemp.getMonth():""+schTemp.getMonth());
					etDay.setText(schTemp.getDay()<10?"0"+schTemp.getDay():""+schTemp.getDay());
					
					//�p�GschTemp����ܬO�_�]�m����ɶ���������timeSet��true�A�Y�]�m�F����ɶ��A�h��w�]�m���ɤ���ܦbEditText��
					if(schTemp.getTimeSet())
					{
						etHour.setText(schTemp.getHour()<10?"0"+schTemp.getHour():""+schTemp.getHour());
						etMin.setText(schTemp.getMinute()<10?"0"+schTemp.getMinute():""+schTemp.getMinute());
					}
					else//�_�h�q�{��ܤK�I
					{
						etHour.setText("08");
						etMin.setText("00");
					}
					
					//�O�_�]�m����ɶ���CheckBox�M�w�����]�m�ɶ�������i���i��
					cbSetTime.setOnCheckedChangeListener
					(
							new OnCheckedChangeListener()
							{
								@Override
								public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) 
								{
									etHour.setEnabled(isChecked);
									etMin.setEnabled(isChecked);
									bHourP.setEnabled(isChecked);
									bHourM.setEnabled(isChecked);
									bMinP.setEnabled(isChecked);
									bMinM.setEnabled(isChecked);
								}
							}
					);
					
					//�o�T���y�y�T�OĲ�ocbSetTime��OnCheckedChangeListener
					cbSetTime.setChecked(schTemp.getTimeSet());
					cbSetTime.setChecked(!schTemp.getTimeSet());
					cbSetTime.setChecked(schTemp.getTimeSet());
				}
				
				//�p�G�եθӬɭ����O�]�m�x�����s
				if(wcSetTimeOrAlarm==WhoCall.SETTING_ALARM)
				{
					llSetTime.setVisibility(LinearLayout.VISIBLE);//�]�m����ɶ���LinearLayout���
					llCheckBox.setVisibility(LinearLayout.GONE);//�O�_�]�m����ɶ���CheckBox�����
					llAlarmCheckBox.setVisibility(LinearLayout.VISIBLE);//�O�_�]�m�x����CheckBox���
					
					//�O�_�]�m�x��CheckBox�M�w�����]�m�x��������i���i��
					cbSetAlarm.setOnCheckedChangeListener
					(
							new OnCheckedChangeListener()
							{
								@Override
								public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) 
								{
									bYearP.setEnabled(isChecked);
									bYearM.setEnabled(isChecked);
									bMonthP.setEnabled(isChecked);
									bMonthM.setEnabled(isChecked);
									bDayP.setEnabled(isChecked);
									bDayM.setEnabled(isChecked);
									bHourP.setEnabled(isChecked);
									bHourM.setEnabled(isChecked);
									bMinP.setEnabled(isChecked);
									bMinM.setEnabled(isChecked);
									etYear.setEnabled(isChecked);
									etMonth.setEnabled(isChecked);
									etDay.setEnabled(isChecked);
									etHour.setEnabled(isChecked);
									etMin.setEnabled(isChecked);
								}
							}
					);
					
					//�T�OOnCheckedChangeListener�QĲ�o
					cbSetAlarm.setChecked(schTemp.getAlarmSet());
					cbSetAlarm.setChecked(!schTemp.getAlarmSet());
					cbSetAlarm.setChecked(schTemp.getAlarmSet());
					
					if(cbSetAlarm.isChecked())//�p�G��ܬO�_�]�m�x����Checkbox�O�襤�A�������x���]�m�A�hŪ���x���ƾڶ�JEditText
					{
						etYear.setText(""+schTemp.getAYear());
						etMonth.setText(schTemp.getAMonth()<10?"0"+schTemp.getAMonth():""+schTemp.getAMonth());
						etDay.setText(schTemp.getADay()<10?"0"+schTemp.getADay():""+schTemp.getADay());
						etHour.setText(schTemp.getAHour()<10?"0"+schTemp.getAHour():""+schTemp.getAHour());
						etMin.setText(schTemp.getAMin()<10?"0"+schTemp.getAMin():""+schTemp.getAMin());
					}
					else//�p�G�S�襤�A�����S���x���]�m�A�q�{Ū����{�ɶ��]�m��x����EditText
					{
						etYear.setText(""+schTemp.getYear());
						etMonth.setText(schTemp.getMonth()<10?"0"+schTemp.getMonth():""+schTemp.getMonth());
						etDay.setText(schTemp.getDay()<10?"0"+schTemp.getDay():""+schTemp.getDay());
						if(schTemp.getTimeSet())//�p�G��{�]�m�F����ɶ��A�x�����p�ɤ����]�m������ɶ����p�ɤ���
						{
							etHour.setText(schTemp.getHour()<10?"0"+schTemp.getHour():""+schTemp.getHour());
							etMin.setText(schTemp.getMinute()<10?"0"+schTemp.getMinute():""+schTemp.getMinute());
						}
						else//�p�G��{�S�]����ɶ��A�h�x�����p�ɤ����q�{�]�m8�I
						{
							etHour.setText("08");
							etMin.setText("00");
						}
					}
				}				
				
				
				bYearP.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int year=Integer.parseInt(etYear.getText().toString().trim());
								year++;
								etYear.setText(""+year);
							}
						}
				);
				bYearM.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int year=Integer.parseInt(etYear.getText().toString().trim());
								year--;
								etYear.setText(""+year);
							}
						}
				);
				bMonthP.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int month=Integer.parseInt(etMonth.getText().toString().trim());
								if(++month>12)
								{
									month=1;
								}
								etMonth.setText(month<10?"0"+month:""+month);
							}
						}
				);
				bMonthM.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int month=Integer.parseInt(etMonth.getText().toString().trim());
								if(--month<1)
								{
									month=12;
								}
								etMonth.setText(month<10?"0"+month:""+month);
							}
						}
				);
				
				bDayP.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int year=Integer.parseInt(etYear.getText().toString().trim());
								int month=Integer.parseInt(etMonth.getText().toString().trim());
								int day=Integer.parseInt(etDay.getText().toString().trim());
								if(++day>getMaxDayOfMonth(year,month))
								{
									day=1;
								}
								etDay.setText(day<10?"0"+day:""+day);
							}
						}
				);
				bDayM.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int year=Integer.parseInt(etYear.getText().toString().trim());
								int month=Integer.parseInt(etMonth.getText().toString().trim());
								int day=Integer.parseInt(etDay.getText().toString().trim());
								if(--day<1)
								{
									day=getMaxDayOfMonth(year,month);
								}
								etDay.setText(day<10?"0"+day:""+day);
							}
						}
				);
				
				bHourP.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int hour=Integer.parseInt(etHour.getText().toString().trim());
								if(++hour>23)
								{
									hour=0;
								}
								etHour.setText(hour<10?"0"+hour:""+hour);
							}
						}
				);
				bHourM.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int hour=Integer.parseInt(etHour.getText().toString().trim());
								if(--hour<0)
								{
									hour=23;
								}
								etHour.setText(hour<10?"0"+hour:""+hour);
							}
						}
				);
				bMinP.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int min=Integer.parseInt(etMin.getText().toString().trim());
								if(++min>59)
								{
									min=0;
								}
								etMin.setText(min<10?"0"+min:""+min);
							}
						}
				);
				bMinM.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								int min=Integer.parseInt(etMin.getText().toString().trim());
								if(--min<0)
								{
									min=59;
								}
								etMin.setText(min<10?"0"+min:""+min);
							}
						}
				);
				
				bSetDateOk.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								String year=etYear.getText().toString().trim();
								String month=etMonth.getText().toString().trim();
								String day=etDay.getText().toString().trim();
								//�̫�A�ˬd�@�U�O�_���~���]�m���~�A��p2��30������
								if(Integer.parseInt(day)>getMaxDayOfMonth(Integer.parseInt(year),Integer.parseInt(month)))
								{
									Toast.makeText(RcActivity.this, "����]�m���~", Toast.LENGTH_SHORT).show();
									return;
								}
								
								//�p�G����ܮجO�Q�]�m������s�եΪ��A��~���ᵹSchedule����Date1�A�Y��{���
								if(wcSetTimeOrAlarm==WhoCall.SETTING_DATE)
								{
									schTemp.setDate1(year, month, day);
									schTemp.setTimeSet(cbSetTime.isChecked());
									if(cbSetTime.isChecked())//�p�G�]�m�F����ɶ��A��ɤ��ᵹSchedule����Time1�A�Y��{�ɶ�
									{							
										String hour=etHour.getText().toString().trim();
										String min=etMin.getText().toString().trim();
										schTemp.setTime1(hour, min);
									}
									
								}
								//�p�G����ܮجO�Q�]�m�x�����s�եΪ��A��~���ᵹSchedule����Date2�A�Y�x������A�ɤ��ᵹTime2�A�Y�x���ɶ�
								else if(wcSetTimeOrAlarm==WhoCall.SETTING_ALARM)
								{
									schTemp.setAlarmSet(cbSetAlarm.isChecked());
									if(cbSetAlarm.isChecked())
									{
										schTemp.setDate2(year, month, day);
										String hour=etHour.getText().toString().trim();
										String min=etMin.getText().toString().trim();
										schTemp.setTime2(hour, min);
									}
								}
								dialogSetDatetime.cancel();
								gotoSetting();		
							}
						}
				);
				bSetDateCancel.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								dialogSetDatetime.cancel();
							}
						}
				);
				break;
			case DIALOG_SCH_DEL_CONFIRM://�R����{��ܮ�
				dialog.setContentView(R.layout.dialogschdelconfirm);
				Button bDelOk=(Button)dialog.findViewById(R.id.bdialogschdelconfirmOk);
				Button bDelCancel=(Button)dialog.findViewById(R.id.bdialogschdelconfirmCancel);
				
				bDelOk.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								deleteSchedule(RcActivity.this);
								gotoMain();
								dialogSchDelConfirm.cancel();
							}
						}
				);
				
				bDelCancel.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								dialogSchDelConfirm.cancel();
							}
						}
				);
				break;
				
			case DIALOG_CHECK://�d�ݤ�{��ܮ�
				dialog.setContentView(R.layout.dialogcheck);
				TextView tvType=(TextView)dialog.findViewById(R.id.tvdialogcheckType);//���������TextView
				TextView tvTitle=(TextView)dialog.findViewById(R.id.tvdialogcheckTitle);//��ܼ��D��TextView
				TextView tvNote=(TextView)dialog.findViewById(R.id.tvdialogcheckNote);//��ܳƵ���TextView
				TextView tvDatetime1=(TextView)dialog.findViewById(R.id.tvdialogcheckDate1);//��ܤ�{����M�ɶ���TextView
				TextView tvDatetime2=(TextView)dialog.findViewById(R.id.tvdialogcheckDate2);//��ܾx������M�ɶ���TextView
				Button bEdit=(Button)dialog.findViewById(R.id.bdialogcheckEdit);//�s����s
				Button bDel=(Button)dialog.findViewById(R.id.bdialogcheckDel);//�R�����s
				Button bBack=(Button)dialog.findViewById(R.id.bdialogcheckBack);//��^���s
				
				tvType.setText(schTemp.typeForListView());
				tvTitle.setText(schTemp.getTitle());
				tvNote.setText(schTemp.getNote());
				
				//�p�G�Ƶ����šA��ܵL�Ƶ�
				if(schTemp.getNote().equals(""))
				{
					tvNote.setText("(�L�Ƶ�)");
				}
				String time1=schTemp.getTime1();
				
				//�p�G����ɶ����šA�ɶ���ܦ�--:--
				if(time1.equals("null"))
				{
					time1="- -:- -";
				}
				tvDatetime1.setText(schTemp.getDate1()+"  "+time1);
				
				String date2=schTemp.getDate2();
				String time2=schTemp.getTime2();
				
				//�x��������Ū��ܻ����S���x��
				if(date2.equals("null"))
				{
					date2="(�L�x��)";
					time2="";
				}
				tvDatetime2.setText(date2+"  "+time2);
				
		        bEdit.setOnClickListener
		        (
		        		new OnClickListener()
				        {
							@Override
							public void onClick(View v) {
								dialogCheck.cancel();
								gotoSetting();
							}        	
				        }
		        ); 
		        
		        bDel.setOnClickListener
		        (
		        		new OnClickListener()
				        {
							@Override
							public void onClick(View v) {
								dialogCheck.cancel();
								showDialog(DIALOG_SCH_DEL_CONFIRM);
							}
				        }
		        );
		        
		        bBack.setOnClickListener
		        (
		        		new OnClickListener()
				        {
							@Override
							public void onClick(View v) {
								dialogCheck.cancel();
							}
				        }
		        );
				break;
			case DIALOG_ALL_DEL_CONFIRM://�R���Ҧ��L����{��ܮ�
				dialog.setContentView(R.layout.dialogdelpassedconfirm);
				Button bAllDelOk=(Button)dialog.findViewById(R.id.bdialogdelpassedconfirmOk);
				Button bAllDelCancel=(Button)dialog.findViewById(R.id.bdialogdelpassedconfirmCancel);
				bAllDelOk.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								deletePassedSchedule(RcActivity.this);
								gotoMain();
								dialogAllDelConfirm.cancel();
							}
						}
				);
				
				bAllDelCancel.setOnClickListener
				(
						new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								dialogAllDelConfirm.cancel();
							}
						}
			    );
				break;
    	}
	}
	//onKeyDown��k
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
    	//���U�����^���s��
    	if(keyCode==4){
        	switch(curr)
        	{
        		case MAIN://�b�D�ɭ����ܰh�X�{��
        			System.exit(0);
        		break;
        		case SETTING://�b��{�s��ɭ����ܪ�^�D�ɭ�
        			gotoMain();
        		break;
        		case TYPE_MANAGER:////�b�����޲z�ɭ����ܪ�^��{�s��ɭ�
        			gotoSetting();
        		break;
        		case SEARCH://�b��{�d��ɭ����ܪ�^�D�ɭ�
        			gotoMain();
        		break;
        		case SEARCH_RESULT://�b��{�d�䵲�G�ɭ����ܪ�^��{�d��ɭ�
        			gotoSearch();
        		break;
        		case HELP://�b���U�ɭ����ܪ�^�D�ɭ�
        			gotoMain();
        		break;
        		case ABOUT:
        			gotoMain();
        		break;
        	}
        	return true;
    	}
    	return false;
	}
    //�Ы�Menu
    @Override
	public boolean onCreateOptionsMenu(Menu menu) 
    {
    	if(curr!=Layout.MAIN)//�u���\�b�D�ɭ��եε��???????????????????????
    	{
    		return false;  
    	}
    	MenuItem miHelp=menu.add(1, MENU_HELP, 0, "����");
    	miHelp.setIcon(R.drawable.help);
		MenuItem miAbout=menu.add(1, MENU_ABOUT, 0, "����");
		miAbout.setIcon(R.drawable.about);
		
		miAbout.setOnMenuItemClickListener
		(
				new OnMenuItemClickListener()
				{
					@Override
					public boolean onMenuItemClick(MenuItem item) 
					{	
						setContentView(R.layout.rcabout);
						curr=Layout.HELP;
						return true;
					}
					
				}
		);
		
		miHelp.setOnMenuItemClickListener
		(
				new OnMenuItemClickListener()
				{
					@Override
					public boolean onMenuItemClick(MenuItem item) 
					{		
						setContentView(R.layout.rchelp);
						curr=Layout.ABOUT;
						return true;
					}
				}
		);
		return true;
	}
    @Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) 
	{
		return super.onMenuItemSelected(featureId, item);
	}
    //�Ψӱo��year�~month�몺�̤j�Ѽ�
	public int getMaxDayOfMonth(int year,int month)
    {
    	int day=0;
    	boolean run=false;
    	if(year%400==0||year%4==0&&year%100!=0)
    	{
    		run=true;
    	}
    	if(month==4||month==6||month==9||month==11)
    	{
    		day=30;
    	}
    	else if(month==2)
    	{
    		if(run)
    		{
    			day=29;
    		}
    		else
    		{
    			day=28;
    		}
    	}
    	else
    	{
    		day=31;
    	}
    	return day;
    }
    //��^��YYYY/MM/DD���j�᪺�~���r�Ŧ�Ʋ�
	public String[] splitYMD(String ss)
    {
    	String[] s=ss.split("/");
    	return s;
    }
}

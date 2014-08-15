package com.bn.lccx;

import java.util.Vector;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import static com.bn.lccx.LoadUtil.*;
enum WhichView {MAIN_MENU,ZZCX_VIEW,CCCX_VIEW,CZCCCX_VIEW,LIST_VIEW,PASSSTATION_VIEW,
	             CCTJ_VIEW,CZTJ_VIEW,GXTJ_VIEW,FJGN_VIEW,WELCOME_VIEW,ABOUT_VIEW,HELP_VIEW}
public class LCCXActivity extends Activity 
{
	WelcomeView wv;//�i�J�w��ɭ�
	WhichView curr;//��e�T�|��	
	static int flag;//�]�m�������лx��@�@�@���@�����d�ߡ@���@�����d�ߡ@���@�����d��	
	
	
	String[][]msgg=new String[][]{{""}};//�n���ޥ�
	
	
	String s1[];
	String s2[];
	
	
	Handler hd=new Handler()//�n�������B�z��
	{
			@Override
			public void handleMessage(Message msg)//���g��k
        	{
        		switch(msg.what)
        		{
	        		case 0://�i�J�w��ɭ�
	        			goToWelcomeView();
	        			
	        		break;
	        		case 1://�i�J���ɭ�
	        			goToMainMenu();       			
	        		break;
	        		case 2://�i�J����ɭ�
	        			setContentView(R.layout.about);
	        	    	curr=WhichView.ABOUT_VIEW;
	        			break;
	        		case 3://�i�J���U�ɭ�
	        			setContentView(R.layout.help);
	        	    	curr=WhichView.HELP_VIEW;
	        			break;
	        		
        		}
        	}
	};
	
	
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        //�]�m������
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//�]�m��̼Ҧ�
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);		
		CreatTable.creattable();//�ت�
		iniTLisit();//��l�ƼƲ�
		this.hd.sendEmptyMessage(0);						//�o�e�����i�J�w��ɭ�
    }
	
	 public void goToWelcomeView()
	    {
	    	if(wv==null)//�p�G�ӹﹳ�S�Ыثh�Ы�
	    	{
	    		wv=new WelcomeView(this);
	    	}
	    	setContentView(wv);
	    	curr=WhichView.WELCOME_VIEW;//���ѷ�e�Ҧb�ɭ�
	    }
	public void goToMainMenu()//�h�D���
	 {	
	      	setContentView(R.layout.main);	
	      	curr=WhichView.MAIN_MENU;
			//����D��椤�ӫ��s���ޥ�
			ImageButton ibzzcx=(ImageButton)findViewById(R.id.ibzzcx);
			ImageButton ibcccx=(ImageButton)findViewById(R.id.ibcccx);
			ImageButton ibczcccx=(ImageButton)findViewById(R.id.ibczcccx);
			ImageButton ibfjgn=(ImageButton)findViewById(R.id.ibfjgn);
			ImageButton ibabout=(ImageButton)findViewById(R.id.about_button);
			ImageButton ibhelp=(ImageButton)findViewById(R.id.help_button);
			ibabout.setOnClickListener//������s����ť
			(
			   new OnClickListener()
			   {
				public void onClick(View v) 
				{
					
					hd.sendEmptyMessage(3);//�o�����i�J����ɭ�					
				}
			   }
			);
			ibhelp.setOnClickListener//���U�d�ߪ���ť
			(
			   new OnClickListener()
			   {
				public void onClick(View v) 
				{

					hd.sendEmptyMessage(2);	//�o�����i�J���U�ɭ�
				}
			   }
			);
			ibzzcx.setOnClickListener//�����d�߫��s����ť
			(
			   new OnClickListener()
			   {
				public void onClick(View v) 
				{
					goTozzcxView();//�i�J�����d�߼Ҷ�
				}
			   }
			);
			ibcccx.setOnClickListener//�����d�߫��s����ť
			(
			   new OnClickListener()
			   {
				public void onClick(View v) 
				{
					goTocccxView();//�i�J�����d�߼Ҷ�
				}   
			   }
			);
			ibczcccx.setOnClickListener//?�޸����ӿ�����?
			(
			   new OnClickListener()
			   {
				public void onClick(View v) 
				{
					goToczcccxView();//�i�J�����d�߼Ҷ�
				}   
			   }
			); 
			ibfjgn.setOnClickListener//���[�\����s����ť
			(
			   new OnClickListener()
			   {
				public void onClick(View v) 
				{
                    goTofjgnView();//�i�J���[�\��Ҷ�
				}
			   }
			);
	 }
	 public void goTozzcxView()//�h�����d��
	 {
		 setContentView(R.layout.zzcx);
		 curr=WhichView.ZZCX_VIEW;
		 flag=0;//�лx��
		
		 Button bcx=(Button) findViewById(R.id.zzcxbt);//�d�߫��s
		 Button bfh=(Button) findViewById(R.id.zzcxfhbt);//��^���s
		
		 iniTLisitarray(R.id.EditText01);//���U�Ө�����J�奻�زK�[�A�t��
		 iniTLisitarray(R.id.zzcxzzz);
		 iniTLisitarray(R.id.zzcxzdz); 

		 final CheckBox zzzcx=(CheckBox)findViewById(R.id.zzcxzzzbt);//���௸�_��ت��ޥ�
		 		 
		 bcx.setOnClickListener//���d�߫��s�K�[��ť
		 (
			new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					if(!isLegal())
					{
						return;
					}

					AutoCompleteTextView zzcx_cfz = (AutoCompleteTextView) findViewById(R.id.EditText01);//�X�o��
					AutoCompleteTextView zzcx_zzz = (AutoCompleteTextView) findViewById(R.id.zzcxzzz);//���௸
					AutoCompleteTextView zzcx_zdz= (AutoCompleteTextView) findViewById(R.id.zzcxzdz);//���I��
					
					String start=zzcx_cfz.getText().toString().trim();//�o��������奻
					String end =zzcx_zdz.getText().toString().trim();
					String between=zzcx_zzz.getText().toString().trim();
					
					
					Vector<Vector<String>> temp;
					if(zzzcx.isChecked()==true)//�p�G���௸�d�߫��s�Q�襤�A�h�i�椤�௸�d��
					{
						 temp= LoadUtil.Zjzquery(start, between, end);//�i�椤�௸�d��
						 if(temp.size()==0)//�p�G�d�ߵ��G�V�q���׬�0�A�h�L�d�ߵ��G
							{
								Toast.makeText(LCCXActivity.this, "�S���A�Ҭd�䪺���௸���u!!!", Toast.LENGTH_SHORT).show();
								zzcx_cfz.setText("");zzcx_zzz.setText("");zzcx_zdz.setText("");
								return;
							}
						 
					}else //�_�h�i�毸���d��
					{
						temp= LoadUtil.getSameVector(start, end);
						if(temp.size()==0)
						{
							Toast.makeText(LCCXActivity.this, "�藍�_�A�S���������C���H��!!!", Toast.LENGTH_SHORT).show();
							zzcx_cfz.setText("");zzcx_zzz.setText("");zzcx_zdz.setText("");
							return;
						}
					}

					zzcx_cfz=null;//�N�ӿ�J�ت��ޥθm����
					zzcx_zdz=null;
					zzcx_zzz=null;
					
					String[][] msgInfo=new String[temp.elementAt(0).size()][temp.size()];//�s�ةM���G�V�q�������Ʋ�
					for(int i=0;i<temp.size();i++)
					{//for�`���N���G�V�q�����ƾھɤJ�Ʋ�
						for(int j=0;j<temp.elementAt(0).size();j++)
						{
							msgInfo[j][i]=(String)temp.get(i).get(j);
						}
					}
					goToListView(msgInfo);//������d�ߵ��G��ܬɭ�ListView�ɭ�
				}	
			}
		 );
		 bfh.setOnClickListener//����^���s�K�[��ť
		 (
			new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					goToMainMenu();	//��^��D���ɭ�
				}	
			}
		 );		
		 //�إ߾A�t��
			
	 }
	 public void goTocccxView()//�h�����d�߬ɭ�
	 {
		 setContentView(R.layout.cccx);//�����쨮���d�߬ɭ�
		 curr=WhichView.CCCX_VIEW;//���Ѭɭ�
		 flag=1;
		 Button bcx=(Button) findViewById(R.id.cccx_cx);
		 Button bfh=(Button) findViewById(R.id.cccx_fh);
		 bcx.setOnClickListener
		 (
			new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					if(!isLegal())//�p�G�U�ӿ�J�ؤ������W�h�h��^
					{						
						return;
					}
					AutoCompleteTextView cccx_cc= (AutoCompleteTextView) findViewById(R.id.cccxcc);//�o�쨮����J�ت��ޥ�
					 String cccxcc=cccx_cc.getText().toString().trim();//�o��䤤���奻
					 Vector<Vector<String>> temp= LoadUtil.trainSearch(cccxcc);//�եΤu���Ƭd�߱o�쵲�G��
					 cccx_cc=null;
					 if(temp.size()==0)//�p�G���G�V�q���׬�0�A�����S���d�ߵ��G�A�Y�L�����������H��
						{
							Toast.makeText(LCCXActivity.this, "�S�������H��!!!", Toast.LENGTH_SHORT).show();
							return;
						}
					 String[][] msgInfo=new String[temp.elementAt(0).size()][temp.size()];//�s�ع�����V�q���Ʋ�					 
						for(int i=0;i<temp.size();i++)//�_�h�N�V�q�����ƾھɤJ�������Ʋ�
						{
							for(int j=0;j<temp.elementAt(i).size();j++)
							{
								msgInfo[j][i]=(String)temp.get(i).get(j);
							}
						}						
						goToListView(msgInfo);//�����쵲�G��ܬɭ�ListView�ɭ�
				}	
			}
		 );
		 bfh.setOnClickListener//����^���s�K�[��ť
		 (
			new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					goToMainMenu();	//��^����ɭ�
				}	
			}
		 );
		 
	 }
	 public void goToczcccxView()//�h�����Ҧ������d��
	 {
		 setContentView(R.layout.czcx);//�����쨮���d�߬ɭ�
		 curr=WhichView.CZCCCX_VIEW;//���Ѭɭ�
		 flag=2;//���ѩҦb�ɭ��������d�߬ɭ�
		 Button bcx=(Button) findViewById(R.id.czcx_cx);//����d�߫��s���ޥ�
		 Button bfh=(Button) findViewById(R.id.czcx_fh);//�����^���s���ޥ�
		 
		 iniTLisitarray(R.id.czcxwb);//�������奻�زK�[�A�t���ӧ����奻��J�����ܥ\��
		 bcx.setOnClickListener//���d�߫��s�K�[��ť
		 (
			new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					if(!isLegal())//�p�G�Y�Ӥ奻�ؤ��X�W�h�A�h��^
					{
						return;
					}
					AutoCompleteTextView czcx_czzm= (AutoCompleteTextView) findViewById(R.id.czcxwb);//���쨮����J�ت��ޥ�
					String czcxczzm=czcx_czzm.getText().toString().trim();//�o������奻�ؤ����奻
					 Vector<Vector<String>> temp= stationSearch(czcxczzm);//�եΤu���Ƭd�߱o�쵲�G�V�q
					 czcx_czzm=null;
					 if(temp.size()==0)//�p�G���G�V�q�����׬�0�A�����S�������H��
						{
							Toast.makeText(LCCXActivity.this, "�S�������H��!!!", Toast.LENGTH_SHORT).show();
							return;
						}
					 String[][] msgInfo=new String[temp.elementAt(0).size()][temp.size()];//�_�h�Ыع����󵲪G�V�q���Ʋ�
						for(int i=0;i<temp.size();i++)//�N���G�V�q�����ƾھɤJ�Ʋ�
						{
							for(int j=0;j<temp.elementAt(0).size();j++)
							{
								msgInfo[j][i]=(String)temp.get(i).get(j);
								
							}
						}
						
					goToListView(msgInfo);
				}	
			}
		 );
		 bfh.setOnClickListener//����^���s�K�[��ť
		 (
			new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					goToMainMenu();	//������D���ɭ�
				}	
			}
		 );
		 
	 }
	 public void goTofjgnView()//�h���[�\��ɭ�
	 {
		 setContentView(R.layout.fjgnmenu);//��������[�\��ɭ�
		 curr=WhichView.FJGN_VIEW;//���ѷ�e�Ҧb�ɭ������[�\��ɭ�
		 ImageButton ibcctj=(ImageButton)findViewById(R.id.ibcctj);//���쨮���K�[���s�ޥ�
	     ImageButton ibcztj=(ImageButton)findViewById(R.id.ibcztj);//���쨮���K�[���s�ޥ�
		 ImageButton ibgxtj=(ImageButton)findViewById(R.id.ibgxtj);//�������Y�K�[���s���ޥ�
		 ibcctj.setOnClickListener//�����K�[���s����ť
		 (
			   new OnClickListener()
			   {
				public void onClick(View v) 
				{
					goTocctjView();//�h�����K�[�ɭ�
				}
			   }
		 );
		 ibcztj.setOnClickListener//�����K�[���s����ť
		 (
			   new OnClickListener()
			   {
				public void onClick(View v) 
				{
					goTocztjView();//�����쨮���K�[�ɭ�
				}
			   }
		 );
		 ibgxtj.setOnClickListener//���Y�K�[���s����ť
		 (
			   new OnClickListener()
			   {
				public void onClick(View v) 
				{
					goTogxtjView();
				}
			   }
		 );
	 }
	 public void goTocctjView()//�h�����K�[�ɭ�
	 {
		 setContentView(R.layout.cctj);//�����ɭ�
		 curr=WhichView.CCTJ_VIEW;//���Ѭɭ�
		 Button bcctjtj=(Button)findViewById(R.id.cctj_tj);//����K�[���s���@�ޥ�
		 Button bcctjfh=(Button)findViewById(R.id.cctj_fh);//�����^���s���ޥ�
		 iniTLisitarray(R.id.cctj_sfz);//���l�o���M���I���奻�زK�[�A�t��
		 iniTLisitarray(R.id.cctj_zdz);		 
		 final int tid=LoadUtil.getInsertId("train","Tid")+1;//���즹�ɨ�����TID�C���̤jID�A�M��[1�o�X�n���J��������ID�C
		 bcctjtj.setOnClickListener//���K�[���s�K�[��ť
		 (
			new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					if(!isLegal())//�P�_��J�جO�_�ŦX�W�h
					{
						return;
					}
					AutoCompleteTextView cctjcnm=(AutoCompleteTextView)findViewById(R.id.cctj_cm);//����ӿ�J�ت��ޥ�
					 AutoCompleteTextView cctjclx=(AutoCompleteTextView)findViewById(R.id.cctj_lclx);
					 AutoCompleteTextView cctjcsf=(AutoCompleteTextView)findViewById(R.id.cctj_sfz);
					 AutoCompleteTextView cctjczd=(AutoCompleteTextView)findViewById(R.id.cctj_zdz);
					 String cnm=cctjcnm.getText().toString().trim();
					 String clx=cctjclx.getText().toString().trim();
					 String csf=cctjcsf.getText().toString().trim();
					 String czd=cctjczd.getText().toString().trim();
					 String sql="select * from train where Tname='" +cnm+//�d�ݬO�_���Ө���
					"'";
					Vector<Vector<String>> ss=query(sql);					
					if(ss.size()>0)
					{
						Toast.makeText(LCCXActivity.this, "�藍�_�A�w�g���F������!!!", Toast.LENGTH_SHORT).show();
						return;
					}
					 sql ="select Sid from station where Sname='"+csf+"'";
					if(query(sql).size()==0)//�d�ݬO�_���Ө���
					{
						Toast.makeText(LCCXActivity.this, "�藍�_�A�өl�o�����s�b!!!", Toast.LENGTH_SHORT).show();
						return;
					}
					sql="select Sid from station where Sname='"+czd+"'";//�d�ݬO�_���Ө���
					if(query(sql).size()==0)
					{
						Toast.makeText(LCCXActivity.this, "�藍�_�A�Ӳ��I�����s�b!!!", Toast.LENGTH_SHORT).show();
						return;
					}
					
					
					
					sql="insert into train values(" +
					tid +",'" +cnm+"','" +csf +"'" +",'" +czd +"','" +clx +"')";//�K�[���Y
					if(!insert(sql))//�p�G����
					{
					Toast.makeText(LCCXActivity.this, "�藍�_�A�K�[����!!!", Toast.LENGTH_SHORT).show();
						
					}else{//�_�h���K�[���\
						Toast.makeText(LCCXActivity.this, "���ߧA�A�K�[���\!!!", Toast.LENGTH_SHORT).show();
					}
					
				}	
			}
		 );
		 bcctjfh.setOnClickListener//����^���s�K�[��ť
		 (
			new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
				  goTofjgnView();//��^����[�\��ɭ�
				}	
			}
		 );
		 
	 }
	 public void goTocztjView()//�h�����K�[�ɭ�
	 {
		 setContentView(R.layout.cztj);//�����ɭ�
		 curr=WhichView.CZTJ_VIEW;//���Ѭɭ�
		 Button bcztjtj=(Button)findViewById(R.id.cztj_tj);//����K�[���s���ޥ�
		 Button bcztjfh=(Button)findViewById(R.id.cztj_fh);//�����^���s���ޥ�
		
		 
		 
		 final int sid=LoadUtil.getInsertId("station","Sid")+1;//�d�XSId�C���̤j��ID�A�[1�o�즹�ɻݭn���J��������ID
		 bcztjtj.setOnClickListener//���K�[���s�K�[��ť
		 (
			new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					if(!isLegal())
					{
						return;
					}
					 EditText cztjmc=(EditText)findViewById(R.id.et_cztj_czmc);//�o��ӿ�J�ؤ����ޥ�
					 EditText cztjjc=(EditText)findViewById(R.id.et_cztj_czjc);
					String cnm=cztjmc.getText().toString().trim();//�o��������奻
					String clx=cztjjc.getText().toString().trim();
					 if(!clx.matches("[a-zA-Z]+"))//���h���ǰt�A�d��²�ٿ�J�ؤ����奻�O�_�ŦX���O�r�����W�h
					{
						//�o���ǰt����
						 Toast.makeText(LCCXActivity.this, "�藍�_�A²�٥u�ର�r��!!!", Toast.LENGTH_SHORT).show();
							return;
					}
					
					String sql="select * from station where Sname='" +cnm+
					"'";
					Vector<Vector<String>> ss=query(sql);//�d�ݸӨ����O�_�w�g�s�b					
					if(ss.size()>0)//�p�G���G�V�q�����פj��0�A�����w�g���F�Ө��F
					{
						Toast.makeText(LCCXActivity.this, "�藍�_�A�w�g���F������!!!", Toast.LENGTH_SHORT).show();
						return;
					}
					sql="insert into station values(" +sid +	",'" +cnm +	"','" +	clx +"')";
					if(!insert(sql))//�i�洡�J�ާ@�A�p�G�O�K�[����
					{
					Toast.makeText(LCCXActivity.this, "�藍�_�A�K�[����!!!", Toast.LENGTH_SHORT).show();
						return;
					}else{//�_�h���K�[���\
						iniTLisit();
						Toast.makeText(LCCXActivity.this, "���ߧA�A�K�[���\!!!", Toast.LENGTH_SHORT).show();
					}
				}	
			}
		 );
		 bcztjfh.setOnClickListener//����^���s�K�[��ť
		 (
			new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
				  goTofjgnView();//��^����[�\��ɭ�
				}	
			}
		 );
	 }
	 public void goTogxtjView()//�h���Y�K�[�ɭ�
	 {
		 setContentView(R.layout.gxtj);//�����ɭ�
		 curr=WhichView.GXTJ_VIEW;//���Ѭɭ�
		 Button bgxtjtj=(Button)findViewById(R.id.gxtj_tj);//����K�[���s���ޥ�
		 Button bgxtjfh=(Button)findViewById(R.id.gxtj_fh);//�����^���s���ޥ�
		
		 iniTLisitarray(R.id.et_gxtj_zm);//�������W�r�K�[�A�t��
		 
		 bgxtjtj.setOnClickListener//���K�[���s�K�[��ť
		 (
			new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					EditText gxtjcnm=(EditText)findViewById(R.id.et_gxtj_cm);//���쨮�W��J�ت��ޥ�
					AutoCompleteTextView gxtjclx=(AutoCompleteTextView)findViewById(R.id.et_gxtj_zm);//���쯸�W��J�ت��ޥ�
					EditText gxtjcsf=(EditText)findViewById(R.id.et_gxtj_dzsj);//����쯸�ɶ���J�ت��ޥ�
					EditText gxtjczd=(EditText)findViewById(R.id.et_gxtj_kcsj);//����o���ɶ���J�ت��ޥ�
					
					String cnm=gxtjcnm.getText().toString().trim();//�o��������奻�H��
					String znm=gxtjclx.getText().toString().trim();
					String dct=gxtjcsf.getText().toString().trim();
					String fct=gxtjczd.getText().toString().trim();
					 
					int Rid=LoadUtil.getInsertId("relation","Rid")+1;//�d�Xrelation���̤j��ID�[1�o���e���J�����Y��ID
					 
					int cnmm=0;//����������ID
					int cznm=0;//����������ID
					
					if(!isLegal())
					{
						return;
					}					
					
					String sql = "select Tid "+
					"from train where Tname='"+cnm+"'";
					Vector<Vector<String>> ss=query(sql);					
					if(ss.size()>0)//�o�쨮��������ID
					{
						cnmm=Integer.parseInt((String)ss.get(0).get(0));						
					}else if(ss.size()==0){
						Toast.makeText(LCCXActivity.this, "�藍�_�A�S���Ө�!!!", Toast.LENGTH_SHORT).show();
					return;
					}
					sql="select Sid from station where Sname='"+znm+"'";				
					ss=query(sql);
					if(ss.size()>0)//�o�쨮��������ID
					{
						cznm=Integer.parseInt((String)ss.get(0).get(0));						
					}
					else if(ss.size()==0){
						Toast.makeText(LCCXActivity.this, "�藍�_�A�S���ӯ�!!!", Toast.LENGTH_SHORT).show();
						return;
					}
					
					sql="select Rid from relation where Sid="+cznm+" and Tid="+cnmm;//�i��d�ݸ����Y�O�_�w�g�s�b
				
					if(query(sql).size()>0)//�p�G�w�g�s�b
					{
					Toast.makeText(LCCXActivity.this, "�藍�_�A�����Y�w�g���F!!!", Toast.LENGTH_SHORT).show();
					return;
					}//�_�h�i�洡�J�ާ@
					sql="insert into relation values(" +
					Rid +
							"," +
							cnmm +
							"," +
							cznm +
							",'" +
							dct +
							"','" +
							fct +
							"')";
					
					if(!insert(sql))//�p�G���J����
					{
					Toast.makeText(LCCXActivity.this, "�藍�_�A�K�[����!!!", Toast.LENGTH_SHORT).show();
					return;
					}else{
						Toast.makeText(LCCXActivity.this, "���ߧA�A�K�[���\!!!", Toast.LENGTH_SHORT).show();
					}
				}	
			}
		 );
		 bgxtjfh.setOnClickListener//����^���s�K�[��ť
		 (
			new OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
				  goTofjgnView();//��^����[�\��ɭ�
				}	
			}
		 );
	 }
	 public void goToListView(String[][]mssg)//�hListView�ɭ�
	 {
		 	msgg=mssg;//��Ȥޥε������ƲաA�Ψӹ�{��^���s�\��
	        setContentView(R.layout.listview);//�����ɭ�
	        curr=WhichView.LIST_VIEW;//���Ѭɭ�
	        final String[][]msg=mssg;//�s�ؼƲաA�ý��
	        ListView lv_detail=(ListView)this.findViewById(R.id.ListView_detail);//����ListView���ޥ�
	        BaseAdapter ba_detail=new BaseAdapter()//�s�ؾA�t��
	        {
				@Override
				public int getCount() 
				{
					return msg[0].length;//�o��C������
				}
				@Override
				public Object getItem(int arg0){return null;}
				@Override
				public long getItemId(int arg0){return 0;}
				@Override
				public View getView(int arg0, View arg1, ViewGroup arg2)//���C�@���K�[���e
				{
					LinearLayout ll_detail=new LinearLayout(LCCXActivity.this);
					ll_detail.setOrientation(LinearLayout.HORIZONTAL);		//�]�m�¦V	
					ll_detail.setPadding(5,5,5,5);//�|�P�d��

					for(int i=0;i<msg.length;i++)//���C�@��]�m��ܪ��ƾ�
					{					    
						TextView s= new TextView(LCCXActivity.this);
						s.setText(msg[i][arg0]);//TextView����ܪ���r
						s.setTextSize(14);//�r��j�p
						s.setTextColor(getResources().getColor(R.color.black));//�r���C��
						s.setPadding(1,2,2,1);//�|�P�d��
						s.setWidth(60);//�e��
					    s.setGravity(Gravity.CENTER);
					    ll_detail.addView(s);//��JLinearLayout
					}
					return ll_detail;//�N��LinearLayout��^
				}        	
	        };    
	        lv_detail.setAdapter(ba_detail);//�N�A�t���K�[�iListView
	        
	        lv_detail.setOnItemClickListener//���C��K�[��ť
	        (
	           new OnItemClickListener()
	           {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,//���I���C�����Y�@���ɽեΦ����
						long arg3) //arg2���I�����ĴX��
				{
					String cccx=msg[0][arg2];//���X�������������������H��
					
					 Vector<Vector<String>> temp= LoadUtil.getInfo(cccx);//�d�߸Ө����g�L���Ҧ�����
					 if(temp.size()==0)//�P�_�O�_���d�ߵ��G
						{
							Toast.makeText(LCCXActivity.this, "�S�������H��!!!", Toast.LENGTH_SHORT).show();
							return;
						}
					 String[][] msgInfo=new String[temp.elementAt(0).size()][temp.size()];//�p�G���h�N���G��J�������Ʋ�
						for(int i=0;i<temp.size();i++)
						{
							for(int j=0;j<temp.elementAt(0).size();j++)
							{
								msgInfo[j][i]=(String)temp.get(i).get(j);								
							}
						}
						msgg=msg;
						goToPassStationView(msgInfo);//�����쨮�����鱡�p��ܬɭ�

				}        	   
	           }
	        );
	 }
	//�Y�C���g�L���Ҧ������C�h�g�L�����ɭ�
	 public void goToPassStationView(String[][]mssg)
	 {
		 setContentView(R.layout.passstation);//�����ɭ�
		 curr=WhichView.PASSSTATION_VIEW;//���Ѭɭ�
	        ListView lv_detail=(ListView)this.findViewById(R.id.ListView_passstation);//�o��ListView���ޥ�
	        final String[][]msg=mssg;
	       
	        BaseAdapter ba_detail=new BaseAdapter()//�s�ؾA�t��
	        {
				@Override
				public int getCount() 
				{
					return msg[0].length;//�o��C������
				}
				@Override
				public Object getItem(int arg0){return null;}
				@Override
				public long getItemId(int arg0){return 0;}
				@Override
				public View getView(int arg0, View arg1, ViewGroup arg2)
				{
					LinearLayout ll_detail=new LinearLayout(LCCXActivity.this);
					ll_detail.setOrientation(LinearLayout.HORIZONTAL);		//�]�m�¦V	
					ll_detail.setPadding(5,2,2,4);//�]�m�|�P�d��
					TextView []tv=
					{
					   new TextView(LCCXActivity.this),new TextView(LCCXActivity.this),new TextView(LCCXActivity.this)
					};
					for(int i=0;i<msg.length;i++)//�]�m�C�@�椤��ܪ��ƾ�
					{
						tv[i].setText(msg[i][arg0]);//�C��TextView�����奻
						tv[i].setTextSize(13);//�r��j�p
						tv[i].setTextColor(getResources().getColor(R.color.black));//�r���C��
						tv[i].setPadding(5,2,3,2);//�|�P�d��
						tv[i].setWidth(150);//�e��
					    tv[i].setGravity(Gravity.CENTER);
					    ll_detail.addView(tv[i]);//�K�[�iLinearLayout

					}
					return ll_detail;//�N��LinearLayout��^
				}        	
	        };    
	        lv_detail.setAdapter(ba_detail);//�N�A�t���K�[�i�C��
	        
	 }
	 //�d�ݦb�Y�Ӭɭ����I���d�߫��s�ɡA�P�_��J�جO�_����
	 public boolean isLegal()
	 {
		 if(curr==WhichView.ZZCX_VIEW)//�p�G��e�������d�߬ɭ��A��������奻�ضi��X�k����
		 {
			EditText etcfz=(EditText)findViewById(R.id.EditText01);//�X�o��
			EditText etzzz=(EditText)findViewById(R.id.zzcxzzz);//���௸
			EditText etzdz=(EditText)findViewById(R.id.zzcxzdz);//���I��
			CheckBox cbzzz=(CheckBox)findViewById(R.id.zzcxzzzbt);//���௸�_���
			if(etcfz.getText().toString().trim().equals(""))//�X�o������
			{
				Toast.makeText(this, "�X�o�����ର�šI�I�I",Toast.LENGTH_LONG).show();
				return false;
			}
			if(etzzz.getText().toString().trim().equals("")&&cbzzz.isChecked())//���௸����
			{
				Toast.makeText(this, "���௸���ର�šI�I�I",Toast.LENGTH_LONG).show();
				return false;
			}
			if(etzdz.getText().toString().trim().equals(""))//���I������
			{
				Toast.makeText(this, "���I�����ର�šI�I�I",Toast.LENGTH_LONG).show();
				return false;
			}
			if(etcfz.getText().toString().trim().contentEquals(etzdz.getText().toString().trim()))//�X�o���M���I���ۦP
			{
				Toast.makeText(this, "�X�o���M���I������ۦP�I�I�I",Toast.LENGTH_LONG).show();
				return false;
			}
			if(cbzzz.isChecked()&&etcfz.getText().toString().trim().contentEquals(etzzz.getText().toString().trim()))//�X�o���M���௸�ۦP
			{
				Toast.makeText(this, "�X�o���M���௸����ۦP�I�I�I",Toast.LENGTH_LONG).show();
				return false;
		    }
			if(cbzzz.isChecked()&&etzdz.getText().toString().trim().contentEquals(etzzz.getText().toString().trim()))//���I���M���௸
			{
				Toast.makeText(this, "���I���M���௸����ۦP�I�I�I",Toast.LENGTH_LONG).show();
				return false;
			}
		 }
		 if(curr==WhichView.CCCX_VIEW)//�p�G��e�������d�߬ɭ�
		 {
			 EditText etcccx=(EditText)findViewById(R.id.cccxcc);
			 if(etcccx.getText().toString().trim().contentEquals(""))
			 {
				 Toast.makeText(this, "�������ର�šI�I�I",Toast.LENGTH_LONG).show();
					return false;
			 }
		 }
		 if(curr==WhichView.CZCCCX_VIEW)//�p�G��e�����������d�߬ɭ�
		 {
			 EditText etczcccx=(EditText)findViewById(R.id.czcxwb);
			 if(etczcccx.getText().toString().trim().contentEquals(""))
			 {
				 Toast.makeText(this, "�������ର�šI�I�I",Toast.LENGTH_LONG).show();
					return false;
			 }
		 }
		 if(curr==WhichView.CCTJ_VIEW)//�p�G��e�b�����K�[
		 {
			 EditText et_cm=(EditText)findViewById(R.id.cctj_cm);//���W
			 EditText et_lclx=(EditText)findViewById(R.id.cctj_lclx);//�C������
			 EditText et_sfz=(EditText)findViewById(R.id.cctj_sfz);//�l�o��
			 EditText et_zdz=(EditText)findViewById(R.id.cctj_zdz);//���I��
			 if(et_cm.getText().toString().trim().contentEquals(""))
			 {
				 Toast.makeText(this, "���W���ର�šI�I�I",Toast.LENGTH_SHORT).show();
					return false;
			 }
			 if(et_lclx.getText().toString().trim().contentEquals(""))
			 {
				 Toast.makeText(this, "�C���������ର�šI�I�I",Toast.LENGTH_SHORT).show();
					return false;
			 }
			 if(et_sfz.getText().toString().trim().contentEquals(""))
			 {
				 Toast.makeText(this, "�l�o�����ର�šI�I�I",Toast.LENGTH_SHORT).show();
					return false;
			 }
			 if(et_zdz.getText().toString().trim().contentEquals(""))
			 {
				 Toast.makeText(this, "���I�����ର�šI�I�I",Toast.LENGTH_SHORT).show();
					return false;
			 }	 
		 }
		 if(curr==WhichView.CZTJ_VIEW)//�p�G��e�b�����K�[�ɭ�
		 {
			 EditText et_czmc=(EditText)findViewById(R.id.et_cztj_czmc);//�����W��
			 EditText et_czjc=(EditText)findViewById(R.id.et_cztj_czjc);//����²��
			 if(et_czmc.getText().toString().trim().contentEquals(""))
			 {
				 Toast.makeText(this, "�����W�٤��ର�šI�I�I",Toast.LENGTH_SHORT).show();
					return false;
			 }
			 if(et_czjc.getText().toString().trim().contentEquals(""))
			 {
				 Toast.makeText(this, "����²�٤��ର�šI�I�I",Toast.LENGTH_SHORT).show();
					return false;
			 }
		 }
		 if(curr==WhichView.GXTJ_VIEW)//�p�G��e�b���Y�K�[�ɭ�
		 {
			 EditText et_cm=(EditText)findViewById(R.id.et_gxtj_cm);//���W
			 EditText et_zm=(EditText)findViewById(R.id.et_gxtj_zm);//���W
			 
			 if(et_cm.getText().toString().trim().contentEquals(""))
			 {
				 Toast.makeText(this, "���W���ର�šI�I�I",Toast.LENGTH_SHORT).show();
					return false;
			 }
			 if(et_zm.getText().toString().trim().contentEquals(""))
			 {
				 Toast.makeText(this, "���W���ର�šI�I�I",Toast.LENGTH_SHORT).show();
					return false;
			 }
			 
		 }
		 return true;
	 } 
	 @Override
	  public boolean onKeyDown(int keyCode, KeyEvent e)//��L��ť
	  { 
	    	if(keyCode!=4)//�p�G���O���U����^���s�ɤ�������B�z�A������^
	    	{
	    		return false;
	    	}
	    	if(curr==WhichView.ZZCX_VIEW|| curr==WhichView.CCCX_VIEW||curr==WhichView.CZCCCX_VIEW||
	    			curr==WhichView.FJGN_VIEW)//�����d��//�����d��//�����d��//���[�\��
	    	{
	    		goToMainMenu();//��^��D���ɭ�
	    		return true;
	    	}
	    	if(curr==WhichView.CCTJ_VIEW|| curr==WhichView.CZTJ_VIEW||curr==WhichView.GXTJ_VIEW)
	    	{//�p�G�O�����K�[�B�����K�[�M���Y�K�[�ɭ�
	    		goTofjgnView();//��^����[�ɭ�
	    		return true;
	    	}	    
	    	
	    	if(curr==WhichView.MAIN_MENU)
	    	{
	    		System.exit(0);//�p�G�O�b�D��椤���U��^���s�A�h�����h�X
	    		return true;
	    	}
	    	if(curr==WhichView.PASSSTATION_VIEW)//�p�G�O�b�����Բӱ��p��ܬɭ�
	    	{
	    		goToListView(msgg);//��^��ListView�ɭ�
	    		return true;
	    	}
	    	
	    	if(curr==WhichView.LIST_VIEW)//�p�G�O�bListView�ɭ��A�h�ھڷ�e���p��^
	    	{
	    		if(flag==0)//�p�G�������d�߬ɭ�
	    		{
	    			goTozzcxView();
		    		return true;
	    		}
	    		else if(flag==1)//�p�G�O�����d�߬ɭ�
	    		{
	    			goTocccxView();
		    		return true;
	    		}
	    		else//�_�h�������d�߬ɭ�
	    		{
	    			goToczcccxView();
		    		return true;
	    		}
	    	}
	    	if(curr==WhichView.ABOUT_VIEW||curr==WhichView.HELP_VIEW)//�p�G�O����M���U�ɭ�
	    	{
	    		
	    		goToMainMenu();//��^��D���ɭ�
	    		return true;
	    	}
	    	return false;
	 }
	 
	 public void iniTLisit()//��l�ƾA�t�����ݭn���ƾڪ����
	    {
		 
		 String sql = "select Sname from station";//�d�X�Ҧ������W�r
		 
		 Vector<Vector<String>> temp= LoadUtil.query(sql);
			String[][] msgInfo=new String[temp.get(0).size()][temp.size()];
			for(int i=0;i<temp.size();i++)
			{
				for(int j=0;j<temp.elementAt(0).size();j++)
				{
					msgInfo[j][i]=(String)temp.get(i).get(j);
				}
			}
			this.s1=msgInfo[0];//�o��ӼƲ�
		 sql="select Spy from station";//�d�X�Ҧ������W�r��²��
		 
		 temp= LoadUtil.query(sql);

			msgInfo=new String[temp.elementAt(0).size()][temp.size()];
			for(int i=0;i<temp.size();i++)
			{
				for(int j=0;j<temp.elementAt(0).size();j++)
				{
					msgInfo[j][i]=(String)temp.get(i).get(j);
				}
			}
			this.s2=msgInfo[0];//�o��ӼƲ�

	    }
	 
	 public void iniTLisitarray(int id)//������ID����J�زK�[�A�t��
	 {
		 CityAdapter<String> cAdapter = new CityAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,this.s1,this.s2); //�]�m���ܮؤ����Ҧ����e  
	        AutoCompleteTextView autoView=(AutoCompleteTextView)findViewById(id);//�]�m�n�K�[���ܫH������J��
	        autoView.setAdapter(cAdapter);   //�K�[�A�t��
	        autoView.setThreshold(1);
	        autoView.setDropDownHeight(100) ;
	        autoView.setDropDownBackgroundResource(R.color.gray);
	 }

	
}

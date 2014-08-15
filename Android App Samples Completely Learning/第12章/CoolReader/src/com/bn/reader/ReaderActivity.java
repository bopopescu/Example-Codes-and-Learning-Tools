package com.bn.reader;

import static com.bn.reader.Constant.BITMAP;
import static com.bn.reader.Constant.CURRENT_LEFT_START;
import static com.bn.reader.Constant.CURRENT_PAGE;
import static com.bn.reader.Constant.PAGE_HEIGHT;
import static com.bn.reader.Constant.PAGE_WIDTH;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

//���ѩҦ�SurfaceView���`�q��
class WhatMessage{
	public static final int GOTO_WELLCOME_VIEW=0;
	public static final int GOTO_MAIN_MENU_VIEW=1;
	public static final int GOTO_SEARCHBOOK_LIST=2;
	public static final int GOTO_BACHGROUND_LIST=3;
}
//��ܦU�Ӭɭ����w�������T�|
enum WhichView {WELLCOM_VIEW,MAIN_VIEW,SEARCHBOOK_LIST,BACKGROUND_LIST }

public class ReaderActivity extends Activity {
	//�I����
	int[] drawableIds=
	{R.drawable.bg_fhzl,R.drawable.bg_lsct,R.drawable.bg_sjzx,R.drawable.bg_ssnh,R.drawable.bg_wffm,R.drawable.bg_ygmm};
	//�I���ϴy�z
	int[] msgIds={R.string.fhzl,R.string.lsct,R.string.sjzx,R.string.ssnh,R.string.wffm,R.string.ygmm};
	//��r�C��
	int[] drawColorIds={R.drawable.tc_black,R.drawable.tc_blue,R.drawable.tc_gray,R.drawable.tc_green,
			R.drawable.tc_purper,R.drawable.tc_red,R.drawable.tc_yellow,R.drawable.tc_white};
	//��r�C��y�z
	int[] msgIds2={R.string.tc_black,R.string.tc_blue,R.string.tc_gray,R.string.tc_green,
			R.string.tc_purper,R.string.tc_red,R.string.tc_yellow,R.string.tc_white};
	//�r��j�p�y�z
	int[] fontSizeIds={R.string.font_size16,R.string.font_size24,R.string.font_size32};
	//�r��Ϥ�
	int[] fontSizeDrawable={R.drawable.font_size3,R.drawable.font_size2,R.drawable.font_size1};
	
	//���֦W��
	int[] musicname={R.string.music_gs,R.string.music_mh};
	
	String sdcardPath="/sdcard";//�ڥؿ�
	String leavePath;//�l�����|
	ListView lv;//�C���ޥ�
	Button root_b;//��^�ڥؿ������s
	Button back_b;//��^��W�h�ؿ�
	
	//dialog�s��
	final int LIST_SEARCH=1;//��ѫ��s
	final int LIST_TURNPAGE=2;//�۰�½�ѫ��s
	final int LIST_SET=3;//�]�m���s
	final int LIST_BOOKMARK=4;//���ҫ��s
	
	final int NAME_INPUT_DIALOG_ID=5;//��J���ҦW�r����ܮ�
	final int SELECT_BOOKMARK=6;//��ܮ��Ҫ���ܮ�	
	final int EXIT_READER=7;//�h�X�n���ܮ�
	final int DELETE_ONE_BOOKMARK=8;//�R���@���O������ܮ�
	final int DELETE_ALL_BOOKMARK=9;//�M�ŷ�e�Ѫ��Ҧ�����
	
	final int SET_FONT_SIZE	=10;//�]�m�r��j�p��ܮ�
	final int SET_FONT_COLOR=11;//�]�m�r���C���ܮ�
	
	final int BACKGROUND_PIC=12;//�I���Ϥ�
	final int BACKGROUND_MUSIC=13;//�I�����ֹ�ܮ�

	DownLoad dl;
	WhichView curr;
	ReaderView readerView;//ReaderView���ޥ�
	WellcomeSurfaceView wellcomView;
	ListViewUtills lvutills;
	TurnPageThread turnpage;
	
	MediaPlayer mp;//�C�鼽�񾹤ޥ�
	
	SharedPreferences sp;//�P�_�O�ĴX�����}�P�@����
	
	List<BookMark> dataBaseBookMark=new ArrayList<BookMark>();//�s��Ҧ��N�n��J�u���Ҭɭ��C��v��������
    
	String[] tempname=null;//�s��q�ƾڮw�����X���Ҧ��u?�J�E�X����?
	
	int[] temppage;//�s��q�ƾڮw�����X���Ҧ���e�Ѯ��Ҫ�����
	
	String deleteOneBookMarkName=null;//�R���@���O�������ҦW��
	boolean haveBookMark=false;//�P�_�ƾڮw���O�_�s�b����
	
	Handler myHandler = new Handler(){//�B�z�U��SurfaceView�o�e������
        public void handleMessage(Message msg) {
        	switch(msg.what)
        	{
        	case WhatMessage.GOTO_WELLCOME_VIEW:
        		goToWellcomView();
        		break;
        	case WhatMessage.GOTO_MAIN_MENU_VIEW:
        		goToReaderView();
        		break;
        	case WhatMessage.GOTO_SEARCHBOOK_LIST:
        		goToSearchBooklist();
        		break;
        	case WhatMessage.GOTO_BACHGROUND_LIST:
        		goToBackground();
        		break;
        	}
        }
	};
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
         
        //�������Activity���]�m
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//�h�����D
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
        	   WindowManager.LayoutParams.FLAG_FULLSCREEN);//�h�����Y
        //���n��O�������
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//�j����        
        //�������v
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);       
        //���`�q�������̹����M�e���
        if(dm.widthPixels>dm.heightPixels)
        {
        	 Constant.SCREEN_WIDTH=dm.widthPixels;
             Constant.SCREEN_HEIGHT=dm.heightPixels;   
        }
        else
        {
        	Constant.SCREEN_WIDTH=dm.heightPixels;
            Constant.SCREEN_HEIGHT=dm.widthPixels;  
        }
        //���`�q�������۾A���̹����ܶq�]���
        Constant.changeRatio();//�եΦ۾A���̹�����k
        
        readerView=new ReaderView(this);//����ReaderView���ޥ�

        isWhichTime();//�P�_�O�ĴX�����}�n��A�ھڲĴX�����}�n��A���}���ѭ���m���P
        
        lvutills=new ListViewUtills(this);
        turnpage=new TurnPageThread(readerView);
        sendMessage(WhatMessage.GOTO_WELLCOME_VIEW);//�����D�ɭ�
    }
	@Override
	public boolean onKeyDown(int keyCode,KeyEvent e)
	{
		 switch(keyCode)
		 {
		 	case 4:
		 		//�����D�ɭ�
		 		sendMessage(WhatMessage.GOTO_MAIN_MENU_VIEW);//�����D�ɭ�
		 		break;
		   case 82:
			   super.openOptionsMenu();
			   break;   
		   }
		   return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
	   //menu����
	   //��ѫ��s
	   MenuItem search=menu.add(0, 0, 0, R.string.search);
	   search.setIcon(R.drawable.m_search);//��ѹϤ�
	   OnMenuItemClickListener searchbook=new OnMenuItemClickListener()
	   {//��{��涵�I���ƥ��ť���f
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				showDialog(LIST_SEARCH);
				return true;
			}    		
		};
		search.setOnMenuItemClickListener(searchbook);//���u��ѡv�K�[��ť��    	

	   //���ҫ��s
	   MenuItem bookMark=menu.add(0,0,0,R.string.bookmark);
	   bookMark.setIcon(R.drawable.m_bookmark);
	   OnMenuItemClickListener bookmark=new OnMenuItemClickListener()
	   {
		   @Override
		   public boolean onMenuItemClick(MenuItem item) {
			   showDialog(LIST_BOOKMARK);
			   
			   return true;
		   }	   
	   };
	   bookMark.setOnMenuItemClickListener(bookmark);

	   //½�����s
	   MenuItem turnPage=menu.add(0,0,0,R.string.turnpage);
	   turnPage.setIcon(R.drawable.m_turnpage);
	   OnMenuItemClickListener turn=new OnMenuItemClickListener()
	   {//��{��涵�I���ƥ��ť���f
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				showDialog(LIST_TURNPAGE);
				return true;
			}    		
		};
		turnPage.setOnMenuItemClickListener(turn);//���u½���v�K�[��ť��  
	    
	   //�]�m���s
	   MenuItem setUp=menu.add(0,0,0,R.string.setup);
	   setUp.setIcon(R.drawable.m_set);
	   OnMenuItemClickListener set=new OnMenuItemClickListener()
	   {//��{��涵�I���ƥ��ť���f
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				showDialog(LIST_SET);
				return true;
			}    		
		};
		setUp.setOnMenuItemClickListener(set);//���u�]�m�v�K�[��ť��    
	     return true;
	}
   
	@Override
	public Dialog onCreateDialog(int id)
	{
		Dialog dialog=null;
		switch(id)
		{
		case LIST_SEARCH://���
			Builder b=new AlertDialog.Builder(this); 
 		  	b.setIcon(R.drawable.m_search);//�]�m�ϼ�
 		  	b.setTitle(R.string.search);//�]�m���D
 		  	b.setItems(
 			 R.array.searchbook,
 			 new DialogInterface.OnClickListener()
     		 {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//�o�̥[�I���C�������e���͵��G���N�X
						switch(which)
						{
						case 0:
							searchBook();
							break;
						case 1:
							downTxt();
							break;
						}
					}      			
     		 }
 		   );
 		  	dialog=b.create();
 		  	break;
		case LIST_BOOKMARK://���ҤG�ŵ��
			b=new AlertDialog.Builder(this);
			b.setIcon(R.drawable.m_bookmark);//�]�m�ϼ�
			b.setTitle(R.string.bookmark);//�]�m�u���ҡv���D
			b.setItems(
				R.array.bookmark,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					//�o�̲K�[�I���C�������e���͵��G���N�X
						switch(which)
						{
						case 0://�K�[����
							showDialog(NAME_INPUT_DIALOG_ID);
							break;
						case 1://��ܮ���
							try
							{//�P�_�ƾڮw���O�_�s�b��e�o���Ѫ�����
								haveBookMark=SQLDBUtil.judgeHaveBookMark(Constant.FILE_PATH);
							}catch(Exception e)
							{
								e.printStackTrace();
							}
							if(haveBookMark)
							{
								//�p�G�s�b����
								showDialog(SELECT_BOOKMARK);
							}else
							{//�p�G���s�b���ҡA�XToast
								Toast.makeText
								(
									ReaderActivity.this, 
									"�Х��K�[����", 
									Toast.LENGTH_SHORT
								).show();
							}
							
							break;
						case 2://�M�Ů���
							showDialog(DELETE_ALL_BOOKMARK);							
							break;
						}
					}
				}	
			);
			dialog=b.create();
			break;
		case NAME_INPUT_DIALOG_ID://�u�X�K�[���ҹ�ܮ�
			dialog=new MyDialog(this);
			break;
		case SELECT_BOOKMARK://�u�X��ܮ��Ҫ���ܮ�
			b=new AlertDialog.Builder(this);
			b.setItems(null, null);
			dialog=b.create();
			break;
		case EXIT_READER://�h�X�n���ܮ�
			b=new AlertDialog.Builder(this);
			b.setItems(null, null);
			dialog=b.create();
			break;
		case DELETE_ONE_BOOKMARK://�R���@�����ҰO��
			b=new AlertDialog.Builder(this);
			b.setItems(null, null);
			dialog=b.create();
			break;
		case DELETE_ALL_BOOKMARK://�M�ŷ�e�o���Ѫ������O��
			b=new AlertDialog.Builder(this);
			b.setItems(null, null);
			dialog=b.create();
			break;
			
		case SET_FONT_SIZE://�r��j�p
			b=new AlertDialog.Builder(this);
			b.setItems(null, null);
			dialog=b.create();
			break;
		case BACKGROUND_MUSIC://�r���C��
			b=new AlertDialog.Builder(this);
			b.setItems(null, null);
			dialog=b.create();
			break;
		case SET_FONT_COLOR://�]�m�r���C��
			dialog=new MyDialogFontColor(this);//��xml���ۤv�G��
			break;
		case BACKGROUND_PIC://�I���Ϥ���ܮ�
			dialog=new MyDialogBackgroundPic(this);//�Φۤv�G����xml�ݤ��
			break;
		case LIST_TURNPAGE://�۰�½��
			b=new AlertDialog.Builder(this); 
 		  	b.setIcon(R.drawable.m_turnpage);//�]�m�ϼ�
 		  	b.setTitle(R.string.turnpage);//�]�m���D
 		  	b.setItems(
 			 R.array.turnpage, 
 			 new DialogInterface.OnClickListener()
     		 {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//�o�̥[�I���C�������e���͵��G���N�X
						switch(which)
						{
						case 0://30��۰�½��
							if(Constant.FILE_PATH==null)//�p�G�S�����
				    		    {
				    		    	Toast.makeText
									(
										ReaderActivity.this, 
										"�п�ܱz�n�\Ū���奻", 
										Toast.LENGTH_SHORT
									).show();
				    		    	
				    		    }else//�p�G�w�g���
				    		    {
					    		    if(turnpage.isPageflag()==false)//�p�G�u�{�S���}�l�A�h�}�l�u�{
					    		    {
					    		    	turnpage.setPageflag(true);
					    		    	turnpage.start();
					    		    }
							    	turnpage.setFiftySecond(false);
							    	turnpage.setFortySecond(false);
							    	turnpage.setThirtySecond(true);//�N30��]��true��L��false
							    	sendMessage(WhatMessage.GOTO_MAIN_MENU_VIEW);//�����D�ɭ�
				    		    }
							break;
						case 1://40��۰�½��
			    		    if(Constant.FILE_PATH==null)//�p�G�S�����
			    		    {
			    		    	Toast.makeText
								(
									ReaderActivity.this, 
									"�п�ܱz�n�\Ū���奻", 
									Toast.LENGTH_SHORT
								).show();
			    		    	
			    		    }else//�p�G�w�g���
			    		    {
			    		    	if(turnpage.isPageflag()==false)//�p�G�u�{�S���}�l�A�h�}�l�u�{
				    		    {
				    		    	turnpage.setPageflag(true);
				    		    	turnpage.start();
				    		    }
						    	turnpage.setFiftySecond(false);
						    	turnpage.setThirtySecond(false);//�N40��]��true��L��false
						    	turnpage.setFortySecond(true);
						    	sendMessage(WhatMessage.GOTO_MAIN_MENU_VIEW);//�����D�ɭ�
						    }
			    		    break;
						case 2://50��۰�½��
							if(Constant.FILE_PATH==null)//�p�G�S�����
				    		    {
				    		    	Toast.makeText
									(
										ReaderActivity.this, 
										"�п�ܱz�n�\Ū���奻", 
										Toast.LENGTH_SHORT
									).show();
				    		    	
				    		    }else//�p�G�w�g���
				    		    {
				    		    	if(turnpage.isPageflag()==false)//�p�G�u�{�S���}�l�A�h�}�l�u�{
					    		    {
					    		    	turnpage.setPageflag(true);
					    		    	turnpage.start();
					    		    }
							    	turnpage.setThirtySecond(false);//�N50��]��true��L��false
							    	turnpage.setFortySecond(false);
							    	turnpage.setFiftySecond(true);
							    	sendMessage(WhatMessage.GOTO_MAIN_MENU_VIEW);//�����D�ɭ�
				    		    }
							break;
						case 3://����۰�½��
							turnpage.setPageflag(false);//����u�{
							sendMessage(WhatMessage.GOTO_MAIN_MENU_VIEW);//�����D�ɭ�
							break;
						}
					}      			
     		 }
 		   );
 		  dialog=b.create();
 		  break;  
   	case LIST_SET://�]�m
   			b=new AlertDialog.Builder(this); 
 		  	b.setIcon(R.drawable.m_set);//�]�m�ϼ�
 		  	b.setTitle(R.string.setup);//�]�m���D
 		  	b.setItems(
 			 R.array.setup, 
 			 new DialogInterface.OnClickListener()
     		 {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//�o�̥[�I���C�������e���͵��G���N�X
						switch(which)
						{
						case 0://�I������
							showDialog(BACKGROUND_MUSIC);
							break;
						case 1://�I���Ϥ�
							showDialog(BACKGROUND_PIC);
							break;
						case 2://�r���C��
							showDialog(SET_FONT_COLOR);//�]�m�r���C��
							break;
						case 3://�r��j�p
							showDialog(SET_FONT_SIZE);//��ܦr��j�p��ܮ�
							break;
						
						}
					}    
     		 }
 		   );
 		  	dialog=b.create();
 		  	break;    
		}
		return dialog;
   }
    //�C���u�X��ܮخɳQ�^�եH�ʺA��s��ܮؤ��e����k
    @Override
    public void onPrepareDialog(int id, final Dialog dialog)
    {
    	//�Y���O���ݹ�ܮثh��^
    	switch(id)
    	{
    	   case NAME_INPUT_DIALOG_ID://�m�W��J��ܮ�
    		   //�T�w���s
    		   Button bjhmcok=(Button)dialog.findViewById(R.id.bjhmcOk);
    		   //�������s
       		   Button bjhmccancel=(Button)dialog.findViewById(R.id.bjhmcCancle);
       		   //���T�w���s�K�[��ť��
       		   bjhmcok.setOnClickListener
               (
    	          new OnClickListener()
    	          {
    				@Override
    				public void onClick(View v) 
    				{
						if(Constant.FILE_PATH==null)//�p�G�S����ܮѡA���i�H�[����
						{
							Toast.makeText(ReaderActivity.this, "�Х���ܱz�Q�n�\Ū����", Toast.LENGTH_SHORT).show();
						}else
						{
							//�����ܮظ̪����e�å�Toast���
	    					EditText et=(EditText)dialog.findViewById(R.id.etname);
	    					String name=et.getText().toString();
	    					if(name.equals(""))//�p�G���Ҭ���
	    					{
	    						Toast.makeText(ReaderActivity.this, "���ҦW�r���ର��", Toast.LENGTH_SHORT).show();
	    					}else//���Ҥ�����
	    					{
	    						try
								{
									//��e���Ҫ��u�W�r�v�M��e���Ҫ��u���ơv�s�J�ƾڮw
									SQLDBUtil.bookMarkInsert(name,Constant.CURRENT_PAGE);
								}catch(Exception e)
								{
									e.printStackTrace();
								}
	    						//������ܮ�
	    						dialog.cancel();
	    					}
						}	
    				}        	  
    	          }
    	        );   
       		    //���������s�K�[��ť��
       		    bjhmccancel.setOnClickListener
	            (
	 	          new OnClickListener()
	 	          {
	 				@Override
	 				public void onClick(View v) 
	 				{	//������ܮ�
	 					dialog.cancel();					
	 				}        	  
	 	          }
	 	        );   
    	   break;
    	   case SELECT_BOOKMARK://��ܮ��Ҫ���ܮ�
    		   try
    		   {
    			   //�b�ƾڮw�����X�Ҧ������ҰO��
    			   dataBaseBookMark=SQLDBUtil.getBookmarkList(Constant.FILE_PATH);				   
    			   int i=0;
    			   tempname=new String[dataBaseBookMark.size()];//�Ʋդj�p
    			   temppage=new int[dataBaseBookMark.size()];//�ѭ�

    			   for(BookMark dataBookMark:dataBaseBookMark)
    			   {
    				   tempname[i]=dataBookMark.bmname;//����Ҧ����Ҫ��W�r
    				   temppage[i]=dataBookMark.page;
    				   i++;					   
    			   }
    		   }catch(Exception e)
    		   {
    			   e.printStackTrace();
    		   }
    		   
			   //��ܮع������`������VLinearLayout
       		   	LinearLayout ll=new LinearLayout(ReaderActivity.this);
       			ll.setOrientation(LinearLayout.VERTICAL);		//�]�m�¦V	
       			ll.setGravity(Gravity.CENTER_HORIZONTAL);
       			ll.setBackgroundResource(R.drawable.dialog);
       			
       			//���D�檺����LinearLayout
       			LinearLayout lln=new LinearLayout(ReaderActivity.this);
       			lln.setOrientation(LinearLayout.HORIZONTAL);		//�]�m�¦V	
       			lln.setGravity(Gravity.CENTER);//�~��
       			lln.setLayoutParams(new ViewGroup.LayoutParams(200, LayoutParams.WRAP_CONTENT));
       			
       			//���D�檺��r
       			TextView tvTitle=new TextView(ReaderActivity.this);
       			tvTitle.setText(R.string.bookmark_dialog);
       			tvTitle.setTextSize(20);//�]�m�r��j�p
       			tvTitle.setTextColor(ReaderActivity .this.getResources().getColor(R.color.white));//�]�m�r���C��
       			lln.addView(tvTitle);
       			
       			//�N���D��K�[���`LinearLayout
       			ll.addView(lln);		
       		   	
       		   	//����ܮؤ������v�O�����سЫ�ListView
       		    //��l��ListView
       	        ListView lv=new ListView(this);
       	        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE); 
       	        
       	        //��ListView�ǳƤ��e�A�t��
       	        BaseAdapter ba=new BaseAdapter()
       	        {
       				@Override
       				public int getCount() {
       					return tempname.length;//�`�@�X�ӿﶵ
       				}

       				@Override
       				public Object getItem(int arg0) { return null; }

       				@Override
       				public long getItemId(int arg0) { return 0; }

       				@Override
       				public View getView(int arg0, View arg1, ViewGroup arg2) {
       					
       					LinearLayout llb=new LinearLayout(ReaderActivity.this);
						llb.setOrientation(LinearLayout.HORIZONTAL);//�]�m�¦V	
						llb.setPadding(5,5,5,5);//�]�m�|�P�d��
       					
       					//�����ҲK�[�Ϥ�
       					ImageView image=new ImageView(ReaderActivity.this);
       					image.setImageDrawable(ReaderActivity.this.getResources().getDrawable(R.drawable.sl_bookmark));
       					image.setScaleType(ImageView.ScaleType.FIT_XY);//���ӭ�Ϥ��
       					image.setLayoutParams(new Gallery.LayoutParams(30, 30));
       					llb.addView(image);
       					
       					//�ʺA�ͦ��C�����ҹ�����TextView
       					TextView tv=new TextView(ReaderActivity.this);
       					tv.setGravity(Gravity.LEFT);
       					tv.setText(tempname[arg0]+"     "+"��"+String.valueOf(temppage[arg0]+1)+"��");//�]�m���e
       					tv.setTextSize(20);//�]�m�r��j�p
       					tv.setTextColor(ReaderActivity.this.getResources().getColor(R.color.white));//�]�m�r���C��
       					tv.setPadding(0,0,0,0);//�]�m�|�P�d��
       					llb.addView(tv);
       					return llb;
       				}        	
       	        };       
       	        lv.setAdapter(ba);//��ListView�]�m���e�A�t��
       	        
       	        //�]�m�ﶵ�Q��������ť��
       	        lv.setOnItemClickListener(
       	           new OnItemClickListener()
       	           {
       				@Override
       				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
       						long arg3) {//���g�ﶵ�Q�����ƥ󪺳B�z��k
   					
   						int page=temppage[arg2];//�o��o���O������������
   						readerView.currRR=readerView.currBook.get(page);//�bhashMap����������readerView.currRR�ﹳ
   						Constant.CURRENT_LEFT_START=readerView.currRR.leftStart;//�O����eŪ��Bleftstart����
   						Constant.CURRENT_PAGE=readerView.currRR.pageNo;//�O����eŪ��B��page����
   						
   						//ø�s���k��T�Ϥ�
   						readerView.currRR.isLeft=true;
   						readerView.bmLeft=readerView.drawPage(readerView.currRR);
   						readerView.bmRight=readerView.drawPage(readerView.currRR);
   						readerView.repaint();
       					
       					dialog.cancel();//������ܮ�
       				}        	   
       	           }
       	        );
       	        //�N���v�O������ListView�[�J�`LinearLayout
	       	    ll.addView(lv);

	       	    lv.setOnItemLongClickListener(
	       	    		new OnItemLongClickListener()
	       	    		{
							@Override
							public boolean onItemLongClick(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								//�ھڷ�e�����Ҫ��W�r�A�����������Ҫ�����,�R���o���O��
								deleteOneBookMarkName=tempname[arg2];
								try
								{
									showDialog(DELETE_ONE_BOOKMARK);
									
								}catch(Exception e)
								{
									e.printStackTrace();
								}
								dialog.cancel();//������ܮ�
					
								return true;
							}
	       	    		}
	       	    );
	       	     dialog.setContentView(ll); 
    		   break;
    	   case EXIT_READER://�h�X��ܮ�
    		   
    		   //��ܮع������`������VLinearLayout
      		   	LinearLayout lle=new LinearLayout(ReaderActivity.this);
      			lle.setOrientation(LinearLayout.VERTICAL);		//�]�m�¦V	
      			lle.setGravity(Gravity.CENTER_HORIZONTAL);
      			lle.setBackgroundResource(R.drawable.dialog);
      			
      			//���D�檺����LinearLayout
      			LinearLayout llt=new LinearLayout(ReaderActivity.this);
      			llt.setOrientation(LinearLayout.HORIZONTAL);		//�]�m�¦V	
      			llt.setGravity(Gravity.CENTER);//�~��
      			llt.setLayoutParams(new ViewGroup.LayoutParams(200, LayoutParams.WRAP_CONTENT));
      			
      			//���D�檺��r
      			TextView eTitle=new TextView(ReaderActivity.this);
      			eTitle.setText(R.string.exit_bookmark);
      			eTitle.setTextSize(20);//�]�m�r��j�p
      			eTitle.setTextColor(ReaderActivity .this.getResources().getColor(R.color.white));//�]�m�r���C��
      			llt.addView(eTitle);
      			
      			//�N���D��K�[���`LinearLayout
      			lle.addView(llt);
      			
      			LinearLayout lleb=new LinearLayout(ReaderActivity.this);
      			lleb.setOrientation(LinearLayout.HORIZONTAL);//������V
      			lleb.setLayoutParams(new ViewGroup.LayoutParams(200, LayoutParams.WRAP_CONTENT));
      			lleb.setGravity(Gravity.CENTER);

	       	    //�T�w���s
	       	    Button eok=new Button(ReaderActivity.this);//�M�Ů��ҫ��s
	       	    eok.setText(R.string.ok);//�]�m�u���s�v���W�r
	       	    eok.setTextSize(18);//�]�m�r��j�p
	       	    eok.setWidth(100);
	       	    eok.setHeight(20);
	       	    eok.setGravity(Gravity.CENTER);
	       	    eok.setOnClickListener(
	       	    		 new OnClickListener()
	       	    		 {
							public void onClick(View v) {
								dialog.cancel();//������ܮ�
								savePreference();//��e�ѭ��h�X��,�O�s�{��
								saveCurrentData();//��ehashMap���H���s�J�ƾڮw
								System.exit(0);	
							} 
	       	    		 } 
	       	     );
	       	    lleb.addView(eok);//�[�J��linearLayout��
	       	     //�������s
	       	    Button eCancel=new Button(ReaderActivity.this);
	       	    eCancel.setText(R.string.cancel);//�]�m���s���W�r
	       	    eCancel.setTextSize(18);
	       	    eCancel.setWidth(100);
	       	    eCancel.setHeight(20);
	       	    eCancel.setGravity(Gravity.CENTER);
	            eCancel.setOnClickListener(
	      	    		 new OnClickListener()
	       	    		 {
							public void onClick(View v) {
								dialog.cancel();//������ܮ�
							}
	       	    		 }
	       	     );
	       	    lleb.addView(eCancel);
	       	    lle.addView(lleb);
	       	    dialog.setContentView(lle);
    		   break;
    	   case DELETE_ONE_BOOKMARK://�R���@�����ҰO����ܮ�
    		   
    		   //��ܮع������`������VLinearLayout
     		   	LinearLayout lld=new LinearLayout(ReaderActivity.this);
     			lld.setOrientation(LinearLayout.VERTICAL);		//�]�m�¦V	
     			lld.setGravity(Gravity.CENTER_HORIZONTAL);
     			lld.setBackgroundResource(R.drawable.dialog);
     			
     			//���D�檺����LinearLayout
     			LinearLayout lldt=new LinearLayout(ReaderActivity.this);
     			lldt.setOrientation(LinearLayout.HORIZONTAL);		//�]�m�¦V	
     			lldt.setGravity(Gravity.CENTER);//�~��
     			lldt.setLayoutParams(new ViewGroup.LayoutParams(240, LayoutParams.WRAP_CONTENT));
     			
     			//���D�檺��r
     			TextView deTitle=new TextView(ReaderActivity.this);
     			deTitle.setText(R.string.delete_onebookmark);
     			deTitle.setTextSize(20);//�]�m�r��j�p
     			deTitle.setTextColor(ReaderActivity .this.getResources().getColor(R.color.white));//�]�m�r���C��
     			lldt.addView(deTitle);
     			
     			//�N���D��K�[���`LinearLayout
     			lld.addView(lldt);
     			
     			LinearLayout lldeb=new LinearLayout(ReaderActivity.this);
     			lldeb.setOrientation(LinearLayout.HORIZONTAL);//������V
     			lldeb.setLayoutParams(new ViewGroup.LayoutParams(200, LayoutParams.WRAP_CONTENT));
     			lldeb.setGravity(Gravity.CENTER);

	       	    //�T�w���s
	       	    Button deok=new Button(ReaderActivity.this);//�M�Ů��ҫ��s
	       	    deok.setText(R.string.ok);//�]�m�u���s�v���W�r
	       	    deok.setTextSize(18);//�]�m�r��j�p
	       	    deok.setWidth(100);
	       	    deok.setHeight(20);
	       	    deok.setGravity(Gravity.CENTER);
	       	    deok.setOnClickListener(
	       	    		 new OnClickListener()
	       	    		 {
							public void onClick(View v) {	
								try
								{//�ƾڮw���R���@�����ҰO��
									SQLDBUtil.deleteOneBookMark(deleteOneBookMarkName);
								}catch(Exception e)
								{
									e.printStackTrace();
								}
								dialog.cancel();//������ܮ�
							} 
	       	    		 } 
	       	     );
	       	    lldeb.addView(deok);//�[�J��linearLayout��
	       	     //�������s
	       	    Button deCancel=new Button(ReaderActivity.this);
	       	    deCancel.setText(R.string.cancel);//�]�m���s���W�r
	       	    deCancel.setTextSize(18);
	       	    deCancel.setWidth(100);
	       	    deCancel.setHeight(20);
	       	    deCancel.setGravity(Gravity.CENTER);
	            deCancel.setOnClickListener(
	      	    		 new OnClickListener()
	       	    		 {
							public void onClick(View v) {
								dialog.cancel();//������ܮ�
								
								showDialog(SELECT_BOOKMARK);//��ܿ�ܮ��Ҫ���ܮ�
									
							}
	       	    		 }
	       	     );
	            lldeb.addView(deCancel);
	       	    lld.addView(lldeb);
	       	    dialog.setContentView(lld);
   		   break;
    	   case DELETE_ALL_BOOKMARK:
    		   
    		 //��ܮع������`������VLinearLayout
    		   	LinearLayout lla=new LinearLayout(ReaderActivity.this);
    			lla.setOrientation(LinearLayout.VERTICAL);		//�]�m�¦V	
    			lla.setGravity(Gravity.CENTER_HORIZONTAL);
    			lla.setBackgroundResource(R.drawable.dialog);
    			
    			//���D�檺����LinearLayout
    			LinearLayout llat=new LinearLayout(ReaderActivity.this);
    			llat.setOrientation(LinearLayout.HORIZONTAL);		//�]�m�¦V	
    			llat.setGravity(Gravity.CENTER);//�~��
    			llat.setLayoutParams(new ViewGroup.LayoutParams(200, LayoutParams.WRAP_CONTENT));
    			
    			//���D�檺��r
    			TextView deaTitle=new TextView(ReaderActivity.this);
    			deaTitle.setText(R.string.delete_allbookmark);
    			deaTitle.setTextSize(20);//�]�m�r��j�p
    			deaTitle.setTextColor(ReaderActivity .this.getResources().getColor(R.color.white));//�]�m�r���C��
    			llat.addView(deaTitle);
    			
    			//�N���D��K�[���`LinearLayout
    			lla.addView(llat);
    			
    			LinearLayout lldeab=new LinearLayout(ReaderActivity.this);
    			lldeab.setOrientation(LinearLayout.HORIZONTAL);//������V
    			lldeab.setLayoutParams(new ViewGroup.LayoutParams(200, LayoutParams.WRAP_CONTENT));
    			lldeab.setGravity(Gravity.CENTER);

	       	    //�T�w���s
	       	    Button deaok=new Button(ReaderActivity.this);//�M�Ů��ҫ��s
	       	    deaok.setText(R.string.ok);//�]�m�u���s�v���W�r
	       	    deaok.setTextSize(18);//�]�m�r��j�p
	       	    deaok.setWidth(100);
	       	    deaok.setHeight(20);
	       	    deaok.setGravity(Gravity.CENTER);
	       	    deaok.setOnClickListener(
	       	    		 new OnClickListener()
	       	    		 {
							public void onClick(View v) {	
						
								try
								{//�M�ŷ�e�o���Ѫ���������
									SQLDBUtil.deleteAllBookMark(Constant.FILE_PATH);
								}catch(Exception e)
								{
									e.printStackTrace();
								}

								dialog.cancel();//������ܮ�
							} 
	       	    		 } 
	       	     );
	       	    lldeab.addView(deaok);//�[�J��linearLayout��
	       	     //�������s
	       	    Button deaCancel=new Button(ReaderActivity.this);
	       	    deaCancel.setText(R.string.cancel);//�]�m���s���W�r
	       	    deaCancel.setTextSize(18);
	       	    deaCancel.setWidth(100);
	       	    deaCancel.setHeight(20);
	       	    deaCancel.setGravity(Gravity.CENTER);
	            deaCancel.setOnClickListener(
	      	    		 new OnClickListener()
	       	    		 {
							public void onClick(View v) {
								dialog.cancel();//������ܮ�
							}
	       	    		 }
	       	     );
	            lldeab.addView(deaCancel);
	       	    lla.addView(lldeab);
	       	    dialog.setContentView(lla);
    		   break;
    	   case BACKGROUND_MUSIC://�G���I�����ֹ�ܮ�
    		   setBackgroundMusicDialog(dialog);
    		   break;
    	   case BACKGROUND_PIC://�I���Ϥ�
    		   setBackgroundPic(dialog);
    		   break;
    	   case SET_FONT_SIZE://�ۤv�G���r��j�p��ܮ�
    		   setFontSize(dialog);//�ե�setFontSize��k��ܧG����dialog
    		   break;
    	   case SET_FONT_COLOR://�G���r�骺�C��
    		   setFontColor(dialog);//�G���r�骺�C��
    		   break;
    		   
    	}
    	
    }

	//�d��ѥ�
	public void searchBook()
	{

		goToSearchBooklist();
		
		lv=(ListView)ReaderActivity.this.findViewById(R.id.flist);//���ListView����ﹳ
		root_b=(Button)findViewById(R.id.Button01);
	    back_b=(Button)findViewById(R.id.Button02);
		final File[] files=lvutills.getFiles(sdcardPath);//����ڸ`�I���C�� 
		lvutills.intoListView(files,lv);//�N�U�Ӥ��K�[��ListView�C��
		root_b.setOnClickListener(
				//OnClickListener��View���������f�A���{�̭t�d��ť�����I���ƥ�
	           new View.OnClickListener(){ 
	        	   public void onClick(View v){
	        		   lvutills.intoListView(files,lv);
	        	   }}); 
		back_b.setOnClickListener(
				new View.OnClickListener(){
					@Override
					public void onClick(View v) {
						if(lvutills.currentPath.equals("/sdcard"))
						{
							Toast.makeText
							(
								ReaderActivity.this,
								"�w�g��ڥؿ��F", 
								Toast.LENGTH_SHORT
							).show();
						}else
						{
								File cf=new File(lvutills.currentPath);//�����e���C�����|���������
								cf=cf.getParentFile();//������ؿ����
								lvutills.currentPath=cf.getPath();//�O����e���C����|
								lvutills.intoListView(lvutills.getFiles(lvutills.currentPath),lv);	
						}
						
					}});
	}

	public void setBackgroundMusicDialog(final Dialog dialog)
	{
		//��ܮع������`������VLinearLayout
	   	LinearLayout ll=new LinearLayout(ReaderActivity.this);
		ll.setOrientation(LinearLayout.VERTICAL);		//�]�m�¦V	
		ll.setGravity(Gravity.CENTER_HORIZONTAL);
		ll.setBackgroundResource(R.drawable.dialog);
		
		//���D�檺����LinearLayout
		LinearLayout lln=new LinearLayout(ReaderActivity.this);
		lln.setOrientation(LinearLayout.HORIZONTAL);		//�]�m�¦V	
		lln.setGravity(Gravity.CENTER);//�~��
		lln.setLayoutParams(new ViewGroup.LayoutParams(200, LayoutParams.WRAP_CONTENT));
		
		//���D�檺��r
		TextView tvTitle=new TextView(ReaderActivity.this);
		tvTitle.setText(R.string.music_name);
		tvTitle.setTextSize(20);//�]�m�r��j�p
		tvTitle.setTextColor(ReaderActivity .this.getResources().getColor(R.color.white));//�]�m�r���C��
		lln.addView(tvTitle);
		
		//�N���D��K�[���`LinearLayout
		ll.addView(lln);		
	   	
	   	//����ܮؤ������v�O�����سЫ�ListView
	    //��l��ListView
        ListView lv=new ListView(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE); 
        
        //��ListView�ǳƤ��e�A�t��
        BaseAdapter ba=new BaseAdapter()
        {
			@Override
			public int getCount() {
				return musicname.length;//�`�@�X�ӿﶵ
			}

			@Override
			public Object getItem(int arg0) { return null; }

			@Override
			public long getItemId(int arg0) { return 0; }

			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				
			LinearLayout llb=new LinearLayout(ReaderActivity.this);
			llb.setOrientation(LinearLayout.HORIZONTAL);//�]�m�¦V	
			llb.setPadding(5,5,5,5);//�]�m�|�P�d��
				
				//�����ҲK�[�Ϥ�
				ImageView image=new ImageView(ReaderActivity.this);
				image.setImageDrawable(ReaderActivity.this.getResources().getDrawable(R.drawable.sl_music));//�]�w�Ϥ�
				image.setScaleType(ImageView.ScaleType.FIT_XY);//���ӭ�Ϥ��
				image.setLayoutParams(new Gallery.LayoutParams(30, 30));
				llb.addView(image);
				
				//�ʺA�ͦ��C�����ҹ�����TextView
				TextView tv=new TextView(ReaderActivity.this);
				tv.setGravity(Gravity.LEFT);
				tv.setText(ReaderActivity.this.getResources().getString(musicname[arg0]));//�]�m���e
				tv.setTextSize(20);//�]�m�r��j�p
				tv.setTextColor(ReaderActivity.this.getResources().getColor(R.color.white));//�]�m�r���C��
				tv.setPadding(12,0,0,0);//�]�m�|�P�d��
				llb.addView(tv);
				return llb;
			}        	
        };       
        lv.setAdapter(ba);//��ListView�]�m���e�A�t��
        
        //�]�m�ﶵ�Q��������ť��
        lv.setOnItemClickListener(
           new OnItemClickListener()
           {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {//���g�ﶵ�Q�����ƥ󪺳B�z��k
				//�ھڷ�e�����Ҫ��W�r�A�����������Ҫ������A�ھڭ����T�w�����쨺�@��

				switch(arg2)
				{
					case 0:
						if(ReaderActivity.this.mp==null||Constant.I==R.raw.mh)
						{
							Constant.I=R.raw.gsls;
							ReaderActivity.this.mp=MediaPlayer.create(ReaderActivity.this, R.raw.gsls);
							ReaderActivity.this.mp.setLooping(true);
							ReaderActivity.this.mp.start();
							
						}else{
							ReaderActivity.this.mp.release();
							ReaderActivity.this.mp=null;
						}
						
						ReaderActivity.this.goToReaderView();
						
						break;
					case 1:

						if(ReaderActivity.this.mp==null||Constant.I==R.raw.gsls)
						{  	
							Constant.I=R.raw.mh;
							ReaderActivity.this.mp=MediaPlayer.create(ReaderActivity.this, R.raw.mh);
							ReaderActivity.this.mp.setLooping(true);
							ReaderActivity.this.mp.start();
						}else{
							ReaderActivity.this.mp.release();
							ReaderActivity.this.mp=null;
							}
						
						ReaderActivity.this.goToReaderView();
						
						break;
				}
				dialog.cancel();//������ܮ�

				//��l�ƨ��e����X��
				readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

				//ø�s���k��T�Ϥ�
				readerView.bmLeft=readerView.drawPage(readerView.currRR);
				readerView.bmRight=readerView.drawPage(readerView.currRR);
				readerView.repaint();
			}        	   
           }
        );
        //�N���v�O������ListView�[�J�`LinearLayout
        ll.addView(lv);
        dialog.setContentView(ll); 	
		
	}
	//�]�m�r���C��
	public void setFontSize(final Dialog dialog)
	{
		//��ܮع������`������VLinearLayout
	   	LinearLayout ll=new LinearLayout(ReaderActivity.this);
		ll.setOrientation(LinearLayout.VERTICAL);		//�]�m�¦V	
		ll.setGravity(Gravity.CENTER_HORIZONTAL);
		ll.setBackgroundResource(R.drawable.dialog);
		
		//���D�檺����LinearLayout
		LinearLayout lln=new LinearLayout(ReaderActivity.this);
		lln.setOrientation(LinearLayout.HORIZONTAL);		//�]�m�¦V	
		lln.setGravity(Gravity.CENTER);//�~��
		lln.setLayoutParams(new ViewGroup.LayoutParams(200, LayoutParams.WRAP_CONTENT));
		
		//���D�檺��r
		TextView tvTitle=new TextView(ReaderActivity.this);
		tvTitle.setText(R.string.font_size);
		tvTitle.setTextSize(20);//�]�m�r��j�p
		tvTitle.setTextColor(ReaderActivity .this.getResources().getColor(R.color.white));//�]�m�r���C��
		lln.addView(tvTitle);
		
		//�N���D��K�[���`LinearLayout
		ll.addView(lln);		
	   	
	   	//����ܮؤ������v�O�����سЫ�ListView
	    //��l��ListView
        ListView lv=new ListView(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE); 
        
        //��ListView�ǳƤ��e�A�t��
        BaseAdapter ba=new BaseAdapter()
        {
			@Override
			public int getCount() {
				return fontSizeIds.length;//�`�@�X�ӿﶵ
			}

			@Override
			public Object getItem(int arg0) { return null; }

			@Override
			public long getItemId(int arg0) { return 0; }

			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				
			LinearLayout llb=new LinearLayout(ReaderActivity.this);
			llb.setOrientation(LinearLayout.HORIZONTAL);//�]�m�¦V	
			llb.setPadding(5,5,5,5);//�]�m�|�P�d��
				
				//�����ҲK�[�Ϥ�
				ImageView image=new ImageView(ReaderActivity.this);
				image.setImageDrawable(ReaderActivity.this.getResources().getDrawable(fontSizeDrawable[arg0]));//�]�w�Ϥ�
				image.setScaleType(ImageView.ScaleType.FIT_XY);//���ӭ�Ϥ��
				image.setLayoutParams(new Gallery.LayoutParams(30, 30));
				llb.addView(image);
				
				//�ʺA�ͦ��C�����ҹ�����TextView
				TextView tv=new TextView(ReaderActivity.this);
				tv.setGravity(Gravity.LEFT);
				tv.setText(ReaderActivity.this.getResources().getString(fontSizeIds[arg0]));//�]�m���e
				tv.setTextSize(20);//�]�m�r��j�p
				tv.setTextColor(ReaderActivity.this.getResources().getColor(R.color.white));//�]�m�r���C��
				tv.setPadding(0,0,0,0);//�]�m�|�P�d��
				llb.addView(tv);
				return llb;
			}        	
        };       
        lv.setAdapter(ba);//��ListView�]�m���e�A�t��
        
        //�]�m�ﶵ�Q��������ť��
        lv.setOnItemClickListener(
           new OnItemClickListener()
           {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {//���g�ﶵ�Q�����ƥ󪺳B�z��k
				
				switch(arg2)
				{
					case 0:
						if(Constant.TEXT_SIZE!=16)//�p�G��e�r��j�p������n���쪺�r��j�p
						{
							//�ܴ��r��j�p
							Constant.TEXT_SIZE=16;
							Constant.TEXT_SPACE_BETWEEN_CN=16;
							Constant.TEXT_SPACE_BETWEEN_EN=8;							
							//���`�q�������۾A���̹����ܶq���s���
					        Constant.changeRatio();
							
					        updataBookMarkAndHashMap();//��shashMap						
						}else//�p�G�۵�
						{
							//�����ܴ�
						}
						
						break;
					case 1:
						if(Constant.TEXT_SIZE!=24)//�p�G��e�r��j�p������n���쪺�r��j�p
						{
							//�ܴ��r��j�p
							Constant.TEXT_SIZE=24;
							Constant.TEXT_SPACE_BETWEEN_CN=24;
							Constant.TEXT_SPACE_BETWEEN_EN=12;						
							//���`�q�������۾A���̹����ܶq���s���
					        Constant.changeRatio();
							
					        updataBookMarkAndHashMap();//��shashMap						
						}else//�p�G�۵�
						{
							//�����ܴ�
						}
						break;
					case 2:
						if(Constant.TEXT_SIZE!=32)//�p�G��e�r��j�p������n���쪺�r��j�p
						{
							//�ܴ��r��j�p
							Constant.TEXT_SIZE=32;
							Constant.TEXT_SPACE_BETWEEN_CN=32;
							Constant.TEXT_SPACE_BETWEEN_EN=16;
							
							//���`�q�������۾A���̹����ܶq���s���
					        Constant.changeRatio();
							
					        updataBookMarkAndHashMap();//��shashMap
					
						}else//�p�G�۵�
						{
							//�����ܴ�
						}
						break;
				}
				dialog.cancel();//������ܮ�


				//��l�ƨ��e����X��
				readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

				//ø�s���k��T�Ϥ�
				readerView.bmLeft=readerView.drawPage(readerView.currRR);
				readerView.bmRight=readerView.drawPage(readerView.currRR);
				readerView.repaint();
			}        	   
           }
        );
        //�N���v�O������ListView�[�J�`LinearLayout
        ll.addView(lv);
        dialog.setContentView(ll); 
	
	}
	
	//�I���Ϥ�
	public void setBackgroundPic(final Dialog dialog)
	{
		Button bg_fhzl=(Button)dialog.findViewById(R.id.bg_fhzl);
		Button bg_lsct=(Button)dialog.findViewById(R.id.bg_lsct);
		
		Button bg_sjzx=(Button)dialog.findViewById(R.id.bg_sjzx);
		Button bg_ssnh=(Button)dialog.findViewById(R.id.bg_ssnh);
		
		Button bg_wffm=(Button)dialog.findViewById(R.id.bg_wffm);
		Button bg_ygmm=(Button)dialog.findViewById(R.id.bg_ygmm);
		
		
		
		bg_fhzl.setOnClickListener
        (
	          new OnClickListener()
	          {
				@Override
				public void onClick(View v) 
				{
					Constant.BITMAP=drawableIds[0];
					
					readerView.bmBack=PicLoadUtil.LoadBitmap(readerView.getResources(), BITMAP);//�۾A���̹����I���Ϥ�
					readerView.bmBack=PicLoadUtil.scaleToFit(readerView.bmBack, PAGE_WIDTH, PAGE_HEIGHT);
					
					//��l�ƨ��e����X��
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);
					//ø�s���k��T�Ϥ�
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();
 					dialog.cancel();
 						
				}        	  
	          }
	        );   
		
		bg_lsct.setOnClickListener
         (
	          new OnClickListener()
	          {
				@Override
				public void onClick(View v) 
				{	
					Constant.BITMAP=drawableIds[1];
					
					readerView.bmBack=PicLoadUtil.LoadBitmap(readerView.getResources(), BITMAP);//�۾A���̹����I���Ϥ�
					readerView.bmBack=PicLoadUtil.scaleToFit(readerView.bmBack, PAGE_WIDTH, PAGE_HEIGHT);
					
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//ø�s���k��T�Ϥ�
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();
					//������ܮ�
					dialog.cancel();					
				}        	  
	          }
	        );   
		
		bg_sjzx.setOnClickListener
        (
	          new OnClickListener()
	          {
				@Override
				public void onClick(View v) 
				{
					Constant.BITMAP=drawableIds[2];
					
					readerView.bmBack=PicLoadUtil.LoadBitmap(readerView.getResources(), BITMAP);//�۾A���̹����I���Ϥ�
					readerView.bmBack=PicLoadUtil.scaleToFit(readerView.bmBack, PAGE_WIDTH, PAGE_HEIGHT);
					
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//ø�s���k��T�Ϥ�
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();
 					dialog.cancel();
 						
				}        	  
	          }
	        );   
		
		bg_ssnh.setOnClickListener
         (
	          new OnClickListener()
	          {
				@Override
				public void onClick(View v) 
				{
					Constant.BITMAP=drawableIds[3];
					
					readerView.bmBack=PicLoadUtil.LoadBitmap(readerView.getResources(), BITMAP);//�۾A���̹����I���Ϥ�
					readerView.bmBack=PicLoadUtil.scaleToFit(readerView.bmBack, PAGE_WIDTH, PAGE_HEIGHT);
					
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//ø�s���k��T�Ϥ�
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();
					//������ܮ�
					dialog.cancel();					
				}        	  
	          }
	        );   
		
		bg_wffm.setOnClickListener
        (
	          new OnClickListener()
	          {
				@Override
				public void onClick(View v) 
				{
					Constant.BITMAP=drawableIds[4];
					
					readerView.bmBack=PicLoadUtil.LoadBitmap(readerView.getResources(), BITMAP);//�۾A���̹����I���Ϥ�
					readerView.bmBack=PicLoadUtil.scaleToFit(readerView.bmBack, PAGE_WIDTH, PAGE_HEIGHT);
					
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//ø�s���k��T�Ϥ�
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();
 					dialog.cancel();
 						
				}        	  
	          }
	        );   
		
		bg_ygmm.setOnClickListener
         (
	          new OnClickListener()
	          {
				@Override
				public void onClick(View v) 
				{

					Constant.BITMAP=drawableIds[5];

					readerView.bmBack=PicLoadUtil.LoadBitmap(readerView.getResources(), BITMAP);//�۾A���̹����I���Ϥ�
					readerView.bmBack=PicLoadUtil.scaleToFit(readerView.bmBack, PAGE_WIDTH, PAGE_HEIGHT);

					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//ø�s���k��T�Ϥ�
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();
					//������ܮ�
					dialog.cancel();					
				}        	  
	          }
	        );   	
	}
	
	
	
	//�]�m�r���C��
	public void setFontColor(final Dialog dialog)
	{
		Button black=(Button)dialog.findViewById(R.id.tc_black);
		Button blue=(Button)dialog.findViewById(R.id.tc_blue);
		
		Button gray=(Button)dialog.findViewById(R.id.tc_gray);
		Button green=(Button)dialog.findViewById(R.id.tc_green);
		
		Button purper=(Button)dialog.findViewById(R.id.tc_purper);
		Button red=(Button)dialog.findViewById(R.id.tc_red);
		
		Button white=(Button)dialog.findViewById(R.id.tc_white);
		Button yellow=(Button)dialog.findViewById(R.id.tc_yellow);
		black.setOnClickListener
        (
	          new OnClickListener()
	          {
				@Override
				public void onClick(View v) 
				{
					Constant.COLOR=0xff000000;
					//��l�ƨ��e����X��
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//ø�s���k��T�Ϥ�
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();
 					dialog.cancel();
 						
				}        	  
	          }
	        );   
		
		blue.setOnClickListener
         (
	          new OnClickListener()
	          {
				@Override
				public void onClick(View v) 
				{
					Constant.COLOR=0xff0000ff;
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//ø�s���k��T�Ϥ�
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();
					
					//������ܮ�
					dialog.cancel();					
				}        	  
	          }
	        );   
		
		gray.setOnClickListener
        (
	          new OnClickListener()
	          {
				@Override
				public void onClick(View v) 
				{
					Constant.COLOR=0xff5b5b5b;
					
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//ø�s���k��T�Ϥ�
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();

 						dialog.cancel();
 						
				}        	  
	          }
	        );   
		
		green.setOnClickListener
         (
	          new OnClickListener()
	          {
				@Override
				public void onClick(View v) 
				{
					Constant.COLOR=0xff00ff00;
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//ø�s���k��T�Ϥ�
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();

					//������ܮ�
					dialog.cancel();					
				}        	  
	          }
	        );   
		
		purper.setOnClickListener
        (
	          new OnClickListener()
	          {
				@Override
				public void onClick(View v) 
				{
					Constant.COLOR=0xff930093;
					
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//ø�s���k��T�Ϥ�
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();

 						dialog.cancel();
 						
				}        	  
	          }
	        );   
		
		red.setOnClickListener
         (
	          new OnClickListener()
	          {
				@Override
				public void onClick(View v) 
				{
					Constant.COLOR=0xffff0000;
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//ø�s���k��T�Ϥ�
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();

					//������ܮ�
					dialog.cancel();					
				}        	  
	          }
	        );   
		
		yellow.setOnClickListener
        (
	          new OnClickListener()
	          {
				@Override
				public void onClick(View v) 
				{
					Constant.COLOR=0xffffff00;
					
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//ø�s���k��T�Ϥ�
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();

 						dialog.cancel();
				}        	  
	          }
	        );   
		
		white.setOnClickListener
         (
	          new OnClickListener()
	          {
				@Override
				public void onClick(View v) 
				{
					Constant.COLOR=0xffffffff;
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//ø�s���k��T�Ϥ�
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();

					//������ܮ�
					dialog.cancel();					
				}        	  
	          }
	        );   
	}
	
	
	public void downTxt()
	{
		dl=new DownLoad("txt_list.txt",ReaderActivity.this);
		dl.lv.setOnItemClickListener(
				new OnItemClickListener(){
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {//���g�ﶵ�Q�����ƥ󪺳B�z��k
						String s=dl.txtName[arg2*2+1];
						dl.downFile(Constant.ADD_PRE+s,"/",s);
					} });
	}
	//�VHandler�o�e�H������k
    public void sendMessage(int what)
    {
    	Message msg = myHandler.obtainMessage(what); 
    	myHandler.sendMessage(msg);
    }  
    //�V�U�Ӭɭ����઺��k
	public void goToSearchBooklist()
    {
    	setContentView(R.layout.searchbook_list);
    	curr=WhichView.SEARCHBOOK_LIST;
    }
	
	public void goToReaderView()
	{
		if(readerView==null)
    	{
			readerView=new ReaderView(this);
    	}
    	setContentView(readerView);
    	
    	readerView.requestFocus();
    	readerView.setFocusableInTouchMode(true);
    	
    	readerView.requestFocus();
    	readerView.setFocusableInTouchMode(true);
    	
    	curr=WhichView.MAIN_VIEW;
	}
	public void goToBackground()
	{
		setContentView(R.layout.background_list);
    	curr=WhichView.BACKGROUND_LIST;
	}
	public void goToWellcomView()//�i�J"�ʯǬ��"�ɭ�
	{
		if(wellcomView==null)
    	{
			wellcomView=new WellcomeSurfaceView(this);
    	}
		setContentView(wellcomView);
		curr=WhichView.WELLCOM_VIEW;
	}
	public void isWhichTime()//�P�_�O�ĴX�����}�n��
	{
		sp=this.getSharedPreferences("read", Context.MODE_PRIVATE);//�]���Ҧ����p���Ҧ�
		
		String isOneTime=sp.getString("isOneTime", null);//�P�_�O���O�Ĥ@�����}�n��
        String lastTimePath=sp.getString("path", null);//�o��W�@���X�ݪ��Ѫ����|
        String lastTimePage=sp.getString("page", null);//�o��W�@���X�ݮѪ��ѭ�
        String lastTimeName=sp.getString("name", null);//�o��W�@���X�ݮѪ��W�r
        String lastTimeFontSize=sp.getString("fontsize", null);//�o��W�@���X�ݮѪ��r��j�p
        if(lastTimePath==null)//�p�G�O�S����L��(�]�A�b�����ɭ��h�X�{�ǩM�Ĥ@�����}�n��)
        {
        	Constant.FILE_PATH=null;//��e���|����
        	Constant.CURRENT_LEFT_START=0;//��e�ѭ����W�誺���ޭȬ�0
        	Constant.CURRENT_PAGE=0;//��e�ѭ���0	
        	
        	if(isOneTime==null)//�p�G�O�Ĥ@�����}�n��
        	{//�ϥ��q�{�r��j�p�A�L�ʧ@
        	}else//�p�G���g�b�����ɭ��h�X�L�n��
        	{
        		//�T�w�r��j�p
        		Constant.TEXT_SIZE=Integer.parseInt(lastTimeFontSize);//�o��W�@���b�����ɭ����r��j�p
        		Constant.TEXT_SPACE_BETWEEN_CN=Constant.TEXT_SIZE;
        		Constant.TEXT_SPACE_BETWEEN_EN=Constant.TEXT_SIZE/2;
        		//���`�q�������U�ӱ`�q���s���
                Constant.changeRatio();//�եΦ۾A���̹�����k
        	}        	
        	
        }else//�_�h�A����W�@�����}�Ѫ��u���|�v�P�u���ơv
        {
        	//�T�w�r�骺�j�p
        	Constant.TEXT_SIZE=Integer.parseInt(lastTimeFontSize);//�o��W�@���r��j�p
    		Constant.TEXT_SPACE_BETWEEN_CN=Constant.TEXT_SIZE;
    		Constant.TEXT_SPACE_BETWEEN_EN=Constant.TEXT_SIZE/2;
    		//���`�q�������U�ӱ`�q���s���
            Constant.changeRatio();//�եΦ۾A���̹�����k
        	//*****************************�T�w�r��j�p����******************************************
        	Constant.TEXTNAME=lastTimeName;//����W�@�����}��󪺮ѦW
        	
        	Constant.FILE_PATH=lastTimePath;//����W�@�����}��󪺸��|
        	
        	//�ե�getCharacterCount��k�p��峹���r�Ū��ס]����Ū��̫�@���i�H�~��V�UŪ�^
        	Constant.CONTENTCOUNT=TextLoadUtil.getCharacterCount(Constant.FILE_PATH);  
        	
        	
        	int page=Integer.parseInt(lastTimePage);//�o��Ѫ����ơA��Ƭ�int��
        	try{
        		//�ھڷ�e���|�d��ƾڮw��������hashMap��byte���ƾ�
        		byte[] data=SQLDBUtil.selectRecordData(Constant.FILE_PATH);
        		//��readerView����hashMap
        		readerView.currBook=SQLDBUtil.fromBytesToListRowNodeList(data);//�Nbyte���ƾ���Ƭ�hashMap�����ƾ�      		
        		readerView.currRR=readerView.currBook.get(page);//�ھ�hashMap�����ި��XReadRecord����H�]�O����e�ѭ������W�I���ޡ^
        		Constant.CURRENT_LEFT_START=readerView.currRR.leftStart;//����e�ѭ����W���޽��
        		Constant.CURRENT_PAGE=readerView.currRR.pageNo;//����e�ѭ���page���      		
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        	}        	
        }	
	}
	//�p�G�o�ʹ��Ѩƥ�
	public void saveCurrentData()//�h�X�n��M���Ѯɳ��n�s�ƾڮw
	{
		if(Constant.FILE_PATH==null)//�p�G�O�Ĥ@�����}�n��(�b�����ɭ��h�X�n��)
		{
			//�S���ʧ@
		}else//�p�G�O��n�����
		{
			try
			{
				byte[] data=SQLDBUtil.fromListRowNodeListToBytes(readerView.currBook);//hashMap��Ƭ�byte
				SQLDBUtil.recordInsert(Constant.FILE_PATH,data);//�N��e�����|�MhashMap��byte�Φ��s�J�ƾڮw
				//��e�Ѫ��ѭ��H���s�J�ƾڮw�A��K�U�����}���٬O��e��
				SQLDBUtil.lastTimeInsert(Constant.FILE_PATH, Constant.CURRENT_PAGE,Constant.TEXT_SIZE);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public void savePreference()//��e�ѭ��h�X�ɡ]�I���h�X���s�^�O�s�{��Preference
	{
		SharedPreferences.Editor editor=sp.edit();//�s��SharedPreferences
		if(Constant.FILE_PATH==null)//�p�G�O�b�����ɭ��h�X
		{
			//�S���ʧ@�o��
		}else//�p�G�O�bŪ�Ѭɭ��h�X�A�O�s�{��
		{
			//��e�������|�M���page�s�Jpreference
			int page=readerView.currRR.pageNo;//��e����
			editor.putString("path", Constant.FILE_PATH);//��e���|�s�JSharedPreferences
			editor.putString("page", String.valueOf(page));//�N��e���Ʃ�Jpreference(��Ƭ�String��)
			editor.putString("name",Constant.TEXTNAME);//�N��e�Ѫ��W�r��Jpreference
		}
		editor.putString("isOneTime", "is");
		editor.putString("fontsize", String.valueOf(Constant.TEXT_SIZE));//���e�r��s�Jpreference
		editor.commit();//����
		
	}
	//��r���ܤƫ�,��s���ҩMHashMap���s��ƾڪ���k
	public void updataBookMarkAndHashMap()
	{
		try
		{//�P�_�ƾڮw���O�_�s�b��e�o���Ѫ�����
			haveBookMark=SQLDBUtil.judgeHaveBookMark(Constant.FILE_PATH);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(haveBookMark)//�p�G�s�b����
		{
			/*
			 * �p�G�s�b���ҡA����s���ҡA�b�ƾڮw�����X�j�����Ҫ����ƩM�W�r�A�q�ӱo��Ҧ���left_start���ȩ�b�@�ӼƲդ�
			 * ��shashMap����Ҥ���left_start�B���ȡC�M�ᤣ�M��hashMap�A���sø�s��{�bŪ�쪺��m
			 */
			
			String[] nameBookMark=null;//�Ȯɦs����Ҫ��W�r
			int[] pageBookMark = null;//�Ȯɦs����Ҫ�����
			int[] leftStart=null;//�Ȯɦs��C�����ҹ�����leftStart
		   try
		   {
			   //�b�ƾڮw�����X�Ҧ������ҰO��
			   dataBaseBookMark=SQLDBUtil.getBookmarkList(Constant.FILE_PATH);				   
			   int i=0;
			   nameBookMark=new String[dataBaseBookMark.size()];//�Ʋդj�p
			   pageBookMark=new int[dataBaseBookMark.size()];//�ѭ�
			   leftStart=new int[dataBaseBookMark.size()];//������leftStart

			   for(BookMark bm:dataBaseBookMark)
			   {
				   nameBookMark[i]=bm.bmname;//����Ҧ����Ҫ��W�r
				   pageBookMark[i]=bm.page;
				   i++;					   
			   }
		   }catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		   for(int i=0;i<dataBaseBookMark.size();i++)
		   {
			   int m=i;
			   readerView.currRR=readerView.currBook.get(pageBookMark[m]);//�bhashMap����������readerView.currRR�ﹳ
			   leftStart[m]=readerView.currRR.leftStart;//���leftstart����  
		   }
		   int tempLeftStart=Constant.CURRENT_LEFT_START;//�N��e��left_start�O���U��
		   //���sø�s�ɭ��A�åB�s����
		   for(int i=0;i<dataBaseBookMark.size();i++)
		   {			   
			   	Constant.CURRENT_LEFT_START=0;//�]���n�q�Ĥ@���}�l����ø�s �ҥH�ȭn�k�s
		   		Constant.CURRENT_PAGE=0;
		   		Constant.nextPageStart=0;
		   		Constant.nextPageNo=0;
		   		
		   		readerView.currBook.clear();//�M��hashMap
		   		int m=i;
		   		while(Constant.CURRENT_LEFT_START<leftStart[m])
		   		{
		   			readerView.currRR=new ReadRecord(Constant.nextPageStart,0,Constant.nextPageNo);
		   			
		   			Constant.CURRENT_LEFT_START=readerView.currRR.leftStart;//�O����eŪ��Bleftstart����
		   			Constant.CURRENT_PAGE=readerView.currRR.pageNo;//�O����eŪ��B��page����

		   			readerView.currBook.put(readerView.currRR.pageNo, readerView.currRR);//��e�����H���[�JhashMap
		   			
		   			readerView.currRR.isLeft=true;
		   			readerView.drawVirtualPage(readerView.currRR);//ø�s���������
		   			readerView.drawVirtualPage(readerView.currRR);//ø�s�k�������	
		   		}
		   		//�VbookMark���s�J��s�᪺�ƾ�
		   		SQLDBUtil.bookMarkInsert(nameBookMark[m],Constant.CURRENT_PAGE);
		   }
		   //���MhashMap�A���sø�s��ڭ̲{�bŪ���ɭ�
		   
		   Constant.CURRENT_LEFT_START=0;//�]���n�q�Ĥ@���}�l����ø�s �ҥH�ȭn�k�s
		   Constant.CURRENT_PAGE=0;
		   Constant.nextPageStart=0;
		   Constant.nextPageNo=0;
		   
		   readerView.currRR=new ReadRecord(0,0,0);
		   readerView.currBook.put(0, readerView.currRR);//�N�Ĥ@����JhashMap��
		   
		   while(Constant.CURRENT_LEFT_START<tempLeftStart)
		   {
			   readerView.currRR=new ReadRecord(Constant.nextPageStart,0,Constant.nextPageNo);
	
			   Constant.CURRENT_LEFT_START=readerView.currRR.leftStart;//�O����eŪ��Bleftstart����
			   Constant.CURRENT_PAGE=readerView.currRR.pageNo;//�O����eŪ��B��page����

			   readerView.currBook.put(readerView.currRR.pageNo, readerView.currRR);//��e�����H���[�JhashMap
	
			   readerView.currRR.isLeft=true;
			   readerView.drawVirtualPage(readerView.currRR);//ø�s���������
			   readerView.drawVirtualPage(readerView.currRR);//ø�s�k�������	
		   }	   	
		}else//�p�G���s�b���ҡA�u��s��e���ƪ�hashMap
		//�_�h�A�ھڷ�e����Left_Start�p��
	   	{
	   		int tempLeftStart=Constant.CURRENT_LEFT_START;//�N��e��left_start�O���U��

	   		Constant.CURRENT_LEFT_START=0;//�]���n�q�Ĥ@���}�l����ø�s �ҥH�ȭn�k�s
	   		Constant.CURRENT_PAGE=0;
	   		Constant.nextPageStart=0;
	   		Constant.nextPageNo=0;
	   		
	   		readerView.currBook.clear();//�M��hashMap
	   		
	   		readerView.currRR=new ReadRecord(0,0,0);
			readerView.currBook.put(0, readerView.currRR);//�N�Ĥ@����JhashMap��
	   		
	   		while(Constant.CURRENT_LEFT_START<tempLeftStart)
	   		{
	   			readerView.currRR=new ReadRecord(Constant.nextPageStart,0,Constant.nextPageNo);
	   			
	   			Constant.CURRENT_LEFT_START=readerView.currRR.leftStart;//�O����eŪ��Bleftstart����
	   			Constant.CURRENT_PAGE=readerView.currRR.pageNo;//�O����eŪ��B��page����

	   			readerView.currBook.put(readerView.currRR.pageNo, readerView.currRR);//��e�����H���[�JhashMap
	   			
	   			readerView.currRR.isLeft=true;
	   			readerView.drawVirtualPage(readerView.currRR);//ø�s���������
	   			readerView.drawVirtualPage(readerView.currRR);//ø�s�k�������	
	   		}
	   	}
	}
	
    @Override 
    public void onResume()
    {
    	super.onResume();
    }
    @Override 
    public void onPause()
    {
    	super.onPause();
    }
}

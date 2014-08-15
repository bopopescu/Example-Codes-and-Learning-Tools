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

//標識所有SurfaceView的常量類
class WhatMessage{
	public static final int GOTO_WELLCOME_VIEW=0;
	public static final int GOTO_MAIN_MENU_VIEW=1;
	public static final int GOTO_SEARCHBOOK_LIST=2;
	public static final int GOTO_BACHGROUND_LIST=3;
}
//表示各個界面的安全類型枚舉
enum WhichView {WELLCOM_VIEW,MAIN_VIEW,SEARCHBOOK_LIST,BACKGROUND_LIST }

public class ReaderActivity extends Activity {
	//背景圖
	int[] drawableIds=
	{R.drawable.bg_fhzl,R.drawable.bg_lsct,R.drawable.bg_sjzx,R.drawable.bg_ssnh,R.drawable.bg_wffm,R.drawable.bg_ygmm};
	//背景圖描述
	int[] msgIds={R.string.fhzl,R.string.lsct,R.string.sjzx,R.string.ssnh,R.string.wffm,R.string.ygmm};
	//文字顏色
	int[] drawColorIds={R.drawable.tc_black,R.drawable.tc_blue,R.drawable.tc_gray,R.drawable.tc_green,
			R.drawable.tc_purper,R.drawable.tc_red,R.drawable.tc_yellow,R.drawable.tc_white};
	//文字顏色描述
	int[] msgIds2={R.string.tc_black,R.string.tc_blue,R.string.tc_gray,R.string.tc_green,
			R.string.tc_purper,R.string.tc_red,R.string.tc_yellow,R.string.tc_white};
	//字體大小描述
	int[] fontSizeIds={R.string.font_size16,R.string.font_size24,R.string.font_size32};
	//字體圖片
	int[] fontSizeDrawable={R.drawable.font_size3,R.drawable.font_size2,R.drawable.font_size1};
	
	//音樂名稱
	int[] musicname={R.string.music_gs,R.string.music_mh};
	
	String sdcardPath="/sdcard";//根目錄
	String leavePath;//子文件路徑
	ListView lv;//列表的引用
	Button root_b;//返回根目錄的按鈕
	Button back_b;//返回到上層目錄
	
	//dialog編號
	final int LIST_SEARCH=1;//找書按鈕
	final int LIST_TURNPAGE=2;//自動翻書按鈕
	final int LIST_SET=3;//設置按鈕
	final int LIST_BOOKMARK=4;//書籤按鈕
	
	final int NAME_INPUT_DIALOG_ID=5;//輸入書籤名字的對話框
	final int SELECT_BOOKMARK=6;//選擇書籤的對話框	
	final int EXIT_READER=7;//退出軟件對話框
	final int DELETE_ONE_BOOKMARK=8;//刪除一條記錄的對話框
	final int DELETE_ALL_BOOKMARK=9;//清空當前書的所有書籤
	
	final int SET_FONT_SIZE	=10;//設置字體大小對話框
	final int SET_FONT_COLOR=11;//設置字體顏色對話框
	
	final int BACKGROUND_PIC=12;//背景圖片
	final int BACKGROUND_MUSIC=13;//背景音樂對話框

	DownLoad dl;
	WhichView curr;
	ReaderView readerView;//ReaderView的引用
	WellcomeSurfaceView wellcomView;
	ListViewUtills lvutills;
	TurnPageThread turnpage;
	
	MediaPlayer mp;//媒體播放器引用
	
	SharedPreferences sp;//判斷是第幾次打開同一本書
	
	List<BookMark> dataBaseBookMark=new ArrayList<BookMark>();//存放所有將要放入「書籤界面列表」中的書籤
    
	String[] tempname=null;//存放從數據庫中取出的所有「?榍�E鋇拿��?
	
	int[] temppage;//存放從數據庫中取出的所有當前書書籤的頁數
	
	String deleteOneBookMarkName=null;//刪除一條記錄的書籤名稱
	boolean haveBookMark=false;//判斷數據庫中是否存在書籤
	
	Handler myHandler = new Handler(){//處理各個SurfaceView發送的消息
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
        
         
        //全屏顯示Activity的設置
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉標題
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
        	   WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉標頭
        //本軟件是橫屏應用
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//強制橫屏        
        //獲取分辨率
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);       
        //給常量類中的屏幕高和寬賦值
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
        //給常量類中的自適應屏幕的變量設初值
        Constant.changeRatio();//調用自適應屏幕的方法
        
        readerView=new ReaderView(this);//拿到ReaderView的引用

        isWhichTime();//判斷是第幾次打開軟件，根據第幾次打開軟件，打開的書頁位置不同
        
        lvutills=new ListViewUtills(this);
        turnpage=new TurnPageThread(readerView);
        sendMessage(WhatMessage.GOTO_WELLCOME_VIEW);//跳轉到主界面
    }
	@Override
	public boolean onKeyDown(int keyCode,KeyEvent e)
	{
		 switch(keyCode)
		 {
		 	case 4:
		 		//跳轉到主界面
		 		sendMessage(WhatMessage.GOTO_MAIN_MENU_VIEW);//跳轉到主界面
		 		break;
		   case 82:
			   super.openOptionsMenu();
			   break;   
		   }
		   return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
	   //menu菜單組
	   //找書按鈕
	   MenuItem search=menu.add(0, 0, 0, R.string.search);
	   search.setIcon(R.drawable.m_search);//找書圖片
	   OnMenuItemClickListener searchbook=new OnMenuItemClickListener()
	   {//實現菜單項點擊事件監聽接口
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				showDialog(LIST_SEARCH);
				return true;
			}    		
		};
		search.setOnMenuItemClickListener(searchbook);//給「找書」添加監聽器    	

	   //書籤按鈕
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

	   //翻頁按鈕
	   MenuItem turnPage=menu.add(0,0,0,R.string.turnpage);
	   turnPage.setIcon(R.drawable.m_turnpage);
	   OnMenuItemClickListener turn=new OnMenuItemClickListener()
	   {//實現菜單項點擊事件監聽接口
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				showDialog(LIST_TURNPAGE);
				return true;
			}    		
		};
		turnPage.setOnMenuItemClickListener(turn);//給「翻頁」添加監聽器  
	    
	   //設置按鈕
	   MenuItem setUp=menu.add(0,0,0,R.string.setup);
	   setUp.setIcon(R.drawable.m_set);
	   OnMenuItemClickListener set=new OnMenuItemClickListener()
	   {//實現菜單項點擊事件監聽接口
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				showDialog(LIST_SET);
				return true;
			}    		
		};
		setUp.setOnMenuItemClickListener(set);//給「設置」添加監聽器    
	     return true;
	}
   
	@Override
	public Dialog onCreateDialog(int id)
	{
		Dialog dialog=null;
		switch(id)
		{
		case LIST_SEARCH://找書
			Builder b=new AlertDialog.Builder(this); 
 		  	b.setIcon(R.drawable.m_search);//設置圖標
 		  	b.setTitle(R.string.search);//設置標題
 		  	b.setItems(
 			 R.array.searchbook,
 			 new DialogInterface.OnClickListener()
     		 {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//這裡加點擊列表中的內容產生結果的代碼
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
		case LIST_BOOKMARK://書籤二級菜單
			b=new AlertDialog.Builder(this);
			b.setIcon(R.drawable.m_bookmark);//設置圖標
			b.setTitle(R.string.bookmark);//設置「書籤」標題
			b.setItems(
				R.array.bookmark,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					//這裡添加點擊列表中的內容產生結果的代碼
						switch(which)
						{
						case 0://添加書籤
							showDialog(NAME_INPUT_DIALOG_ID);
							break;
						case 1://選擇書籤
							try
							{//判斷數據庫中是否存在當前這本書的書籤
								haveBookMark=SQLDBUtil.judgeHaveBookMark(Constant.FILE_PATH);
							}catch(Exception e)
							{
								e.printStackTrace();
							}
							if(haveBookMark)
							{
								//如果存在書籤
								showDialog(SELECT_BOOKMARK);
							}else
							{//如果不存在書籤，出Toast
								Toast.makeText
								(
									ReaderActivity.this, 
									"請先添加書籤", 
									Toast.LENGTH_SHORT
								).show();
							}
							
							break;
						case 2://清空書籤
							showDialog(DELETE_ALL_BOOKMARK);							
							break;
						}
					}
				}	
			);
			dialog=b.create();
			break;
		case NAME_INPUT_DIALOG_ID://彈出添加書籤對話框
			dialog=new MyDialog(this);
			break;
		case SELECT_BOOKMARK://彈出選擇書籤的對話框
			b=new AlertDialog.Builder(this);
			b.setItems(null, null);
			dialog=b.create();
			break;
		case EXIT_READER://退出軟件對話框
			b=new AlertDialog.Builder(this);
			b.setItems(null, null);
			dialog=b.create();
			break;
		case DELETE_ONE_BOOKMARK://刪除一條書籤記錄
			b=new AlertDialog.Builder(this);
			b.setItems(null, null);
			dialog=b.create();
			break;
		case DELETE_ALL_BOOKMARK://清空當前這本書的全部記錄
			b=new AlertDialog.Builder(this);
			b.setItems(null, null);
			dialog=b.create();
			break;
			
		case SET_FONT_SIZE://字體大小
			b=new AlertDialog.Builder(this);
			b.setItems(null, null);
			dialog=b.create();
			break;
		case BACKGROUND_MUSIC://字體顏色
			b=new AlertDialog.Builder(this);
			b.setItems(null, null);
			dialog=b.create();
			break;
		case SET_FONT_COLOR://設置字體顏色
			dialog=new MyDialogFontColor(this);//用xml文件自己佈局
			break;
		case BACKGROUND_PIC://背景圖片對話框
			dialog=new MyDialogBackgroundPic(this);//用自己佈局的xml問文件
			break;
		case LIST_TURNPAGE://自動翻書
			b=new AlertDialog.Builder(this); 
 		  	b.setIcon(R.drawable.m_turnpage);//設置圖標
 		  	b.setTitle(R.string.turnpage);//設置標題
 		  	b.setItems(
 			 R.array.turnpage, 
 			 new DialogInterface.OnClickListener()
     		 {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//這裡加點擊列表中的內容產生結果的代碼
						switch(which)
						{
						case 0://30秒自動翻書
							if(Constant.FILE_PATH==null)//如果沒有選書
				    		    {
				    		    	Toast.makeText
									(
										ReaderActivity.this, 
										"請選擇您要閱讀的文本", 
										Toast.LENGTH_SHORT
									).show();
				    		    	
				    		    }else//如果已經選書
				    		    {
					    		    if(turnpage.isPageflag()==false)//如果線程沒有開始，則開始線程
					    		    {
					    		    	turnpage.setPageflag(true);
					    		    	turnpage.start();
					    		    }
							    	turnpage.setFiftySecond(false);
							    	turnpage.setFortySecond(false);
							    	turnpage.setThirtySecond(true);//將30秒設為true其他為false
							    	sendMessage(WhatMessage.GOTO_MAIN_MENU_VIEW);//跳轉到主界面
				    		    }
							break;
						case 1://40秒自動翻書
			    		    if(Constant.FILE_PATH==null)//如果沒有選書
			    		    {
			    		    	Toast.makeText
								(
									ReaderActivity.this, 
									"請選擇您要閱讀的文本", 
									Toast.LENGTH_SHORT
								).show();
			    		    	
			    		    }else//如果已經選書
			    		    {
			    		    	if(turnpage.isPageflag()==false)//如果線程沒有開始，則開始線程
				    		    {
				    		    	turnpage.setPageflag(true);
				    		    	turnpage.start();
				    		    }
						    	turnpage.setFiftySecond(false);
						    	turnpage.setThirtySecond(false);//將40秒設為true其他為false
						    	turnpage.setFortySecond(true);
						    	sendMessage(WhatMessage.GOTO_MAIN_MENU_VIEW);//跳轉到主界面
						    }
			    		    break;
						case 2://50秒自動翻書
							if(Constant.FILE_PATH==null)//如果沒有選書
				    		    {
				    		    	Toast.makeText
									(
										ReaderActivity.this, 
										"請選擇您要閱讀的文本", 
										Toast.LENGTH_SHORT
									).show();
				    		    	
				    		    }else//如果已經選書
				    		    {
				    		    	if(turnpage.isPageflag()==false)//如果線程沒有開始，則開始線程
					    		    {
					    		    	turnpage.setPageflag(true);
					    		    	turnpage.start();
					    		    }
							    	turnpage.setThirtySecond(false);//將50秒設為true其他為false
							    	turnpage.setFortySecond(false);
							    	turnpage.setFiftySecond(true);
							    	sendMessage(WhatMessage.GOTO_MAIN_MENU_VIEW);//跳轉到主界面
				    		    }
							break;
						case 3://停止自動翻頁
							turnpage.setPageflag(false);//停止線程
							sendMessage(WhatMessage.GOTO_MAIN_MENU_VIEW);//跳轉到主界面
							break;
						}
					}      			
     		 }
 		   );
 		  dialog=b.create();
 		  break;  
   	case LIST_SET://設置
   			b=new AlertDialog.Builder(this); 
 		  	b.setIcon(R.drawable.m_set);//設置圖標
 		  	b.setTitle(R.string.setup);//設置標題
 		  	b.setItems(
 			 R.array.setup, 
 			 new DialogInterface.OnClickListener()
     		 {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//這裡加點擊列表中的內容產生結果的代碼
						switch(which)
						{
						case 0://背景音樂
							showDialog(BACKGROUND_MUSIC);
							break;
						case 1://背景圖片
							showDialog(BACKGROUND_PIC);
							break;
						case 2://字體顏色
							showDialog(SET_FONT_COLOR);//設置字體顏色
							break;
						case 3://字體大小
							showDialog(SET_FONT_SIZE);//顯示字體大小對話框
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
    //每次彈出對話框時被回調以動態更新對話框內容的方法
    @Override
    public void onPrepareDialog(int id, final Dialog dialog)
    {
    	//若不是等待對話框則返回
    	switch(id)
    	{
    	   case NAME_INPUT_DIALOG_ID://姓名輸入對話框
    		   //確定按鈕
    		   Button bjhmcok=(Button)dialog.findViewById(R.id.bjhmcOk);
    		   //取消按鈕
       		   Button bjhmccancel=(Button)dialog.findViewById(R.id.bjhmcCancle);
       		   //給確定按鈕添加監聽器
       		   bjhmcok.setOnClickListener
               (
    	          new OnClickListener()
    	          {
    				@Override
    				public void onClick(View v) 
    				{
						if(Constant.FILE_PATH==null)//如果沒有選擇書，不可以加書籤
						{
							Toast.makeText(ReaderActivity.this, "請先選擇您想要閱讀的書", Toast.LENGTH_SHORT).show();
						}else
						{
							//獲取對話框裡的內容並用Toast顯示
	    					EditText et=(EditText)dialog.findViewById(R.id.etname);
	    					String name=et.getText().toString();
	    					if(name.equals(""))//如果書籤為空
	    					{
	    						Toast.makeText(ReaderActivity.this, "書籤名字不能為空", Toast.LENGTH_SHORT).show();
	    					}else//書籤不為空
	    					{
	    						try
								{
									//當前書籤的「名字」和當前書籤的「頁數」存入數據庫
									SQLDBUtil.bookMarkInsert(name,Constant.CURRENT_PAGE);
								}catch(Exception e)
								{
									e.printStackTrace();
								}
	    						//關閉對話框
	    						dialog.cancel();
	    					}
						}	
    				}        	  
    	          }
    	        );   
       		    //給取消按鈕添加監聽器
       		    bjhmccancel.setOnClickListener
	            (
	 	          new OnClickListener()
	 	          {
	 				@Override
	 				public void onClick(View v) 
	 				{	//關閉對話框
	 					dialog.cancel();					
	 				}        	  
	 	          }
	 	        );   
    	   break;
    	   case SELECT_BOOKMARK://選擇書籤的對話框
    		   try
    		   {
    			   //在數據庫中取出所有的書籤記錄
    			   dataBaseBookMark=SQLDBUtil.getBookmarkList(Constant.FILE_PATH);				   
    			   int i=0;
    			   tempname=new String[dataBaseBookMark.size()];//數組大小
    			   temppage=new int[dataBaseBookMark.size()];//書頁

    			   for(BookMark dataBookMark:dataBaseBookMark)
    			   {
    				   tempname[i]=dataBookMark.bmname;//獲取所有書籤的名字
    				   temppage[i]=dataBookMark.page;
    				   i++;					   
    			   }
    		   }catch(Exception e)
    		   {
    			   e.printStackTrace();
    		   }
    		   
			   //對話框對應的總垂直方向LinearLayout
       		   	LinearLayout ll=new LinearLayout(ReaderActivity.this);
       			ll.setOrientation(LinearLayout.VERTICAL);		//設置朝向	
       			ll.setGravity(Gravity.CENTER_HORIZONTAL);
       			ll.setBackgroundResource(R.drawable.dialog);
       			
       			//標題行的水平LinearLayout
       			LinearLayout lln=new LinearLayout(ReaderActivity.this);
       			lln.setOrientation(LinearLayout.HORIZONTAL);		//設置朝向	
       			lln.setGravity(Gravity.CENTER);//居中
       			lln.setLayoutParams(new ViewGroup.LayoutParams(200, LayoutParams.WRAP_CONTENT));
       			
       			//標題行的文字
       			TextView tvTitle=new TextView(ReaderActivity.this);
       			tvTitle.setText(R.string.bookmark_dialog);
       			tvTitle.setTextSize(20);//設置字體大小
       			tvTitle.setTextColor(ReaderActivity .this.getResources().getColor(R.color.white));//設置字體顏色
       			lln.addView(tvTitle);
       			
       			//將標題行添加到總LinearLayout
       			ll.addView(lln);		
       		   	
       		   	//為對話框中的歷史記錄條目創建ListView
       		    //初始化ListView
       	        ListView lv=new ListView(this);
       	        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE); 
       	        
       	        //為ListView準備內容適配器
       	        BaseAdapter ba=new BaseAdapter()
       	        {
       				@Override
       				public int getCount() {
       					return tempname.length;//總共幾個選項
       				}

       				@Override
       				public Object getItem(int arg0) { return null; }

       				@Override
       				public long getItemId(int arg0) { return 0; }

       				@Override
       				public View getView(int arg0, View arg1, ViewGroup arg2) {
       					
       					LinearLayout llb=new LinearLayout(ReaderActivity.this);
						llb.setOrientation(LinearLayout.HORIZONTAL);//設置朝向	
						llb.setPadding(5,5,5,5);//設置四周留白
       					
       					//為書籤添加圖片
       					ImageView image=new ImageView(ReaderActivity.this);
       					image.setImageDrawable(ReaderActivity.this.getResources().getDrawable(R.drawable.sl_bookmark));
       					image.setScaleType(ImageView.ScaleType.FIT_XY);//按照原圖比例
       					image.setLayoutParams(new Gallery.LayoutParams(30, 30));
       					llb.addView(image);
       					
       					//動態生成每條書籤對應的TextView
       					TextView tv=new TextView(ReaderActivity.this);
       					tv.setGravity(Gravity.LEFT);
       					tv.setText(tempname[arg0]+"     "+"第"+String.valueOf(temppage[arg0]+1)+"頁");//設置內容
       					tv.setTextSize(20);//設置字體大小
       					tv.setTextColor(ReaderActivity.this.getResources().getColor(R.color.white));//設置字體顏色
       					tv.setPadding(0,0,0,0);//設置四周留白
       					llb.addView(tv);
       					return llb;
       				}        	
       	        };       
       	        lv.setAdapter(ba);//為ListView設置內容適配器
       	        
       	        //設置選項被單擊的監聽器
       	        lv.setOnItemClickListener(
       	           new OnItemClickListener()
       	           {
       				@Override
       				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
       						long arg3) {//重寫選項被單擊事件的處理方法
   					
   						int page=temppage[arg2];//得到這條記錄對應的頁號
   						readerView.currRR=readerView.currBook.get(page);//在hashMap中找到對應的readerView.currRR對像
   						Constant.CURRENT_LEFT_START=readerView.currRR.leftStart;//記錄當前讀到處leftstart的值
   						Constant.CURRENT_PAGE=readerView.currRR.pageNo;//記錄當前讀到處的page的值
   						
   						//繪製左右兩幅圖片
   						readerView.currRR.isLeft=true;
   						readerView.bmLeft=readerView.drawPage(readerView.currRR);
   						readerView.bmRight=readerView.drawPage(readerView.currRR);
   						readerView.repaint();
       					
       					dialog.cancel();//關閉對話框
       				}        	   
       	           }
       	        );
       	        //將歷史記錄條的ListView加入總LinearLayout
	       	    ll.addView(lv);

	       	    lv.setOnItemLongClickListener(
	       	    		new OnItemLongClickListener()
	       	    		{
							@Override
							public boolean onItemLongClick(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								//根據當前的書籤的名字，找到對應的書籤的頁號,刪除這條記錄
								deleteOneBookMarkName=tempname[arg2];
								try
								{
									showDialog(DELETE_ONE_BOOKMARK);
									
								}catch(Exception e)
								{
									e.printStackTrace();
								}
								dialog.cancel();//關閉對話框
					
								return true;
							}
	       	    		}
	       	    );
	       	     dialog.setContentView(ll); 
    		   break;
    	   case EXIT_READER://退出對話框
    		   
    		   //對話框對應的總垂直方向LinearLayout
      		   	LinearLayout lle=new LinearLayout(ReaderActivity.this);
      			lle.setOrientation(LinearLayout.VERTICAL);		//設置朝向	
      			lle.setGravity(Gravity.CENTER_HORIZONTAL);
      			lle.setBackgroundResource(R.drawable.dialog);
      			
      			//標題行的水平LinearLayout
      			LinearLayout llt=new LinearLayout(ReaderActivity.this);
      			llt.setOrientation(LinearLayout.HORIZONTAL);		//設置朝向	
      			llt.setGravity(Gravity.CENTER);//居中
      			llt.setLayoutParams(new ViewGroup.LayoutParams(200, LayoutParams.WRAP_CONTENT));
      			
      			//標題行的文字
      			TextView eTitle=new TextView(ReaderActivity.this);
      			eTitle.setText(R.string.exit_bookmark);
      			eTitle.setTextSize(20);//設置字體大小
      			eTitle.setTextColor(ReaderActivity .this.getResources().getColor(R.color.white));//設置字體顏色
      			llt.addView(eTitle);
      			
      			//將標題行添加到總LinearLayout
      			lle.addView(llt);
      			
      			LinearLayout lleb=new LinearLayout(ReaderActivity.this);
      			lleb.setOrientation(LinearLayout.HORIZONTAL);//水平方向
      			lleb.setLayoutParams(new ViewGroup.LayoutParams(200, LayoutParams.WRAP_CONTENT));
      			lleb.setGravity(Gravity.CENTER);

	       	    //確定按鈕
	       	    Button eok=new Button(ReaderActivity.this);//清空書籤按鈕
	       	    eok.setText(R.string.ok);//設置「按鈕」的名字
	       	    eok.setTextSize(18);//設置字體大小
	       	    eok.setWidth(100);
	       	    eok.setHeight(20);
	       	    eok.setGravity(Gravity.CENTER);
	       	    eok.setOnClickListener(
	       	    		 new OnClickListener()
	       	    		 {
							public void onClick(View v) {
								dialog.cancel();//取消對話框
								savePreference();//當前書頁退出時,保存現場
								saveCurrentData();//當前hashMap的信息存入數據庫
								System.exit(0);	
							} 
	       	    		 } 
	       	     );
	       	    lleb.addView(eok);//加入到linearLayout中
	       	     //取消按鈕
	       	    Button eCancel=new Button(ReaderActivity.this);
	       	    eCancel.setText(R.string.cancel);//設置按鈕的名字
	       	    eCancel.setTextSize(18);
	       	    eCancel.setWidth(100);
	       	    eCancel.setHeight(20);
	       	    eCancel.setGravity(Gravity.CENTER);
	            eCancel.setOnClickListener(
	      	    		 new OnClickListener()
	       	    		 {
							public void onClick(View v) {
								dialog.cancel();//取消對話框
							}
	       	    		 }
	       	     );
	       	    lleb.addView(eCancel);
	       	    lle.addView(lleb);
	       	    dialog.setContentView(lle);
    		   break;
    	   case DELETE_ONE_BOOKMARK://刪除一條書籤記錄對話框
    		   
    		   //對話框對應的總垂直方向LinearLayout
     		   	LinearLayout lld=new LinearLayout(ReaderActivity.this);
     			lld.setOrientation(LinearLayout.VERTICAL);		//設置朝向	
     			lld.setGravity(Gravity.CENTER_HORIZONTAL);
     			lld.setBackgroundResource(R.drawable.dialog);
     			
     			//標題行的水平LinearLayout
     			LinearLayout lldt=new LinearLayout(ReaderActivity.this);
     			lldt.setOrientation(LinearLayout.HORIZONTAL);		//設置朝向	
     			lldt.setGravity(Gravity.CENTER);//居中
     			lldt.setLayoutParams(new ViewGroup.LayoutParams(240, LayoutParams.WRAP_CONTENT));
     			
     			//標題行的文字
     			TextView deTitle=new TextView(ReaderActivity.this);
     			deTitle.setText(R.string.delete_onebookmark);
     			deTitle.setTextSize(20);//設置字體大小
     			deTitle.setTextColor(ReaderActivity .this.getResources().getColor(R.color.white));//設置字體顏色
     			lldt.addView(deTitle);
     			
     			//將標題行添加到總LinearLayout
     			lld.addView(lldt);
     			
     			LinearLayout lldeb=new LinearLayout(ReaderActivity.this);
     			lldeb.setOrientation(LinearLayout.HORIZONTAL);//水平方向
     			lldeb.setLayoutParams(new ViewGroup.LayoutParams(200, LayoutParams.WRAP_CONTENT));
     			lldeb.setGravity(Gravity.CENTER);

	       	    //確定按鈕
	       	    Button deok=new Button(ReaderActivity.this);//清空書籤按鈕
	       	    deok.setText(R.string.ok);//設置「按鈕」的名字
	       	    deok.setTextSize(18);//設置字體大小
	       	    deok.setWidth(100);
	       	    deok.setHeight(20);
	       	    deok.setGravity(Gravity.CENTER);
	       	    deok.setOnClickListener(
	       	    		 new OnClickListener()
	       	    		 {
							public void onClick(View v) {	
								try
								{//數據庫中刪除一條書籤記錄
									SQLDBUtil.deleteOneBookMark(deleteOneBookMarkName);
								}catch(Exception e)
								{
									e.printStackTrace();
								}
								dialog.cancel();//取消對話框
							} 
	       	    		 } 
	       	     );
	       	    lldeb.addView(deok);//加入到linearLayout中
	       	     //取消按鈕
	       	    Button deCancel=new Button(ReaderActivity.this);
	       	    deCancel.setText(R.string.cancel);//設置按鈕的名字
	       	    deCancel.setTextSize(18);
	       	    deCancel.setWidth(100);
	       	    deCancel.setHeight(20);
	       	    deCancel.setGravity(Gravity.CENTER);
	            deCancel.setOnClickListener(
	      	    		 new OnClickListener()
	       	    		 {
							public void onClick(View v) {
								dialog.cancel();//取消對話框
								
								showDialog(SELECT_BOOKMARK);//顯示選擇書籤的對話框
									
							}
	       	    		 }
	       	     );
	            lldeb.addView(deCancel);
	       	    lld.addView(lldeb);
	       	    dialog.setContentView(lld);
   		   break;
    	   case DELETE_ALL_BOOKMARK:
    		   
    		 //對話框對應的總垂直方向LinearLayout
    		   	LinearLayout lla=new LinearLayout(ReaderActivity.this);
    			lla.setOrientation(LinearLayout.VERTICAL);		//設置朝向	
    			lla.setGravity(Gravity.CENTER_HORIZONTAL);
    			lla.setBackgroundResource(R.drawable.dialog);
    			
    			//標題行的水平LinearLayout
    			LinearLayout llat=new LinearLayout(ReaderActivity.this);
    			llat.setOrientation(LinearLayout.HORIZONTAL);		//設置朝向	
    			llat.setGravity(Gravity.CENTER);//居中
    			llat.setLayoutParams(new ViewGroup.LayoutParams(200, LayoutParams.WRAP_CONTENT));
    			
    			//標題行的文字
    			TextView deaTitle=new TextView(ReaderActivity.this);
    			deaTitle.setText(R.string.delete_allbookmark);
    			deaTitle.setTextSize(20);//設置字體大小
    			deaTitle.setTextColor(ReaderActivity .this.getResources().getColor(R.color.white));//設置字體顏色
    			llat.addView(deaTitle);
    			
    			//將標題行添加到總LinearLayout
    			lla.addView(llat);
    			
    			LinearLayout lldeab=new LinearLayout(ReaderActivity.this);
    			lldeab.setOrientation(LinearLayout.HORIZONTAL);//水平方向
    			lldeab.setLayoutParams(new ViewGroup.LayoutParams(200, LayoutParams.WRAP_CONTENT));
    			lldeab.setGravity(Gravity.CENTER);

	       	    //確定按鈕
	       	    Button deaok=new Button(ReaderActivity.this);//清空書籤按鈕
	       	    deaok.setText(R.string.ok);//設置「按鈕」的名字
	       	    deaok.setTextSize(18);//設置字體大小
	       	    deaok.setWidth(100);
	       	    deaok.setHeight(20);
	       	    deaok.setGravity(Gravity.CENTER);
	       	    deaok.setOnClickListener(
	       	    		 new OnClickListener()
	       	    		 {
							public void onClick(View v) {	
						
								try
								{//清空當前這本書的全部書籤
									SQLDBUtil.deleteAllBookMark(Constant.FILE_PATH);
								}catch(Exception e)
								{
									e.printStackTrace();
								}

								dialog.cancel();//取消對話框
							} 
	       	    		 } 
	       	     );
	       	    lldeab.addView(deaok);//加入到linearLayout中
	       	     //取消按鈕
	       	    Button deaCancel=new Button(ReaderActivity.this);
	       	    deaCancel.setText(R.string.cancel);//設置按鈕的名字
	       	    deaCancel.setTextSize(18);
	       	    deaCancel.setWidth(100);
	       	    deaCancel.setHeight(20);
	       	    deaCancel.setGravity(Gravity.CENTER);
	            deaCancel.setOnClickListener(
	      	    		 new OnClickListener()
	       	    		 {
							public void onClick(View v) {
								dialog.cancel();//取消對話框
							}
	       	    		 }
	       	     );
	            lldeab.addView(deaCancel);
	       	    lla.addView(lldeab);
	       	    dialog.setContentView(lla);
    		   break;
    	   case BACKGROUND_MUSIC://佈局背景音樂對話框
    		   setBackgroundMusicDialog(dialog);
    		   break;
    	   case BACKGROUND_PIC://背景圖片
    		   setBackgroundPic(dialog);
    		   break;
    	   case SET_FONT_SIZE://自己佈局字體大小對話框
    		   setFontSize(dialog);//調用setFontSize方法顯示佈局的dialog
    		   break;
    	   case SET_FONT_COLOR://佈局字體的顏色
    		   setFontColor(dialog);//佈局字體的顏色
    		   break;
    		   
    	}
    	
    }

	//查找書目
	public void searchBook()
	{

		goToSearchBooklist();
		
		lv=(ListView)ReaderActivity.this.findViewById(R.id.flist);//獲取ListView控件對像
		root_b=(Button)findViewById(R.id.Button01);
	    back_b=(Button)findViewById(R.id.Button02);
		final File[] files=lvutills.getFiles(sdcardPath);//獲取根節點文件列表 
		lvutills.intoListView(files,lv);//將各個文件添加到ListView列表中
		root_b.setOnClickListener(
				//OnClickListener為View的內部接口，其實現者負責監聽鼠標點擊事件
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
								"已經到根目錄了", 
								Toast.LENGTH_SHORT
							).show();
						}else
						{
								File cf=new File(lvutills.currentPath);//獲取當前文件列表的路徑對應的文件
								cf=cf.getParentFile();//獲取父目錄文件
								lvutills.currentPath=cf.getPath();//記錄當前文件列表路徑
								lvutills.intoListView(lvutills.getFiles(lvutills.currentPath),lv);	
						}
						
					}});
	}

	public void setBackgroundMusicDialog(final Dialog dialog)
	{
		//對話框對應的總垂直方向LinearLayout
	   	LinearLayout ll=new LinearLayout(ReaderActivity.this);
		ll.setOrientation(LinearLayout.VERTICAL);		//設置朝向	
		ll.setGravity(Gravity.CENTER_HORIZONTAL);
		ll.setBackgroundResource(R.drawable.dialog);
		
		//標題行的水平LinearLayout
		LinearLayout lln=new LinearLayout(ReaderActivity.this);
		lln.setOrientation(LinearLayout.HORIZONTAL);		//設置朝向	
		lln.setGravity(Gravity.CENTER);//居中
		lln.setLayoutParams(new ViewGroup.LayoutParams(200, LayoutParams.WRAP_CONTENT));
		
		//標題行的文字
		TextView tvTitle=new TextView(ReaderActivity.this);
		tvTitle.setText(R.string.music_name);
		tvTitle.setTextSize(20);//設置字體大小
		tvTitle.setTextColor(ReaderActivity .this.getResources().getColor(R.color.white));//設置字體顏色
		lln.addView(tvTitle);
		
		//將標題行添加到總LinearLayout
		ll.addView(lln);		
	   	
	   	//為對話框中的歷史記錄條目創建ListView
	    //初始化ListView
        ListView lv=new ListView(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE); 
        
        //為ListView準備內容適配器
        BaseAdapter ba=new BaseAdapter()
        {
			@Override
			public int getCount() {
				return musicname.length;//總共幾個選項
			}

			@Override
			public Object getItem(int arg0) { return null; }

			@Override
			public long getItemId(int arg0) { return 0; }

			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				
			LinearLayout llb=new LinearLayout(ReaderActivity.this);
			llb.setOrientation(LinearLayout.HORIZONTAL);//設置朝向	
			llb.setPadding(5,5,5,5);//設置四周留白
				
				//為書籤添加圖片
				ImageView image=new ImageView(ReaderActivity.this);
				image.setImageDrawable(ReaderActivity.this.getResources().getDrawable(R.drawable.sl_music));//設定圖片
				image.setScaleType(ImageView.ScaleType.FIT_XY);//按照原圖比例
				image.setLayoutParams(new Gallery.LayoutParams(30, 30));
				llb.addView(image);
				
				//動態生成每條書籤對應的TextView
				TextView tv=new TextView(ReaderActivity.this);
				tv.setGravity(Gravity.LEFT);
				tv.setText(ReaderActivity.this.getResources().getString(musicname[arg0]));//設置內容
				tv.setTextSize(20);//設置字體大小
				tv.setTextColor(ReaderActivity.this.getResources().getColor(R.color.white));//設置字體顏色
				tv.setPadding(12,0,0,0);//設置四周留白
				llb.addView(tv);
				return llb;
			}        	
        };       
        lv.setAdapter(ba);//為ListView設置內容適配器
        
        //設置選項被單擊的監聽器
        lv.setOnItemClickListener(
           new OnItemClickListener()
           {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {//重寫選項被單擊事件的處理方法
				//根據當前的書籤的名字，找到對應的書籤的頁號，根據頁號確定切換到那一頁

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
				dialog.cancel();//關閉對話框

				//初始化到當前文件第X頁
				readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

				//繪製左右兩幅圖片
				readerView.bmLeft=readerView.drawPage(readerView.currRR);
				readerView.bmRight=readerView.drawPage(readerView.currRR);
				readerView.repaint();
			}        	   
           }
        );
        //將歷史記錄條的ListView加入總LinearLayout
        ll.addView(lv);
        dialog.setContentView(ll); 	
		
	}
	//設置字體顏色
	public void setFontSize(final Dialog dialog)
	{
		//對話框對應的總垂直方向LinearLayout
	   	LinearLayout ll=new LinearLayout(ReaderActivity.this);
		ll.setOrientation(LinearLayout.VERTICAL);		//設置朝向	
		ll.setGravity(Gravity.CENTER_HORIZONTAL);
		ll.setBackgroundResource(R.drawable.dialog);
		
		//標題行的水平LinearLayout
		LinearLayout lln=new LinearLayout(ReaderActivity.this);
		lln.setOrientation(LinearLayout.HORIZONTAL);		//設置朝向	
		lln.setGravity(Gravity.CENTER);//居中
		lln.setLayoutParams(new ViewGroup.LayoutParams(200, LayoutParams.WRAP_CONTENT));
		
		//標題行的文字
		TextView tvTitle=new TextView(ReaderActivity.this);
		tvTitle.setText(R.string.font_size);
		tvTitle.setTextSize(20);//設置字體大小
		tvTitle.setTextColor(ReaderActivity .this.getResources().getColor(R.color.white));//設置字體顏色
		lln.addView(tvTitle);
		
		//將標題行添加到總LinearLayout
		ll.addView(lln);		
	   	
	   	//為對話框中的歷史記錄條目創建ListView
	    //初始化ListView
        ListView lv=new ListView(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE); 
        
        //為ListView準備內容適配器
        BaseAdapter ba=new BaseAdapter()
        {
			@Override
			public int getCount() {
				return fontSizeIds.length;//總共幾個選項
			}

			@Override
			public Object getItem(int arg0) { return null; }

			@Override
			public long getItemId(int arg0) { return 0; }

			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				
			LinearLayout llb=new LinearLayout(ReaderActivity.this);
			llb.setOrientation(LinearLayout.HORIZONTAL);//設置朝向	
			llb.setPadding(5,5,5,5);//設置四周留白
				
				//為書籤添加圖片
				ImageView image=new ImageView(ReaderActivity.this);
				image.setImageDrawable(ReaderActivity.this.getResources().getDrawable(fontSizeDrawable[arg0]));//設定圖片
				image.setScaleType(ImageView.ScaleType.FIT_XY);//按照原圖比例
				image.setLayoutParams(new Gallery.LayoutParams(30, 30));
				llb.addView(image);
				
				//動態生成每條書籤對應的TextView
				TextView tv=new TextView(ReaderActivity.this);
				tv.setGravity(Gravity.LEFT);
				tv.setText(ReaderActivity.this.getResources().getString(fontSizeIds[arg0]));//設置內容
				tv.setTextSize(20);//設置字體大小
				tv.setTextColor(ReaderActivity.this.getResources().getColor(R.color.white));//設置字體顏色
				tv.setPadding(0,0,0,0);//設置四周留白
				llb.addView(tv);
				return llb;
			}        	
        };       
        lv.setAdapter(ba);//為ListView設置內容適配器
        
        //設置選項被單擊的監聽器
        lv.setOnItemClickListener(
           new OnItemClickListener()
           {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {//重寫選項被單擊事件的處理方法
				
				switch(arg2)
				{
					case 0:
						if(Constant.TEXT_SIZE!=16)//如果當前字體大小不等於要換到的字體大小
						{
							//變換字體大小
							Constant.TEXT_SIZE=16;
							Constant.TEXT_SPACE_BETWEEN_CN=16;
							Constant.TEXT_SPACE_BETWEEN_EN=8;							
							//給常量類中的自適應屏幕的變量重新賦值
					        Constant.changeRatio();
							
					        updataBookMarkAndHashMap();//更新hashMap						
						}else//如果相等
						{
							//不做變換
						}
						
						break;
					case 1:
						if(Constant.TEXT_SIZE!=24)//如果當前字體大小不等於要換到的字體大小
						{
							//變換字體大小
							Constant.TEXT_SIZE=24;
							Constant.TEXT_SPACE_BETWEEN_CN=24;
							Constant.TEXT_SPACE_BETWEEN_EN=12;						
							//給常量類中的自適應屏幕的變量重新賦值
					        Constant.changeRatio();
							
					        updataBookMarkAndHashMap();//更新hashMap						
						}else//如果相等
						{
							//不做變換
						}
						break;
					case 2:
						if(Constant.TEXT_SIZE!=32)//如果當前字體大小不等於要換到的字體大小
						{
							//變換字體大小
							Constant.TEXT_SIZE=32;
							Constant.TEXT_SPACE_BETWEEN_CN=32;
							Constant.TEXT_SPACE_BETWEEN_EN=16;
							
							//給常量類中的自適應屏幕的變量重新賦值
					        Constant.changeRatio();
							
					        updataBookMarkAndHashMap();//更新hashMap
					
						}else//如果相等
						{
							//不做變換
						}
						break;
				}
				dialog.cancel();//關閉對話框


				//初始化到當前文件第X頁
				readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

				//繪製左右兩幅圖片
				readerView.bmLeft=readerView.drawPage(readerView.currRR);
				readerView.bmRight=readerView.drawPage(readerView.currRR);
				readerView.repaint();
			}        	   
           }
        );
        //將歷史記錄條的ListView加入總LinearLayout
        ll.addView(lv);
        dialog.setContentView(ll); 
	
	}
	
	//背景圖片
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
					
					readerView.bmBack=PicLoadUtil.LoadBitmap(readerView.getResources(), BITMAP);//自適應屏幕的背景圖片
					readerView.bmBack=PicLoadUtil.scaleToFit(readerView.bmBack, PAGE_WIDTH, PAGE_HEIGHT);
					
					//初始化到當前文件第X頁
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);
					//繪製左右兩幅圖片
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
					
					readerView.bmBack=PicLoadUtil.LoadBitmap(readerView.getResources(), BITMAP);//自適應屏幕的背景圖片
					readerView.bmBack=PicLoadUtil.scaleToFit(readerView.bmBack, PAGE_WIDTH, PAGE_HEIGHT);
					
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//繪製左右兩幅圖片
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();
					//關閉對話框
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
					
					readerView.bmBack=PicLoadUtil.LoadBitmap(readerView.getResources(), BITMAP);//自適應屏幕的背景圖片
					readerView.bmBack=PicLoadUtil.scaleToFit(readerView.bmBack, PAGE_WIDTH, PAGE_HEIGHT);
					
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//繪製左右兩幅圖片
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
					
					readerView.bmBack=PicLoadUtil.LoadBitmap(readerView.getResources(), BITMAP);//自適應屏幕的背景圖片
					readerView.bmBack=PicLoadUtil.scaleToFit(readerView.bmBack, PAGE_WIDTH, PAGE_HEIGHT);
					
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//繪製左右兩幅圖片
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();
					//關閉對話框
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
					
					readerView.bmBack=PicLoadUtil.LoadBitmap(readerView.getResources(), BITMAP);//自適應屏幕的背景圖片
					readerView.bmBack=PicLoadUtil.scaleToFit(readerView.bmBack, PAGE_WIDTH, PAGE_HEIGHT);
					
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//繪製左右兩幅圖片
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

					readerView.bmBack=PicLoadUtil.LoadBitmap(readerView.getResources(), BITMAP);//自適應屏幕的背景圖片
					readerView.bmBack=PicLoadUtil.scaleToFit(readerView.bmBack, PAGE_WIDTH, PAGE_HEIGHT);

					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//繪製左右兩幅圖片
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();
					//關閉對話框
					dialog.cancel();					
				}        	  
	          }
	        );   	
	}
	
	
	
	//設置字體顏色
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
					//初始化到當前文件第X頁
					readerView.currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);

					//繪製左右兩幅圖片
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

					//繪製左右兩幅圖片
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();
					
					//關閉對話框
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

					//繪製左右兩幅圖片
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

					//繪製左右兩幅圖片
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();

					//關閉對話框
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

					//繪製左右兩幅圖片
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

					//繪製左右兩幅圖片
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();

					//關閉對話框
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

					//繪製左右兩幅圖片
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

					//繪製左右兩幅圖片
					readerView.bmLeft=readerView.drawPage(readerView.currRR);
					readerView.bmRight=readerView.drawPage(readerView.currRR);
					readerView.repaint();

					//關閉對話框
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
							long arg3) {//重寫選項被單擊事件的處理方法
						String s=dl.txtName[arg2*2+1];
						dl.downFile(Constant.ADD_PRE+s,"/",s);
					} });
	}
	//向Handler發送信息的方法
    public void sendMessage(int what)
    {
    	Message msg = myHandler.obtainMessage(what); 
    	myHandler.sendMessage(msg);
    }  
    //向各個界面跳轉的方法
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
	public void goToWellcomView()//進入"百納科技"界面
	{
		if(wellcomView==null)
    	{
			wellcomView=new WellcomeSurfaceView(this);
    	}
		setContentView(wellcomView);
		curr=WhichView.WELLCOM_VIEW;
	}
	public void isWhichTime()//判斷是第幾次打開軟件
	{
		sp=this.getSharedPreferences("read", Context.MODE_PRIVATE);//設為模式為私有模式
		
		String isOneTime=sp.getString("isOneTime", null);//判斷是不是第一次打開軟件
        String lastTimePath=sp.getString("path", null);//得到上一次訪問的書的路徑
        String lastTimePage=sp.getString("page", null);//得到上一次訪問書的書頁
        String lastTimeName=sp.getString("name", null);//得到上一次訪問書的名字
        String lastTimeFontSize=sp.getString("fontsize", null);//得到上一次訪問書的字體大小
        if(lastTimePath==null)//如果是沒有選過書(包括在說明界面退出程序和第一次打開軟件)
        {
        	Constant.FILE_PATH=null;//當前路徑為空
        	Constant.CURRENT_LEFT_START=0;//當前書頁左上方的索引值為0
        	Constant.CURRENT_PAGE=0;//當前書頁為0	
        	
        	if(isOneTime==null)//如果是第一次打開軟件
        	{//使用默認字體大小，無動作
        	}else//如果曾經在說明界面退出過軟件
        	{
        		//確定字體大小
        		Constant.TEXT_SIZE=Integer.parseInt(lastTimeFontSize);//得到上一次在說明界面的字體大小
        		Constant.TEXT_SPACE_BETWEEN_CN=Constant.TEXT_SIZE;
        		Constant.TEXT_SPACE_BETWEEN_EN=Constant.TEXT_SIZE/2;
        		//給常量類中的各個常量重新賦值
                Constant.changeRatio();//調用自適應屏幕的方法
        	}        	
        	
        }else//否則，獲取上一次打開書的「路徑」與「頁數」
        {
        	//確定字體的大小
        	Constant.TEXT_SIZE=Integer.parseInt(lastTimeFontSize);//得到上一次字體大小
    		Constant.TEXT_SPACE_BETWEEN_CN=Constant.TEXT_SIZE;
    		Constant.TEXT_SPACE_BETWEEN_EN=Constant.TEXT_SIZE/2;
    		//給常量類中的各個常量重新賦值
            Constant.changeRatio();//調用自適應屏幕的方法
        	//*****************************確定字體大小結束******************************************
        	Constant.TEXTNAME=lastTimeName;//獲取上一次打開文件的書名
        	
        	Constant.FILE_PATH=lastTimePath;//獲取上一次打開文件的路徑
        	
        	//調用getCharacterCount方法計算文章的字符長度（防止讀到最後一頁可以繼續向下讀）
        	Constant.CONTENTCOUNT=TextLoadUtil.getCharacterCount(Constant.FILE_PATH);  
        	
        	
        	int page=Integer.parseInt(lastTimePage);//得到書的頁數，轉化為int型
        	try{
        		//根據當前路徑查找數據庫中的對應hashMap的byte型數據
        		byte[] data=SQLDBUtil.selectRecordData(Constant.FILE_PATH);
        		//為readerView中的hashMap
        		readerView.currBook=SQLDBUtil.fromBytesToListRowNodeList(data);//將byte型數據轉化為hashMap型的數據      		
        		readerView.currRR=readerView.currBook.get(page);//根據hashMap的索引取出ReadRecord的對象（記錄當前書頁的左上點索引）
        		Constant.CURRENT_LEFT_START=readerView.currRR.leftStart;//為當前書頁左上索引賦值
        		Constant.CURRENT_PAGE=readerView.currRR.pageNo;//為當前書頁的page賦值      		
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        	}        	
        }	
	}
	//如果發生換書事件
	public void saveCurrentData()//退出軟件和換書時都要存數據庫
	{
		if(Constant.FILE_PATH==null)//如果是第一次打開軟件(在說明界面退出軟件)
		{
			//沒有動作
		}else//如果是第n次選書
		{
			try
			{
				byte[] data=SQLDBUtil.fromListRowNodeListToBytes(readerView.currBook);//hashMap轉化為byte
				SQLDBUtil.recordInsert(Constant.FILE_PATH,data);//將當前的路徑和hashMap的byte形式存入數據庫
				//當前書的書頁信息存入數據庫，方便下次打開時還是當前頁
				SQLDBUtil.lastTimeInsert(Constant.FILE_PATH, Constant.CURRENT_PAGE,Constant.TEXT_SIZE);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public void savePreference()//當前書頁退出時（點擊退出按鈕）保存現場Preference
	{
		SharedPreferences.Editor editor=sp.edit();//編輯SharedPreferences
		if(Constant.FILE_PATH==null)//如果是在說明界面退出
		{
			//沒有動作發生
		}else//如果是在讀書界面退出，保存現場
		{
			//當前的文件路徑和文件的page存入preference
			int page=readerView.currRR.pageNo;//當前頁數
			editor.putString("path", Constant.FILE_PATH);//當前路徑存入SharedPreferences
			editor.putString("page", String.valueOf(page));//將當前頁數放入preference(轉化為String型)
			editor.putString("name",Constant.TEXTNAME);//將當前書的名字放入preference
		}
		editor.putString("isOneTime", "is");
		editor.putString("fontsize", String.valueOf(Constant.TEXT_SIZE));//把當前字體存入preference
		editor.commit();//提交
		
	}
	//當字體變化後,更新書籤和HashMap中存放數據的方法
	public void updataBookMarkAndHashMap()
	{
		try
		{//判斷數據庫中是否存在當前這本書的書籤
			haveBookMark=SQLDBUtil.judgeHaveBookMark(Constant.FILE_PATH);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(haveBookMark)//如果存在書籤
		{
			/*
			 * 如果存在書籤，先更新書籤，在數據庫中取出搜有書籤的頁數和名字，從而得到所有的left_start的值放在一個數組中
			 * 更新hashMap到書籤中的left_start處的值。然後不清空hashMap，重新繪製到現在讀到的位置
			 */
			
			String[] nameBookMark=null;//暫時存放書籤的名字
			int[] pageBookMark = null;//暫時存放書籤的頁數
			int[] leftStart=null;//暫時存放每條書籤對應的leftStart
		   try
		   {
			   //在數據庫中取出所有的書籤記錄
			   dataBaseBookMark=SQLDBUtil.getBookmarkList(Constant.FILE_PATH);				   
			   int i=0;
			   nameBookMark=new String[dataBaseBookMark.size()];//數組大小
			   pageBookMark=new int[dataBaseBookMark.size()];//書頁
			   leftStart=new int[dataBaseBookMark.size()];//對應的leftStart

			   for(BookMark bm:dataBaseBookMark)
			   {
				   nameBookMark[i]=bm.bmname;//獲取所有書籤的名字
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
			   readerView.currRR=readerView.currBook.get(pageBookMark[m]);//在hashMap中找到對應的readerView.currRR對像
			   leftStart[m]=readerView.currRR.leftStart;//獲取leftstart的值  
		   }
		   int tempLeftStart=Constant.CURRENT_LEFT_START;//將當前的left_start記錄下來
		   //重新繪製界面，並且存書籤
		   for(int i=0;i<dataBaseBookMark.size();i++)
		   {			   
			   	Constant.CURRENT_LEFT_START=0;//因為要從第一頁開始虛擬繪製 所以值要歸零
		   		Constant.CURRENT_PAGE=0;
		   		Constant.nextPageStart=0;
		   		Constant.nextPageNo=0;
		   		
		   		readerView.currBook.clear();//清空hashMap
		   		int m=i;
		   		while(Constant.CURRENT_LEFT_START<leftStart[m])
		   		{
		   			readerView.currRR=new ReadRecord(Constant.nextPageStart,0,Constant.nextPageNo);
		   			
		   			Constant.CURRENT_LEFT_START=readerView.currRR.leftStart;//記錄當前讀到處leftstart的值
		   			Constant.CURRENT_PAGE=readerView.currRR.pageNo;//記錄當前讀到處的page的值

		   			readerView.currBook.put(readerView.currRR.pageNo, readerView.currRR);//當前頁的信息加入hashMap
		   			
		   			readerView.currRR.isLeft=true;
		   			readerView.drawVirtualPage(readerView.currRR);//繪製左邊虛擬頁
		   			readerView.drawVirtualPage(readerView.currRR);//繪製右邊虛擬頁	
		   		}
		   		//向bookMark中存入更新後的數據
		   		SQLDBUtil.bookMarkInsert(nameBookMark[m],Constant.CURRENT_PAGE);
		   }
		   //不清hashMap，重新繪製到我們現在讀的界面
		   
		   Constant.CURRENT_LEFT_START=0;//因為要從第一頁開始虛擬繪製 所以值要歸零
		   Constant.CURRENT_PAGE=0;
		   Constant.nextPageStart=0;
		   Constant.nextPageNo=0;
		   
		   readerView.currRR=new ReadRecord(0,0,0);
		   readerView.currBook.put(0, readerView.currRR);//將第一頁放入hashMap中
		   
		   while(Constant.CURRENT_LEFT_START<tempLeftStart)
		   {
			   readerView.currRR=new ReadRecord(Constant.nextPageStart,0,Constant.nextPageNo);
	
			   Constant.CURRENT_LEFT_START=readerView.currRR.leftStart;//記錄當前讀到處leftstart的值
			   Constant.CURRENT_PAGE=readerView.currRR.pageNo;//記錄當前讀到處的page的值

			   readerView.currBook.put(readerView.currRR.pageNo, readerView.currRR);//當前頁的信息加入hashMap
	
			   readerView.currRR.isLeft=true;
			   readerView.drawVirtualPage(readerView.currRR);//繪製左邊虛擬頁
			   readerView.drawVirtualPage(readerView.currRR);//繪製右邊虛擬頁	
		   }	   	
		}else//如果不存在書籤，只更新當前頁數的hashMap
		//否則，根據當前頁的Left_Start計算
	   	{
	   		int tempLeftStart=Constant.CURRENT_LEFT_START;//將當前的left_start記錄下來

	   		Constant.CURRENT_LEFT_START=0;//因為要從第一頁開始虛擬繪製 所以值要歸零
	   		Constant.CURRENT_PAGE=0;
	   		Constant.nextPageStart=0;
	   		Constant.nextPageNo=0;
	   		
	   		readerView.currBook.clear();//清空hashMap
	   		
	   		readerView.currRR=new ReadRecord(0,0,0);
			readerView.currBook.put(0, readerView.currRR);//將第一頁放入hashMap中
	   		
	   		while(Constant.CURRENT_LEFT_START<tempLeftStart)
	   		{
	   			readerView.currRR=new ReadRecord(Constant.nextPageStart,0,Constant.nextPageNo);
	   			
	   			Constant.CURRENT_LEFT_START=readerView.currRR.leftStart;//記錄當前讀到處leftstart的值
	   			Constant.CURRENT_PAGE=readerView.currRR.pageNo;//記錄當前讀到處的page的值

	   			readerView.currBook.put(readerView.currRR.pageNo, readerView.currRR);//當前頁的信息加入hashMap
	   			
	   			readerView.currRR.isLeft=true;
	   			readerView.drawVirtualPage(readerView.currRR);//繪製左邊虛擬頁
	   			readerView.drawVirtualPage(readerView.currRR);//繪製右邊虛擬頁	
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

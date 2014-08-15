package com.bn.map;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import static com.bn.map.GameSurfaceView.*;
import static com.bn.map.Constant.*;
enum WhichView {WELCOME_VIEW,MAIN_MENU,SETTING_VIEW,
	MAPLEVEL_VIEW,GAME_VIEW,RANKING_VIEW,WIN_VIEW,FAIL_VIEW}
public class MapMasetActivity extends Activity{
	WhichView curr;//��e�T�|��
	WelcomeView wv;//�i�J�w��ɭ�
	GameSurfaceView msv;//�i�J�C���ɭ�
	GameView gameView;	//�Ʀ�]�ɭ�
	boolean collision_soundflag=true;//�O�_�}�ҸI���n��
	Vibrator mVibrator;//�n�����ʾ�
	boolean shakeflag=true;//�O�_�_��
	int level;//��e�ҿ����d
	int map_level_index=1;//�Ʀ�]���ҿ�����
	int curr_grade;//��e�C�����o��
	SensorManager mySensorManager;	//SensorManager�ﹳ�ޥ�
//	SensorManagerSimulator mySensorManager;	//�ϥ�SensorSimulator�������n��SensorSensorManager�ﹳ�ޥΪ���k
	SoundPool soundPool;//�n����
	HashMap<Integer, Integer> soundPoolMap; //�n�������n��ID�P�۩w�q�n��ID��Map
    Handler hd=new Handler(){
			@Override
			public void handleMessage(Message msg){
        		switch(msg.what){
	        		case 0://�����D���ɭ�
	        			goToMainView();
	        		break;
	        		case 1://������Ĺ���ɭ�
	        			goToWinView();
	                    break;
	        		case 2://������骺�ɭ�
	        			goToFailView();
	        			break;
	        		case 3://������C�����ɭ�
	        			goToGameView();
	        			break;
	        		case 4://����������ɭ�
	        			goToMapLevelView();
	        			break;
	        		case 5://������]�m�ɭ�
	        			goToSettingView();
	        			break;
	        		case 6://������Ʀ�]�ɭ�
	        			goToRankView();
	        			break;
        		}}};
    @Override
    public void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);        
        //�]�m�������
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        getWindow().setFlags(
        		WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        //�j����
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        SCREEN_HEIGHT=dm.heightPixels;
        SCREEN_WIDTH=dm.widthPixels;        
		//��oSensorManager�ﹳ
        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);	           
        //�ϥ�SensorSimulator�������n��SensorSensorManager�ﹳ�ޥΪ���k
//        mySensorManager = SensorManagerSimulator.getSystemService(this, SENSOR_SERVICE);
//        mySensorManager.connectSimulator();
        initSound();
        collisionShake();
        initDatabase();  
        goToWelcomeView();//�i�J�w��ɭ�
       // goToWinView();
    }
    //�Ыؼƾڮw
    public  void initDatabase(){
    	//�Ыت�
    	String sql="create table if not exists rank(id int(2) primary key not" +
    			" null,level int(2),grade int(4),time char(20));";
    	SQLiteUtil.createTable(sql);
    }
    //���J�ɶ�����k
    public  void insertTime(int level,int grade)
    {
    	Date d=new Date();
    	int curr_Id;
        String curr_time=(d.getYear()+1900)+"-"+(d.getMonth()+1<10?"0"+
        		(d.getMonth()+1):(d.getMonth()+1))+"-"+d.getDate();
    	String sql_maxId="select max(id) from rank";
    	Vector<Vector<String>> vector=SQLiteUtil.query(sql_maxId);
    	if(vector.get(0).get(0)==null)
    	{
    		curr_Id=0;
    	}
    	else
    	{
    		curr_Id=Integer.parseInt(vector.get(0).get(0))+1;
    	}
    	String sql_insert="insert into rank values("+curr_Id+","+level+
    								","+grade+","+"'"+curr_time+"');";
    	SQLiteUtil.insert(sql_insert);
    }
  //�i�J�w��ɭ�
    public void goToWelcomeView(){
    	if(wv==null){
    		wv=new WelcomeView(this);
    	}
    	setContentView(wv);
    	curr=WhichView.WELCOME_VIEW;
    }
    //�i�J�D���
    public void goToMainView(){
    	setContentView(R.layout.main);
    	curr=WhichView.MAIN_MENU;
    	ImageButton ib_start=(ImageButton)findViewById(R.id.ImageButton_Start);
    	ImageButton ib_rank=(ImageButton)findViewById(R.id.ImageButton_Rank);
    	ImageButton ib_set=(ImageButton)findViewById(R.id.ImageButton_Set);
    	ib_start.setOnClickListener(//�i�J������ɭ�
              new OnClickListener(){
				@Override
				public void onClick(View v){
					shake();
					hd.sendEmptyMessage(4);
				}});
    	ib_rank.setOnClickListener(//������Ʀ�]�ɭ�
              new OnClickListener(){
				@Override
				public void onClick(View v){
					shake();
					hd.sendEmptyMessage(6);
				}});
    	ib_set.setOnClickListener(//������]�m�ɭ�
              new OnClickListener(){
				@Override
				public void onClick(View v){
					shake();
					hd.sendEmptyMessage(5);
				}});}
    //�i�J�]�m�ɭ�
    public void goToSettingView()
    {
    	setContentView(R.layout.setting);
    	curr=WhichView.SETTING_VIEW;
    	final CheckBox cb_collision=(CheckBox)findViewById(R.id.CheckBox_collision);
    	cb_collision.setChecked(collision_soundflag);
    	final CheckBox cb_shake=(CheckBox)findViewById(R.id.CheckBox_shake);
    	cb_shake.setChecked(shakeflag);
    	ImageButton ib_ok=(ImageButton)findViewById(R.id.ImageButton_ok);
    	ib_ok.setOnClickListener
    	( 
              new OnClickListener() 
              {
				@Override
				public void onClick(View v) 
				{
					collision_soundflag=cb_collision.isChecked();
					shakeflag=cb_shake.isChecked();
					shake();
					//�e���D���
					hd.sendEmptyMessage(0);
				}
			}
    	);
    }
    //�i�J�}�l�C�������ɭ�
    public void goToMapLevelView()
    {
    	setContentView(R.layout.level_map);
    	curr=WhichView.MAPLEVEL_VIEW;
    	final ImageButton ib_map[]=
    	{
    			(ImageButton)findViewById(R.id.ImageButton_map01),
    			(ImageButton)findViewById(R.id.ImageButton_map02),
    			(ImageButton)findViewById(R.id.ImageButton_map03),
    			(ImageButton)findViewById(R.id.ImageButton_map04),
    			(ImageButton)findViewById(R.id.ImageButton_map05),
    			(ImageButton)findViewById(R.id.ImageButton_map06)
    	};
    		ib_map[0].setOnClickListener//�i�J�C��
        	( 
                  new OnClickListener() 
                  {
    				@Override
    				public void onClick(View v) 
    				{
    					//��l�Ʀa�ϼƾ�
    					shake();
    					guankaID=level=0;
    					BallGDThread.initDiTu(); 
    					hd.sendEmptyMessage(3);
    				}
    			}
        	);
    		ib_map[1].setOnClickListener//�i�J�C��
        	( 
                  new OnClickListener() 
                  {
    				@Override
    				public void onClick(View v) 
    				{
    					//��l�Ʀa�ϼƾ�
    					shake();
    					guankaID=level=1;
    					BallGDThread.initDiTu(); 
    					hd.sendEmptyMessage(3);
    				}
    			}
        	);
    		ib_map[2].setOnClickListener//�i�J�C��
        	( 
                  new OnClickListener() 
                  {
    				@Override
    				public void onClick(View v) 
    				{
    					//��l�Ʀa�ϼƾ�
    					shake();
    					guankaID=level=2;
    					BallGDThread.initDiTu(); 
    					hd.sendEmptyMessage(3);
    				}
    			}
        	);
    		ib_map[3].setOnClickListener//�i�J�C��
        	( 
                  new OnClickListener() 
                  {
    				@Override
    				public void onClick(View v) 
    				{
    					//��l�Ʀa�ϼƾ�
    					shake();
    					guankaID=level=3;
    					BallGDThread.initDiTu(); 
    					hd.sendEmptyMessage(3);
    				}
    			}
        	);
    		ib_map[4].setOnClickListener//�i�J�C��
        	( 
                  new OnClickListener() 
                  {
    				@Override
    				public void onClick(View v) 
    				{
    					//��l�Ʀa�ϼƾ�
    					shake();
    					guankaID=level=4;
    					BallGDThread.initDiTu(); 
    					hd.sendEmptyMessage(3);
    				}
    			}
        	);
    		ib_map[5].setOnClickListener//�i�J�C��
        	( 
                  new OnClickListener() 
                  {
    				@Override
    				public void onClick(View v) 
    				{
    					//��l�Ʀa�ϼƾ�
    					shake();
    					guankaID=level=5;
    					BallGDThread.initDiTu(); 
    					hd.sendEmptyMessage(3);
    				}
    			}
        	);
    		
    }
    //�i�J�C���ɭ�
    public void goToGameView()
    {
    	 msv=new GameSurfaceView(this); 
    	 msv.requestFocus();//����J�I
         msv.setFocusableInTouchMode(true);//�]�m���iĲ��
         curr=WhichView.GAME_VIEW;
    	 setContentView(msv);
    }
    //�i�J�Ʀ�]
    public void goToRankView()
    {
    	if(gameView==null)
    	{
    		 gameView = new GameView(this);
    	}    	   	
         setContentView(gameView);         
    	curr=WhichView.RANKING_VIEW;
    }
    //�p�G�������\
    public void goToWinView()
    {
    	setContentView(R.layout.win);
    	curr=WhichView.WIN_VIEW;
        TextView tv_score=(TextView)findViewById(R.id.TextView_score);//��e�o��
        TextView tv_flag=(TextView)findViewById(R.id.TextView_flag);//�O�_��s����
        ImageButton ib_replay=(ImageButton)findViewById(R.id.ImageButton_Replay);//�������s
        ImageButton ib_next=(ImageButton)findViewById(R.id.ImageButton_Next);//�U�@�����s
        ImageButton ib_back=(ImageButton)findViewById(R.id.ImageButton_Back);//��^���s
        tv_score.setText("�����o����:"+curr_grade);
        //�d�ߥ����̤j�����ưO��
        String sql_maxScore="select max(grade) from rank where level="+(level+1);
        System.out.println(sql_maxScore);
    	Vector<Vector<String>> vector=SQLiteUtil.query(sql_maxScore);
    	//�p�G��e���Ƥj����v�O��,�h��s�O��
    	
    	if(vector.get(0).get(0)==null||curr_grade>Integer.parseInt(vector.get(0).get(0)))
    	{
    		tv_flag.setText("��s����!");
    	}
    	else
    	{
    		tv_flag.setText("�S����s����!");
    	}
    	insertTime(level+1,curr_grade);
    	//�p�G��e�w��F���� �h�U�@�����s���i��
    	if(level==5)
    	{
    		ib_next.setEnabled(false);
    		ib_next.setVisibility(INVISIBLE);
    	}
        ib_replay.setOnClickListener//�������s��ť   
    	( 
              new OnClickListener() 
              {
				@Override
				public void onClick(View v) 
				{
					shake();
					BallGDThread.initDiTu();
					hd.sendEmptyMessage(3);
				}
			}
    	);
        ib_next.setOnClickListener//�U�@�����s��ť
    	( 
              new OnClickListener() 
              {
				@Override
				public void onClick(View v) 
				{
					shake();
					if(level<5)
					{
						level++;
					}
					guankaID=level;//�i�J�U�@��
					BallGDThread.initDiTu();
					hd.sendEmptyMessage(3);
				}
			}
    	);
        ib_back.setOnClickListener//��^���s��ť   ��^������ɭ�
    	( 
              new OnClickListener() 
              {
				@Override
				public void onClick(View v) 
				{
					shake();
					hd.sendEmptyMessage(4);
				}
			}
    	);
    }
    //�p�G��������
    public void goToFailView()
    {
    	setContentView(R.layout.fail);
    	curr=WhichView.FAIL_VIEW;
        ImageButton ib_replay=(ImageButton)findViewById(R.id.Fail_ImageButton_Replay);
        ImageButton ib_back=(ImageButton)findViewById(R.id.Fail_ImageButton_Back);
        ib_replay.setOnClickListener//�������s��ť
    	( 
              new OnClickListener() 
              {
				@Override
				public void onClick(View v) 
				{
					shake();
					BallGDThread.initDiTu();
					hd.sendEmptyMessage(3);
				}
			}
    	);
        ib_back.setOnClickListener//��^���s��ť   ��^������ɭ�
    	( 
              new OnClickListener() 
              {
				@Override
				public void onClick(View v) 
				{
					shake();
					hd.sendEmptyMessage(4);
				}
			}
    	);
    }
    @SuppressWarnings("deprecation")
	private SensorListener mySensorListener = new SensorListener(){
		@Override
		public void onAccuracyChanged(int sensor, int accuracy) 
		{	
		}
		@Override
		public void onSensorChanged(int sensor, float[] values) 
		{
			if(sensor == SensorManager.SENSOR_ORIENTATION)
			{//�P�_�O�_���[�t�׶ǷP���ܤƲ��ͪ��ƾ�	
				int directionDotXY[]=RotateUtil.getDirectionDot
				(
						new double[]{values[0],values[1],values[2]} 
			    );
				
				ballGX=-directionDotXY[0]*3.2f;//�o��X�MZ��V�W���[�t��
				ballGZ=directionDotXY[1]*3.2f;
			}	
		}		
	};
    @Override
	protected void onResume() //���gonResume��k
    {		
    	super.onResume();
		mySensorManager.registerListener
		(			//���U��ť��
				mySensorListener, 					//��ť���ﹳ
				SensorManager.SENSOR_ORIENTATION,	//�ǷP������
				SensorManager.SENSOR_DELAY_UI		//�ǷP���ƥ�ǻ����W��
		);
	}
	@Override
	protected void onPause() //���gonPause��k
	{		
		super.onPause();
		mySensorManager.unregisterListener(mySensorListener);	//�������U��ť��
	}
	public void initSound()
    {
			//�n����
			soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
		    soundPoolMap = new HashMap<Integer, Integer>();   
		    //�Y�F�譵��
		    soundPoolMap.put(1, soundPool.load(this, R.raw.dong, 1)); 
    }
    //�����n��
    public void playSound(int sound, int loop) 
    {
	   if(collision_soundflag)
	   {
		   AudioManager mgr = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);   
		    float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);   
		    float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);       
		    float volume = streamVolumeCurrent / streamVolumeMax; 
		    soundPool.play(soundPoolMap.get(sound), volume, volume, 1, loop, 1f);
	   }
	}
    //����_��
    public void collisionShake()
    {
    		mVibrator=(Vibrator)getApplication().getSystemService
            (Service.VIBRATOR_SERVICE);	
    }
    //�_��
    public void shake()
    {
    	if(shakeflag)
    	{
    		mVibrator.vibrate( new long[]{0,50},-1);
    	}
    }
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent e)
    { 
    	if(keyCode==4&&(curr==WhichView.MAPLEVEL_VIEW||curr==WhichView.SETTING_VIEW||
    			curr==WhichView.RANKING_VIEW))//��^�����ɭ�
    	{
    		goToMainView();
    		return true;
    	}
    	if(keyCode==4&&(curr==WhichView.WIN_VIEW||curr==WhichView.FAIL_VIEW))//�p�G��e�bĹ��ɭ�
    	{
    		goToMapLevelView();
    		return true;
    	}
    	if(keyCode==4&&curr==WhichView.MAIN_MENU)//�p�G��e�b�D���ɭ�
    	{
    		System.exit(0);
    		return true;
    	}
    	if(keyCode==4&&curr==WhichView.GAME_VIEW)//�p�G��e�b�C���ɭ�
    	{
    		msv.ballgdT.flag=false;
    		goToMapLevelView();
    		return true;
    	}
    	return false;
    }
}

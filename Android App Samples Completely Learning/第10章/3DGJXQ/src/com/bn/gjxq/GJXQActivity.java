package com.bn.gjxq;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

enum WhichView {WELCOME_VIEW,MAIN_MENU,IP_VIEW,GL_VIEW,LOAD_VIEW,
	   WAIT_VIEW,WIN_VIEW,EXIT_VIEW,FULL_VIEW,ABOUT_VIEW,HELP_VIEW}

public class GJXQActivity extends Activity 
{
	WelcomeView wv;//�i�J�w��ɭ�
	MainMenuView mmv;//�i�J�D���
	MySurfaceView msv;//�i�J�C���ɭ�
	WhichView curr;//��e�T�|��
	ClientAgent ca;//�Ȥ�ݥN�z�u�{
	SoundPool soundPool;//�n����
	HashMap<Integer, Integer> soundPoolMap; //�n�������n��ID�P�۩w�q�n��ID��Map
	Handler hd=new Handler()//�n�������B�z��
	{
			@Override
			public void handleMessage(Message msg)//���g��k
        	{
        		switch(msg.what)
        		{
	        		case 0://�������ݬɭ�
	        			setContentView(R.layout.wait);
	        			curr=WhichView.WAIT_VIEW;
	        		break;
	        		case 1://�����i�J3D�C���ɭ�
	        			gotoGameView();	        			
	        		break;
	        		case 2://����Ĺ���ɭ�
	        			setContentView(R.layout.win);
	        			curr=WhichView.WIN_VIEW;
	        		break;
	        		case 3://�����骺�ɭ�
	        			setContentView(R.layout.lost);
	        			curr=WhichView.WIN_VIEW;
	        		break;
	        		case 4://�������H�h�X�ɭ�
	        			setContentView(R.layout.exit);
	        			curr=WhichView.EXIT_VIEW;
	        		break;
	        		case 5://�i�J�[���ɭ�
	        			gotoLoadView();
	        		break;
	        		case 6://�i�J���ɭ�
	        			goToMainMenu();
	        		break;
	        		case 7://�i�J�H�Ƥw���ɭ�
	        			setContentView(R.layout.full);
	        			curr=WhichView.FULL_VIEW;
	        		break;
	        		case 8://��l�ƴѤl�ҫ�
	        			initChess();
	        		break;
        		}
        	}
	};
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);        
        //�]�m�������
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        getWindow().setFlags
        (
        		WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        //�j����
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        goToWelcomeView();//�i�J�w��ɭ�
    }
    //�Ұʽu�{�[���Ѥl�ҫ�
    public void initChess()
    {
    	 new Thread()
         {
         	public void run()
         	{
         		hd.sendEmptyMessage(5);//�i�J�[���ɭ�
         		MySurfaceView.initChessForDraw(GJXQActivity.this.getResources());//��l�ƴѤl
         		hd.sendEmptyMessage(6);	//�i�J�D���
         	}
         }.start();
    }
    //�i�J�w��ɭ�
    public void goToWelcomeView()
    {
    	if(wv==null)
    	{
    		wv=new WelcomeView(this);
    	}
    	setContentView(wv);
    	curr=WhichView.WELCOME_VIEW;
    }
    //�i�J�D���
    public void goToMainMenu()
    {
    	if(mmv==null)
    	{
    		mmv=new MainMenuView(this);
    	}
    	setContentView(mmv);	
    	curr=WhichView.MAIN_MENU;
    }
    //�i�Jip�a�}�Mport�ɭ�
    public void gotoIpView()
    {
    	setContentView(R.layout.main);   
    	final Button blj=(Button)this.findViewById(R.id.Button01);
    	final Button bfh=(Button)this.findViewById(R.id.Button02);
    	
        blj.setOnClickListener
        (
    		new  OnClickListener()
    		{
				@Override
				public void onClick(View v) 
				{
					final EditText eta=(EditText)findViewById(R.id.EditText01);
			    	final EditText etb=(EditText)findViewById(R.id.EditText02);
					String ipStr=eta.getText().toString();					
					String portStr=etb.getText().toString();
					//ip�a�}���a����
					String[] ipA=ipStr.split("\\.");
					if(ipA.length!=4)
					{
						Toast.makeText
						(
								GJXQActivity.this,
								"�A�Ⱦ�IP�a�}���X�k", 
								Toast.LENGTH_SHORT
						).show();
						
						return;
					}
					
					for(String s:ipA)
					{
						try
						{
							int ipf=Integer.parseInt(s);
							if(ipf>255||ipf<0)
							{
								Toast.makeText
								(
										GJXQActivity.this,
										"�A�Ⱦ�IP�a�}���X�k", 
										Toast.LENGTH_SHORT
								).show();							
								return;
							}
						}
						catch(Exception e)
						{
							Toast.makeText
							(
									GJXQActivity.this,
									"�A�Ⱦ�IP�a�}���X�k!", 
									Toast.LENGTH_SHORT
							).show();							
							return;
						}
					}
					//�ݤf�����a����
					try
					{
						int port=Integer.parseInt(portStr);
						if(port>65535||port<0)
						{
							Toast.makeText
							(
									GJXQActivity.this,
									"�A�Ⱦ��ݤf�����X�k!", 
									Toast.LENGTH_SHORT
							).show();							
							return;
						}						
					}
					catch(Exception e)
					{
						Toast.makeText
						(
								GJXQActivity.this,
								"�A�Ⱦ��ݤf�����X�k!", 
								Toast.LENGTH_SHORT
						).show();							
						return;
					}	
					
					//���ҹL��
					int port=Integer.parseInt(portStr);
					try
					{
						Socket sc=new Socket(ipStr,port);
						DataInputStream din=new DataInputStream(sc.getInputStream());
						DataOutputStream dout=new DataOutputStream(sc.getOutputStream());
						ca=new ClientAgent
						(
								GJXQActivity.this,
								sc,
								din,
								dout
						);
						ca.start();
					}
					catch(Exception e)
					{
						Toast.makeText
						(
								GJXQActivity.this,
								"�����s������,�еy�᭫��!", 
								Toast.LENGTH_SHORT
						).show();	
						return;	
					}	
				}    			
    		} 
        );
        bfh.setOnClickListener
        (
    		new  OnClickListener()
    		{
				@Override
				public void onClick(View v) 
				{
					goToMainMenu();//�i�J�D���
				}    			
    		}
        );
    	
    	curr=WhichView.IP_VIEW;
    }
    //�i�J�C���ɭ�
    public void gotoGameView()
    {
    	 msv=new MySurfaceView(this); 
         msv.requestFocus();//����J�I
         msv.setFocusableInTouchMode(true);//�]�m���iĲ��
         initSound();//��l���n����
         setContentView(msv);    	
    	 curr=WhichView.GL_VIEW;    	
    }
    //�i�J�[���ɭ�
    public void gotoLoadView()
    {
    	setContentView(R.layout.load);
    	curr=WhichView.LOAD_VIEW;
    }
    
    //�i�J���U�ɭ�
    public void gotoHelpView()
    {
    	setContentView(R.layout.help);
    	curr=WhichView.HELP_VIEW;
    }
    //�i�J����ɭ�
    public void gotoAboutView()
    {
    	setContentView(R.layout.about);
    	curr=WhichView.ABOUT_VIEW;
    }
    //��^�D���
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent e)
    { 
    	if(keyCode!=4)
    	{
    		return false;
    	}	
    	if(curr==WhichView.WIN_VIEW||curr==WhichView.IP_VIEW||
    	   curr==WhichView.EXIT_VIEW||curr==WhichView.FULL_VIEW||
    	   curr==WhichView.ABOUT_VIEW||curr==WhichView.HELP_VIEW)
    	{
    		goToMainMenu();
    		return true;
    	}
    	if(curr==WhichView.MAIN_MENU)
    	{
    		System.exit(0);
    	}
    	if(curr==WhichView.GL_VIEW)
    	{
    		try 
    		{
				ca.dout.writeUTF("<#EXIT#>");
			}
    		catch (IOException e1) 
			{
				e1.printStackTrace();
			}
    		return true;
    	}
    	if(curr==WhichView.LOAD_VIEW||curr==WhichView.WAIT_VIEW)
    	{
    		return true;
    	}
    	return false;
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
	    AudioManager mgr = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);   
	    float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);   
	    float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);       
	    float volume = streamVolumeCurrent / streamVolumeMax;   
	    soundPool.play(soundPoolMap.get(sound), volume, volume, 1, loop, 1f);
	}
}










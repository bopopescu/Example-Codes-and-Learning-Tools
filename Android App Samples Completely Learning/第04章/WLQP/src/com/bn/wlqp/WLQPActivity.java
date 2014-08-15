package com.bn.wlqp;
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
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

enum WhichView {WELCOMEVIEW,MAIN_MENU,IP_VIEW,GAME_VIEW,WAIT_OTHER,WIN,LOST,EXIT,FULL,ABOUT,HELP}  //�ɭ��T�|  

public class WLQPActivity extends Activity 
{
	MainMenuView mmv;  //�D�ɭ�
	GameView gameview; //�C���ɭ�
	WhichView curr;  //��ܭ��Ӭɭ�
	ClientAgent ca; //�Ȥ�ݥN�z�u�{
	SoundPool  soundPool;
	HashMap<Integer, Integer>  soundPoolMap; 
	static String cardListStr; 
	
	Handler hd=new Handler()//�n�������B�z��
	{
			@Override
			public void handleMessage(Message msg)//���g��k
        	{
        		switch(msg.what)
        		{
	        		case 0:   //�i�J���ݬɭ�
	        			setContentView(R.layout.wait);
	        			curr=WhichView.WAIT_OTHER;
	        		break;
	        		case 1:  //�i�J�C���ɭ�
	        			gotoGameView();
	        		break;
	        		case 2:  //�i�J�AĹ�F�ɭ�
	        			setContentView(R.layout.win);
	        			curr=WhichView.WIN;
	        		break;
	        		case 3:  //�i�J�A��F�ɭ�
	        			setContentView(R.layout.lost);
	        			curr=WhichView.LOST;
	        		break; 
	        		case 4:  //�i�J�����a�h�X�ɭ�
	        			setContentView(R.layout.exit);
	        			curr=WhichView.EXIT;
	        		break;
	        		case 5:  //�H�Ƥw��
	        			setContentView(R.layout.full);
	        			curr=WhichView.FULL;
	        		break;
	        		case 6:   //�i�J���U����
	        			setContentView(R.layout.help);
	        			curr=WhichView.HELP;
	        		break;
	        		case 7:   //�i�J����ɭ�
	        			setContentView(R.layout.about);
	        			curr=WhichView.ABOUT;
	        		break;
	        		case 8:
	        			goToMainMenu();
	        			curr=WhichView.WELCOMEVIEW;
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
        initSounds();
        goToWelcomeView();
          
    }

    //�n���w�Ħ�����l��
    public void initSounds()
    {
    	 //�Ы��n���w�Ħ�
	     soundPool = new SoundPool
	     (
	    		 4, 							//�P�ɯ�̦h���񪺭Ӽ�
	    		 AudioManager.STREAM_MUSIC,     //���W������
	    		 100							//�n���������q�A�ثe�L��
	     );
	     
	     //�Ы��n���귽Map	     
	     soundPoolMap = new HashMap<Integer, Integer>();   
	     //�N�[�����n���귽id��i��Map
	     soundPoolMap.put(1, soundPool.load(this, R.raw.tweet, 1));
	     //���X�ӭ��ĴN����e�o�ӴX�y  R.raw.gamestart��^�s�� ���w     �᭱��1���u���� �ثe���Ҽ{
	} 
   
   //�����n������k
   public void playSound(int sound, int loop) {	    
	    AudioManager mgr = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);   
	    float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);//��e���q   
	    float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//�̤j���q       
	    float volume = streamVolumeCurrent / streamVolumeMax;   
	    
	    soundPool.play
	    (
    		soundPoolMap.get(sound), //�n���귽id
    		volume, 				 //���n�D���q
    		volume, 				 //�k�n�D���q
    		1, 						 //�u����				 
    		loop, 					 //�`������ -1�a��û��`��
    		0.5f					 //�^��t��0.5f��2.0f����
	    );
	}
   
   
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent e)
    {   //��ť�����L���U�ƥ�
    	if(keyCode==4)//�ջs�W�@�Ӭɭ�����
    	{//�ھڰO������e�O���Ӭɭ��H����curr�i�H���D�n����쪺�O���Ӭɭ�
    		if(curr==WhichView.WIN||curr==WhichView.LOST||curr==WhichView.EXIT)
    		{
    			goToMainMenu();  
    			return true;
    		}
    		if(curr==WhichView.WELCOMEVIEW)
    		{
    			return true;
    		}
    		if(curr==WhichView.IP_VIEW)
    		{//�����MainMenu
    			goToMainMenu();
    			return true;
    		}
    		if(curr==WhichView.GAME_VIEW)
    		{//�����EXIT�ɭ�
    			try 
    			{
					ca.dout.writeUTF("<#EXIT#>");
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
    			return true;          
    		}
    		if(curr==WhichView.WAIT_OTHER)
    		{//������
    			return true;
    		}
    		if(curr==WhichView.MAIN_MENU)
    		{//�h�X�C��
    			System.exit(0);
    		}
    		if(curr==WhichView.FULL)
    		{//�����IPView
    			gotoIpView();
    			return true;
    		}
    		if(curr==WhichView.HELP)
    		{//�����MainMenu
    			goToMainMenu();
    			return true;
    		}
    		if(curr==WhichView.ABOUT)
    		{//�����MainMenu
    			goToMainMenu();
    			return true;
    		}
    	}
    	
    	
    	return false;
    }
    
    public void goToWelcomeView()
    {
    	WelcomeView mySurfaceView = new WelcomeView(this);
        this.setContentView(mySurfaceView);
        curr=WhichView.WELCOMEVIEW;
    }
    public void goToMainMenu()
    {//�h�D�ɭ�����k
    	if(mmv==null)
    	{
    		mmv=new MainMenuView(this);
    	}    	
    	setContentView(mmv);    
    	//��e��View��MAIN_MENU;
    	curr=WhichView.MAIN_MENU;
    }
    
    public void gotoIpView()
    {//�h�DIP�M�ݤf�����ɭ�����k
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
					//�o��C��EditText���ޥ�
					final EditText eta=(EditText)findViewById(R.id.EditText01);
			    	final EditText etb=(EditText)findViewById(R.id.EditText02);
					String ipStr=eta.getText().toString();//�o��EditText�̭����H��					
					String portStr=etb.getText().toString();
					
					String[] ipA=ipStr.split("\\.");
					if(ipA.length!=4)
					{//�P�_IP���榡�O�_�X�k
						Toast.makeText
						(
								WLQPActivity.this,
								"�A�Ⱦ�IP�a�}���X�k", 
								Toast.LENGTH_SHORT
						).show();
						
						return;
					}
					
					for(String s:ipA)
					{//�bIP���榡�X�k���e���U�P�_�ݤf���O�_�X�k
						try
						{
							int ipf=Integer.parseInt(s);
							if(ipf>255||ipf<0)
							{//�P�_Ip���X�k��
								Toast.makeText
								(//�ɭ��u�XToast��ܫH�� --->�A�Ⱦ�IP�a�}���X�k!
										WLQPActivity.this,
										"�A�Ⱦ�IP�a�}���X�k", 
										Toast.LENGTH_SHORT
								).show();							
								return;
							}
						}
						catch(Exception e)
						{
							Toast.makeText
							(//�ɭ��u�XToast��ܫH�� --->�A�Ⱦ�IP�a�}���X�k!
									WLQPActivity.this,
									"�A�Ⱦ�IP�a�}���X�k!", 
									Toast.LENGTH_SHORT
							).show();							
							return;
						}
					}
					
					try
					{
						int port=Integer.parseInt(portStr);
						if(port>65535||port<0)
						{//�P�_�ݤf���O�_�X�k
							Toast.makeText
							(//�ɭ��u�XToast��ܫH�� --->�A�Ⱦ��ݤf�����X�k!
									WLQPActivity.this,
									"�A�Ⱦ��ݤf�����X�k!", 
									Toast.LENGTH_SHORT
							).show();							
							return;
						}						
					}
					catch(Exception e)
					{
						Toast.makeText
						(//�ɭ��u�XToast��ܫH�� --->�A�Ⱦ��ݤf�����X�k!
								WLQPActivity.this,
								"�A�Ⱦ��ݤf�����X�k!", 
								Toast.LENGTH_SHORT
						).show();							
						return;
					}	
					
					//���ҹL��
					int port=Integer.parseInt(portStr);
					try
					{//���ҹL����ҰʥN�z���Ȥ�ݽu�{
						Socket sc=new Socket(ipStr,port);
						DataInputStream din=new DataInputStream(sc.getInputStream());
						DataOutputStream dout=new DataOutputStream(sc.getOutputStream());
						ca=new ClientAgent
						(
							WLQPActivity.this,
							sc,
							din,
							dout
						);
						ca.start();
					}
					catch(Exception e)
					{
						Toast.makeText
						(//�ɭ��u�XToast��ܫH�� 
								WLQPActivity.this,
								"�p�����ѡA�еy��A��!", 
								Toast.LENGTH_SHORT
						).show();							
						return;
					}	
				}    			
    		} 
        );
        bfh.setOnClickListener
        (//���^���s�]�m��ť   �����D�ɭ�
    		new  OnClickListener()
    		{
				@Override
				public void onClick(View v) 
				{
					goToMainMenu();
				}    			
    		}
        );
    	//��e��View��IP_VIEW;
    	curr=WhichView.IP_VIEW;
    }
    
    public void gotoGameView()
    { 	
    	gameview=new GameView(this); 
    	setContentView(gameview);
    	//��e��View��GAME_VIEW;
    	curr=WhichView.GAME_VIEW;
    }
}

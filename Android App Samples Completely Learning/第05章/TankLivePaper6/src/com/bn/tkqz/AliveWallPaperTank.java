package com.bn.tkqz;//�n���]�y�y
import static com.bn.tkqz.Constant.SCREEN_HEIGHT;//�ޤJ������
import static com.bn.tkqz.Constant.SCREEN_WIDTH;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.SoundPool;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class AliveWallPaperTank extends WallpaperService
{	
	static Handler hd = new Handler();//�Ы��R�AHandler�ﹳ
	EngineCrazyTank tankEngine;//EngineCrazyTank��H���ޥ�
    
    static Bitmap bullet;//�l�u���
    static Bitmap heroBullet;//�^���l�u���
    //�Ĥ�թZ�J
    static Bitmap[] tanki1;//�Z�J��ϼƲ�1
    static Bitmap[] tanki2;//�Z�J��ϼƲ�2
    static Bitmap[] tanki3;//�Z�J��ϼƲ�3
    //�Ĥ���Z�J
    static Bitmap[] tankRedi1;//���Z�J��ϼƲ�1
    static Bitmap[] tankRedi2;//���Z�J��ϼƲ�2
    static Bitmap[] tankRedi3;//���Z�J��ϼƲ�3
    //�^���Z�J
    static Bitmap[] heroTanki1;//�^���Z�J��ϼƲ�1
    static Bitmap[] heroTanki2;//�^���Z�J��ϼƲ�2
    static Bitmap[] heroTanki3;//�^���Z�J��ϼƲ�3
    static Bitmap[] heroTanki4;//�^���Z�J��ϼƲ�4
    
    //��ê��
    static Bitmap brickBitmap;
    static Bitmap stoneBitmap;
    static Bitmap seaBitmap;
    static Bitmap iceBitmap;
    static Bitmap grassBitmap;
    //�Ѻ�
    static Bitmap homeBitmap;
    static Bitmap homediedBitmap;
    //���y��
    static Bitmap starBitmap;
    static Bitmap bombBitmap;
    static Bitmap lifeBitmap;
    static Bitmap shovelBitmap;
    static Bitmap protectorBitmap;
    static Bitmap timerBitmap;
    //�^���Z�J�O�@���~��
    static Bitmap coveringBitmap;
    //�������s
    static Bitmap controlBitmap;
    static Bitmap redDotBitmap;
    static Bitmap fireBtnUpBitmap;
    static Bitmap fireBtnDownBitmap;
    //�䥦
    static Bitmap[] numbers;
    static Bitmap gameOver;
    static Bitmap restartBitmap;
    static SoundPool soundPool;
    static Map<Integer,Integer> soundPoolMap;
    //================================== �D�귽 �ܶq begin ==========================================
    static BattleMap map;//�a�Ϥޥ�
 	static Hero hero;
 	static int keyState=0;//���䪬�A,1�X�Xup,2�X�Xdown,4�X�Xleft,8�X�Xright
 	
 	static boolean heroGoFlag=true;//��s���ѽu�{�лx��
 	
 	static ArrayList<Tank> alTank;//�Ĥ�Z�J�C��
 	private TankGeneratorThread  generator;//�H�����ͼĤ�Z�J�u�{���ޥ�
 	static TankGoThread go;//�Ĥ�Z�J��i�u�{���ޥ�
 	HeroGoThread heroGo;//�^���Z�J��i�u�{���ޥ�
 	static HeroSendBulletThread heroSendBullet;//�^���Z�J�o�g�l�u�u�{���ޥ�
 	static boolean heroSendBulletFlag=true;//�^���Z�J�o�g�l�u���лx��
 	static TankChangeDirectionThread changeDirection;//�Ĥ�Z�J�H�����ܤ�V�u�{���ޥ�
 	static boolean TankGeneratorFlag=true;//�H�����ͩZ�J���лx��
 	static boolean TankGoFlag=true;//�^���Z�J��i���лx
 	static boolean TankChangeDirectionFlag=true;//�Z�J�H�����ܤ�V���лx��
 	
 	static Vector<HeroBullet> alHeroBullet;//�^���l�u�C��
 	private HeroBulletGoThread heroBulletGo;
 	static boolean heroBulletGoFlag=true;
 	
 	static ArrayList<Bullet> alBullet;//�Ĥ�l�u�C��
 	static TankSendBulletThread tankSendBullet;
 	private TankBulletGoThread tankBulletGo;
 	static boolean tankSendBulletFlag=true;
 	static boolean tankBulletGoFlag=true;
 	
 	static boolean gameOverFlag=false;			//�C�������лx��
 	static int countTankDestoryed=0;//�O�������Z�J�ƶq
 	static long gameStartTime;//�C���}�l�ɶ�
 	static int time=0;//�C���i��ɶ�
 	static int fullTime=0;//�O���C���ɭ��{�b�`�ɶ��A�q�}�l�C�� ��h�X�C��
 	static int score=0;//�C���o��
 	private boolean fireButtonDownFlag=false;//�o�g���s�O�_�Q���U���лx
 	static ScreenScaleResult ssr;
 	 //================================== �D�귽 �ܶq end ==========================================
	@Override
    public Engine onCreateEngine() 
    {
		initBitmap();//��l�Ʀ�ϸ귽 
	    tankEngine=new EngineCrazyTank(); 
        return tankEngine;
    }
	private void initBitmap()
    {//��l�Ʀ�ϸ귽
    	bullet=BitmapFactory.decodeResource(this.getResources(), R.drawable.b);//�Ыؤl�u���
    	heroBullet=BitmapFactory.decodeResource(this.getResources(), R.drawable.hb);//�Ыؤl�u���
    	tanki1=new Bitmap[]
    	{//�ЫةZ�J���1
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.up1),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.right1),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.down1),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.left1)    			
    	};
    	tanki2=new Bitmap[]
    	{//�ЫةZ�J���1
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.up2),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.right2),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.down2),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.left2)    			
    	};
    	tanki3=new Bitmap[]
    	{//�ЫةZ�J���2
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.up3),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.right3),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.down3),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.left3)    			
    	};
    	//�^���Z�J
    	heroTanki1=new Bitmap[]
    	                      {//�Ыح^���Z�J���1
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.heroup1),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.heroright1),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.herodown1),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.heroleft1)    			
    	};
    	heroTanki2=new Bitmap[]
    	                      {//�Ыح^���Z�J���2
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.heroup2),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.heroright2),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.herodown2),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.heroleft2)    			
    	};
    	heroTanki3=new Bitmap[]
    	                      {//�Ыح^���Z�J���3
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.heroup3),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.heroright3),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.herodown3),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.heroleft3)    			
    	};
    	heroTanki4=new Bitmap[]
    	                      {//�Ыح^���Z�J���3
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.heroup4),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.heroright4),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.herodown4),
    			BitmapFactory.decodeResource(this.getResources(), R.drawable.heroleft4)    			
    	};
    	//���Z�J
    	tankRedi1=new Bitmap[]
					    	{//�Ыج��Z�J���1
					    			BitmapFactory.decodeResource(this.getResources(), R.drawable.upred1),
					    			BitmapFactory.decodeResource(this.getResources(), R.drawable.rightred1),
					    			BitmapFactory.decodeResource(this.getResources(), R.drawable.downred1),
					    			BitmapFactory.decodeResource(this.getResources(), R.drawable.leftred1)    			
					    	};
    	tankRedi2=new Bitmap[]
    	                 	{//�Ыج��Z�J���2
    	                 			BitmapFactory.decodeResource(this.getResources(), R.drawable.upred2),
    	                 			BitmapFactory.decodeResource(this.getResources(), R.drawable.rightred2),
    	                 			BitmapFactory.decodeResource(this.getResources(), R.drawable.downred2),
    	                 			BitmapFactory.decodeResource(this.getResources(), R.drawable.leftred2)    			
    	                 	};
    	tankRedi3=new Bitmap[]
    	                 	{//�Ыج��Z�J���3
    	                 			BitmapFactory.decodeResource(this.getResources(), R.drawable.upred3),
    	                 			BitmapFactory.decodeResource(this.getResources(), R.drawable.rightred3),
    	                 			BitmapFactory.decodeResource(this.getResources(), R.drawable.downred3),
    	                 			BitmapFactory.decodeResource(this.getResources(), R.drawable.leftred3)    			
    	                 	};
    	//��ê��
    	brickBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.brick);//�Ыؿj����
    	stoneBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.stone);//�Ыإ�����
    	seaBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.sea);//�Ыخ��v���
    	iceBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.ice);//�ЫئB���
    	grassBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.grass);//�Ыد�a���
    	//�Ѻ�
    	homeBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.home);//�ЫئѺۦ��
    	homediedBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.homedied);//�ЫئѺۦ�����
    	//���y��
    	starBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.star);//�P�P
    	bombBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.bomb);//���u
    	lifeBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.life);//�R
    	shovelBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.shovel);//�K��
    	protectorBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.protector);//�O�@��
    	timerBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.timer);//�w��
    	//�~��
    	coveringBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.covering);
    	//�Ʀr
		numbers=new Bitmap[]{//�Ʋզ��
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number0),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number1),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number2),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number3),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number4),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number5),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number6),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number7),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number8),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number9)
			};
		gameOver =BitmapFactory.decodeResource(this.getResources(), R.drawable.gameover);//�C���������
		restartBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.restart);//���ܭ��s�}�l�����
		controlBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.control);//�������s���
		redDotBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.reddot);//���I���
		fireBtnUpBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.fireup);//�o�g���
		fireBtnDownBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.firedown);//�o�g���
    }  
	//===========================================�D�귽��k== begin ====================
	//�C����������k
 	public static void overGame()
 	{
 		gameOverFlag=true;
 		TankGeneratorFlag=false;
 		heroGoFlag=true;
 		TankGoFlag=true;
 		TankChangeDirectionFlag=true;
 		heroBulletGoFlag=false;
 		tankSendBulletFlag=false;
 		tankBulletGoFlag=false;
 		AliveWallPaperTank.keyState=0;//���䪬�A�m��
 	}
 	//�˴���e���ȬO�_��������k
 	public static boolean isCurrentMissionCompleted()
 	{
 		return AliveWallPaperTank.countTankDestoryed>=Constant.TANK_TOTAL_NUM;	
 	}
 	//�P�_�O�_���ݫ̪���k
 	public static boolean isShuPing()
 	{
 		return (ssr.so==ScreenOrien.SP);
 	}
 	//===========================================?�˸մ���?== end ====================
 	
class EngineCrazyTank extends Engine 
 {
     private final Paint paint = new Paint();
     boolean ifDraw;

     private final Runnable drawTask = new Runnable() {
         public void run() {
             repaint();
         }
     };
     
     EngineCrazyTank() 
     {
    	 
     }

     @Override
     public void onCreate(SurfaceHolder surfaceHolder) 
     {
         super.onCreate(surfaceHolder);            
         setTouchEventsEnabled(true); 
     } 

     @Override
     public void onDestroy() 
     {
         super.onDestroy();       
     }

     @Override
     public void onVisibilityChanged(boolean visible) 
     {
    	 ifDraw=visible;  	 
    	 if(ifDraw)
         {
    		 //�p�G�i���A���s��l�ƩҦ��ƾ�
    		 this.initAllData();
        	 hd.postDelayed(drawTask, 1000 / 25);
         }
    	 else
    	 {
    		 //�p�G���i���A����Ҧ��u�{
    		 this.stopAllThreads();
    	 }
     }

     @Override
     public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) 
     {System.out.println(width+","+height);
        super.onSurfaceChanged(holder, format, width, height);
        //�۰ʧP�_��̽ݫ�
 		ssr=ScreenScaleUtil.calScale(width, height);
 		Constant.LEFT_TOP_X=ssr.lucX;
 		Constant.LEFT_TOP_Y=ssr.lucY;
 		this.initAllData();//��l�ƩҦ��ƾ�
 	}

     @Override
     public void onSurfaceCreated(SurfaceHolder holder) 
     {
         super.onSurfaceCreated(holder);
     }

     @Override
     public void onSurfaceDestroyed(SurfaceHolder holder) 
     {
        super.onSurfaceDestroyed(holder);
        this.stopAllThreads();// ����Ҧ��u�{
     }

     //�ୱ���l�̹��ɪ��^�դ�k
     @Override
     public void onOffsetsChanged(float xOffset, float yOffset,
             float xStep, float yStep, int xPixels, int yPixels) 
     {
    	 Constant.LEFT_TOP_X=(int) (ssr.lucX+xOffset);
  		 Constant.LEFT_TOP_Y=(int) (ssr.lucY+yOffset);
     }

     @Override
     public void onTouchEvent(MotionEvent event) 
     {
         float y = event.getY();
         float x = event.getX();
         switch (event.getAction()) {
 	        case MotionEvent.ACTION_DOWN://===========���U������L==========
 	            if(//===== up ===
 	            		Constant.pointIsInRect
 	            		(
 	            				x, y, 
 	            				Constant.UP_X, Constant.UP_Y,Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT
 	            		)
 	            )
 	            {
 	            	keyState=(keyState==0x1)?0:0x1;
 	            }
 	            else if(//===== down ===
 		            		Constant.pointIsInRect
 		            		(
 		            				x, y, 
 		            				Constant.DOWN_X, Constant.DOWN_Y,Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT
 		            		)
 	            		)
 	            {
 	            	keyState=(keyState==0x2)?0:0x2;
 	            }
 	            else if(//===== left ===
 	            		Constant.pointIsInRect
 	            		(
 	            				x, y, 
 	            				Constant.LEFT_X, Constant.LEFT_Y,Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT
 	            		)
             		)
 		            {
 	            		keyState=(keyState==0x4)?0:0x4;
 		            }
 	            else if(//===== right ===
 	            		Constant.pointIsInRect
 	            		(
 	            				x, y, 
 	            				Constant.RIGHT_X, Constant.RIGHT_Y,Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT
 	            		)
             		)
 	            {
 	            	keyState=(keyState==0x8)?0:0x8;
 	            }
 	            else if(//===== fire area ===
 	            		Constant.pointIsInRect
 	            		(
 	            				x, y, 
 	            				Constant.FIRE_BTN_X, Constant.FIRE_BTN_Y, Constant.FIRE_BTN_WIDTH, Constant.FIRE_BTN_HEIGHT
 	            		)
             		)
 	            {
 	            	fireButtonDownFlag=((fireButtonDownFlag==true)?false:true);
 	            	if(fireButtonDownFlag)
 	            	{
 	            		AliveWallPaperTank.heroSendBulletFlag=true;
 	            		AliveWallPaperTank.heroSendBullet=new HeroSendBulletThread();
 	            		heroSendBullet.start();
 	            	}
 	            	else
 	            	{
 	            		AliveWallPaperTank.heroSendBulletFlag=false;
 	            	}
 	            	
 	            }
 	            //�p�G�C�������A�I��������Ĳ���ϰ�A���s�}�l�C��
 	            if(gameOverFlag)
 	            {
 	            	if(
 	            			Constant.pointIsInRect
	 	            		(
	 	            				x, y, 
	 	            				Constant.FIRE_BTN_X, Constant.FIRE_BTN_Y, Constant.FIRE_BTN_WIDTH, Constant.FIRE_BTN_HEIGHT
	 	            		)
 	            	)
 	            	{
 	            		stopAllThreads();//����H�e�Ҧ��u�{
 	            		initAllData();//���s��l�ƩҦ��ƾکM�u�{
 	            	}
 	            }
 		        break;
 		        //===================����===============================================
         }
     }


     void repaint()
     {
         final SurfaceHolder holder = getSurfaceHolder();
         Canvas c = null;
         try 
         {
             c = holder.lockCanvas();
             if (c != null) 
             {
            	 onDraw(c);
             }
         } 
         finally 
         {
             if (c != null) holder.unlockCanvasAndPost(c);
         }
         if(ifDraw)
         {
        	 hd.postDelayed(drawTask, 1000 / 20);
         }
     }
     public void onDraw(Canvas canvas)
 	{	
 		canvas.drawColor(Color.argb(255, 0, 0, 0));//���Ŭɭ�
 		//ø�s�U�h�a��
 		map.drawSelfBelow(canvas, paint);
 		hero.drawSelf(canvas, paint);//ø�s�^���Z�J
 		//ø�s�Ĥ�Z�J
 		ArrayList<Tank> alTank=new ArrayList<Tank>(AliveWallPaperTank.alTank);//��o��e�w�s�b�ĩZ�J�s��C��
 		for(Tank t:alTank)
 		{
 			t.drawSelf(canvas, paint);
 		}
 		//ø�s�^���l�u
 		ArrayList<HeroBullet> alHeroBullet=new ArrayList<HeroBullet>(AliveWallPaperTank.alHeroBullet);//�ƻs�Ĥl�u�C��
 		for(HeroBullet hb:alHeroBullet)
 		{
 			hb.drawSelf(canvas, paint);
 		}
 		//ø�s�Ĥ�l�u
 		ArrayList<Bullet> alBullet=new ArrayList<Bullet>(AliveWallPaperTank.alBullet);//�ƻs�Ĥl�u�C��
 		for(Bullet b:alBullet)
 		{
 			b.drawSelf(canvas, paint);
 		}
 		//ø�s�W�h�a��
 		map.drawSelfAbove(canvas, paint);
 		if(AliveWallPaperTank.isShuPing())
 		{
 			//ø�s�̹��k���ƾګH��
 			drawAllDataMessageSP(canvas,paint);
 			//ø�s��������
 			drawVirtualButtonSP(canvas,paint);
 		}
 		else
 		{
 			//ø�s�̹��W���ƾګH��
 			drawAllDataMessageHP(canvas,paint);
 			//ø�s��������
 			drawVirtualButtonHP(canvas,paint);
 			
 		}
 		//��C�������ɡAø�sGame Over���ܫH��
 		long currentTime=System.currentTimeMillis();//�O����e�ɶ�
 		fullTime=(int) ((currentTime-gameStartTime)/1000);//�O���C���`�ɶ�
 		
 		if(gameOverFlag)
 		{
 			if(fullTime%2==0)
 			{//ø�s�C�������ɭ�
 				canvas.drawBitmap(gameOver, SCREEN_WIDTH/2-100, SCREEN_HEIGHT/2-26, paint);
 				canvas.drawBitmap(restartBitmap, SCREEN_WIDTH/2-100, SCREEN_HEIGHT-120, paint);
 			}
 		}

 	}
 	//=============================================== SP =========================== begin =======
 	//ø�s�̹��k���ƾګH������k
 	void drawAllDataMessageSP(Canvas canvas,Paint paint)
 	{
 		paint.setColor(Color.RED);	//�N�e���C��]�m������
 		//ø�s�W���Ǧ��
 		canvas.drawRect
 		(
 				Constant.GAME_VIEW_X,						//left, 
 				Constant.GAME_VIEW_Y-Constant.UP_BAR-2,			//top, 
 				Constant.GAME_VIEW_X+Constant.GAME_VIEW_WIDTH,	//right, 
 				Constant.GAME_VIEW_Y-2,	//bottom, 
 				paint
 		);
 		paint.setColor(Color.YELLOW);	//�N�e���C��]�m������
 		paint.setTextSize(13);		//�]�m�r��j�p
 		
 		drawOneDataMessageSP(5,"�o��",score,canvas,paint);
 		drawOneDataMessageSP(3,"����",countTankDestoryed,canvas,paint);
 		drawOneDataMessageSP(4,"�^��",Hero.HEROLIFE,canvas,paint);
 		drawOneDataMessageSP(1,"���d",map.getMissionNum(),canvas,paint);
 		drawOneDataMessageSP(2,"�ĩZ�J",Constant.TANK_TOTAL_NUM,canvas,paint);
 	}
 	void drawOneDataMessageSP(int order,String msg,int number,Canvas canvas,Paint paint)
 	{		
 		canvas.drawText
 		(
 				msg, 
 				Constant.GAME_VIEW_X+Constant.FIRST_HANZI_WIDTH+(order-1)*Constant.HANZI_WIDTH, 
 				Constant.GAME_VIEW_Y-Constant.HANZI_HEIGHT-Constant.NUMBER_HEIGHT, 
 				paint
 		);
 		String numberStr=number+"";
 		for(int i=0;i<numberStr.length();i++)
 		{
 			char c=numberStr.charAt(i);
 			canvas.drawBitmap
 			(
 					numbers[c-'0'], 
 					Constant.GAME_VIEW_X+Constant.FIRST_NUMBER_WIDTH+order*Constant.NUMBER_TOTAL_WIDTH-Constant.NUMBER_WIDTH*(numberStr.length()-i), 
 					Constant.GAME_VIEW_Y-Constant.NUMBER_HEIGHT, 
 					paint
 			);
 		}
 	}
 	//�e�������s����k
 	void drawVirtualButtonSP(Canvas canvas,Paint paint)
 	{
 		canvas.drawBitmap
 		(
 				AliveWallPaperTank.controlBitmap, 
 				Constant.BUTTON_X,
 				Constant.BUTTON_Y, 
 				paint
 		);
// 		//�Φ���x�μаO�iĲ���ϰ�
// 		paint.setColor(Color.BLUE);
// 		drawColorRect
// 		(
// 				canvas,paint,
// 				Constant.UP_X, Constant.UP_Y,Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT
// 		);
// 		drawColorRect
// 		(
// 				canvas,paint,
// 				Constant.DOWN_X, Constant.DOWN_Y,Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT
// 		);
// 		drawColorRect
// 		(
// 				canvas,paint,
// 				Constant.LEFT_X, Constant.LEFT_Y,Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT
// 		);
// 		drawColorRect
// 		(
// 				canvas,paint,
// 				Constant.RIGHT_X, Constant.RIGHT_Y,Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT
// 		);
// 		//�аO�o�g�l�u���ϰ�
// 		drawColorRect
// 		(
// 				canvas,paint,
// 				Constant.FIRE_BTN_X, Constant.FIRE_BTN_Y, Constant.FIRE_BTN_WIDTH, Constant.FIRE_BTN_HEIGHT
// 		);
 		//�e������V��Ϥ�
 		canvas.drawBitmap
 		(
 				AliveWallPaperTank.controlBitmap, 
 				Constant.BUTTON_X,
 				Constant.BUTTON_Y, 
 				paint
 		);
 		switch(keyState)
 		{
 			case 0x1:	//�W
 			{
 				//�e���I
 				canvas.drawBitmap
 				(
 						AliveWallPaperTank.redDotBitmap, 
 						Constant.RED_DOT_CENTER_X,
 						Constant.RED_DOT_CENTER_Y-Constant.BUTTON_HEIGHT, 
 						paint
 				);
 			}
 			break;
 			case 0x2:	//�U
 			{
 				//�e���I
 				canvas.drawBitmap
 				(
 						AliveWallPaperTank.redDotBitmap, 
 						Constant.RED_DOT_CENTER_X,
 						Constant.RED_DOT_CENTER_Y+Constant.BUTTON_HEIGHT, 
 						paint
 				);
 			}
 			break;
 			case 0x4:	//��
 			{
 				//�e���I
 				canvas.drawBitmap
 				(
 						AliveWallPaperTank.redDotBitmap, 
 						Constant.RED_DOT_CENTER_X-Constant.BUTTON_WIDTH,
 						Constant.RED_DOT_CENTER_Y, 
 						paint
 				);
 			}
 			break;
 			case 0x8:	//�k
 			{
 				//�e���I
 				canvas.drawBitmap
 				(
 						AliveWallPaperTank.redDotBitmap, 
 						Constant.RED_DOT_CENTER_X+Constant.BUTTON_WIDTH,
 						Constant.RED_DOT_CENTER_Y, 
 						paint
 				);
 			}
 			break;
 			case 0:
 			{
 				//�e���I
 				canvas.drawBitmap
 				(
 						AliveWallPaperTank.redDotBitmap, 
 						Constant.RED_DOT_CENTER_X,
 						Constant.RED_DOT_CENTER_Y, 
 						paint
 				);
 			}
 			break;
 		}//switch
 		//�e�o�g�Ϥ�
 		if(fireButtonDownFlag)
 		{//System.out.println("+++++fireButtonDownFlag++++++ true ++++++");
 			canvas.drawBitmap
 			(
 					AliveWallPaperTank.fireBtnDownBitmap, 
 					Constant.FIR_MAP_X,
 					Constant.FIR_MAP_Y, 
 					paint
 			);
 		}
 		else
 		{//System.out.println("+++++fireButtonDownFlag+++++++ false        +++++");
 			canvas.drawBitmap
 			(
 					AliveWallPaperTank.fireBtnUpBitmap, 
 					Constant.FIR_MAP_X,
 					Constant.FIR_MAP_Y, 
 					paint
 			);
 		}
 	}
 	//=============================================== SP =========================== end =======
 	//=============================================== HP ====== begin =======
 	//ø�s�̹��k���ƾګH������k
 	void drawAllDataMessageHP(Canvas canvas,Paint paint)
 	{
 		paint.setColor(Color.RED);	//�N�e���C��]�m���Ǧ�
 		//ø�s�k���Ǧ��
 		canvas.drawRect
 		(
 				Constant.GAME_VIEW_X+Constant.GAME_VIEW_WIDTH+1,						//left, 
 				Constant.GAME_VIEW_Y,												//top, 
 				Constant.GAME_VIEW_X+Constant.GAME_VIEW_WIDTH+Constant.RIGHT_BAR,	//right, 
 				Constant.GAME_VIEW_Y+Constant.GAME_VIEW_HEIGHT,						//bottom, 
 				paint
 		);
 		paint.setColor(Color.YELLOW);	//�N�e���C��]�m������
 		paint.setTextSize(13);		//�]�m�r��j�p
 		
 		drawOneDataMessageHP(5,"�o��",score,canvas,paint);
 		drawOneDataMessageHP(3,"����",countTankDestoryed,canvas,paint);
 		drawOneDataMessageHP(4,"�^��",Hero.HEROLIFE,canvas,paint);
 		drawOneDataMessageHP(1,"���d",map.getMissionNum(),canvas,paint);
 		drawOneDataMessageHP(2,"�ĩZ�J",Constant.TANK_TOTAL_NUM,canvas,paint);
 	}
 	void drawOneDataMessageHP(int order,String msg,int number,Canvas canvas,Paint paint)
 	{
 		//ø�s�^���ƶq		
 		canvas.drawText
 		(
 				msg, 
 				Constant.GAME_VIEW_X+Constant.GAME_VIEW_WIDTH+Constant.RIGHT_BAR+2, 
 				Constant.GAME_VIEW_Y+Constant.FIRST_MESSAGE_HEIGHT+(order-1)*Constant.HANZI_HEIGHT+(order-1)*Constant.NUMBER_HEIGHT, 
 				paint
 		);
 		String numberStr=number+"";
 		for(int i=0;i<numberStr.length();i++)
 		{
 			char c=numberStr.charAt(i);
 			canvas.drawBitmap
 			(
 					numbers[c-'0'], 
 					Constant.GAME_VIEW_X+SCREEN_WIDTH-Constant.NUMBER_WIDTH*(numberStr.length()-i), 
 					Constant.GAME_VIEW_Y+Constant.FIRST_MESSAGE_HEIGHT+order*Constant.HANZI_HEIGHT+(order-1)*Constant.NUMBER_HEIGHT, 
 					paint
 			);
 		}
 	}
 	//�e�������s����k
 	void drawVirtualButtonHP(Canvas canvas,Paint paint)
 	{
 		//�e������V��Ϥ�
 		canvas.drawBitmap
 		(
 				AliveWallPaperTank.controlBitmap, 
 				Constant.BUTTON_X,
 				Constant.BUTTON_Y, 
 				paint
 		);
// 		//�Φ���x�μаO�iĲ���ϰ�
// 		paint.setColor(Color.BLUE);
// 		drawColorRect
// 		(
// 				canvas,paint,
// 				Constant.UP_X, Constant.UP_Y,Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT
// 		);
// 		drawColorRect
// 		(
// 				canvas,paint,
// 				Constant.DOWN_X, Constant.DOWN_Y,Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT
// 		);
// 		drawColorRect
// 		(
// 				canvas,paint,
// 				Constant.LEFT_X, Constant.LEFT_Y,Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT
// 		);
// 		drawColorRect
// 		(
// 				canvas,paint,
// 				Constant.RIGHT_X, Constant.RIGHT_Y,Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT
// 		);
// 		//�аO�o�g�l�u���ϰ�
// 		drawColorRect
// 		(
// 				canvas,paint,
// 				Constant.FIRE_BTN_X, Constant.FIRE_BTN_Y, Constant.FIRE_BTN_WIDTH, Constant.FIRE_BTN_HEIGHT
// 		);
 		//�e������V��Ϥ�
 		canvas.drawBitmap
 		(
 				AliveWallPaperTank.controlBitmap, 
 				Constant.BUTTON_X,
 				Constant.BUTTON_Y, 
 				paint
 		);
 		switch(keyState)
 		{
 			case 0x1:	//�W
 			{
 				//�e���I
 				canvas.drawBitmap
 				(
 						AliveWallPaperTank.redDotBitmap, 
 						Constant.RED_DOT_CENTER_X,
 						Constant.RED_DOT_CENTER_Y-Constant.BUTTON_HEIGHT, 
 						paint
 				);
 			}
 			break;
 			case 0x2:	//�U
 			{
 				//�e���I
 				canvas.drawBitmap
 				(
 						AliveWallPaperTank.redDotBitmap, 
 						Constant.RED_DOT_CENTER_X,
 						Constant.RED_DOT_CENTER_Y+Constant.BUTTON_HEIGHT, 
 						paint
 				);
 			}
 			break;
 			case 0x4:	//��
 			{
 				//�e���I
 				canvas.drawBitmap
 				(
 						AliveWallPaperTank.redDotBitmap, 
 						Constant.RED_DOT_CENTER_X-Constant.BUTTON_WIDTH,
 						Constant.RED_DOT_CENTER_Y, 
 						paint
 				);
 			}
 			break;
 			case 0x8:	//�k
 			{
 				//�e���I
 				canvas.drawBitmap
 				(
 						AliveWallPaperTank.redDotBitmap, 
 						Constant.RED_DOT_CENTER_X+Constant.BUTTON_WIDTH,
 						Constant.RED_DOT_CENTER_Y, 
 						paint
 				);
 			}
 			break;
 			case 0:
 			{
 				//�e���I
 				canvas.drawBitmap
 				(
 						AliveWallPaperTank.redDotBitmap, 
 						Constant.RED_DOT_CENTER_X,
 						Constant.RED_DOT_CENTER_Y, 
 						paint
 				);
 			}
 			break;
 		}//switch
 		//�e�o�g�Ϥ�
 		if(fireButtonDownFlag)
 		{
 			canvas.drawBitmap
 			(
 					AliveWallPaperTank.fireBtnDownBitmap, 
 					Constant.FIR_MAP_X,
 					Constant.FIR_MAP_Y, 
 					paint
 			);
 		}
 		else
 		{
 			canvas.drawBitmap
 			(
 					AliveWallPaperTank.fireBtnUpBitmap, 
 					Constant.FIR_MAP_X,
 					Constant.FIR_MAP_Y, 
 					paint
 			);
 		}
 	}
 	//=============================================== HP ====== end =======
 	//����ҵe�x�Ϊ���k
 	void drawColorRect
 	(
 			Canvas canvas,Paint paint,
 			float xLeftTop,float yLeftTop,float length,float width		//���ЭȦb0��1����
 	)
 	{
 		canvas.drawRect
 		(
 				xLeftTop,
 				yLeftTop,
 				(xLeftTop+length),
 				(yLeftTop+width),
 				paint
 		);
 	}
 	//��l�ƩҦ��ƾڪ���k
 	void initAllData()
 	{
 		//�n����l�Ʊ`�q�A�A��l�Ʀa�ϼƾڡI
 		Constant.initConst();//��l�Ʊ`�q
 		map=new BattleMap();//�Ыئa�Ϲﹳ
 		map.intiMapData();//��l�Ʀa�ϼƾ�		
 		hero=new Hero(AliveWallPaperTank.heroTanki1);//�Ыح^���Z�J�ﹳ(��m�P�`�q�����A�ҥH�n��b��l�Ʊ`�q�M�ƾګ�)
 		hero.backHome();//�^���^�a
 		//��l�ƺ޲z�C��
 		alTank=new ArrayList<Tank>();		
 		alHeroBullet=new Vector<HeroBullet>();	
 		alBullet=new ArrayList<Bullet>();
 		//��_���
 		score=0;
 		countTankDestoryed=0;
 		Hero.HEROLIFE=Constant.HERO_LIFE;
 		map.setMissionNum(1);
 		AliveWallPaperTank.map.reward=null;//�M�ż��y��
 		//��_�u�{�лx
 		AliveWallPaperTank.gameOverFlag=false;
 		AliveWallPaperTank.heroGoFlag=true;
 		AliveWallPaperTank.TankGeneratorFlag=true;
 		AliveWallPaperTank.TankGoFlag=true;
 		AliveWallPaperTank.TankChangeDirectionFlag=true;
 		AliveWallPaperTank.heroBulletGoFlag=true;
 		AliveWallPaperTank.tankSendBulletFlag=true;
 		AliveWallPaperTank.tankBulletGoFlag=true;
 		//�Ыؽu�{
 		generator=new TankGeneratorThread();
 		go=new TankGoThread();
 		heroGo=new HeroGoThread();
 		changeDirection=new TankChangeDirectionThread();
 		heroBulletGo=new HeroBulletGoThread();		
 		tankSendBullet=new TankSendBulletThread();
 		tankBulletGo=new TankBulletGoThread();
 		//�Ұʽu�{
 		generator.start();
 		go.start();
 		heroGo.start();
 		changeDirection.start();
 		heroBulletGo.start();
 		tankSendBullet.start();
 		tankBulletGo.start();
 	}
 	//����Ҧ��u�{����k
 	void stopAllThreads()
 	{
 		//�N�Ҧ�����u�{���лx�]��false
  		AliveWallPaperTank.heroGoFlag=false;
  		AliveWallPaperTank.TankGeneratorFlag=false;
  		AliveWallPaperTank.TankGoFlag=false;
  		AliveWallPaperTank.TankChangeDirectionFlag=false;
  		AliveWallPaperTank.heroBulletGoFlag=false;
  		AliveWallPaperTank.tankSendBulletFlag=false;
  		AliveWallPaperTank.tankBulletGoFlag=false;
 	}
  }
}

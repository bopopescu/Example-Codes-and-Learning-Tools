package com.bn.summer;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class WelcomeView extends SurfaceView 
implements SurfaceHolder.Callback   //��{�ͩR�g���^�ձ��f
{
	MainActivity activity;//activity���ޥ�
	Paint paint;      //�e��
	int currentAlpha=0;  //��e�����z����
	int screenWidth=320;   //�̹��e��
	int screenHeight=480;  //�̹�����
	int sleepSpan=50;      //�ʵe���ɩ�ms
	Bitmap[] logos=new Bitmap[2];//logo�Ϥ��Ʋ�
	Bitmap currentLogo;  //��elogo�Ϥ��ޥ�
	int currentX;      //�Ϥ���m
	int currentY;
	public WelcomeView(MainActivity activity)
	{
		super(activity);
		this.activity = activity;
		this.getHolder().addCallback(this);  //�]�m�ͩR�g���^�ձ��f����{��
		paint = new Paint();  //�Ыصe��
		paint.setAntiAlias(true);  //���}�ܿ���
		//�[���Ϥ�
		logos[0]=BitmapFactory.decodeResource(activity.getResources(), R.drawable.baina);
		logos[1]=BitmapFactory.decodeResource(activity.getResources(), R.drawable.bnkjs);		
	}
	public void onDraw(Canvas canvas)
	{	
		//ø�s�¶�R�x�βM�I��
		paint.setColor(Color.BLACK);//�]�m�e���C��
		paint.setAlpha(255);//�]�m���z���׬�255
		canvas.drawRect(0, 0, screenWidth, screenHeight, paint);
		//�i�業���K��
		if(currentLogo==null)return;
		paint.setAlpha(currentAlpha);		
		canvas.drawBitmap(currentLogo, currentX, currentY, paint);	
	}
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3)
	{
	}
	public void surfaceCreated(SurfaceHolder holder) //�ЫخɳQ�ե�	
	{	
		new Thread()
		{
			public void run()
			{
				for(Bitmap bm:logos)
				{
					currentLogo=bm;//��e�Ϥ����ޥ�
					currentX=screenWidth/2-bm.getWidth()/2;//�Ϥ���m
					currentY=screenHeight/2-bm.getHeight()/2;
					for(int i=255;i>-10;i=i-10)
					{//�ʺA���Ϥ����z���׭Ȩä��_��ø	
						currentAlpha=i;
						if(currentAlpha<0)//�p�G��e���z���פp��s
						{
							currentAlpha=0;//�N���z���׸m���s
						}
						SurfaceHolder myholder=WelcomeView.this.getHolder();//����^�ձ��f
						Canvas canvas = myholder.lockCanvas();//����e��
						try{
							synchronized(myholder)//�P�B
							{
								onDraw(canvas);//�i��ø�sø�s
							}
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						finally
						{
							if(canvas!= null)//�p�G��e�e��������
							{
								myholder.unlockCanvasAndPost(canvas);//����e��
							}
						}
						try
						{
							if(i==255)//�Y�O�s�Ϥ��A�h���ݤ@�|
							{
								Thread.sleep(1000);
							}
							Thread.sleep(sleepSpan);
						}
						catch(Exception e)//�ߥX���`
						{
							e.printStackTrace();
						}
					}
				}
				Message msg=new Message();
				msg.what=Constant.GOTOLOGIN;
				activity.hd.sendMessage(msg);//�o�e�����A�}�l�[���Ѥl�ҫ�
			}
		}.start();
	}
	public void surfaceDestroyed(SurfaceHolder arg0)
	{//�P���ɳQ�ե�
	}
}

package com.bn.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView 
implements SurfaceHolder.Callback  //��{�ͩR�g���^�ձ��f
{
	TrainSystemHelperActivity activity;
	Paint paint;//�e��
	int currentA=0;//��e�����z����
	int sleeptime=50;//�ʵe���ɩ�ms	
	Bitmap[] logos=new Bitmap[2];//logo�Ϥ��Ʋ�
	Bitmap currentLogo;//��elogo�Ϥ��ޥ�
	int currentX;
	int currentY;	
	
	public MySurfaceView(TrainSystemHelperActivity activity) {
		super(activity);
		this.activity = activity;
		this.getHolder().addCallback(this);//�]�m�ͩR�g���^�ձ��f����{��
		paint = new Paint();//�Ыصe��
		paint.setAntiAlias(true);//���}�ܿ���
		
		
		
		//�[���Ϥ�
		logos[0]=BitmapFactory.decodeResource(activity.getResources(), R.drawable.dukea); 
		logos[1]=BitmapFactory.decodeResource(activity.getResources(), R.drawable.dukeb);		
	}

	public void onDraw(Canvas canvas){	 
		//ø�s�¶�R�x�βM�I��
		paint.setColor(Color.BLACK);//�]�m�e���C��
		paint.setAlpha(255);
		canvas.drawRect(0, 0, activity.screenWidth, activity.screenHeight, paint);
		
		//�i�業���K��
		if(currentLogo==null)return;
		paint.setAlpha(currentA);		
		canvas.drawBitmap(currentLogo, currentX, currentY, paint);	
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		
	}

	public void surfaceCreated(SurfaceHolder holder) {//�ЫخɳQ�ե�		
		 
		new Thread()
		{
			public void run()
			{
				for(Bitmap bm:logos)
				{
					currentLogo=bm;
					//�p��Ϥ���m
					currentX=activity.screenWidth/2-bm.getWidth()/2;
					currentY=activity.screenHeight/2-bm.getHeight()/2;
					
					for(int i=255;i>-10;i=i-5)
					{//�ʺA���Ϥ����z���׭Ȩä��_��ø	
						currentA=i;
						if(currentA<0)
						{
							currentA=0;
						}
						SurfaceHolder myholder=MySurfaceView.this.getHolder();
						Canvas canvas = myholder.lockCanvas();//����e��
						try{
							synchronized(myholder){
								onDraw(canvas);//ø�s
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}
						finally{
							if(canvas != null){
								myholder.unlockCanvasAndPost(canvas);
							}
						}
						
						try
						{
							if(i==255)
							{//�Y�O�s�Ϥ��A�h���ݤ@�|
								Thread.sleep(2000);
							}
							Thread.sleep(sleeptime);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				}
				activity.hd.sendEmptyMessage(0);
			}
		}.start();
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {//�P���ɳQ�ե�

	}
}

package com.bn.reader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class WellcomeSurfaceView extends SurfaceView 
implements SurfaceHolder.Callback  //實現生命週期回調接口
{
	ReaderActivity activity;
	Paint paint;//畫筆
	int currentAlpha=0;//當前的不透明值
	
	int sleepSpan=50;//動畫的時延ms
	
	Bitmap[] logos=new Bitmap[2];//logo圖片數組
	Bitmap currentLogo;//當前logo圖片引用
	int currentX;
	int currentY;
	
	public WellcomeSurfaceView(ReaderActivity activity) {
		super(activity);
		this.activity = activity;
		this.getHolder().addCallback(this);//設置生命週期回調接口的實現者
		paint = new Paint();//創建畫筆
		paint.setAntiAlias(true);//打開抗鋸齒
		
		//加載圖片
		logos[0]=BitmapFactory.decodeResource(activity.getResources(), R.drawable.dukea); 
		logos[1]=BitmapFactory.decodeResource(activity.getResources(), R.drawable.dukeb);		
	}

	public void onDraw(Canvas canvas){	
		//繪製黑填充矩形清背景
		paint.setColor(Color.BLACK);//設置畫筆顏色
		paint.setAlpha(255);
		canvas.drawRect(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT, paint);
		//進行平面貼圖
		if(currentLogo==null)return;
		paint.setAlpha(currentAlpha);		
		canvas.drawBitmap(currentLogo, currentX, currentY, paint);
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		
	}

	public void surfaceCreated(SurfaceHolder holder) {//創建時被調用		
		new Thread()
		{
			public void run()
			{
				for(Bitmap bm:logos)
				{
					currentLogo=bm;
					//計算圖片位置
					currentX=Constant.SCREEN_WIDTH/2-bm.getWidth()/2;
					currentY=Constant.SCREEN_HEIGHT/2-bm.getHeight()/2;
					
					for(int i=255;i>-10;i=i-10)
					{//動態更改圖片的透明度值並不斷重繪	
						currentAlpha=i;
						if(currentAlpha<0)
						{
							currentAlpha=0;
						}
						SurfaceHolder myholder=WellcomeSurfaceView.this.getHolder();
						Canvas canvas = myholder.lockCanvas();//獲取畫布
						try{
							synchronized(myholder){
								onDraw(canvas);//繪製
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
							{//若是新圖片，多等待一會
								Thread.sleep(1000);
							}
							Thread.sleep(sleepSpan);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				}
				activity.sendMessage(WhatMessage.GOTO_MAIN_MENU_VIEW);
			}
		}.start();
		
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {//銷毀時被調用

	}
}

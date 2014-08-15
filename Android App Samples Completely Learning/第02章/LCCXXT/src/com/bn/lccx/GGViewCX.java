package com.bn.lccx;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GGViewCX extends View 
{
	int COMPONENT_WIDTH;							//�ӱ���e��
	int COMPONENT_HEIGHT;							//�ӱ��󰪫�
	boolean initflag=false;								//�O�_�n������󪺰��שM�e�׼лx
	Bitmap[] bma;										//�ݭn���񪺹Ϥ����Ʋ�
	Paint paint;										//�e��
	static int[] drawablesId;									//�Ϥ�ID�Ʋ�
	int currIndex=0;										//�Ϥ�ID�ƲդU�СA�ھڦ��ܶq�e�Ϥ�
	boolean workFlag=true;								//����Ϥ��u�{�лx��
	public GGViewCX(Context father,AttributeSet as) { 			//�c�y��
		super(father,as);								
		drawablesId=new int[]{						//��l�ƹϤ�ID�Ʋ�
		
			R.drawable.adv7,	
			R.drawable.adv8,	
			R.drawable.adv9
				
		};
		bma=new Bitmap[drawablesId.length];				//�Ыئs��Ϥ����Ʋ�
		initBitmaps();									//�եΪ�l�ƹϤ���ơA��l�ƹϤ��Ʋ�
		paint=new Paint();								//�Ыصe��
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);				//��������	
		new Thread(){									//�Ыؼ���Ϥ��u�{
			public void run(){
				while(workFlag){
					currIndex=(currIndex+1)%drawablesId.length;//����ID�ƲդU�Э�
					GGViewCX.this.postInvalidate();			//ø�s
					try {
						Thread.sleep(3000);				//�𮧤T��
					} catch (InterruptedException e) {						
						e.printStackTrace();
					}}}}.start();							//�Ұʽu�{
	}	
	public void initBitmaps(){								//��l�ƹϤ����
		Resources res=this.getResources();					//���Resources�ﹳ
		for(int i=0;i<drawablesId.length;i++){					
			bma[i]=BitmapFactory.decodeResource(res, drawablesId[i]);
		}}	
	public void onDraw(Canvas canvas){						//ø�s���
		if(!initflag) {									//�Ĥ@��ø�s�ɻݭn����e�שM����
			COMPONENT_WIDTH=this.getWidth();			//���view���e��
			COMPONENT_HEIGHT=this.getHeight();			//���view������
			initflag=true;
		}
		int picWidth=bma[currIndex].getWidth();				//�����eø�s�Ϥ����e��
		int picHeight=bma[currIndex].getHeight();				//�����eø�s�Ϥ�������
		int startX=(COMPONENT_WIDTH-picWidth)/2;			//�o��ø�s�Ϥ������W��X����
		int startY=(COMPONENT_HEIGHT-picHeight)/2; 		//�o��ø�s�Ϥ������W��Y����
		canvas.drawARGB(255, 200, 128, 128);				//�]�m�I����
		canvas.drawBitmap(bma[currIndex], startX,startY, paint);	//ø�s�Ϥ�
	}}




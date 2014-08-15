package com.bn.map;			//�n���]�y�y
import java.util.Vector;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import static com.bn.map.Constant.*;
public class GameView extends SurfaceView implements SurfaceHolder.Callback{
	MapMasetActivity activity;		//Activity�ޥ�
	Canvas c;
	SurfaceHolder holder;
    int scoreWidth = 10;
    int guanshuX;//���Ƥ�rX����
    int guanshuY;//���Ƥ�rY����
    int guanshu=1;
    Bitmap iback;//�I����
    Bitmap[] iscore=new Bitmap[10];//�o����
    Bitmap JianHaotupian;//���
    Bitmap JiaHaotupian;//�[����
    Bitmap[] guanShu=new Bitmap[10];//���ƹ�
    Bitmap time_wz;//�ɶ���r��
    Bitmap gread_wz;//���Z��r��
    Bitmap hengXian;//��u
	public GameView(MapMasetActivity activity) {
		super(activity);
		getHolder().addCallback(this);//���U�^�ձ��f
		this.activity = activity;
		initBitmap();
	}
	//�N�Ϥ��[��
	public void initBitmap(){
		iback = BitmapFactory.decodeResource(getResources(), R.drawable.main);
		iscore[0] = BitmapFactory.decodeResource(getResources(), R.drawable.d0);//�Ʀr��
		iscore[1] = BitmapFactory.decodeResource(getResources(), R.drawable.d1);
		iscore[2] = BitmapFactory.decodeResource(getResources(), R.drawable.d2);
		iscore[3] = BitmapFactory.decodeResource(getResources(), R.drawable.d3);
		iscore[4] = BitmapFactory.decodeResource(getResources(), R.drawable.d4);
		iscore[5] = BitmapFactory.decodeResource(getResources(), R.drawable.d5);
		iscore[6] = BitmapFactory.decodeResource(getResources(), R.drawable.d6);
		iscore[7] = BitmapFactory.decodeResource(getResources(), R.drawable.d7);
		iscore[8] = BitmapFactory.decodeResource(getResources(), R.drawable.d8);
		iscore[9] = BitmapFactory.decodeResource(getResources(), R.drawable.d9);
		
		guanShu[0] = BitmapFactory.decodeResource(getResources(), R.drawable.guanka);//���d��
		guanShu[1] = BitmapFactory.decodeResource(getResources(), R.drawable.guanka1);
		guanShu[2] = BitmapFactory.decodeResource(getResources(), R.drawable.guanka2);
		guanShu[3] = BitmapFactory.decodeResource(getResources(), R.drawable.guanka3);
		guanShu[4] = BitmapFactory.decodeResource(getResources(), R.drawable.guanka4);
		guanShu[5] = BitmapFactory.decodeResource(getResources(), R.drawable.guanka5);
		JiaHaotupian = BitmapFactory.decodeResource(getResources(), R.drawable.right);
		JianHaotupian = BitmapFactory.decodeResource(getResources(), R.drawable.left);
		gread_wz = BitmapFactory.decodeResource(getResources(), R.drawable.grade);//���Z��r
		time_wz= BitmapFactory.decodeResource(getResources(), R.drawable.time);//�ɶ���r
		hengXian=BitmapFactory.decodeResource(getResources(), R.drawable.hengxian);//��u
	}
	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas); 
		canvas.drawColor(Color.argb(255, 0, 0, 0));
		canvas.drawBitmap(iback,30,0, null);//�e�I��
		//ø�s��M�[���Ϥ�
		canvas.drawBitmap(JianHaotupian,SCREEN_WIDTH/6,SCREEN_HEIGHT/6+40, null);	
		//ø�s���d��r
		canvas.drawBitmap(guanShu[guanshu-1],SCREEN_WIDTH/2-60,SCREEN_HEIGHT/6+40, null);
		//ø�s�k��[��
		canvas.drawBitmap(JiaHaotupian,SCREEN_WIDTH/2+80,SCREEN_HEIGHT/6+40, null);
		//ø�s���Z��rgread_wz
		canvas.drawBitmap(gread_wz,SCREEN_WIDTH/6,SCREEN_HEIGHT/6+63, null);
		//ø�s�C���ɶ���r
		canvas.drawBitmap(time_wz,SCREEN_WIDTH/2+80,SCREEN_HEIGHT/6+63, null);
		String sql_select="select grade,time from rank where level="+guanshu+" order by grade desc limit 0,5;";
    	Vector<Vector<String>> vector=SQLiteUtil.query(sql_select);//�q�ƾڮw�����X�������ƾ�
    	for(int i=0;i<vector.size();i++)//�`��ø�s�Ʀ�]�����ƩM�����ɶ�
    	{
    		drawScoreStr(canvas,vector.get(i).get(0).toString(),SCREEN_WIDTH/6,SCREEN_HEIGHT/6+40+60+i*30);//���Z�A���
    		drawRiQi(canvas,vector.get(i).get(1).toString(),SCREEN_WIDTH/2+65,SCREEN_HEIGHT/6+40+60+i*30);
    	}
	}
	public void drawScoreStr(Canvas canvas,String s,int width,int height)//ø�s�r�Ŧ��k
	{
    	//ø�s�o��
    	String scoreStr=s; 
    	for(int i=0;i<scoreStr.length();i++){//�`��ø�s�o��
    		int tempScore=scoreStr.charAt(i)-'0';
    		canvas.drawBitmap(iscore[tempScore], width+i*scoreWidth,height, null);
    		}
	}
	public void drawRiQi(Canvas canvas,String s,int width,int height)//�e�~��
	{
		String ss[]=s.split("-");//���αo��~���
		drawScoreStr(canvas,ss[0],width,height);//�e�~�ƼƦr
		canvas.drawBitmap(hengXian,width+scoreWidth*4,height, null);//�e��u
		drawScoreStr(canvas,ss[1],width+scoreWidth*5,height);//�e��ƼƦr
		canvas.drawBitmap(hengXian,width+scoreWidth*7,height, null);//�e��u
		drawScoreStr(canvas,ss[2],width+scoreWidth*8,height);//�e��Ʀr
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){
		int x = (int) event.getX();
		int y = (int) event.getY();
		if(x>SCREEN_WIDTH/6&&x<SCREEN_WIDTH/6+60&&
				y>SCREEN_HEIGHT/6+40&&y<SCREEN_HEIGHT/6+40+40)
		{			
			if(guanshu>1)
			{
				guanshu--;
				c = null;
	            try {
	            	// ��w��ӵe���A�b���s�n�D����������p�U�A��ĳ�ѼƤ��n��null
	                c = holder.lockCanvas(null);
	                synchronized (holder) {
	                	onDraw(c);//ø�s
	                }
	            } finally {
	                if (c != null) {
	                	//��������
	                	holder.unlockCanvasAndPost(c);
	                }
	            }
			}
		}
		if(x>SCREEN_WIDTH/2+80&&x<SCREEN_WIDTH/2+140
				&&y>SCREEN_HEIGHT/6+40&&y<SCREEN_HEIGHT/6+80){			
			if(guanshu<MAPP.length)
			{
				guanshu++;
				c = null;
	            try {
	            	// ��w��ӵe���A�b���s�n�D����������p�U�A��ĳ�ѼƤ��n��null
	                c = holder.lockCanvas(null);
	                synchronized (holder) {
	                	onDraw(c);//ø�s
	                }
	            } finally {
	                if (c != null) {
	                	//��������
	                	holder.unlockCanvasAndPost(c);
	                }
	            }
			}
		}		
		return super.onTouchEvent(event);
	}
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
	public void surfaceCreated(SurfaceHolder holder) {//�ЫخɱҰʬ����i�{		
		this.holder=holder;        
            c = null;
            try {
            	// ��w��ӵe���A�b���s�n�D����������p�U�A��ĳ�ѼƤ��n��null
                c = holder.lockCanvas(null);
                synchronized (holder) {
                	onDraw(c);//ø�s
                }
            } finally {
                if (c != null) {
                	//��������
                	holder.unlockCanvasAndPost(c);
                }
            }
	}
	public void surfaceDestroyed(SurfaceHolder holder) {//�R������������i�{
	}
}


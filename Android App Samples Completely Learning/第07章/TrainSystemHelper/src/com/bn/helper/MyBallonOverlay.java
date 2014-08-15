package com.bn.helper;

import java.util.ArrayList;
import java.util.List;

import android.view.MotionEvent;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;
import android.graphics.*;

//��ܮ�y��Overlay
class MyBallonOverlay extends Overlay{
	final static int picWidth=33;  //��y�Ϫ��e��
	final static int picHeight=34; //��y�Ϫ�����
	final static int R=8;//�H�����f���ꨤ�b�|
	
	static MyBallonOverlay currentPIC=null;//��ܷ�e�襤����y
	String msg;	//����y��������r�H��
	Bitmap bm;//�������ϼ�
	
	boolean showWindow=false;//�O�_��ܤ�r�H�����f���лx��     ��true��ܤ�r�H�����f
	
	GeoPoint gp;//����y�������g�n�� 
   
	public MyBallonOverlay(GeoPoint gp,String msg,Bitmap bm)
	{
		this.gp=gp;
		this.msg=msg;
		this.bm=bm;
	}
	
    @Override 
    public boolean onTouchEvent(MotionEvent event, MapView mv) {
        if(currentPIC!=null&&currentPIC!=this)
        {
        	return false;
        }
    	
    	if(event.getAction() == MotionEvent.ACTION_DOWN)
        {    	
        	int x=(int)event.getX();
            int y=(int)event.getY();
            Point p= getPoint(mv); 
            
            int xb=p.x-picWidth/2;
            int yb=p.y-picHeight;
            
            if(x>=xb&&x<xb+picWidth&&y>=yb&&y<yb+picHeight)
            {    	
            	currentPIC=this;
            	return true;
            }
        }
    	else if (event.getAction() == MotionEvent.ACTION_MOVE) 
    	{
    		return currentPIC!=null;
    	}    		
        else if (event.getAction() == MotionEvent.ACTION_UP) 
        {
        	//���Ĳ������m
            int x=(int)event.getX();
            int y=(int)event.getY();
            
            //�����y�b�̹��W�����нd��
            Point p= getPoint(mv);             
            int xb=p.x-picWidth/2;
            int yb=p.y-picHeight;           
            
            if(currentPIC==this&&x>=xb&&x<xb+picWidth&&y>=yb&&y<yb+picHeight)
            {
            	//��ܧ����e��M�ŷ�e��y
            	currentPIC=null;     
            	showWindow=!showWindow;
            	
            	List<Overlay> overlays = mv.getOverlays();
            	overlays.remove(this);//�R������y�A�K�[
            	overlays.add(this);//����y�N���̤W���F
            	return true;
            }
            else if(currentPIC==this)
            {//�Y��e��y���ۤv����_Ĳ�����A�ۤv�W�h�M�Ů�y���A�ê�^true
            	currentPIC=null;
            	return true;            	
            }            
        }
        return false;
    }
    
    @Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {			   	
    	Point p= getPoint(mapView);  	    	
		canvas.drawBitmap(bm, p.x-picWidth/2+3, p.y-picHeight-12, null);
		
		if(showWindow)
		{
			drawWindow(canvas,p,170);
		}
		
		//�եΤ���ø�s
		super.draw(canvas, mapView, shadow);
	}
    
    public Point getPoint(MapView mapView)
    {//�N�g�n��½Ķ���̹��W��XY����
    	Projection projettion = mapView.getProjection();
		Point p = new Point();
		projettion.toPixels(gp, p); 
		return p;
    }
    
	public void drawWindow(Canvas canvas,Point p,int winWidth) 
	{//ø�s�H�����f����k
		int singleSize=15;
		int textSize=16;
		int leftPadding=10;
		
		//���H������
		int line_Width=winWidth-leftPadding*2;//�C�檺�e��
		int lineCharCount=line_Width/(singleSize);	//�C��r�ż�
		//���˾�ӫH���r�Ŧ�i�����
		ArrayList<String> totalRows=new ArrayList<String>();//�O���Ҧ��檺ArrayList
		String currentRow="";//��e�檺�r�Ŧ�
		for(int i=0;i<msg.length();i++)
		{			
			char c=msg.charAt(i);
			if(c!='\n')
			{//�Y��e�r�Ť�������ūh�[�J���e�椤
				currentRow=currentRow+c;
			}
			else
			{
				if(currentRow.length()>0)
				{
					totalRows.add(currentRow);
				}				
				currentRow="";//�M�ŷ�e��
			}
			if(currentRow.length()==lineCharCount)	
			{//�Y��e�檺���׹F��@��W�w���r�żƫh
		     //?�Ի�v�h�׬��꧲������rrayList
				totalRows.add(currentRow);
				currentRow="";//�M�ŷ�e��
			}
		}
		if(currentRow.length()>0)
		{//�Y��e����פj��s�h�N��e��[�J�O���Ҧ��檺ArrayList
			totalRows.add(currentRow);
		}
		int lineCount=totalRows.size();//��o�`���
		int winHeight=lineCount*(singleSize)+2*R;//�۰ʭp��H�����鰪��
		//�Ы�paint�ﹳ
		Paint paint=new Paint();
		paint.setAntiAlias(true);//���}�ܿ���
		paint.setTextSize(textSize);//�]�m��r�j�p	
		//ø�s �H������ꨤ�x��
		paint.setARGB(255, 255,251,239);
		int x1=p.x-winWidth/2;
		int y1=p.y-picHeight-winHeight-12;
		int x2=p.x+winWidth/2;
		int y2=p.y-picHeight-12;		
		RectF r=new RectF(x1,y1,x2,y2);		
		canvas.drawRoundRect(r, R, R, paint);
		//ø�s �H������ꨤ�x�����
		paint.setARGB(255,198,195,198);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(2);
		canvas.drawRoundRect(r, R, R, paint);
		
		//�`��ø�s�C���r
		paint.setStrokeWidth(0);
		paint.setARGB(255, 10, 10, 10);		
		int lineY=y1+R+singleSize;
		for(String lineStr:totalRows)
		{//��C�@��i��`��	
			for(int j=0;j<lineStr.length();j++)
			{//��@�椤���C�Ӧr�`��
				String colChar=lineStr.charAt(j)+"";				
				canvas.drawText(colChar, x1+leftPadding+j*singleSize, lineY, paint);
			}
			lineY=lineY+singleSize;//y���в��V�U�@��
		}
	}
}




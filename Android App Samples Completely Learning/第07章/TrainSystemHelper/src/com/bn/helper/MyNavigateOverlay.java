package com.bn.helper;
import android.view.MotionEvent;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;
import android.graphics.*;

//�Ω�ø�s�ɯ���u�Ϫ�Overlay
class MyNavigateOverlay extends Overlay
{    
	
	static int DWidth=20;
	static int DHeight=29;
	
	GeoPoint[] points;//���u���`�I�g�n�׼Ʋ�
    Paint paint;//�e��

    double direction;//��V��    
    int StartIn;//��e�l���|�_�I����
    int step;//��e�l���|�B�Ư���
    int total;//��e�l���|�`�B��
    GeoPoint gpCurr;//�ɯ�T���η�e��m
    
	public MyNavigateOverlay(GeoPoint[] points)
	{
		this.points=points;	
		paint = new Paint();//�Ыصe��
		paint.setAntiAlias(true);//���}�ܿ���
		paint.setARGB(90,0,0,255);;//�]�m�e���C��
		paint.setStrokeWidth(5);//�]�m���uø�s�e��
	}
    
	@Override 
    public boolean onTouchEvent(MotionEvent event, MapView mv) 
	{//�������B�zĲ���ƥ�
		return false;
	}
	
    @Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
    	
    	//�`��ø�s�ɯ���|
    	for(int i=0;i<points.length-1;i++)
    	{
    		//���X�C���l���|���_�I�B���I
    		Point Start=getPoint(mapView,points[i]);
    		Point End=getPoint(mapView,points[i+1]);
    			
    		paint.setARGB(160,0,0,255);;//�]�m�e���C��
    		canvas.drawLine(Start.x, Start.y, End.x, End.y, paint);
    	}
    	
    		
    	if(MapNavigateActivity.ison&total!=0)
    	{
    		
            GeoPoint gp=points[StartIn];
            
            GeoPoint gpNext=points[StartIn+1];
            
            gpCurr=new GeoPoint
            (
                  gp.getLatitudeE6()+(gpNext.getLatitudeE6()-gp.getLatitudeE6())*step/total,
                  gp.getLongitudeE6()+(gpNext.getLongitudeE6()-gp.getLongitudeE6())*step/total
            );
            
            Point pCurr=getPoint(mapView,gpCurr); 
            
           
            if(pCurr.x<Constant.BORDER_WIDTH||pCurr.x>Constant.MAP_VIEW_WIDTH-Constant.BORDER_WIDTH||
               pCurr.y<Constant.BORDER_WIDTH||pCurr.y>Constant.MAP_VIEW_HEIGHT-Constant.BORDER_WIDTH)
            {
               mapView.getController().setCenter(gpCurr);            	
            }
            
            //��ø�a��            
            mapView.postInvalidate();
            //����ɯ�T���Ϊ�XY����
			float dnX=pCurr.x;
			float dnY=pCurr.y;
			
    		Matrix m1=new Matrix();
    		m1.setRotate((float)direction,dnX,dnY);	  
    		Matrix m2=new Matrix();
    		m2.setTranslate(dnX-DWidth/2,dnY-DHeight/2);
    		Matrix mz=new Matrix();
    		mz.setConcat(m1, m2);    	
    		paint.setARGB(200,0,0,255);;//�]�m�e���C��
    		canvas.drawBitmap(MapNavigateActivity.bitmapDirection, mz, paint);
    	}
    	
    	//�եΤ���ø�s
    	super.draw(canvas, mapView, shadow);
	}
    
    public Point getPoint(MapView mapView,GeoPoint gp)
    {//�N�g�n��½Ķ���̹��W��XY����
    	Projection projettion = mapView.getProjection();
		Point p = new Point();
		projettion.toPixels(gp, p); 
		return p;
    }
    
    //�ھڷ�e���`�I�p��ɯ�T���Τ�V�� 
    public void calDirection(int dIndex, MapView mapView)
    {
    	StartIn=dIndex;    	
    	Point dp=getPoint(mapView,points[dIndex]);   
		if(dIndex<points.length-1)
		{//�S����̫�@���I�ݭn�p���V
			Point dpNext=getPoint(mapView,points[dIndex+1]); 					
			float dx=dpNext.x-dp.x;
			float dy=-(dpNext.y-dp.y);

			int c=1;
			//�Y�U�@���I�P���I���X�A�h�A���U�@���I
            while(dx==0&&dy==0)
            {
            	c++;
            	dpNext=getPoint(mapView,points[dIndex+c]); 
            	dx=dpNext.x-dp.x;
    			dy=-(dpNext.y-dp.y);
            }
            
			if(dx!=0||dy!=0)
			{
				if(dx>0&&dy>0)
    			{//�Ĥ@�H��
					direction=Math.toDegrees(Math.atan(dx/dy));
    			}
				else if(dx<0&&dy>0)
				{//�ĤG�H��
					direction=360-Math.toDegrees(Math.atan(-dx/dy));
				} 
				else if(dx<0&&dy<0)
				{//�ĤT�H��
					direction=180+Math.toDegrees(Math.atan(dx/dy));
				}
				else if(dx>0&&dy<0)
				{//�ĥ|�H��
					direction=180-Math.toDegrees(Math.atan(dx/-dy));
				}	
				else if(dx==0)
				{
					if(dy>0)
					{
						direction=0;
					}
					else
					{
						direction=180;
					}
				}
				else if(dy==0)
				{
					if(dx>0)
					{
						direction=90;
					}
					else
					{
						direction=270;
					}
				}
			}
		}
		
    }
}




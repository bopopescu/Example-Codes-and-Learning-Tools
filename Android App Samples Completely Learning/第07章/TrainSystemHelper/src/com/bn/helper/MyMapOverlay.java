package com.bn.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.bn.helper.MyBallonOverlay;
import static com.bn.helper.Constant.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

//�л\��Ӧa�Ϯ���Ĳ���ƥ󪺳z��OverLay
class MyMapOverlay extends Overlay{
	boolean flagMove=false;//�O�_�����ʰʧ@�лx��
	static int nState=0; //���A�лx�� 0��ܶ}�l���A 1��ܤw�g�]�m�_�l�I�����A
	static GeoPoint gpStart;//�_�l�I�g�n��
	static GeoPoint gpEnd;//�����I�g�n��
	
	float previousX;//�W��Ĳ����X����
	float previousY;//�W��Ĳ����Y����
	
	static final int MOVE_THRESHOLD=10;//��Ĳ���I���ʽd�򤣤j���H�Ȯ��ٻ{���O�I��
	static Bitmap bitmapStart;
	 @Override 
	    public boolean onTouchEvent(MotionEvent event, MapView mv) {
	        if(event.getAction() == MotionEvent.ACTION_MOVE)
	        {//�Y���ʤFĲ�����h�]�m���ʼлx��true
	        	if(flagMove!=true)
	        	{
	        		float dx=Math.abs(event.getX()-previousX);
	        		float dy=Math.abs(event.getY()-previousY);	        		
	        		if(dx>MOVE_THRESHOLD||dy>MOVE_THRESHOLD)
	        		{
	        			flagMove=true;
	        		}
	        	}
	        }
	        else if(event.getAction() == MotionEvent.ACTION_DOWN)
	        {//�Y���U�FĲ�����h�]�m���ʼлx��false�A�ðO�����UĲ��������m
	        	flagMove=false;
	        	previousX=event.getX();
	        	previousY=event.getY();
	        }
	        else if (MyBallonOverlay.currentPIC==null&&
	        		 !flagMove&&
	        		 event.getAction() == MotionEvent.ACTION_UP ) 
	        {
	        	Vector<String> ve=new Vector<String>();
	        	ve=DBUtil.searchjwsn();	        	
	        	GeoPoint gp = mv.getProjection().fromPixels
	            (
	        		 (int) event.getX(),  //Ĳ�����b�̹��W��X����
	        		 (int) event.getY()   //Ĳ�����b�̹��W��Y����
	            );	            
	        	//����I���B���g�n��
	            String latStr=Math.round(gp.getLatitudeE6()/1000.00)/1000.0+"";//�n��
	        	String longStr=Math.round(gp.getLongitudeE6()/1000.00)/1000.0+"";//�g��
	        	
	        	
	        	for(int n=0;n<ve.size()-1;n=n+3)
	        	{
	        		if(TrainOverlay.calculationdistance(longStr, latStr, ve.get(n).toString(), ve.get(n+1).toString())<XIU_ZHENG)
	        		{
	        			double jj=Double.parseDouble(ve.get(n));  //��Ƽƾ�
	        	        double ww=Double.parseDouble(ve.get(n+1));  //��Ƭ��ƾ�
	        	        GeoPoint gpp = new GeoPoint
	        	        (
	        	        		(int)(ww*1E6),  //�n��
	        	        		(int)(jj*1E6)  //�g��
	        	        );
	        			
	    	        	List<Overlay> overlays = mv.getOverlays(); 
	    	        	int i=0;
	    	        	for(Overlay ol:overlays)
	    	        	{//�M����L��y��showWindow�аO
	    	        				i++;	    	        		
	    	        	} 
	    	        	if(i<6)
	    	        	{
	    	        		MyBallonOverlay mbo=new MyBallonOverlay
	    		        	(
	    		        			gpp,		//��y������
	    		        			ve.get(n+2).toString()+"\n�g�סG"+longStr+"\n�n�סG"+latStr, //��y���H��
	    		        			MapNavigateActivity.bitmapEnd
	    		        	);
	    	        		gpEnd=gp;
	    	        		mbo.showWindow=true;
	    		            overlays.add(mbo); 
	    	        	}
	    	        	if(i>=6)
	    	        	{	    	        		
	    	        	   	Overlay first1=overlays.get(0);
	    	        	   	Overlay first2=overlays.get(1);
	    	        	   	Overlay first3=overlays.get(2);
	    	        	   	Overlay first4=overlays.get(3);
	    	        	   	Overlay first5=overlays.get(4);
	    	        		List<Overlay> tol=new ArrayList<Overlay>();
	    	        		for(int j=5;j<overlays.size();j++)
	    	        		{
	    	        			tol.add(overlays.get(j));
	    	        		}
	    	        		//�M�ũҦ�Overlay
	    	        		overlays.clear();
	    	        		//�K�[�Ω��������Ĳ���ƥ󪺳z��Overlay
	    	        		overlays.add(first1);
	    	        		overlays.add(first2);
	    	        		overlays.add(first3);
	    	        		overlays.add(first4);
	    	        		overlays.add(first5);
	    	        		MyBallonOverlay mbo=new MyBallonOverlay
	    		        	(
	    		        			gpp,		//��y������
	    		        			ve.get(n+2).toString()+"\n�g�סG"+longStr+"\n�n�סG"+latStr, //��y���H��
	    		        			MapNavigateActivity.bitmapEnd
	    		        	);
	    	        		gpEnd=gpp;
	    	        		mbo.showWindow=true;
	    		            overlays.add(mbo); 
	    	        	}	        	
	        		}//"��e���I�G\n"+ve.get(n+2).toString()'''"�a�z���Ь��G\n�g�סG"+longStr+"\n�n�סG"+latStr
	        	}	        		        	
	            return true;
	        }
	        return false;
	    }

}

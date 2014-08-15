package com.bn.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.w3c.dom.Document;
import com.bn.helper.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Toast;


public class MapNavigateActivity extends MapActivity {
	
	static Bitmap bitmapStart;//�_�I��y�Ϥ�	 
	static Bitmap bitmapEnd;//���I��y�Ϥ�
	static Bitmap bitmapDirection;//�ɯ�b�Y��y�Ϥ�
	static Bitmap station;
	static Bitmap smallstation;
	static boolean inQuery=false;//���b�d�ߤ��лx��
	static boolean ison=false;//�O�_ø�s�ɯ�b�Y�лx
	
	static Handler hd;
	static String msg;
	
	MapController mc;//�a�ϱ��
	MyNavigateOverlay  currMyNa;//��e���ɯ�Overlay 	
	GeoPoint gpLocation;
	boolean flag=false;//�O�_��ܤF�ۤv��m
	
	boolean yyhh=false;//�ʵe���\�лx��
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //�]�m�����
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        //�U��y���]�m����
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        
        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();        
        String jdStr=bundle.getString("jdStr");//"118.190088";
        String wdStr=bundle.getString("wdStr");//"39.637877";
        String msg=bundle.getString("msg");//"�ؼ�U�a\n����e�_�٭�s�����n�Ϥ�Ƹ�";
        double jd=Double.parseDouble(jdStr);
        double wd=Double.parseDouble(wdStr);
        //�ؼаө����g�n��
        GeoPoint gp = new GeoPoint
        (
        		(int)(wd*1E6), //�n��
        		(int)(jd*1E6) //�g��
        );
               
       
        new Thread()
        {
        	public void run()
        	{
        		Looper.prepare();
        		
        		hd=new Handler()
                {
                	@Override
                	public void handleMessage(Message msg)
                	{
                		super.handleMessage(msg);
                		
                		switch(msg.what)
                		{
                		   case Constant.DISPLAY_TOAST:
                			Toast.makeText
                       		(
                       			MapNavigateActivity.this, 
                       			MapNavigateActivity.msg, 
                       			Toast.LENGTH_SHORT
                       		).show();
                		   break;
                		}
                	}
                };        		        		
        		Looper.loop();
        	}
        }.start();
        //��l�Ʀ۩w�q�����B�z���Ψ�����`���u�{============end===========
        
        
        setContentView(R.layout.map);
        //��l�Ʈ�y�Ϥ�
        bitmapStart= BitmapFactory.decodeResource(this.getResources(), R.drawable.people);
        bitmapEnd= BitmapFactory.decodeResource(this.getResources(), R.drawable.cart);
        bitmapDirection=BitmapFactory.decodeResource(this.getResources(), R.drawable.carl);
        station=BitmapFactory.decodeResource(this.getResources(), R.drawable.tubiao);
        smallstation=BitmapFactory.decodeResource(this.getResources(), R.drawable.xiaotubiao);
        
        //��a�϶i���l��      
        final MapView mv=(MapView)findViewById(R.id.myMapView);
        mv.setBuiltInZoomControls(true);//�]�m�a�ϤW�n�Y�񱱨��
        mc=mv.getController();//����a�ϱ��
        mc.setZoom(14);//�]�m�a���Y����        
        //�]�m�a�Ϥ����I�g�n��
        mc.animateTo(gp);
        
        //���a�ϲK�[�@�ӧ����z����Overlay�Ω󮷮�Ĳ���ƥ�
       MyMapOverlay myOverlay = new MyMapOverlay();
       List<Overlay> overlays = mv.getOverlays();
        overlays.add(myOverlay); 
        
////        //============================================begin====
        overlays = mv.getOverlays(); 
    	Overlay first=overlays.get(0);
    	overlays.clear();
    	overlays.add(first);
    	
////    	
        
        
    	 //�N�Ω��������Ĳ���ƥ󪺳z��Overlay����
        MapView mv1=(MapView)findViewById(R.id.myMapView);
        List<Overlay> overlays1 = mv.getOverlays(); 
        Overlay first1=overlays1.get(0);
        //�N��L��Overlay�O���컲�U�C��
		List<Overlay> tol=new ArrayList<Overlay>();
		for(int i=1;i<overlays1.size();i++)
		{
			tol.add(overlays1.get(i));
		}
		//�M�ũҦ�Overlay
		overlays1.clear();
		//�K�[�Ω��������Ĳ���ƥ󪺳z��Overlay
		overlays1.add(first1);
        //============================================end=======
    	
         if(TrainSystemHelperActivity.flag2==true||TrainSystemHelperActivity.str.equals("�a�K2���u"))
         {
        	 Vector<String> list2=new Vector<String>();         
             list2=DBUtil.inserttotemp("�a�K2���u");               
             list2.add(list2.get(0));
             list2.add(list2.get(1));                
             Vector<GeoPoint> pointt=new Vector<GeoPoint>();
             pointt=MiddleTrainOverl.getPoint1(list2);
             TrainOverlay mno=new TrainOverlay(pointt,178,178,255,"�a�K2���u"); 
             overlays1.add(mno); 
         }
         else 
         {
        	 NothingOverlay mno=new NothingOverlay();
        	 overlays1.add(mno); 
         }
         
         if(TrainSystemHelperActivity.flag1==true||TrainSystemHelperActivity.str.equals("�a�K1���u"))
         {
        	 Vector<String> list3=new Vector<String>();         
             list3=DBUtil.inserttotemp("�a�K1���u");                  
             Vector<GeoPoint> pointt3=new Vector<GeoPoint>();
             pointt3=MiddleTrainOverl.getPoint1(list3);
             TrainOverlay mno3=new TrainOverlay(pointt3,250,0,0,"�a�K1���u");
             overlays1.add(mno3);
         }
         else 
         {
        	 NothingOverlay mno3=new NothingOverlay();
        	 overlays1.add(mno3);
         }
         
         if(TrainSystemHelperActivity.flag3==true||TrainSystemHelperActivity.str.equals("�a�K13���u"))
         {
        	 Vector<String> list4=new Vector<String>();         
             list4=DBUtil.inserttotemp("�a�K13���u");                  
             Vector<GeoPoint> pointt4=new Vector<GeoPoint>();
             pointt4=MiddleTrainOverl.getPoint1(list4);
             TrainOverlay mno4=new TrainOverlay(pointt4,255,128,0,"�a�K13���u");
             overlays1.add(mno4);
         }
         else 
         {
        	 NothingOverlay mno4=new NothingOverlay();
        	 overlays1.add(mno4);
         }
         
         
         //�Ϥ���overlay
         
        
       //�K�[�樮���uOverlay
		
		
		
		StationPicture mnopicture=new StationPicture();
		overlays1.add(mnopicture);
       //�K�[��LOverlay
		for(Overlay o:tol)
		{
			overlays1.add(o);  
       	}
       //�W�z���ҥH���樺��h�ʧ@�O���F�N�樮���uOverlay
       //���_�I�B���I��yOverlay�U���A�_�h�B�׵�ı�ĪG����
     //�b�I����m�K�[�_�I��y
      	MyBallonOverlay mbo=new MyBallonOverlay
       	(
       			gp,		//��y������
       			msg,  //��y���H��
       			MapNavigateActivity.bitmapEnd
       	);               
      		mbo.showWindow=true;
           overlays.add(mbo);                 
           //�O���_�I��m
           MyMapOverlay.gpEnd=gp;
       
       //�������A���l���A
       MapController mc=mv1.getController();//����a�ϱ��
                                            
        //�}�Ҥ@�ӽu�{�������MapView���ؤo
        new Thread()
        {
        	public void run()
        	{
        		try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		Constant.MAP_VIEW_WIDTH=mv.getWidth();
                Constant.MAP_VIEW_HEIGHT=mv.getHeight();
        	}
        }.start(); 
        
    ////�����m�޲z�����
        String serviceName=Context.LOCATION_SERVICE;
        LocationManager lm=(LocationManager)this.getSystemService(serviceName);        
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String provider = lm.getBestProvider(criteria, true);
        Location  tempLo= lm.getLastKnownLocation(provider);
        if(tempLo!=null)
        {
            gpLocation = new GeoPoint
            (
            		(int)(tempLo.getLatitude()*1E6), //�n��
            		(int)(tempLo.getLongitude()*1E6) //�g��
            );
        }
        else
        {
        	Toast.makeText(this, "�Х��}GPS�A�ýT�O�䥿�`�u�@�I", Toast.LENGTH_LONG).show();
        }

        
       
        LocationListener ll=new LocationListener()
        {
			@Override
			public void onLocationChanged(Location location) 
			{
				gpLocation = new GeoPoint
	            (
	            		(int)(location.getLatitude()*1E6), //�n��
	            		(int)(location.getLongitude()*1E6) //�g��
	            );	
			}

			@Override
			public void onProviderDisabled(String provider) 
			{//Location Provider�Q�T�ήɧ�s				
				
			}

			@Override
			public void onProviderEnabled(String provider) 
			{//Location Provider�Q�ҥήɧ�s					
				
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) 
			{
				//��Provider�w�󪬺A�ܤƬO��s
				
			}      	
        };
        
  
        lm.requestLocationUpdates
        (
        		LocationManager.GPS_PROVIDER, 	//�ϥ�GPS�w��
        		2000, 							//�ɶ�����v ms
        		5, 								//�Z������vm
        		ll								//��m�ܤƺ�ť��
        );
        Toast.makeText(this, "�Ы��U�����ާ@�A���U��^���^�I", Toast.LENGTH_LONG).show();
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	//����i���l��
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuItem ok=menu.add(0,0,0,"�ɯ�");
		//ok.setIcon(R.drawable.daohang); 
    	OnMenuItemClickListener lsn=new OnMenuItemClickListener()
    	{//��{��涵�I���ƥ��ť���f
			@Override
			public boolean onMenuItemClick(MenuItem item) { 
				//�Y�ʵe�w�g�b���񤤡A�h��^
				if(MapNavigateActivity.ison==true)
				{
					Toast.makeText
					(
							MapNavigateActivity.this, 
							"�ɯ�i�椤����A���ɯ�A\n�е��ʵe���񧹲��A���Ұʾɯ�I", 
							Toast.LENGTH_LONG
					).show();
					return false;
				}	
				
				if(flag==false) 
				{
					Toast.makeText
					(
							MapNavigateActivity.this, 
							"�Х����\"��ܦۤv��m\"��涵\n�a�Ϥ���ܦۤv����m�A�ɯ�I", 
							Toast.LENGTH_LONG
					).show();
					return false;
				}
				
				//����a�Ϥ��Ҧ�Overlay���C��
				MapView mv=(MapView)findViewById(R.id.myMapView);
				List<Overlay> overlays = mv.getOverlays();
				for(Overlay o:overlays)
				{
					if(o instanceof MyNavigateOverlay)
					{						
						//����ɯ�Overlay���ޥ�
						currMyNa=(MyNavigateOverlay)o;	
						yyhh=true;
						new Thread()
						{
							public void run()
							{
								//���MapView
								MapView mv=(MapView)findViewById(R.id.myMapView);
								GeoPoint[] points=currMyNa.points;								
								for(int i=0;i<points.length-1;i++)
								{	
									if(yyhh==false)
									{										
										MapNavigateActivity.ison=false;
										break;
									}
									int dLat=points[i].getLatitudeE6()-points[i+1].getLatitudeE6();
									int dLon=points[i].getLongitudeE6()-points[i+1].getLongitudeE6();
									double distanceE6=Math.sqrt(dLat*dLat+dLon*dLon);
									int totalSteps=(int)(distanceE6/100);//100���C�@�B�����׳��
									//�Y��e�l���|�`�B�Ƭ�0�A�h����
									if(totalSteps==0)
									{
										continue;
									}									
									//�p���e�l���|����V��	
									currMyNa.calDirection(i, mv);
									currMyNa.total=totalSteps;	
									currMyNa.step=0;																		
									if(i==0)
									{
										mv.getController().animateTo(points[i]);
										MapNavigateActivity.this.sleep(2000);										         
							            mv.postInvalidate();
										MapNavigateActivity.ison=true;
									}									
									//�`��������e�l���|�����C�@�B
									for(int s=0;s<totalSteps;s++)
									{
										currMyNa.step=s;										
										MapNavigateActivity.this.sleep(40);
									}										
								}
								//�ɯ�ʵe�������_�O�_ø�s�ɯ�b�Y�лx�쬰false
								MapNavigateActivity.ison=false;
							}
						}.start();
						break;
					}
				}				
				return true;
			}    		
    	};
    	ok.setOnMenuItemClickListener(lsn);//���T�w��涵�K�[��ť�� 
    	
    	MenuItem dzjjwd=menu.add(0,0,0,"��ܦۤv��m");
    	
    	lsn=new OnMenuItemClickListener()
    	{//��{��涵�I���ƥ��ť���f
			@Override
			public boolean onMenuItemClick(MenuItem item) {	 
				if(gpLocation==null)
				{
					Toast.makeText(MapNavigateActivity.this, "�Х��}GPS�A�ýT�O�䥿�`�u�@�I", Toast.LENGTH_LONG).show();
					return true;
				}
				
				if(MapNavigateActivity.ison==true)
				{
					yyhh=false;
				}	
				
				MyMapOverlay.gpStart=gpLocation;
				MapView mv=(MapView)findViewById(R.id.myMapView);
				List<Overlay> overlays = mv.getOverlays();    
				
				Overlay ovfd=null;
				for(Overlay ov:overlays)
				{
					if(ov instanceof MyNavigateOverlay)
					{
						ovfd=ov;
					}
				}
				overlays.remove(ovfd);
				
				
            	//�b�I����m�K�[�_�I��y
            	MyBallonOverlay mbo=new MyBallonOverlay
            	(
            			MyMapOverlay.gpStart,		//��y������
            			"�z����m",  //��y���H��
            			MapNavigateActivity.bitmapEnd
            	);  
            	mbo.showWindow=true;
                overlays.add(mbo);                 
                //�������A��_�I�]�m�n���A
                flag=true;
                
                //�N���b�d�ߤ��лx��]�m��true�A�ǳƶ}�l�d��
                MapNavigateActivity.inQuery=true;
                //�}�Ҥ@�ӽu�{�q�����W�d�߱q�_�I����I���樮���|
                new Thread()
                {
                	public void run()
                	{                		               		
                		//�q�����W����_�I����I�樮���|�H����XML����
                		Document doc=NavigateUtil.getPointsroute(MyMapOverlay.gpStart,MyMapOverlay.gpEnd);
                		if(doc==null)
                		{//�Y����������ѡA�h��ܿ��~����
                			MapNavigateActivity.msg="�����G�١A�Э��s��ܰ_�I�B���I�A�աI";
                			MapNavigateActivity.hd.sendEmptyMessage(Constant.DISPLAY_TOAST);
                    		MapNavigateActivity.inQuery=false;  
                    		return;
                		}
                		//�q�L�ѪRXML���ɱo��樮���|�����`�I�Ʋ�
                        GeoPoint[] points=NavigateUtil.getRoutePoints(doc);
                        if(points==null)
                		{//�Y���ɤ��R���ѡA�h��ܿ��~����
                        	MapNavigateActivity.msg="�����G�١A�Э��s��ܰ_�I�B���I�A�աI";
                			MapNavigateActivity.hd.sendEmptyMessage(Constant.DISPLAY_TOAST);
                    		MapNavigateActivity.inQuery=false; 
                    		return;
                		}
                        //�Ыئ樮���uOverlay
                        MyNavigateOverlay mno=new MyNavigateOverlay(points); 
                        //�N�Ω��������Ĳ���ƥ󪺳z��Overlay����
                        MapView mv=(MapView)findViewById(R.id.myMapView);
                        List<Overlay> overlays = mv.getOverlays(); 
                        Overlay first=overlays.get(0);
                        Overlay second=overlays.get(1);
                        Overlay third=overlays.get(2);
                        Overlay four=overlays.get(3);
                        Overlay five=overlays.get(4);
                        //�N��L��Overlay�O���컲�U�C��
                		List<Overlay> tol=new ArrayList<Overlay>();
                		for(int i=5;i<overlays.size();i++)
                		{
                			tol.add(overlays.get(i));
                		}
                		//�M�ũҦ�Overlay
                		overlays.clear();
                		//�K�[�Ω��������Ĳ���ƥ󪺳z��Overlay
                		overlays.add(first);
                		overlays.add(second);
                		overlays.add(third);
                		overlays.add(four);
                		overlays.add(five);
                        //�K�[�樮���uOverlay
                        overlays.add(mno); 
                        //�K�[��LOverlay
                        for(Overlay o:tol)
                        {
                        	overlays.add(o);  
                        }
                        //�W�z���ҥH���樺��h�ʧ@�O���F�N�樮���uOverlay
                        //���_�I�B���I��yOverlay�U���A�_�h�B�׵�ı�ĪG����
                        
                        //�������A���l���A
                        MapController mc=mv.getController();//����a�ϱ��
                        mc.animateTo(MyMapOverlay.gpStart);//�N�a�Ϥ����I������u�_�I��m
                        //�N���b�d�ߤ��лx��]�m��false
                        MapNavigateActivity.inQuery=false;
                	}
                }.start();                  
                
				return true;
			}    		
    	};
    	dzjjwd.setOnMenuItemClickListener(lsn);//���T�w��涵�K�[��ť�� 
		
		return true;		
	}
	
	//�𮧤@�w�@��ƪ���k
	public void sleep(int ms)
	{
		try 
		{
			Thread.sleep(ms);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
    @Override
    public void onPause()
    {
    	super.onPause();
		inQuery=false;//���b�d�ߤ��лx��
		ison=false;//�O�_ø�s�ɯ�b�Y�лx				
		flag=false;//�O�_��ܤF�ۤv��m			
		yyhh=false;//�ʵe���\�лx��
    }
}

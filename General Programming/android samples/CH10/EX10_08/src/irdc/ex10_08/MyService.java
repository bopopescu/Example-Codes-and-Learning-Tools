package irdc.ex10_08;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.IBinder;

/* �۩w�qMyService�~��Service */
public class MyService extends Service
{
  private String MY_PREFS = "MosPre";
  private NotificationManager notiManager;
  private int mosStatus;
  private int notiId=99;
  private MediaPlayer myPlayer;
    
  @Override
  public void onCreate()
  {
    try
    {
      /* ���oNotificationManager */
      notiManager=
        (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
      /* Create MediaPlayer */
      myPlayer=new MediaPlayer();
      myPlayer = MediaPlayer.create(MyService.this, R.raw.killmosall);
      
      /* Ū�����A�A�Ȫ��A(1:�ҰʡA0:����) */
      SharedPreferences pres = 
        getSharedPreferences(MY_PREFS,Context.MODE_PRIVATE);
      if(pres !=null)
      {
        mosStatus = pres.getInt("status", 0);
      }  
      
      if(mosStatus==1)
      {
        /* �[�@��Notification */
        setNoti(R.drawable.antimos,notiId,"���A�A�ȱҰ�");     
        /* ���񨾰A�a�n */
        if(!myPlayer.isPlaying())
        {
          myPlayer.seekTo(0);
          myPlayer.setLooping(true);
          myPlayer.start();
        }
      }
      else if(mosStatus==0)
      {
        /* �R��Notification */
        deleteNoti(notiId);
        /* �������A�a�n */
        if(myPlayer.isPlaying())
        {
          myPlayer.setLooping(false);
          myPlayer.pause();
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    super.onCreate();
  }
  
  @Override
  public void onDestroy()
  {
    try
    {
      /* Service����������MediaPlayer�A
       * �çR��Notification */
      myPlayer.release();
      deleteNoti(notiId);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    super.onDestroy();
  }

  
  /* �s�WNotification��method */
  public void setNoti(int iconImg,int iconId,String icontext)
  {
    /* �إ��I��Notification�d�����ɡA�|���檺Activity */
    Intent notifyIntent=new Intent(this,EX10_08.class);  
    notifyIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
    /* �إ�PendingIntent�@���]�w�������檺Activity */ 
    PendingIntent appIntent=PendingIntent.getActivity(this,0,notifyIntent,0); 
    /* �إ�Notification�A�ó]�w�����Ѽ� */ 
    Notification myNoti=new Notification();
    /* �]�wstatus bar��ܪ�icon */
    myNoti.icon=iconImg;
    /* �]�wnotification�o�ͮɦP�ɵo�X�w�]�n�� */
    myNoti.defaults=Notification.DEFAULT_SOUND;
    myNoti.setLatestEventInfo(this,"���A�A�ȱҰ�",icontext,appIntent);
    /* �e�XNotification */
    notiManager.notify(iconId,myNoti);
  } 
  
  /* �R��Notification��method */
  public void deleteNoti(int iconId)
  {
    notiManager.cancel(iconId);
  }
  
  @Override
  public IBinder onBind(Intent arg0)
  {
    return null;
  }
}
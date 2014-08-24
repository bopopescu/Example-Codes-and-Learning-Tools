package irdc.ex07_19_1;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class EX07_19_1 extends Activity
{
  private ImageView image1;
  private Bitmap bm;
  private int width=0;
  private int height=0;
  private int pointX=0;
  private int pointY=0;
  private int scale=0;
  private List<Bitmap> li=new ArrayList<Bitmap>();
  private GestureDetector detector;
  private myGestureListener gListener;
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
   
    /* ���ê��A�C */
    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                              WindowManager.LayoutParams.FLAG_FULLSCREEN);
    /* ���ü��D�C */
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    /* ���Jmain.xml Layout */
    setContentView(R.layout.main);
    /* ���o�ù��e�� */    
    width=this.getWindowManager().getDefaultDisplay().getWidth();
    height=this.getWindowManager().getDefaultDisplay().getHeight();
    
    /* ��lBitmap�]�w(960*1440) */
    bm=BitmapFactory.decodeResource(getResources(),R.drawable.photo);
    /* �w�����ͤT�ؤؤo��Bitmap */
    int[][] f={{320,480},{640,960}};
    for(int i=0;i<f.length;i++)
    {
      Bitmap tmp=Bitmap.createScaledBitmap(bm,f[i][0],f[i][1],true);
      li.add(tmp);
    }
    li.add(bm);
        
    /* ImageView��l�� */
    image1=(ImageView)findViewById(R.id.image1);
    image1.setImageBitmap(li.get(0));
    /* GestureDetector�]�w */
    gListener = new myGestureListener();
    detector = new GestureDetector(EX07_19_1.this,gListener);
    /* �]�wGestureDetector��OnDoubleTapListener */
    detector.setOnDoubleTapListener(
        new GestureDetector.OnDoubleTapListener()
    {
      /* �I�@�U�e������j���ʧ@ */
      @Override
      public boolean onSingleTapConfirmed(MotionEvent e)
      {
        /* �Ȥ��\��j�⦸ */
        if(scale!=2)
        {
          scale++;
          pointX+=(width/2);
          pointY+=(height/2);
          /* ���s�]�w��ܪ��v�� */
          Bitmap newB=Bitmap.createBitmap(li.get(scale),pointX,
                                          pointY,width,height);
          image1.setImageBitmap(newB);
        }
        return false;
      }
      
      @Override
      public boolean onDoubleTapEvent(MotionEvent e)
      {
        return false;
      }
      /* �s�I��U�e�����Y�p���ʧ@ */
      @Override
      public boolean onDoubleTap(MotionEvent e)
      {
        /* �Y�̤p�N�O��e���@�ˤj */
        if(scale!=0)
        {
          scale--;
          Bitmap b=li.get(scale);
          int tmpW=b.getWidth();
          int tmpH=b.getHeight();
          /* �p��X�b����I�Y�p�᪺��m */
          if(pointX-(width/2)>=0)
          {
            if(pointX-(width/2)+width<=tmpW)
            {
              pointX-=(width/2);    
            }
            else
            {
              pointX=tmpW-width;
            }
          }
          else
          {
            pointX=0;
          }
          /* �p��Y�b����I�Y�p�᪺��m */
          if(pointY-(height/2)>=0)
          {
            if(pointY-(height/2)+height<=tmpH)
            {
              pointY-=(height/2);    
            }
            else
            {
              System.out.println("Y2");
              pointY=tmpH-height;
            }
          }
          else
          {
            pointY=0;
          }
          /* ���s�]�w��ܪ��v�� */
          Bitmap newB=Bitmap.createBitmap(b,pointX,pointY,
                                          width,height);
          image1.setImageBitmap(newB);
        }
        return false;
      }
    });
  }
  /* ��Activity��onTouchEvent()�QĲ�o�ɡA
   * Ĳ�oGestureDetector��onTouchEvent() */
  @Override
  public boolean onTouchEvent(MotionEvent event)
  {
    if (detector.onTouchEvent(event))
    {
      return detector.onTouchEvent(event);
    }  
    else
    {
      return super.onTouchEvent(event);
    }
  }
  
  /* �۩w�qGestureListener */
  public class myGestureListener implements GestureDetector.OnGestureListener
  {
    /* ����b�ù��W��Ԯ�Ĳ�o��method */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX,  float distanceY)
    {
      /* ���o�ثe��ܪ�Bitmap */
      Bitmap b=li.get(scale);
      int tmpW=b.getWidth();
      int tmpH=b.getHeight();
      /* �p��X�b����I���ʫ᪺��m */
      if(pointX+distanceX>=0){
        if((pointX+distanceX)>(tmpW-width))
        {  
          pointX=tmpW-width;
        }
        else
        {
          pointX+=distanceX;
        }
      }
      else
      {
        pointX=0;
      }
      /* �p��Y�b����I���ʫ᪺��m */
      if(pointY+distanceY>=0)
      {  
        if((pointY+distanceY)>(tmpH-height))
        {  
          pointY=tmpH-height;
        }
        else
        {
          pointY+=distanceY;
        }
      }
      else
      {
        pointY=0;
      }
      /* �p�G�����ʡA�h��sBitmap�]�w */
      if(distanceX!=0&&distanceY!=0)
      {
        Bitmap newB=Bitmap.createBitmap(b,pointX,pointY,
                                        width,height);
        image1.setImageBitmap(newB);
      }
      return false;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e)
    {
      return false;
    }
    
    @Override
    public boolean onDown(MotionEvent arg0)
    {
      return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2,
        float velocityX, float velocityY)
    {
      return false;
    }

    @Override
    public void onLongPress(MotionEvent e)
    {
    }

    @Override
    public void onShowPress(MotionEvent e)
    {
    }
  }
}
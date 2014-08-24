package irdc.ex07_19;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class EX07_19 extends Activity
{
  private ImageView image1;
  private Bitmap bm;
  private int bmWidth=0;
  private int bmHeight=0;
  private int width=0;
  private int height=0;
  private int pointX=0;
  private int pointY=0;
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
    /* Bitmap�]�w */
    bm=BitmapFactory.decodeResource(getResources(),R.drawable.photo);
    bmWidth=bm.getWidth();
    bmHeight=bm.getHeight();
    /* ImageView��l�� */
    image1=(ImageView)findViewById(R.id.image1);
    Bitmap newB=Bitmap.createBitmap(bm,pointX,pointY, width, height);
    image1.setImageBitmap(newB);
    /* GestureDetector�]�w */
    gListener = new myGestureListener();
    detector = new GestureDetector(EX07_19.this,gListener);
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
      /* �p��X�b����I���ʫ᪺��m */
      if(pointX+distanceX>=0){
        if((pointX+distanceX)>(bmWidth-width)){  
          pointX=bmWidth-width;
        }else{
          pointX+=distanceX;
        }
      }else{
        pointX=0;
      }
      /* �p��Y�b����I���ʫ᪺��m */
      if(pointY+distanceY>=0){  
        if((pointY+distanceY)>(bmHeight-height)){  
          pointY=bmHeight-height;
        }else{
          pointY+=distanceY;
        }
      }else{
        pointY=0;
      }
      /* �p�G�����ʡA�h��sBitmap�]�w */
      if(distanceX!=0&&distanceY!=0)
      {
        Bitmap newB=Bitmap.createBitmap(bm,pointX,pointY,width,height);
        image1.setImageBitmap(newB);
      }
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

    @Override
    public boolean onSingleTapUp(MotionEvent e)
    {
      return false;
    }
  }
}
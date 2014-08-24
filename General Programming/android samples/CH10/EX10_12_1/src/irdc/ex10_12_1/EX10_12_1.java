package irdc.ex10_12_1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class EX10_12_1 extends WallpaperService
{  
  /* �мgonCreateEngine()�A�^�Ǧۭq��MyEngine */
  @Override
  public Engine onCreateEngine()
  {
    return new MyEngine();
  }
  
  /* �۩w�qMyEngine�~��Engine */
  class MyEngine extends Engine
  {
    private final Paint mPaint = new Paint();
    private float mTouchX;
    private float mTouchY;
    Bitmap bg;
    Bitmap bm;

    @Override
    public void onCreate(SurfaceHolder surfaceHolder)
    {
      /* ��l�ƭI��Bitmap���� */
      bg=BitmapFactory.decodeResource(getResources(),R.drawable.bg);
      /* ��l�Ƥ�����U�ɥX�{��Bitmap����A�e����120*66 */
      bm=BitmapFactory.decodeResource(getResources(),R.drawable.walk);
      /* enable TouchEvent(�w�]��false) */
      setTouchEventsEnabled(true);
      
      super.onCreate(surfaceHolder);
    }

    /* �мgonTouchEvent() */
    @Override
    public void onTouchEvent(MotionEvent event)
    {
      if(event.getAction()==MotionEvent.ACTION_DOWN)
      {
        /* ������U�ɰO��XY�y�ЭȡA�ð���draw() */
        mTouchX=event.getX();
        mTouchY=event.getY();
        draw();
      }
      else if(event.getAction()==MotionEvent.ACTION_UP)
      {
        /* ������}�ɭ��]�६�I�� */
        unDraw();
      }
      else if(event.getAction()==MotionEvent.ACTION_MOVE)
      {
        /* ������ʮɰO��XY�y�ЭȡA�ð���draw() */
        mTouchX=event.getX();
        mTouchY=event.getY();
        draw();
      }
      super.onTouchEvent(event);
    }

    @Override
    public void onSurfaceCreated(SurfaceHolder holder)
    {
      /* �]�w�६�I���� */
      Canvas c=holder.lockCanvas();
      if (c != null)
        c.drawBitmap(bg,0,0, mPaint);
      holder.unlockCanvasAndPost(c);
      super.onSurfaceCreated(holder);
    }

    /* �b�६�W�e�Ϫ�method */
    void draw()
    {
      final SurfaceHolder holder = getSurfaceHolder();
      Canvas c=holder.lockCanvas();
      if (c != null)
      {
        /* ���]�६�I�� */
        c.drawBitmap(bg,0,0, mPaint);
        /* �]�w������U����ܰ���������� */
        c.drawBitmap(bm,mTouchX-33,mTouchY-120, mPaint);
        holder.unlockCanvasAndPost(c);
      }
    }
    
    /* ���]�६�I����method */
    void unDraw()
    {
      final SurfaceHolder holder = getSurfaceHolder();
      Canvas c=holder.lockCanvas();
      if (c != null)
      {
        /* ���]�६�I�� */
        c.drawBitmap(bg,0,0, mPaint);
        holder.unlockCanvasAndPost(c);
      }
    }
  }
}
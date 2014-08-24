package irdc.ex10_12;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

public class EX10_12 extends WallpaperService
{
  private final Handler mHandler = new Handler();
  
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
    private float centerX;
    private float centerY;
    private boolean mVisible;
    Bitmap bm1;
    Bitmap bm2;
    Bitmap bm3;
    Bitmap bm4;
    private int x=0;
    
    private final Runnable myDraw = new Runnable()
    {
      public void run()
      {
        /* ����draw() */
        draw();
      }
    };

    @Override
    public void onCreate(SurfaceHolder surfaceHolder)
    {
      /* ��l�ƥ|�ӹw�s��Bitmap���� */
      bm1=BitmapFactory.decodeResource(getResources(),R.drawable.d1);
      bm2=BitmapFactory.decodeResource(getResources(),R.drawable.d2);
      bm3=BitmapFactory.decodeResource(getResources(),R.drawable.d3);
      bm4=BitmapFactory.decodeResource(getResources(),R.drawable.d4);
      super.onCreate(surfaceHolder);
    }

    @Override
    public void onVisibilityChanged(boolean visible)
    {
      mVisible = visible;
      if(visible)
      {
        /* �६���i���ɰ���draw() */
        draw();
      }
      else
      {
        /* �६���i���ɰ���myDraw */
        mHandler.removeCallbacks(myDraw);
      }
    }

    @Override
    public void onSurfaceChanged(SurfaceHolder holder, int format,
                                 int width, int height)
    {
      /* �x�s�ù����I�y�� */
      centerX = width/2.0f;
      centerY = height/2.0f;
      draw();
      super.onSurfaceChanged(holder, format, width, height);
    }
    
    @Override
    public void onDestroy()
    {
      super.onDestroy();
      /* �{�������ɰ���myDraw */
      mHandler.removeCallbacks(myDraw);
    }
    
    /* ���ͮ६�ʵe��method */
    public void draw()
    {
      final SurfaceHolder holder = getSurfaceHolder();
      Canvas c=holder.lockCanvas();
      if (c != null)
      {
        /* �̷�x���ȨM�w�n�X�{����Bitmap */
        if(x==0)
        {
          c.drawBitmap(bm1,centerX-90,centerY-90, mPaint);
          x++;
        }
        else if(x==1)
        {
          c.drawBitmap(bm2,centerX-90,centerY-90,mPaint);
          x++;
        }
        else if(x==2)
        {
          c.drawBitmap(bm3,centerX-90,centerY-90, mPaint);
          x++;
        }
        else
        {
          c.drawBitmap(bm4,centerX-90,centerY-90, mPaint);
          x=0;
        }
        holder.unlockCanvasAndPost(c);
      }
      /* ����myDraw */
      mHandler.removeCallbacks(myDraw);
      /* �]�w�@����A������ */
      if (mVisible)
      {
        mHandler.postDelayed(myDraw, 1000);
      }
    }
  }
}
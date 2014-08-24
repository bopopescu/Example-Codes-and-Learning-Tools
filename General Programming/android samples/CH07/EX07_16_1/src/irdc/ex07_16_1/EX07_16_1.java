package irdc.ex07_16_1; 

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity; 
import android.content.pm.ActivityInfo; 
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat; 
import android.hardware.Camera; 
import android.hardware.Camera.PictureCallback; 
import android.hardware.Camera.ShutterCallback; 
import android.os.Bundle; 
import android.os.Environment;
import android.view.SurfaceHolder; 
import android.view.SurfaceView; 
import android.view.View; 
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button; 
import android.widget.Toast;

public class EX07_16_1 extends Activity implements SurfaceHolder.Callback 
{
  private Camera mCamera; 
  private Button mButton,mButton1,mButton2;
  private SurfaceView mSurfaceView; 
  private SurfaceHolder holder; 
  private AutoFocusCallback mAutoFocusCallback = 
                            new AutoFocusCallback(); 
  private String path="MyPhoto";
  private Bitmap bmp;
  private int cnt=1;
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
    /* �]�w�ù���ܬ���V */
    this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); 
    
    setContentView(R.layout.main);
    /* SurfaceHolder�]�w */ 
    mSurfaceView = (SurfaceView) findViewById(R.id.mSurfaceView); 
    holder = mSurfaceView.getHolder();
    holder.addCallback(EX07_16_1.this); 
    holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    /* Button��l�� */
    mButton = (Button)findViewById(R.id.myButton);
    mButton1 = (Button)findViewById(R.id.myButton1);  
    mButton2 = (Button)findViewById(R.id.myButton2);  
    /* ���Button���ƥ�B�z */
    mButton.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View arg0) 
      { 
        /* �۰ʹ�J���� */
        mCamera.autoFocus(mAutoFocusCallback);
      }
    });
    /* �s��Button���ƥ�B�z */
    mButton1.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View arg0) 
      { 
        /* �x�s�ɮ� */
        if(bmp!=null)
        {
          /* �˵�SDCard�O�_�s�b */ 
          if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) 
          { 
            /* SD�d���s�b�A���Toast�T�� */
            Toast.makeText(EX07_16_1.this,"SD�d���s�b!�L�k�x�s�ۤ�",
                           Toast.LENGTH_LONG).show();
          }
          else
          {
            try
            {
              /* ��Ƨ����b�N���إ� */
              File f=new File
              (
                Environment.getExternalStorageDirectory(),path
              );
              
              if(!f.exists())
              {
                f.mkdir();
              }
              /* �x�s�ۤ��� */
              File n=new File(f,cnt+".jpg");
              FileOutputStream bos = 
                new FileOutputStream(n.getAbsolutePath());
              /* �ɮ��ഫ */
              bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
              /* �I�sflush()��k�A��sBufferStream */
              bos.flush();
              /* ����OutputStream */
              bos.close();
              Toast.makeText(EX07_16_1.this,cnt+".jpg�x�s���\!",
                  Toast.LENGTH_LONG).show();
              cnt++;
            }
            catch (Exception e)
            {
              e.printStackTrace();
            }
          }
        }
        
        mButton.setVisibility(View.VISIBLE);
        mButton1.setVisibility(View.GONE);
        mButton2.setVisibility(View.GONE);
        /* ���s�]�wCamera */
        stopCamera(); 
        initCamera();
      } 
    }); 
    /* ���Button���ƥ�B�z */
    mButton2.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View arg0) 
      { 
        mButton.setVisibility(View.VISIBLE);
        mButton1.setVisibility(View.GONE);
        mButton2.setVisibility(View.GONE);
        /* ���s�]�wCamera */
        stopCamera(); 
        initCamera();
      } 
    }); 
  }
  
  @Override 
  public void surfaceCreated(SurfaceHolder surfaceholder) 
  { 
    try
    {
      /* ���}�۾��A */
      mCamera = Camera.open();
      mCamera.setPreviewDisplay(holder);
    }
    catch (IOException exception)
    {
      mCamera.release();
      mCamera = null;
    }     
  } 
  
  @Override 
  public void surfaceChanged(SurfaceHolder surfaceholder,
                             int format,int w,int h) 
  {
    /* �۾���l�� */
    initCamera();
  } 

  @Override 
  public void surfaceDestroyed(SurfaceHolder surfaceholder) 
  { 
    stopCamera();
    mCamera.release();
    mCamera = null;
  }

  /* ��Ӫ�method */
  private void takePicture()  
  { 
    if (mCamera != null)  
    { 
      mCamera.takePicture(shutterCallback, rawCallback,jpegCallback); 
    } 
  }

  private ShutterCallback shutterCallback = new ShutterCallback()  
  {  
    public void onShutter()  
    {  
      /* ���U�֪������|�I�s�o�̪��{�� */
    }  
  };  
    
  private PictureCallback rawCallback = new PictureCallback()  
  {  
    public void onPictureTaken(byte[] _data, Camera _camera)  
    {  
      /* �n�B�zraw data�i�g�b�� */
    }  
  };  

  private PictureCallback jpegCallback = new PictureCallback()  
  { 
    public void onPictureTaken(byte[] _data, Camera _camera) 
    { 
      /* ���o�ۤ� */
      try 
      { 
        /* �]�wButton�i���� */
        mButton.setVisibility(View.GONE);
        mButton1.setVisibility(View.VISIBLE);
        mButton2.setVisibility(View.VISIBLE);
        /* ���o�ۤ�Bitmap���� */
        bmp = BitmapFactory.decodeByteArray(_data, 0,_data.length); 
      } 
      catch (Exception e) 
      { 
        e.printStackTrace(); 
      } 
    } 
  };
  
  /* �۩w�qclass AutoFocusCallback */
  public final class AutoFocusCallback implements android.hardware.Camera.AutoFocusCallback 
  { 
    public void onAutoFocus(boolean focused, Camera camera) 
    { 
      /* ���J�~��� */
      if (focused) 
      { 
        takePicture();
      } 
    } 
  };
  
  /* �۾���l�ƪ�method */
  private void initCamera() 
  { 
    if (mCamera != null) 
    {
      try 
      { 
        Camera.Parameters parameters = mCamera.getParameters(); 
        /* �]�w�ۤ��j�p��1024*768�A
                             �榡��JPG */
        parameters.setPictureFormat(PixelFormat.JPEG); 
        parameters.setPictureSize(1024,768);
        mCamera.setParameters(parameters); 
        /* �}�ҹw���e�� */
        mCamera.startPreview(); 
      } 
      catch (Exception e) 
      { 
          e.printStackTrace(); 
      } 
    } 
  } 

  /* ����۾���method */ 
  private void stopCamera() 
  { 
    if (mCamera != null) 
    { 
      try 
      { 
        /* ����w�� */
        mCamera.stopPreview(); 
      } 
      catch(Exception e) 
      { 
        e.printStackTrace(); 
      } 
    } 
  }
}
package irdc.ex06_22; 

import java.io.File; 
import java.util.ArrayList; 
import android.app.Activity; 
import android.content.Context; 
import android.gesture.Gesture; 
import android.gesture.GestureLibraries; 
import android.gesture.GestureLibrary; 
import android.gesture.GestureOverlayView; 
import android.gesture.Prediction; 
import android.gesture.GestureOverlayView.OnGesturePerformedListener; 
import android.os.Bundle; 
import android.os.Environment; 
import android.widget.Toast; 

public class EX06_22 extends Activity 
{ 
  private GestureLibrary gesLib; 
  private GestureOverlayView gesOverlay; 
  private String gesPath; 
   
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState);
    /* ���Jmain.xml Layout */
    setContentView(R.layout.main); 
    
    /* �˵�SDCard�O�_�s�b */ 
    if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) 
    { 
      /* SD�d���s�b�A���Toast�T�� */
      Toast.makeText(EX06_22.this,"SD�d���s�b!�{���L�k����",
                     Toast.LENGTH_LONG).show(); 
      finish(); 
    }
    
    /* ���oGestureLibrary���ɮ׸��| */
    gesPath = new File
    (
      Environment.getExternalStorageDirectory(),"gestures" 
    ).getAbsolutePath();
     
    File file = new File(gesPath); 
    
    if(!file.exists()) 
    { 
      /* gestures�ɮפ��s�b�A���Toast�T�� */
      Toast.makeText(EX06_22.this,"gestures�ɮפ��s�b!�{���L�k����",
                     Toast.LENGTH_LONG).show(); 
      finish();
    }
    
    /* ��l��GestureLibrary */
    gesLib = GestureLibraries.fromFile(gesPath);  
    if (!gesLib.load()) 
    { 
      /* Ū�����ѡA���Toast�T�� */
      Toast.makeText(EX06_22.this,"gestures�ɮ�Ū������!�{���L�k����",
                     Toast.LENGTH_LONG).show(); 
      finish();
    } 
    /* GestureOverlayView��l�� */
    gesOverlay = (GestureOverlayView) findViewById(R.id.myGestures1); 
    gesOverlay.addOnGesturePerformedListener(new MyListener(this)); 
  } 
  
  /* �۩w�qOnGesturePerformedListener */
  public class MyListener implements OnGesturePerformedListener 
  { 
    private Context context; 
    
    public MyListener(Context context) 
    { 
      this.context = context; 
    } 
     
    @Override 
    public void onGesturePerformed(GestureOverlayView overlay,
                                   Gesture gesture) 
    {  
      /* ��դ�� */
      ArrayList<Prediction> predictions = gesLib.recognize(gesture); 
      if (predictions.size() > 0) 
      { 
        /* ���̱��񪺤�� */
        Prediction prediction = predictions.get(0); 
        /* ���o�w���Ȧܤ֤j��1.0 */ 
        if (prediction.score > 1.0) 
        { 
          /* prediction.name�Y���r��w�����G  */
          Toast.makeText(this.context, prediction.name,
                         Toast.LENGTH_SHORT).show(); 
        }
        else
        {
          /* ��藍��A���Toast */
          Toast.makeText(this.context,"gesture no match!",
                         Toast.LENGTH_SHORT).show(); 
        }
      }
      else
      {
        /* ��藍��A���Toast */
        Toast.makeText(this.context,"gesture no match!",
                       Toast.LENGTH_SHORT).show();
      } 
    } 
  }  
} 
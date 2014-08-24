package irdc.ex06_23; 

import java.io.File; 
import java.util.ArrayList; 
import android.app.Activity; 
import android.content.ContentResolver;
import android.content.Context; 
import android.content.Intent;
import android.database.Cursor;
import android.gesture.Gesture; 
import android.gesture.GestureLibraries; 
import android.gesture.GestureLibrary; 
import android.gesture.GestureOverlayView; 
import android.gesture.Prediction; 
import android.gesture.GestureOverlayView.OnGesturePerformedListener; 
import android.net.Uri;
import android.os.Bundle; 
import android.os.Environment; 
import android.provider.ContactsContract;
import android.widget.Toast;

public class EX06_23 extends Activity 
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
      Toast.makeText(EX06_23.this,"SD�d���s�b!�{���L�k����",
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
      Toast.makeText(EX06_23.this,"gestures�ɮפ��s�b!�{���L�k����",
                     Toast.LENGTH_LONG).show(); 
      finish();
    }
    
    /* ��l��GestureLibrary */
    gesLib = GestureLibraries.fromFile(gesPath);  
    if (!gesLib.load()) 
    { 
      /* Ū�����ѡA���Toast�T�� */
      Toast.makeText(EX06_23.this,"gestures�ɮ�Ū������!�{���L�k����",
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
          /* �q�q�T�������o�p���H�q�� */
          String phone=getContactPeople(prediction.name);
          /* �����q�ܵ��p���H  */
          if(phone!="")
          {
            Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phone));
            startActivity(intent);
          }
          else
          {
        	  /* ��藍��A���Toast */
              Toast.makeText(this.context,"phone number not found!",
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
      else
      {
        /* ��藍��A���Toast */
        Toast.makeText(this.context,"gesture no match!",
                       Toast.LENGTH_SHORT).show();
      } 
    } 
  }
  
  /* �H�p���H�W�ٷj�M�q�T�����p���H�q�ܪ�method */
  private String getContactPeople(String name)
  {
    String result="";
    ContentResolver contentResolver = 
      EX06_23.this.getContentResolver();
    Cursor cursor = null;

    /* �n�j�M�����W�� */
    String[] projection = new String[]
    { ContactsContract.CommonDataKinds.Phone.NUMBER };

    /* �H�s���H���W�r�h��ӳs���H���q�� */
    cursor = contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection,
        ContactsContract.Contacts.DISPLAY_NAME + "=?", new String[]
        { name }, "");

    if (cursor.getCount() != 0)
    {
      cursor.moveToFirst();
      /* ���o�p���H�q�� */
      result = cursor.getString(0);
    }
    return result;
  }
} 
package irdc.ex05_25; 

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import android.app.Activity; 
import android.gesture.Gesture; 
import android.gesture.GestureLibraries; 
import android.gesture.GestureLibrary; 
import android.gesture.GestureOverlayView; 
import android.os.Bundle; 
import android.os.Environment;  
import android.view.KeyEvent;
import android.view.MotionEvent; 
import android.view.View; 
import android.widget.Button; 
import android.widget.EditText; 
import android.widget.Toast; 

public class EX05_25 extends Activity 
{
  private Gesture ges;
  private GestureLibrary lib; 
  private GestureOverlayView overlay; 
  private Button button01,button02; 
  private EditText et;
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
      Toast.makeText(EX05_25.this,"SD�d���s�b!�{���L�k����",
                     Toast.LENGTH_LONG).show(); 
      finish(); 
    }
    /* �HfindViewById()���o���� */
    et = (EditText)this.findViewById(R.id.myEditText1); 
    button01 = (Button)this.findViewById(R.id.myButton1); 
    button02 = (Button)this.findViewById(R.id.myButton2); 
    overlay = (GestureOverlayView) findViewById(R.id.myGestures1); 
    
    /* ���oGestureLibrary���ɮ׸��| */
    gesPath = new File
    (
      Environment.getExternalStorageDirectory(),"gestures" 
    ).getAbsolutePath();

    /* �]�wEditText��OnKeyListener */
    et.setOnKeyListener(new EditText.OnKeyListener(){
      @Override
      public boolean onKey(View v, int keyCode, KeyEvent event)
      {
        /* �W�ٻP��ճ��w�]�w�ɱN�s�W��Button enable */
        if(ges!=null&&et.getText().length()!=0)
        {
          button01.setEnabled(true);
        }
        else
        {
          button01.setEnabled(false);
        }
        return false;
      }
    });
    
    /* �]�wOverlay��OnGestureListener */
    overlay.addOnGestureListener(new GestureOverlayView.OnGestureListener()
    {
      @Override
      public void onGesture(GestureOverlayView overlay,MotionEvent event) 
      {
      }
      /* �}�l�e��ծɱN�s�W��Button disable�A�òM��Gesture */
      @Override
      public void onGestureStarted(GestureOverlayView overlay,MotionEvent event) 
      { 
        button01.setEnabled(false); 
        ges = null; 
      }
      /* ��յe���ɧP�_�W�ٻP��լO�_����إ� */
      @Override
      public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) 
      { 
        ges = overlay.getGesture(); 
        if (ges!=null&&et.getText().length()!=0) 
        { 
          button01.setEnabled(true);  
        }
      } 
      @Override
      public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) 
      { 
      }
    });
        
    /* �]�wbutton01��OnClickListener */
    button01.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        String gesName=et.getText().toString();       
        try 
        { 
          File file = new File(gesPath);
          lib = GestureLibraries.fromFile(gesPath); 
          
          if(!file.exists())
          {
            /* �ɮפ��s�b�N�����g�J */
            lib.addGesture(gesName,ges);
            if(lib.save()) 
            { 
              /* �N�]�w�e����ƲM�� */
              et.setText(""); 
              button01.setEnabled(false);
              overlay.clear(true); 
              /* �x�s���\�A���Toast�T�� */
              Toast.makeText(EX05_25.this,getString(R.string.save_success)+":"+gesPath,
                             Toast.LENGTH_LONG).show(); 
            }
            else 
            {
              /* �x�s���ѡA���Toast�T�� */
              Toast.makeText(EX05_25.this, getString(R.string.save_failed)+":"+gesPath,
                             Toast.LENGTH_LONG).show(); 
            } 
          }
          else
          {
            /* �ɮצs�b�ɥ�Ū���w�x�s��Gesture */
            if (!lib.load()) 
            { 
              /* LibraryŪ�����ѡA���Toast�T�� */
              Toast.makeText(EX05_25.this, getString(R.string.load_failed)+":"+gesPath,
                             Toast.LENGTH_LONG).show(); 
            }
            else
            {
              /* �p�GLibrary���s�b�ۦP�W�١A�h���N�䲾���A�g�J */
              Set<String> en=lib.getGestureEntries();
              if(en.contains(gesName))
              {
                ArrayList<Gesture> al=lib.getGestures(gesName);
                for(int i=0;i<al.size();i++){
                  lib.removeGesture(gesName,al.get(i)); 
                }
              } 
              lib.addGesture(gesName,ges);
              if(lib.save()) 
              { 
                /* �N�]�w�e����ƲM�� */
                et.setText(""); 
                button01.setEnabled(false);
                overlay.clear(true);
                /* �x�s���\�A���Toast�T�� */
                Toast.makeText(EX05_25.this,getString(R.string.save_success)+":"+gesPath,
                               Toast.LENGTH_LONG).show(); 
              }
              else 
              {  
                /* �x�s���ѡA���Toast�T�� */
                Toast.makeText(EX05_25.this, getString(R.string.save_failed)+":"+gesPath,
                               Toast.LENGTH_LONG).show(); 
              } 
            }
          }
        }
        catch(Exception e) 
        { 
          e.printStackTrace(); 
        } 
      } 
    });
    /* �]�wbutton02��OnClickListener */
    button02.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        et.setText(""); 
        button01.setEnabled(false); 
        overlay.clear(true); 
      } 
    }); 
  }
} 
package irdc.ex07_20;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class EX07_20 extends Activity
{
  private TextView myText1;
  private TextView myText2;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* ���Jmain.xml Layout */
    setContentView(R.layout.main);
    /* TextView��l�� */
    myText1=(TextView)findViewById(R.id.text1);
    myText2=(TextView)findViewById(R.id.text2);
  }
  
  /* �мgonTouchEvent() */  
  @Override
  public boolean onTouchEvent(MotionEvent event) 
  {   
    /* ���Ĳ�I�I���ƶq */
    myText2.setText(""+event.getPointerCount());
    
    /* event��Action�P�_ */
    switch(event.getAction())
    {
      /* Ĳ�I�ƥ�o�� */
      case MotionEvent.ACTION_DOWN:
        myText1.setText(getResources().getString(R.string.act1));
        break;
      /* Ĳ�I�ƥ󵲧� */
      case MotionEvent.ACTION_UP:
        myText1.setText(getResources().getString(R.string.act2));
        /* ����I�Ƭ�0 */
        myText2.setText("0");
        break;
      /* �Ĥ@��Ĳ�I�I�Q���U */
      case MotionEvent.ACTION_POINTER_1_DOWN:
        myText1.setText(getResources().getString(R.string.act3));
        break;
      /* �Ĥ@��Ĳ�I�I�Q���� */
      case MotionEvent.ACTION_POINTER_1_UP:
        myText1.setText(getResources().getString(R.string.act4));
        break;
      /* �ĤG��Ĳ�I�I�Q���U */
      case MotionEvent.ACTION_POINTER_2_DOWN:
        myText1.setText(getResources().getString(R.string.act5));
        break;
      /* �ĤG��Ĳ�I�I�Q���� */
      case MotionEvent.ACTION_POINTER_2_UP:
        myText1.setText(getResources().getString(R.string.act6));
        break;
      default:
        break;
    }
    return super.onTouchEvent(event);
  }
}
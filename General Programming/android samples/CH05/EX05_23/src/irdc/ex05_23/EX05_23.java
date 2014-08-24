package irdc.ex05_23;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EX05_23 extends Activity
{
  private TextView mTextView01;
  private Button mButton01;
  
  /* �ù��e�� */
  private int intScreenH,intScreenW;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    /* �ŧiDisplay����A�H���o�ù��e�� */
    final Display defaultDisplay = getWindow().getWindowManager().getDefaultDisplay();
    intScreenH= defaultDisplay.getHeight();
    intScreenW = defaultDisplay.getWidth();
    
    mButton01 = (Button)findViewById(R.id.myButton1); 
    mTextView01 = (TextView)findViewById(R.id.myTextView1);
    mTextView01.setText(Integer.toString(intScreenW)+"x"+Integer.toString(intScreenH));
    
    /* ���e>���A���ܬ����� */
    if(intScreenW > intScreenH)
    {
      /* Landscape */
      mButton01.setText(R.string.str_button2);
    }
    else
    {
      /* Portrait */
      mButton01.setText(R.string.str_button1);
    }
    
    /* ���s�ƥ�B�z�����e�� */
    mButton01.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        // TODO Auto-generated method stub
        intScreenH= defaultDisplay.getHeight();
        intScreenW = defaultDisplay.getWidth();
        
        /* �p�G��Landscape */
        if(intScreenW > intScreenH)
        {
          /* Landscape => Portrait */
          setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else
        {
          /* Portrait => Landscape */
          setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        
        /* �A�@�����o�ù��e�� */
        intScreenH= defaultDisplay.getHeight();
        intScreenW = defaultDisplay.getWidth();
        mTextView01.setText(Integer.toString(intScreenW)+"x"+Integer.toString(intScreenH));
      }
    });
  }  
  
  @Override
  public void onConfigurationChanged(Configuration newConfig)
  {
    // TODO Auto-generated method stub
    
    /* �мgonConfigurationChanged�ƥ�A�������]�w���᪺�� */
    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
    {
      /* �X�ۨƥ�o�ͤ���A�ܧ���s�W����r */
      mButton01.setText(R.string.str_button2);
      mMakeTextToast
      (
        getResources().getText(R.string.str_onConf_LANDSCAPE).toString(),
        false
      );
    }
    
    /* ���]�wconfigChanges�ݩʤ~�ஷ��onConfigurationChanged */
    if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
    {
      mButton01.setText(R.string.str_button1);
      mMakeTextToast
      (
        getResources().getText(R.string.str_onConf_PORTRAIT).toString(),
        false
      );
    }
    
    if (newConfig.keyboardHidden == Configuration.KEYBOARDHIDDEN_NO)
    {
     
    }
    super.onConfigurationChanged(newConfig);
  }
  
  public void mMakeTextToast(String str, boolean isLong)
  {
    if(isLong==true)
    {
      Toast.makeText(EX05_23.this, str, Toast.LENGTH_LONG).show();
    }
    else
    {
      Toast.makeText(EX05_23.this, str, Toast.LENGTH_SHORT).show();
    }
  }
}
package irdc.ex07_18;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;

public class EX07_18 extends Activity
{
  public static String TAG = "EX07_18_DEBUG";
  private TextToSpeech tts;
  private EditText EditText01;
  private ImageButton ImageButton01;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    /* �ǤJcontext��OnInitListener */
    tts = new TextToSpeech(this, ttsInitListener);

    EditText01 = (EditText) this.findViewById(R.id.EditText01);
    ImageButton01 = (ImageButton) this.findViewById(R.id.ImageButton01);

    ImageButton01.setOnClickListener(new ImageButton.OnClickListener()
    {

      @Override
      public void onClick(View v)
      {
        // TODO Auto-generated method stub
        if (EditText01.getText().length() > 0)
        {
          /* �ǤJ�n�����r�� */
          tts.speak(EditText01.getText().toString(), TextToSpeech.QUEUE_FLUSH,
              null);
        } else
        {
          /* �L��J�r��� */
          tts.speak("Nothing to say", TextToSpeech.QUEUE_FLUSH, null);
        }

      }

    });
  }

  private TextToSpeech.OnInitListener ttsInitListener = new TextToSpeech.OnInitListener()
  {

    @Override
    public void onInit(int status)
    {
      // TODO Auto-generated method stub
      /* �ϥά���ɰϥثe���䴩���� */
      Locale loc = new Locale("us", "", "");
      /* �ˬd�O�_�䴩��J���ɰ� */
      if (tts.isLanguageAvailable(loc) == TextToSpeech.LANG_AVAILABLE)
      {
        /* �]�w�y�� */
        tts.setLanguage(loc);
      }
      tts.setOnUtteranceCompletedListener(ttsUtteranceCompletedListener);
      Log.i(TAG, "TextToSpeech.OnInitListener");
    }

  };
  private TextToSpeech.OnUtteranceCompletedListener ttsUtteranceCompletedListener = new TextToSpeech.OnUtteranceCompletedListener()
  {
    @Override
    public void onUtteranceCompleted(String utteranceId)
    {
      // TODO Auto-generated method stub
      Log.i(TAG, "TextToSpeech.OnUtteranceCompletedListener");
    }
  };

  @Override
  protected void onDestroy()
  {
    // TODO Auto-generated method stub
    /* ����TextToSpeech���귽 */
    tts.shutdown();
    Log.i(TAG, "tts.shutdown");
    super.onDestroy();
  }

}
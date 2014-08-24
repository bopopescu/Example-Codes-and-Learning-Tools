package irdc.ex08_20_1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/* ��@Runnable Interface */
public class EX08_20_1 extends Activity implements Runnable
{
  private ProgressDialog d;
  private TextView tv;
  private Button b1;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* ���Jmain.xml Layout */
    setContentView(R.layout.main);
    /* �����l�� */
    tv = (TextView) findViewById(R.id.text1);
    b1 = (Button) findViewById(R.id.button1);
    
    b1.setOnClickListener(new OnClickListener()
    {
      public void onClick(View v)
      {
        tv.setText("");
        /* ���XProgressDialog */
        d=new ProgressDialog(EX08_20_1.this);
        /* �]�wProgressDialog��style������ */
        d.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        /* �]�w�̤j��� */
        d.setMax(100);
        /* �]�w��l�� */
        d.setProgress(0);
        d.setMessage("�ɮפU����..");
        d.show();
        /* �Ұʥt�@��Thread�A����run() */
        Thread thread = new Thread(EX08_20_1.this);
        thread.start();
      }
    });
    
  }
  
  /* Handler�غc����A�|��ť�ǨӪ��T���N�X  */
  private Handler handler = new Handler()
  {
    @Override 
    public void handleMessage(Message msg)
    { 
      /* ���o�^�Ǫ��i�׭� */
      int p=msg.getData().getInt("percent");
      if(p==100)
      {
        /* �U����������ProgressDialog */
        tv.setText("�U������!");
        d.dismiss();
      }
      else
      {
        /* �U����UPDATE�i�׼ƭ� */
        d.setProgress(p);
      }
    }
  };

  @Override
  public void run()
  {
    /* �����ɮפU���Ӯɬ�10�� */
    try
    {
      for(int i=0;i<10;i++)
      {
        /* �C����@���j��A�Y�Ȱ�1�� */
        Thread.sleep(1000);
        
        /* �j��C����@���i�ץ[10% */
        int percent=(i+1)*10;
        Message m=new Message();
        Bundle data=m.getData();
        /* �N�i�ש�JMessage�� */
        data.putInt("percent",percent);
        m.setData(data);
        /* send Message */
        handler.sendMessage(m);
      }
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }
}
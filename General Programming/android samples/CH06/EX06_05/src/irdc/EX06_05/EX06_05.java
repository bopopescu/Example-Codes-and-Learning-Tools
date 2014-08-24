package irdc.EX06_05;

import android.app.Activity; 
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle; 
import android.telephony.SmsMessage;
import android.widget.TextView; 
import android.widget.Toast;

public class EX06_05 extends Activity 
{
  /*�ŧi�@��TextView,String�}�C�P��Ӥ�r�r���ܼ�*/
  private TextView mTextView1; 
  public String[] strEmailReciver;
  public String strEmailSubject;
  public String strEmailBody;
  /* �t�α���²�T���s��ACTION�`�� */
  private static final String HIPPO_SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";
  private mSMSReceiver mReceiver01;
  
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
    
    /*�z�LfindViewById�غc�l�إ�TextView����*/ 
    mTextView1 = (TextView) findViewById(R.id.myTextView1); 
    mTextView1.setText("���ݱ���²�T..."); 
    
  }
  
  public class mSMSReceiver extends BroadcastReceiver 
  { 
    /*�ŧi�R�A�r��,�èϥ�android.provider.Telephony.SMS_RECEIVED�@��Action��²�T���̾�*/
    private static final String mACTION = "android.provider.Telephony.SMS_RECEIVED"; 
    private String str_receive="����²�T!";
    private String strRet = "";
    
    @Override 
    public void onReceive(Context context, Intent intent) 
    {
      // TODO Auto-generated method stub 
      Toast.makeText(context, str_receive.toString(), Toast.LENGTH_LONG).show(); 
      /*�P�_�Ǩ�Intent�O�_��²�T*/
      if (intent.getAction().equals(mACTION)) 
      { 
        /*�غc�@�r�궰�X�ܼ�sb*/
        StringBuilder sb = new StringBuilder(); 
        /*������Intent�ǨӪ����*/
        Bundle bundle = intent.getExtras(); 
        /*�P�_Intent�O�����*/
        if (bundle != null) 
        { 
          /* pdus�� android����²�T�Ѽ� identifier
           * �z�Lbundle.get("")�^�Ǥ@�]�tpdus������*/
          Object[] myOBJpdus = (Object[]) bundle.get("pdus"); 
          
          /*�غc²�T����array,�è̾ڦ��쪺������רӫإ�array���j�p*/
          SmsMessage[] messages = new SmsMessage[myOBJpdus.length];  
          
          for (int i = 0; i<myOBJpdus.length; i++) 
          {  
            messages[i] = SmsMessage.createFromPdu ((byte[]) myOBJpdus[i]);  
          }
          
          strRet = "";
          /* �N�e�Ӫ�²�T�X�֦ۭq�T����StringBuilder�� */  
          for (SmsMessage currentMessage : messages) 
          {
            strRet = "������Ӧ�:"+currentMessage.getDisplayOriginatingAddress()+" �ǨӪ�²�T"+currentMessage.getDisplayMessageBody();
            sb.append("������Ӧ�:\n");  
            /* �ӰT�̪��q�ܸ��X */ 
            sb.append(currentMessage.getDisplayOriginatingAddress());  
            sb.append("\n------�ǨӪ�²�T------\n");  
            /* ���o�ǨӰT����BODY */  
            sb.append(currentMessage.getDisplayMessageBody());
          }  
        }       
        /* �HNotification(Toase)��ܨӰT�T��  */
        Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
        
        /*�ۭq�@Intent�Ӱ���H�eE-mail���u�@*/
        Intent mEmailIntent = new Intent(android.content.Intent.ACTION_SEND);  
        /*�]�w�l��榡��"plain/text"*/
        mEmailIntent.setType("plain/text");
        
        /*���oEditText01,02,03,04���ȧ@������H�a�},����,�D��,����*/
        String strEmailReciver = "jay.mingchieh@gmail.com";
        String strEmailSubject = "�A���@��²�T!!";
        
        /*�N���o���r���JmEmailIntent��*/
        mEmailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, strEmailReciver); 
        mEmailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, strEmailSubject);
        mEmailIntent.putExtra(android.content.Intent.EXTRA_TEXT, strRet);
        context.startActivity(Intent.createChooser(mEmailIntent, getResources().getString(R.string.str_message)));
        mTextView1.setText(getResources().getString(R.string.str_message));
      }
    } 
  }

  @Override
  protected void onPause()
  {
    // TODO Auto-generated method stub
    super.onPause();
    unregisterReceiver(mReceiver01);
  }
  
  @Override
  protected void onResume()
  {
    // TODO Auto-generated method stub
    IntentFilter mFilter01;
    mFilter01 = new IntentFilter(HIPPO_SMS_ACTION);
    mReceiver01 = new mSMSReceiver();
    registerReceiver(mReceiver01, mFilter01);
    super.onResume();
  }
  
}



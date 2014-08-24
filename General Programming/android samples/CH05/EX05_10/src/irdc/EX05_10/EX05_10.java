package irdc.EX05_10;

import android.app.Activity; 
import android.content.Intent; 
import android.os.Bundle; 
/*���ݤޥ�database.Cursor,Contacts.People�P net.uri�����O�Өϥ��p���H���*/
import android.database.Cursor; 
import android.net.Uri; 
import android.provider.ContactsContract; 
import android.view.View; 
import android.widget.Button; 
import android.widget.EditText;
import android.widget.TextView; 
import android.widget.Toast;

public class EX05_10 extends Activity 
{ 
  /*�ŧi�|��UI�ܼƻP�@�ӱ`�Ƨ@��Activity�����^�ǭȥ�*/
  private TextView mTextView01; 
  private Button mButton01;
  private EditText mEditText01;
  private EditText mEditText02;
  private static final int PICK_CONTACT_SUBACTIVITY = 2; 
   
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
    
    /*�z�LfindViewById�غc�l�ӫغc�@��TextView,���EditText,�@��Button����**/
    mTextView01 = (TextView)findViewById(R.id.myTextView1); 
    mEditText01 = (EditText)findViewById(R.id.myEditText01);
    mEditText02 = (EditText)findViewById(R.id.myEditText02);
    mButton01 = (Button)findViewById(R.id.myButton1); 
    
    /*�]�wonClickListener ���ϥΪ��I��Button�ɷj�M�p���H*/
    mButton01.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        // TODO Auto-generated method stub 
        /*�غcUri�Ө��o�p���H���귽��m*/
       // Uri uri = Uri.parse("content://contacts/people/"); 
        /*�z�LIntent�Ө��o�p���H��ƨæ^�ǩҿ諸��*/
        //Intent intent = new Intent(Intent.ACTION_PICK, uri);
        /*�}�ҷs��Activity�ô����Activity�^�ǭ�*/
        //startActivityForResult(intent, PICK_CONTACT_SUBACTIVITY); 
        
        startActivityForResult(new Intent(Intent.ACTION_PICK, 
            android.provider.ContactsContract.Contacts.CONTENT_URI),PICK_CONTACT_SUBACTIVITY);
      } 
    }); 
  } 
  
  @Override 
  protected void onActivityResult 
(int requestCode, int resultCode, Intent data) 
  { 
    // TODO Auto-generated method stub
    try
    {
      switch (requestCode) 
      {  
        case PICK_CONTACT_SUBACTIVITY: 
          final Uri uriRet = data.getData(); 
          if(uriRet != null) 
          { 
            try 
            { 
              /* �����n��android.permission.READ_CONTACTS�v�� */ 
              Cursor c = managedQuery(uriRet, null, null, null, null); 
              /*�NCursor�����Ƴ̫e��*/
              c.moveToFirst(); 
              /*���o�p���H���m�W*/
              String strName =  
              c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)); 
              /*�N�m�W�g�JEditText01��*/
              mEditText01.setText(strName); 
              /*���o�p���H���q��*/ 
             int contactId = c.getInt(c.getColumnIndex(ContactsContract.Contacts._ID)); 
              Cursor phones = getContentResolver().query 
              ( 
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
                null, 
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId, 
                null, null 
              ); 
              StringBuffer sb = new StringBuffer();   
              int typePhone, resType;   
              String numPhone;
              if (phones.getCount() > 0) 
              { 
                phones.moveToFirst(); 
                /* 2.0�i�H���\User�]�w�h�չq�ܸ��X�A�����d�ҥu���@�չq�ܸ��X�@�ܽd */ 
                
                  typePhone = phones.getInt 
                  ( 
                    phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE) 
                  ); 
                  numPhone = phones.getString 
                  ( 
                    phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER) 
                  ); 
                  resType = ContactsContract.CommonDataKinds.Phone.getTypeLabelResource(typePhone); 
                  sb.append(getString(resType) +": "+ numPhone +"\n");   
                  
                 /*�N�q�ܼg�JEditText02��*/             
                  mEditText02.setText(numPhone);
              } 
              else 
              { 
                sb.append("no Phone number found"); 
              } 
              /*Toast�O�_Ū���짹�㪺�q�ܺ����P�q�ܸ��X*/
              Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show(); 
      
            } 
            catch(Exception e) 
            { 
              /*�N���~��T�bTextView�����*/
              mTextView01.setText(e.toString()); 
              e.printStackTrace(); 
            } 
          } 
          break;
        default:
          break;
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    super.onActivityResult(requestCode, resultCode, data); 
  } 
}
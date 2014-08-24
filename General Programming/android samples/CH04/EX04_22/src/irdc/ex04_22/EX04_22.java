package irdc.ex04_22;

/* import����class */
import java.io.File;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EX04_22 extends Activity
{
  /*�ŧi�����ܼ�*/
  private ImageView mImageView;
  private Button mButton;
  private TextView mTextView;
  private String fileName="/data/data/irdc.ex04_22/ex04_22_2.png";
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* ���Jmain.xml Layout */
    setContentView(R.layout.main);
    
    /* ���oButton����A�å[�JonClickListener */
    mButton = (Button)findViewById(R.id.mButton);
    mButton.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        /* ���o���� */
        mImageView = (ImageView)findViewById(R.id.mImageView);
        mTextView=(TextView)findViewById(R.id.mTextView);
        /* �ˬd�ɮ׬O�_�s�b */
        File f=new File(fileName);   
        if(f.exists()) 
        { 
          /* ����Bitmap����A�é�JmImageView�� */
          Bitmap bm = BitmapFactory.decodeFile(fileName);
          mImageView.setImageBitmap(bm);
          mTextView.setText(fileName); 
        } 
        else 
        {  
          mTextView.setText("�ɮפ��s�b"); 
        } 
      } 
    });
  }
}
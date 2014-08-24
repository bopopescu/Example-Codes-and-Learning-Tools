package irdc.ex04_11;

/* import����class */
import java.io.File;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EX04_11 extends Activity
{
  /*�ŧi�����ܼ�*/
  private Button mButton;
  private EditText mKeyword;
  private TextView mResult;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* ���Jmain.xml Layout */
    setContentView(R.layout.main);
    
    /* ��l�ƪ��� */
    mKeyword=(EditText)findViewById(R.id.mKeyword);
    mButton=(Button)findViewById(R.id.mButton);
    mResult=(TextView) findViewById(R.id.mResult);
    
    /* �NmButton�[�JonClickListener */
    mButton.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        /*���o��J������r*/
        String keyword = mKeyword.getText().toString();
        if(keyword.equals(""))
        {
          mResult.setText("�Фſ�J�ťժ�����r!!");
        }
        else
        {
          mResult.setText(searchFile(keyword));
        }
      }
    });
  }

  /* �j�M�ɮת�method */
  private String searchFile(String keyword)
  {
    String result="";
    File[] files=new File("/").listFiles();
    for( File f : files )
    {
      if(f.getName().indexOf(keyword)>=0)
      {  
        result+=f.getPath()+"\n";
      }
    }
    if(result.equals("")) result="�䤣���ɮ�!!";
    return result;
  }
}
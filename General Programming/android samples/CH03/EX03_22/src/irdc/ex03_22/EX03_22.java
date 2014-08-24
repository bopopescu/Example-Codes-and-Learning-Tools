package irdc.ex03_22;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

public class EX03_22 extends Activity
{
  private EditText et;
  private CheckBox cb;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* ���Jmain.xml Layout */
    setContentView(R.layout.main);
    /* �HfindViewById()���o���� */
    et=(EditText)findViewById(R.id.mPassword);
    cb=(CheckBox)findViewById(R.id.mCheck);
    /* �]�wCheckBox��OnCheckedChangeListener */
    cb.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener()
    {
      @Override
      public void onCheckedChanged(CompoundButton arg0, boolean arg1)
      {
        if(cb.isChecked())
        {
          /* �]�wEditText�����e���i���� */
          et.setTransformationMethod(
              HideReturnsTransformationMethod.getInstance());
        }
        else
        {
          /* �]�wEditText�����e�����ê� */
          et.setTransformationMethod(
              PasswordTransformationMethod.getInstance());
        }
      }
    });
  }
}
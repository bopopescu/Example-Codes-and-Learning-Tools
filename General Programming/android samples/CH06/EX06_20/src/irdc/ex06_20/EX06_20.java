package irdc.ex06_20;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class EX06_20 extends ListActivity
{
  private ListAdapter mListAdapter;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    /* ���o�q�T���̪���� */
    Cursor cursor = getContentResolver().query(
        ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
    /* ���o���� */
    int c = cursor.getCount();
    if (c == 0)
    {
      Toast.makeText(EX06_20.this, "�s���H�L���\n�Цܳs���H�s�W���", Toast.LENGTH_LONG)
          .show();
    }

    /* ��Activity�޲zCursor */
    startManagingCursor(cursor);

    /* ����ܪ����W�� */
    String[] columns =
    { ContactsContract.Contacts.DISPLAY_NAME };

    /* ��������W�٪�view */
    int[] entries =
    { android.R.id.text1 };

    mListAdapter = new SimpleCursorAdapter(this,
        android.R.layout.simple_list_item_1, cursor, columns, entries);
    /* �]�wAdapter */
    setListAdapter(mListAdapter);

  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id)
  {
    // TODO Auto-generated method stub

    /* ���o�I�諸Cursor */
    Cursor c = (Cursor) mListAdapter.getItem(position);

    /* ���o_id�o�����o�� */
    int contactId = c.getInt(c.getColumnIndex(ContactsContract.Contacts._ID));

    /* ��_id�h�d�߹q�ܪ�Cursor */
    Cursor phones = getContentResolver().query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
        null, null);

    StringBuffer sb = new StringBuffer();
    int type, typeLabelResource;
    String number;

    if (phones.getCount() > 0)
    {

      /* 2.0�i�H���\User�]�w�h�չq�ܸ��X�A�̧Ǽ��X */
      while (phones.moveToNext())
      {
        /* ���o�q�ܪ�TYPE */
        type = phones.getInt(phones
            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
        /* ���o�q�ܸ��X */
        number = phones.getString(phones
            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        /* �ѹq�ܪ�TYPE��XLabelResource */
        typeLabelResource = ContactsContract.CommonDataKinds.Phone
            .getTypeLabelResource(type);

        sb.append(getString(typeLabelResource) + ": " + number + "\n");

      }
    } else
    {
      sb.append("no Phone number found");
    }

    Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();

    super.onListItemClick(l, v, position, id);
  }

}
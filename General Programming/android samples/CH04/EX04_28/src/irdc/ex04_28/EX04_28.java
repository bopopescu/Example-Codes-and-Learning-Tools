package irdc.ex04_28;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class EX04_28 extends AppWidgetProvider
{

  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager,
      int[] appWidgetIds)
  {
    // TODO Auto-generated method stub

    /* �s�_UpdateService��Intent */
    Intent intent = new Intent(context, UpdateService.class);
    context.startService(intent);

    super.onUpdate(context, appWidgetManager, appWidgetIds);
  }

  public static class UpdateService extends Service
  {

    @Override
    public IBinder onBind(Intent arg0)
    {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
      super.onStart(intent, startId);
      /* ���oWidget��View */
      RemoteViews updateViews = new RemoteViews(this.getPackageName(),
          R.layout.main);
      /* �榡�Ʈɶ�hh:mm�N��ɸ�� */
      SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
      /* �N�ɶ���mTextView */
      updateViews.setTextViewText(R.id.TextView01, "" + sdf.format(new Date()));

      /* ��swidget */
      ComponentName thisWidget = new ComponentName(this, EX04_28.class);
      AppWidgetManager manager = AppWidgetManager.getInstance(this);
      manager.updateAppWidget(thisWidget, updateViews);

    }
  }

}
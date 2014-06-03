package org.d214.whs.wcc.portal;



import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;

import org.d214.whs.wcc.portal.R;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

public class ServiceWidget extends Service {
	
	private String currentMood;
	private LinkedList<String> moods;
	
	public ServiceWidget(){
		this.moods = new LinkedList<String>();
	}



	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStart(intent, startId);
        updateMood(intent);
			
		stopSelf(startId);
		
		return START_STICKY;
	}

	private String getRandomMood() {
		Random r = new Random(Calendar.getInstance().getTimeInMillis());
		int pos = r.nextInt(moods.size());
		return moods.get(pos);
	}

	private void updateMood(Intent intent) {
        if (intent != null){
    		String requestedAction = intent.getAction();
	            	            	 
	            int widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 0);

	            AppWidgetManager appWidgetMan = AppWidgetManager.getInstance(this);
	            RemoteViews views = new RemoteViews(this.getPackageName(),R.layout.layout_widget);
	            views.setTextViewText(R.id.widgetDate, "Testing!!!");
	            appWidgetMan.updateAppWidget(widgetId, views);
	            
    		
        }
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}

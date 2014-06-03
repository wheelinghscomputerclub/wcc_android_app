package org.d214.whs.wcc.portal;



import org.d214.whs.wcc.portal.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class Provider extends AppWidgetProvider {
	public static final String WIDGETTAG = "WidgetMood";
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
		final int N = appWidgetIds.length;
		
		// Perform t  his loop procedure for each App Widget that belongs to this provider
		for (int i=0; i<N; i++) {
		    int appWidgetId = appWidgetIds[i];

		    RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
		    
		    Intent intent = new Intent(context, ServiceWidget.class);

		    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		    PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
		
		    
		    // Tell the AppWidgetManager to perform an update on the current App Widget
		    appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}	
}

package com.simpetodo.appwidget;


import com.example.simpletodo.R;
import com.simpetodo.view.AppWidgetMainView;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class SimpleTODOAppWidgetProvider extends AppWidgetProvider {

	private Context context;
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		final int N = appWidgetIds.length;
		this.context = context;
		for (int i=0; i<N; i++) {
		int appWidgetId = appWidgetIds[i];
		updateAppWidget(context, appWidgetManager, appWidgetId);
		}
	}
		
	public void onDeleted(Context context, int[] appWidgetIds) {
	}
	
	static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
		int appWidgetId) {
		AppWidgetMainView appWidgetMainView = new AppWidgetMainView(context, null);
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_main);
		appWidgetManager.updateAppWidget(appWidgetId, views);
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		
	}


}
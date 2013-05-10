package com.test.appwidget;

import java.util.ArrayList;
import java.util.List;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class CustomWidget extends AppWidgetProvider{

	private static List<String> sList;

	static{
		sList = new ArrayList<String>();
		sList.add("第一条新闻");
		sList.add("第二条新闻");
		sList.add("第三条新闻");
		sList.add("第四条新闻");
		sList.add("第五条新闻");
		sList.add("第六条新闻");
	}

	private ComponentName thisWidget;
	private RemoteViews remoteViews;

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){

		thisWidget = new ComponentName(context, CustomWidget.class);
		remoteViews = new RemoteViews(context.getPackageName(),	R.layout.widget_layout);

		Intent intent = new Intent(context, UpdateService.class);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[0]);
		//设置适配器
		remoteViews.setRemoteAdapter(R.id.widget_list, intent);

		Intent intent2 = new Intent();
		//TODO
		//intent2.setComponent(new ComponentName("包名", "类名"));
		PendingIntent pendingIntentTemplate = PendingIntent.getActivity(context, 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
		
		//拼接PendingIntent
		remoteViews.setPendingIntentTemplate(R.id.widget_list, pendingIntentTemplate);

		//更新RemoteViews
		appWidgetManager.updateAppWidget(thisWidget, remoteViews);
		
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		manager.notifyAppWidgetViewDataChanged(appWidgetIds[0], R.id.widget_list);
	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
	}


	public static List<String> getList(){
		return sList;
	}

}

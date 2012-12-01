package com.example.ejemplo_adroid;

import java.util.Random;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.Toast;
/*
 * esta clase define un widget que recive actualizaciones de broadcast del dispositivo
 * un widget se implementa mediante la clase AppWidgetProvider y se define en el manifest por
 * medio de un receiver
 * 
 * por defecto se define el metodo onUpdate y onDelete
 */
public class PointlessWidget extends AppWidgetProvider {

	private String rand;
	
	//se llama cuando se elimina un widget
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Toast.makeText(context, "See ya suckaa"+rand, Toast.LENGTH_SHORT).show();
	}

	//se llama cada cierto tiempo definido en widget_stuff
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Random r = new Random();
		int randomInt = r.nextInt(10000000);
		rand = String.valueOf(randomInt);
		final int N = appWidgetIds.length;
		for (int i = 0; i < N; i++) {
			int awID = appWidgetIds[i];
			RemoteViews v = new RemoteViews(context.getPackageName(), R.layout.widget);
			v.setTextViewText(R.id.tvwidgetUpdate, rand);
			appWidgetManager.updateAppWidget(awID, v);
		}
	}
}

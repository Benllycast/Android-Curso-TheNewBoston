package com.example.ejemplo_adroid;


import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

/*esta clase se llama solo una vez para toda instancia de un widget PointLessWidget
 *  y usa cuando se agrega un Widget PointLessWidget la pantalla home del dispositivo
 *  y configura la apariencia del view del widget por primera vez
 *  
 *   esta clase es la que se define en el widget_stuff
 */
public class WidgetConfig extends Activity implements OnClickListener{

	private Button b;
	private EditText info;
	private Context c;	//contexto de la actividad
	private int awID;	//ID del widget que lanza el llamado de la actividad
	private AppWidgetManager awm;	//administrador de widget's

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widgetconfig);
		b = (Button) findViewById(R.id.bwidgetconfig);
		b.setOnClickListener(this);
		c = WidgetConfig.this;
		info =(EditText) findViewById(R.id.etwidgetconfig);
		//obtiniendo info desde el widget que lanzo esta Activity 
		Intent i = getIntent();	//se obtiene el Intent del widget que lanzo la Activity
		Bundle extras = i.getExtras();	//se obtinen los parametros extras desde el intent 
		if(extras != null){
			//si hay extras se rescata el ID del widget que lanzo la aplicaion, pueden haber varios widget hacia la misma aplicaion
			awID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		}else{
			//sino se termina la aplicaion
			finish();
		}
		//se obtiene una instancia del adminsitrador de AppWidget
		awm = AppWidgetManager.getInstance(c);

	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String e = info.getText().toString();
		//se crea un objeto de RemoteView para Actualizar el Layout del Widget 
		RemoteViews views = new RemoteViews(c.getPackageName(), R.layout.widget);
		views.setTextViewText(R.id.tvConfigInput, e);	//actualiza el campo requerido
		
		Intent in = new Intent(c, Menu.class);	//nuevo Intent que se lanzara una aplicacion desde el widget
		PendingIntent pi = PendingIntent.getActivity(c, 0, in, 0);	//????? es importante
		//enlaza el evento on-click al boton para que lanze un pendingIntent
		views.setOnClickPendingIntent(R.id.bwidgetOpen, pi);	
		
		//actualiza el Layout del widget con la nueva view
		awm.updateAppWidget(awID, views);
		
		//nuevo intent que reporta el resultado de la actividad
		Intent result = new Intent();
		//se le pasa un extra con el ID del widget que lanzo al actividad
		result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, awID);
		//se setea el resultado de la actividad en el objeto
		setResult(RESULT_OK, result);
		//se finaliza la actividad
		finish();
	}

	

}

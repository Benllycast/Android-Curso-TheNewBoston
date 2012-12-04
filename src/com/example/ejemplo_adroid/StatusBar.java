package com.example.ejemplo_adroid;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*implementacion de notificaciones en la status bar*/
public class StatusBar extends Activity implements OnClickListener {

	NotificationManager nm;	//se obtiene un notificacion manager
	static final int uniqueID = 3123123;	//se cre un id para identificar las notificaciones  si hay que eliminarlas

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statusbar);
		Button b = (Button) findViewById(R.id.bStatusBar);
		b.setOnClickListener(this);
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);	//se obtine una instancia del servicio de notificaciones
		nm.cancel(uniqueID);	//se cancela la notificacion anteriormente lanzada si la hay
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.statusbar, menu);
		return true;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, StatusBar.class);	//se crea un intent para llamar a la actividad que lanza la notificaion
		PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);	//se cre un Pending Intent para lanzar la actividad con este contexto
		String body = "esto es un mensaje de benlly, gracias por supoort";	//cuerpo de la notificacion
		String title = "Cualqueir titulo";	//titilo
		Notification n = new Notification(R.drawable.lightning, body, System.currentTimeMillis());	//se configura una nueva notificacion
		n.setLatestEventInfo(this, title, body, pi);	//deprecado
		n.defaults = Notification.DEFAULT_ALL;	//acchines que realiza cuando llega una notificaion (vibrar, parpadear, sonar, todas las anteriores)
		nm.notify(uniqueID, n);	//se lanza la notificacion
		finish();	//se finaliza esta actividad
	}
}

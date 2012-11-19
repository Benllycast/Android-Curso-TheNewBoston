package com.example.ejemplo_adroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class GFX extends Activity {

	MyBringBag ourView;
	WakeLock lock;
	PowerManager powerManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//wake-lock
		powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		 lock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "cualquiera");
		
		super.onCreate(savedInstanceState);
		lock.acquire();
		ourView = new MyBringBag(this);//se crea un objeto de la vista personalizada
		setContentView(ourView); //se pasa la vista a la actividad.
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		lock.release();
	}
	
	
	
}

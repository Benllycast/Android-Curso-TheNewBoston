package com.example.ejemplo_adroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Accelerate extends Activity implements SensorEventListener {
	Bitmap ball;
	float x, y , sensorX, sensorY;
	SensorManager sm;
	MyBringBagSurface ourSurfaceView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(new MyBringBagSurface(this));
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);	//obtine un admin de sensores del dispositivo
		if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size()!= 0){	//si no tiene sensores de tipo accelerometro
			//se obtiene una lista de los sensores y se escoje el primero
			Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			//se agrga un escuchador de this al sensorManager relacionado con el sensor escojido
			sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
		}
		
		ball = BitmapFactory.decodeResource(getResources(), R.drawable.radio);
		x = y = sensorX = sensorY = 0;
		ourSurfaceView = new MyBringBagSurface(this);
		ourSurfaceView.resume();
		setContentView(ourSurfaceView);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sm.unregisterListener(this);	//si pausa no captura eventos del sensor
	}
	
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sensorX  = arg0.values[0];	//se obtiene el valor del sensor x
		sensorY = arg0.values[1];	//el sensor y

	}

	// clase que administra el hilo de dibujado en la superficie
	public class MyBringBagSurface extends SurfaceView implements Runnable {
		/*
		 * esta clase implementa la interfas Runnable para pasarle un objeto al
		 * hilo y este ejecute el metodo run
		 */

		SurfaceHolder ourHolder; // mantiene el control sobre la superficie de
									// dibujo de la pantalla
		Thread ourThread = null; // hilo de ejecucion de dibujado
		boolean isRunning = false; // flag para conprobar que el hilo se esta
									// ejecutando

		public MyBringBagSurface(Context context) {
			// TODO Auto-generated constructor stub
			super(context);
			ourHolder = getHolder(); // se obtiene un control para la superficie
										// de dibujo
		}

		// metodo que se llama durante un aparada del hilo dejecucion
		public void pause() {
			isRunning = false; // se indica que el hilo esta detenido
			while (true) {
				try {
					ourThread.join(); // se destruye el hilo de ejecucion
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
		}

		public void resume() {
			isRunning = true; // se indica que el hilo continua
			ourThread = new Thread(this); // se crea un nuevo hilo con los
											// codigos de ejecucion de esta
											// clase
			ourThread.start(); // se inicia el hilo
		}

		// metodo de ejecuta el hilo de dibujado
		public void run() {
			while (isRunning) { // miestras el hilo este corriendo
				if (!ourHolder.getSurface().isValid()) { // se comprueba que sea
															// posible obtener
															// la superficie
					continue;
				}
				Canvas canvas = ourHolder.lockCanvas(); // se obtine el canvas
														// de dibujo y se blokea
														// para otros objetos no
														// puedan dibujar en el
				canvas.drawRGB(10, 10, 150); // se dibuja la pantalla en azul
				float centerX = canvas.getWidth()/2;
				float centerY = canvas.getHeight()/2;
				
				canvas.drawBitmap(ball, centerX + sensorX*250, centerY+ sensorY*250, null);

				ourHolder.unlockCanvasAndPost(canvas); // se dibuja el canvas y
														// se desbloquea para
														// que otros objetos
														// tengan acceso
			}

		}

	}

	

}

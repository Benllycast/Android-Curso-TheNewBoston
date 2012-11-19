package com.example.ejemplo_adroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

public class GFXSurface extends Activity implements OnTouchListener {

	MyBringBagSurface ourSurfaceView;	//variable que mantiene la superficie de dibujo de la vista
	float x, y, sx, sy, fx, fy, dx, dy, scaleX, scaleY, aniX, aniY;	//variables para las coordenadas de dibujo
	Bitmap test, plus;	//imagenes a dibujar

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);	//quita el titulo de la ventana
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);	//coloca la aplicacion a pantalla completa

		ourSurfaceView = new MyBringBagSurface(this);
		ourSurfaceView.setOnTouchListener(this);
		test = BitmapFactory.decodeResource(getResources(), R.drawable.radio);	//carga las imagens desde los recursos
		plus = BitmapFactory.decodeResource(getResources(), R.drawable.cohete);
		
		//coloca todas las coordenadas a 0;
		x = 0;
		y = 0;
		sx = 0;
		sy = 0;
		fx = 0;
		fy = 0;
		dx = dy = scaleX = scaleY =  aniX = aniY = 0;
		setContentView(ourSurfaceView);	//set la vista con el contenido de la superficie de ourSurfaceView
	}
	
	@Override
	protected void onPause() {
		// metodo para pausar el hilo de dibujo
		super.onPause();
		ourSurfaceView.pause();	//pausa la ejecucion del hilo
	}

	@Override
	protected void onResume() {
		/*metodo para continuar el hilo de dibujo 
		 * cuando comienza la actividad tambien 
		 * lo hace el hilo de dibujo*/
		
		super.onResume();
		ourSurfaceView.resume();	//reanuda la ejecucion del hilo
	}

	public boolean onTouch(View v, MotionEvent event) {
		// metodo para detectar eventos de toque
		
		try {
			//se duerme el hilo por50 milisegundo
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//consigue las coordenadas x, y dela pulsacion
		x = event.getX();
		y = event.getY();
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:	//inicio de una pulsacion
			//guarda las coordenadas para el primer objeto de dibujo
			sx = event.getX();
			sy = event.getY();
			dx = dy = scaleX = scaleY =  aniX = aniY = fx = fy = 0;	//coloca las demas variables a 0 para l aanimacion
			break;

		case MotionEvent.ACTION_UP:	//final de una pulsacion
			//guarda las coordenadas para el primer objeto de dibujo
			fx = event.getX();
			fy = event.getY();
			
			dx = fx - sx;	//calcula la distancia entre el punto de inicio y final de la pulsacion
			dy = fy - sy;
			
			scaleX = dx / 30;	//se divide la distancia entre 30 frames para calcular el desplazamiento por cuadro del objeto
			scaleY = dy / 30;
			
			x = y = 0;
			break;

		default:
			break;
		}
		return true;
	}
	//clase que administra el hilo de dibujado en la superficie
	public class MyBringBagSurface extends SurfaceView implements Runnable {
		/*
		 * esta clase implementa la interfas Runnable
		 *  para pasarle un objeto al hilo y este ejecute el metodo run
		 * */
		
		SurfaceHolder ourHolder;	//mantiene el control sobre la superficie de dibujo de la pantalla
		Thread ourThread = null;	//hilo de ejecucion de dibujado
		boolean isRunning = false;	//flag para conprobar que el hilo se esta ejecutando

		public MyBringBagSurface(Context context) {
			// TODO Auto-generated constructor stub
			super(context);
			ourHolder = getHolder();	//se obtiene un control para la superficie de dibujo
		}

		//metodo que se llama durante un aparada del hilo dejecucion
		public void pause() {
			isRunning = false;	//se indica que el hilo esta detenido
			while (true) {
				try {
					ourThread.join();	//se destruye el hilo de ejecucion
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
		}

		public void resume() {
			isRunning = true;	//se indica que el hilo continua
			ourThread = new Thread(this);	//se crea un nuevo hilo con los codigos de ejecucion de esta clase
			ourThread.start();	//se inicia el hilo
		}

		//metodo de ejecuta el hilo de dibujado
		public void run() {
			while (isRunning) {	//miestras el hilo este corriendo
				if (!ourHolder.getSurface().isValid()) {	//se comprueba que sea posible obtener la superficie
					continue;
				}
				Canvas canvas = ourHolder.lockCanvas();	//se obtine el canvas de dibujo y se blokea para otros objetos no puedan dibujar en el
				canvas.drawRGB(10, 10, 150);	//se dibuja la pantalla en azul
				if (x != 0 && y != 0) {
					//se dibuja el objeto donde se realizo la pulsacion
					canvas.drawBitmap(test, x - (test.getWidth() / 2), y - (test.getHeight() / 2), null); 
				}
				if (sx != 0 && sy != 0) {
					//se dibuja un objeto en el inicio de las coordenadas de la pulsacion
					canvas.drawBitmap(plus, sx - (plus.getWidth() / 2), sy - (plus.getHeight() / 2), null);	
				}
				if (fx != 0 && fy != 0) {
					//se un objeto donde se marca el la coordenada final mas el desplazamiento por frame del objeto
					canvas.drawBitmap(test, fx - (test.getWidth() / 2)- aniX, fy - (test.getHeight() / 2) - aniY, null);
					//se dibuja un objeto final donde se termino la pulsacion
					canvas.drawBitmap(plus, fx - (plus.getWidth() / 2), fy - (plus.getHeight() / 2), null);	
				}
				aniX = aniX + scaleX;	//se suma 1 unidad de desplazamiento para el proximo dibujado del hilo
				aniY = aniY + scaleY;
				ourHolder.unlockCanvasAndPost(canvas); //se dibuja el canvas y se desbloquea para que otros objetos tengan acceso
			}

		}

	}

}

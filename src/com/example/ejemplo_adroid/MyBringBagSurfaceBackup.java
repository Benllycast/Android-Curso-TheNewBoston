package com.example.ejemplo_adroid;
/*
import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MyBringBagSurface extends SurfaceView implements Runnable {

	SurfaceHolder ourHolder;
	Thread ourThread = null;
	boolean isRunning = false;

	public MyBringBagSurface(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		ourHolder = getHolder();

	}

	public void pause() {
		isRunning = false;
		while (true) {
			try {
				ourThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	public void resume() {
		isRunning = true;
		ourThread = new Thread(this);
		ourThread.start();
	}

	public void run() {
		while (isRunning) {
			if (!ourHolder.getSurface().isValid()) {
				continue;
			}
			Canvas canvas = ourHolder.lockCanvas();
			canvas.drawRGB(10, 10, 150);
			ourHolder.unlockCanvasAndPost(canvas);
		}

	}

}
*/
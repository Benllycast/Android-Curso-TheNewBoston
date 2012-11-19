package com.example.ejemplo_adroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

//una view personalizada para la activity
public class MyBringBag extends View {
	Bitmap gball;
	float cambiandoY;
	Typeface fontTypeface;
	
	//constructor para la view perzinalizada
	public MyBringBag(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		//se carga una imagen atravez de los recursos y el id
		gball = BitmapFactory.decodeResource(getResources(), R.drawable.radio);
		cambiandoY = 0;
		fontTypeface = Typeface.createFromAsset(context.getAssets(), "G-Unit.TTF");//crea una funete desde el /asset
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// cuando se pinta la pantalla se llama a este metodo
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);//se pinta el fondo de blanco
		Paint textPaint = new Paint();
		textPaint.setARGB(50, 254, 10, 50);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(50);
		textPaint.setTypeface(fontTypeface);
		canvas.drawText("mybringbag", (canvas.getWidth()/2), 200, textPaint);//se dibuja el texto con la fuente y colores desado
		canvas.drawBitmap(gball, (canvas.getWidth()/2), cambiandoY, null); //se dibukja la imagen en la pantalla
		if(cambiandoY < canvas.getWidth()){//compruab que la coordenada no sobrepase el limite inferior
			cambiandoY+= 10;
		}else{
			cambiandoY = 0;
		}
		Rect medioRect = new Rect();//nuevo rectangulo
		medioRect.set(0, 400, canvas.getWidth(), 550);//dimensiones
		Paint ourPaint = new Paint();//objeto pintura
		ourPaint.setColor(Color.BLUE);//se coloca de color azul
		canvas.drawRect(medioRect, ourPaint);//se dibuja elrectangulo en la pantalla con el color de paint
		invalidate();// llama nuevamente el repintado de la pantalla
	}	
}

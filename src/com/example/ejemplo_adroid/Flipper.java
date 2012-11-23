package com.example.ejemplo_adroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;
/*tutorial 97 flipper
 * intercambiar los componetes uno tras el otro despues de un tiempo
 * los componentes estan uno encima del otro
 * despues de un tiempo se oberva el primero y despues el siguiente y asi sucesivamente
 * */
public class Flipper extends Activity implements OnClickListener {

	private ViewFlipper flippy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flipper);
		//contenedor que muestra los componentes 
		flippy = (ViewFlipper) findViewById(R.id.viewFlipper1);
		flippy.setOnClickListener(this);
		flippy.setFlipInterval(500);//setea un intervalo de intercambio
		flippy.startFlipping();//comienza el intercambio
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		flippy.showNext();
	}

	
}

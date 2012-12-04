package com.example.ejemplo_adroid;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Voice extends Activity implements OnClickListener {

	ListView lv;
	Button b;
	static final int check = 1111;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice);
        lv = (ListView) super.findViewById(R.id.lvVoiceReturn);
        b = (Button) findViewById(R.id.bVoice);
        b.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.voice, menu);
        return true;
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);	//se crea un nuevo intent hacia el reconocedor de Voz
		i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);	//se configura el modelo de lenguaje????
		i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak up Song");	//mensaje que se muestra al usuario cuando se le pide que hable para el reconocimiento
		startActivityForResult(i, check);	//se inicia la actividad del reconocimiento de voz
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		//si el resultado del reconocimiento es correcto
		if(requestCode == check && resultCode == RESULT_OK){
			ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS); //se obtienen los resultados extas de la actividad de reconocimeinto
			lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, result));	//se adaptan a un listView las palabras reconocida por la actividad
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
}

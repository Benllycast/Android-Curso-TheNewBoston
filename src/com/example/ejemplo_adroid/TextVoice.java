package com.example.ejemplo_adroid;

import java.util.Locale;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TextVoice extends Activity implements OnClickListener {
	
	//palabras que va a parlear el celular
	static final String[] texts = {
		"What up man!!!", "How are you?", "this is Crazy!"
	};
	
	TextToSpeech tts;	//objeto que administra la conversion sonora
	Button b;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textvoice);
        b = (Button) findViewById(R.id.bTextToVoice);
        b.setOnClickListener(this);
        //se inicia el TTS con el contexto actual i se redefine el metodo init
        tts = new TextToSpeech(TextVoice.this, new TextToSpeech.OnInitListener() {
			
			public void onInit(int status) {
				// si no hay error
				if(status != TextToSpeech.ERROR){
					tts.setLanguage(Locale.US);	//se selecciona el lenguaje del TTS
				}
				
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.textvoice, menu);
        return true;
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Random r = new Random();
		String random = texts[r.nextInt(3)];
		tts.speak(random, TextToSpeech.QUEUE_FLUSH, null);	//se se la traduccion fonetica
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		//control para cuando se pausa la actividad, sino  el motor TTS sigue andando
		if(tts != null){
			tts.stop();
			tts.shutdown();
		}
		super.onPause();
	}
}

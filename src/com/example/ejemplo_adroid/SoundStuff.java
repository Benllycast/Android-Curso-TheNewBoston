package com.example.ejemplo_adroid;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class SoundStuff extends Activity implements OnClickListener, OnLongClickListener {

	SoundPool sp;
	int explosion = 0;
	MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View view = new View(this);
		view.setOnClickListener(this);
		view.setOnLongClickListener(this);
		setContentView(view);
		
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);	//crea un pool de sonidos para mesclar stream
		explosion = sp.load(this, R.raw.explosion, 1);
		mp = MediaPlayer.create(this, R.raw.splash);
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(explosion != 0){
			sp.play(explosion, 1, 1, 0, 0, 1);
		}
		
	}

	public boolean onLongClick(View arg0) {
		// TODO Auto-generated method stub
		mp.start();
		return false;
	}
	
}

package com.example.ejemplo_adroid;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class Slider extends Activity implements OnCheckedChangeListener,
		OnClickListener, OnDrawerOpenListener {
	Button button, button2, button3, button4;
	CheckBox checkBox;
	MediaPlayer mp;
	SlidingDrawer sd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding);
		button = (Button) findViewById(R.id.handle1);
		button2 = (Button) findViewById(R.id.handle2);
		button3 = (Button) findViewById(R.id.handle3);
		button4 = (Button) findViewById(R.id.handle4);
		checkBox = (CheckBox) findViewById(R.id.cbSlider);
		checkBox.setOnCheckedChangeListener(this);
		sd = (SlidingDrawer) findViewById(R.id.slidingD);
		sd.setOnDrawerOpenListener(this);
		button.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.handle1:
			sd.open();
			break;
		case R.id.handle2:

			break;
		case R.id.handle3:
			sd.toggle();
			break;
		case R.id.handle4:
			sd.close();
			break;
		default:
			break;
		}

	}

	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		if (arg0.isChecked()) {
			sd.lock();
		} else {
			sd.unlock();
		}
	}

	public void onDrawerOpened() {
		// TODO Auto-generated method stub
		mp = MediaPlayer.create(this, R.raw.explosion);
		mp.start();
	}

}

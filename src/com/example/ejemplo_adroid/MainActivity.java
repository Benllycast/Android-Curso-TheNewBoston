package com.example.ejemplo_adroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	Button addButton;
	Button subButton;
	TextView numTextView;
	TextView helloTextView;
	int value = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initialize();
		addButton.setOnClickListener(this);
		subButton.setOnClickListener(this);
	}

	public void initialize() {
		addButton = (Button) findViewById(R.id.btAddOne);
		subButton = (Button) findViewById(R.id.btSubtractOne);
		numTextView = (TextView)findViewById(R.id.tvNumber);
		helloTextView = (TextView) findViewById(R.id.tvHello);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btAddOne:
			value++;
			break;
		case R.id.btSubtractOne:
			value--;
			break;
		default:
			break;
		}
		numTextView.setText("Your total is:"+ value);
	}

}

package com.example.ejemplo_adroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data extends Activity implements OnClickListener{

	private Button start;
	private Button startFor;
	private EditText sendEt;
	private TextView gotAnswer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		start = (Button) findViewById(R.id.btSA);
		startFor = (Button) findViewById(R.id.btSAFR);
		sendEt = (EditText) findViewById(R.id.etSend);
		gotAnswer = (TextView) findViewById(R.id.tvGot);
		start.setOnClickListener(this);
		startFor.setOnClickListener(this);
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.btSA:
			String bread = sendEt.getText().toString();
			Bundle basket = new Bundle();
			basket.putString("key", bread);
			Intent intent = new Intent(Data.this, OpenedClass.class);
			intent.putExtras(basket);
			startActivity(intent);
			break;
		case R.id.btSAFR:
			startActivityForResult(new Intent(Data.this, OpenedClass.class), 0);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			Bundle baskt = data.getExtras();
			String string = baskt.getString("answer");
			gotAnswer.setText(string);
		}
	}
	
	
}

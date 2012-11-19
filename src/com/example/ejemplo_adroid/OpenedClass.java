package com.example.ejemplo_adroid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class OpenedClass extends Activity implements OnClickListener, OnCheckedChangeListener {

	TextView question;
	TextView test;
	Button returnData;
	RadioGroup selectionList;
	String gotBread, sendData;

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(OpenedClass.this, Data.class);
		Bundle back = new Bundle();
		back.putString("answer", sendData);
		intent.putExtras(back);
		setResult(RESULT_OK,intent);
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		initialize();
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		//se obtine el valor desde el xml de las preferencias "name"
		String et = preferences.getString("name", "Benjamin si");
		//se obtiene el valor del lista de preferencias
		String values = preferences.getString("list", "4");
		
		if(values.contentEquals("1")){
			question.setText(et);
		}
		/*Bundle bundle = getIntent().getExtras();
		gotBread = bundle.getString("key");
		question.setText(gotBread);*/
	}

	private void initialize() {
		// TODO Auto-generated method stub
		question = (TextView) findViewById(R.id.tvQuestion);
		test = (TextView) findViewById(R.id.tvText);
		returnData = (Button) findViewById(R.id.bReturn);
		selectionList = (RadioGroup) findViewById(R.id.rgAnswers);
		returnData.setOnClickListener(this);
		selectionList.setOnCheckedChangeListener(this);
	}

	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		switch (arg1) {
		case R.id.rCrazy:
			sendData = "Problamente Correto";
			break;
		case R.id.rSexy:
			sendData = "Definitivamente Correto";
			break;
		case R.id.rBoth:
			sendData = "Muy Mal";
			break;
		default:
			break;
		}
		test.setText(sendData);
	}
	
	
}

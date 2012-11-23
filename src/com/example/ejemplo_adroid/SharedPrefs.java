package com.example.ejemplo_adroid;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*tutoial 95 compartiendo datos de preferencia*/
public class SharedPrefs extends Activity implements OnClickListener {

	EditText sharedData;
	TextView dataResult;
	Button save, load;
	public static String fileName = "MysharedString";
	SharedPreferences someData;//variable que gaurada las preferencias

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		setupVariables();
		someData = getSharedPreferences(fileName, 0);//obtencion de las preferencias de la actividad
	}

	private void setupVariables() {
		// TODO Auto-generated method stub
		save = (Button) findViewById(R.id.btSave);
		load = (Button) findViewById(R.id.btLoad);
		sharedData = (EditText) findViewById(R.id.txtSharedData);
		dataResult = (TextView) findViewById(R.id.tvDataResult);
		save.setOnClickListener(this);
		load.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btSave:
			//agregando preferencias al editor de preferencias
			String stringData =sharedData.getText().toString();
			SharedPreferences.Editor editor = someData.edit(); //se obtenie un editor de preferencias
			editor.putString("sharedString", stringData);//se agrega una nueva preferencia
			editor.commit();
			break;
		case R.id.btLoad:
			//cargando datos desde el editor de preferencias
			someData = getSharedPreferences(fileName, 0);
			String dataReturn = someData.getString("sharedString", "No se puede cargar Los Datos!!!");//cargando un string desde las preferencias
			dataResult.setText(dataReturn);
			break;

		default:
			break;
		}
	}

}

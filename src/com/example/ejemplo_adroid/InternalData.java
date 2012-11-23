package com.example.ejemplo_adroid;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*tutorial 100 datos internos*/
public class InternalData extends Activity implements OnClickListener {

	EditText sharedData;
	TextView dataResult;
	Button save, load;
	public static String fileName = "MysharedString";
	SharedPreferences someData;// variable que gaurada las preferencias
	FileOutputStream fos;
	String FILENAME = "InternalString";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		setupVariables();
		someData = getSharedPreferences(fileName, 0);// obtencion de las
														// preferencias de la 
														// actividad
	}

	private void setupVariables() {
		// TODO Auto-generated method stub
		save = (Button) findViewById(R.id.btSave);
		load = (Button) findViewById(R.id.btLoad);
		sharedData = (EditText) findViewById(R.id.txtSharedData);
		dataResult = (TextView) findViewById(R.id.tvDataResult);
		save.setOnClickListener(this);
		load.setOnClickListener(this);
		try {
			fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btSave:
			// agregando preferencias al editor de preferencias
			String data = sharedData.getText().toString();
			/*
			 * //salvando datos via archivo File f = new File(FILENAME); try {
			 * fos = new FileOutputStream(f); //garadando datos fos.close(); }
			 * catch (FileNotFoundException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); } catch (IOException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
			try {
				fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
				fos.write(data.getBytes());

				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.btLoad:
			new LoadSomeStuff().execute(FILENAME);
			break;

		default:
			break;
		}
	}
	
	public class LoadSomeStuff extends AsyncTask<String, Integer, String>{
		//tutorial 103 progress dialog
		ProgressDialog dialog;
		
		@Override	//metodo de precarga de la tarea asincrona
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(InternalData.this); //se crea una barra de progreso
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//se coloca el destylo como horizontal
			dialog.setMax(100);//se coloca le maximo de 100
			dialog.show();//muestra la barra de carga
		}
		
		//realiza una tarea en segundo plano
		@Override	//metodo para la ejecucion de la tarea
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String collected = null;
			FileInputStream fis = null;
			
			for (int i = 0; i < 20; i++) {
				publishProgress(5);		//se llama para avisar un progreso en la tarea
				try {
					Thread.sleep(88);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
			dialog.dismiss();	//se elimina la barra de porgreso
			
			//cargando datos desde el editor de preferencias
			try {
				fis = openFileInput(FILENAME);	//se abre un archivo
				byte[] dataArray = new byte[fis.available()];	//reserva memoria para los datos
				while(fis.read(dataArray) != -1){
					collected = new String(dataArray);	//se cargan los datos en el buffer
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					fis.close();	//cierra el archivo
					//dataResult.setText(collected);
					return collected; //returna los datos leidos
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		
		@Override	//muestra el progreso de la tarea
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			dialog.incrementProgressBy(values[0]);
		}

		@Override	//metodo para llamar despues de la tarea, se le pasa el resultado de doInBackground
		protected void onPostExecute(String result) {
			dataResult.setText(result);
		}
		
	}
}

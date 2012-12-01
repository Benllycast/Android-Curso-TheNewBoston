package com.example.ejemplo_adroid;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {

	public String clases[] = { "MainActivity", "Ejemplo1", "Email", "Camera",
			"Data", "GFX", "GFXSurface", "SoundStuff", "Slider", "Tabs","SimpleBrowser",
			"Flipper","SharedPrefs","InternalData","ExternalData","SQLiteExample","Accelerate","HttpExample","WeatherXmlParser" };

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id); 
		String seleccionada = clases[position];
		try {
			Class myclase = Class.forName("com.example.ejemplo_adroid."	+ seleccionada);
			Intent intent = new Intent(Menu.this, myclase);
			this.startActivity(intent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		//colocando las ventanas en full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);//no titulo en las ventanas
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//coloca la actividad en fullscreen de la patalla
		setListAdapter(new ArrayAdapter<String>(Menu.this,android.R.layout.simple_list_item_1, clases));
		//comprobar las preferencias desde del xml
		/*SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean music = getPrefs.getBoolean("checkbox", true);
		if(music == true){
			//codigo de musica
		}else{
			//otra cosa
		}*/
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.cool_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// return super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.aboutUS:
			Intent intent = new Intent("com.example.ejemplo_adroid.ABOUTUS");
			startActivity(intent);
			break;
		case R.id.preferences:
			Intent intent2 = new Intent("com.example.ejemplo_adroid.PREFS");
			startActivity(intent2);
			break;
		case R.id.exit:
			this.finish();
			break;
		default:
			break;
		}

		return false;
	}

}

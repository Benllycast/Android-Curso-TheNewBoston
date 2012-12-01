package com.example.ejemplo_adroid;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WeatherXmlParser extends Activity implements OnClickListener {
	/*esta actividad no trabaja por que le dieron dabaja al api de weather de google*/
	Button bwather;
	EditText etCity, etState;
	TextView tvWather;
	static final String url = "http://www.google.com/ig/api?weather=Bogota";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        bwather =(Button) findViewById(R.id.bWether);
        etCity = (EditText) findViewById(R.id.etCity);
        etState = (EditText) findViewById(R.id.etState);
        tvWather = (TextView) findViewById(R.id.tvWeather);
        bwather.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather, menu);
        return true;
    }

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String c = etCity.getText().toString();
		String s = etState.getText().toString();
		StringBuilder URL = new StringBuilder(url);
		URL.append(c+","+s);
		String fullURL = URL.toString();
		try {
			URL website = new URL(fullURL);
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			HandlingXMLStuff doingWork = new HandlingXMLStuff();
			xr.setContentHandler(doingWork);
			xr.parse(new InputSource(website.openStream()));
			String information = doingWork.getInformation();
			tvWather.setText(information);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tvWather.setText(e.toString());
		} finally {
		}
	}
}

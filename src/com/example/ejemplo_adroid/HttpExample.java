package com.example.ejemplo_adroid;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class HttpExample extends Activity{

	private TextView httpStuff;
	HttpClient client;		//cliente para conectarse por el protocolo http
	//url a conectarse 
	final static String URL = "http://api.twitter.com/1/statuses/user_timeline.json?screen_name=";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpex);
		httpStuff = (TextView) findViewById(R.id.tvHttp);
		client = new DefaultHttpClient();	//se crea un cliente http
		new Read().execute("text");
		
	}
	
	public JSONObject lastTweet(String Username) throws ClientProtocolException, IOException, JSONException{
		StringBuilder url = new StringBuilder(URL);
		url.append(Username);
		HttpGet get = new HttpGet(url.toString());
		HttpResponse r = client.execute(get);
		int status = r.getStatusLine().getStatusCode();
		if(status == 200){
			HttpEntity e = r.getEntity();
			String data = EntityUtils.toString(e);
			JSONArray timeline = new JSONArray(data);
			JSONObject last = timeline.getJSONObject(0);
			return last;
		}else{
			Toast.makeText(HttpExample.this, "error", Toast.LENGTH_LONG).show();
			return null;
		}
	}
	
	public class Read extends AsyncTask<String, Integer, String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			JSONObject json = null;
			try {
				json = lastTweet("mybringback");
				return json.getString(params[0]);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "";
			
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			httpStuff.setText(result);
			
		}
		
		
		
	}
}

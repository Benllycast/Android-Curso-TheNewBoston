package com.example.ejemplo_adroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

@SuppressLint("SetJavaScriptEnabled")
public class SimpleBrowser extends Activity implements OnClickListener {

	WebView webView;
	Button go, foward, back, clearHistory, refresh;
	EditText url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplebrowser);//activar el javascript en el WebView
		webView = (WebView) findViewById(R.id.wvBrowser);

		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);
		webView.setWebViewClient(new ourViewClient());// para evitar abrir los enlaces con un webIntent
		try {
			webView.loadUrl("http://benllycast.99h.com.ar");
		} catch (Exception e) {
			e.printStackTrace();
		}

		go = (Button) findViewById(R.id.btGo);
		back = (Button) findViewById(R.id.btGoBack);
		foward = (Button) findViewById(R.id.btGoFoward);
		refresh = (Button) findViewById(R.id.btRfresh);
		clearHistory = (Button) findViewById(R.id.btHistory);
		url = (EditText) findViewById(R.id.etURl);

		go.setOnClickListener(this);
		back.setOnClickListener(this);
		foward.setOnClickListener(this);
		refresh.setOnClickListener(this);
		clearHistory.setOnClickListener(this);
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.btGo:
			String website = url.getText().toString();
			webView.loadUrl(website);
			//ecultando el keyboard despues de editar text
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(url.getWindowToken(), 0);//se pide cerrar la ventana del teclado
			
			break;
		case R.id.btGoBack:
			if (webView.canGoBack()) {
				webView.goBack();
			}
			break;
		case R.id.btGoFoward:
			if (webView.canGoForward()) {
				webView.goForward();
			}
			break;
		case R.id.btRfresh:
			webView.reload();
			break;
		case R.id.btHistory:
			webView.clearHistory();
			break;

		default:
			break;
		}
	}

}

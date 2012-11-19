package com.example.ejemplo_adroid;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ourViewClient extends WebViewClient {
	@Override
	public boolean shouldOverrideUrlLoading(WebView v, String url){//para sobreescribir el metodo de carga de url
		v.loadUrl(url);
		return true;
	}
}

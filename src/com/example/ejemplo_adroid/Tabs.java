package com.example.ejemplo_adroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener {

	TabHost tabHost;
	TabSpec tabSpec;
	Button bStart, bStop, bAddTab;
	TextView showResult;
	long start, stop;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		tabHost = (TabHost) findViewById(R.id.tabhost);
		bStart = (Button) findViewById(R.id.btStartwatch);
		bStop= (Button) findViewById(R.id.btStopWatch);
		bAddTab = (Button) findViewById(R.id.btAddTab);
		showResult = (TextView) findViewById(R.id.tvShowResult);
		
		bStart.setOnClickListener(this);
		bStop.setOnClickListener(this);
		bAddTab.setOnClickListener(this);
		
		tabHost.setup();
		tabSpec = tabHost.newTabSpec("tab1");
		tabSpec.setContent(R.id.tab1);
		tabSpec.setIndicator("StopWatch");
		tabHost.addTab(tabSpec);
		
		tabSpec = tabHost.newTabSpec("tab2");
		tabSpec.setContent(R.id.tab2);
		tabSpec.setIndicator("Tab 2");
		tabHost.addTab(tabSpec);
		
		tabSpec = tabHost.newTabSpec("tab3");
		tabSpec.setContent(R.id.tab3);
		tabSpec.setIndicator("Add a Tab");
		tabHost.addTab(tabSpec);
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btStartwatch:
			start = System.currentTimeMillis();
			
			break;
		case R.id.btStopWatch:
			stop = System.currentTimeMillis();
			if(start != 0) {
				long result = stop- start;
				int milli = (int) result;
				int second =  (int) result / 1000;
				int minutes = second / 60;
				milli %= 100;
				second %= 60;
				showResult. setText(String.format("%d:%d:%d", minutes, second, milli));
			}
			break;
		case R.id.btAddTab:
			TabSpec spec = tabHost.newTabSpec("tag4");
			spec.setContent(new TabHost.TabContentFactory() {
				
				public View createTabContent(String tag) {
					// TODO Auto-generated method stub
					TextView textView = new TextView(Tabs.this);
					textView.setText("cuelarquies consa en este tab");
					return(textView);
				}
			});
			spec.setIndicator("new");
			tabHost.addTab(spec);
			break;

		default:
			break;
		}
	}
	
}

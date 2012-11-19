package com.example.ejemplo_adroid;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Ejemplo1 extends Activity implements View.OnClickListener{
	
	Button chkCmd;
	ToggleButton passtog;
	EditText input;
	TextView textView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ejemplo1);
        init();
        passtog.setOnClickListener(this);
        chkCmd.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.layout_ejemplo1, menu);
        return true;
    }
    
    public void init(){
    	chkCmd = (Button) findViewById(R.id.btResult);
        passtog = (ToggleButton) findViewById(R.id.tbToggleButton);
        input = (EditText) findViewById(R.id.etTexto);
        textView= (TextView) findViewById(R.id.tvResult);
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.btResult:
				Random random;
				// TODO Auto-generated method stub
				String chek= input.getText().toString();
				textView.setText(chek);
				if(chek.contentEquals("left")){
					textView.setGravity(Gravity.LEFT);
				}else if(chek.contentEquals("center")){
					textView.setGravity(Gravity.CENTER);
				}else if(chek.contentEquals("right")){
					textView.setGravity(Gravity.RIGHT);					
				}else if(chek.contentEquals("blue")){
					textView.setTextColor(Color.BLUE);
				}else if(chek.contentEquals("wtf")){
					random = new Random();
					textView.setText("WTF!!!!!");
					textView.setTextSize(random.nextInt(75));
					textView.setTextColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
					switch(random.nextInt(3)){
					case 0:
						textView.setGravity(Gravity.LEFT);
						break;
					case 1:
						textView.setGravity(Gravity.CENTER);
						break;
					case 2:
						textView.setGravity(Gravity.RIGHT);
						break;
					}
					
				}else{
					textView.setText("Invalid!!!");
					textView.setTextSize(10);
					textView.setTextColor(Color.BLACK);
					textView.setGravity(Gravity.CENTER);
				}
				break;
			case R.id.tbToggleButton:
				if(passtog.isChecked()){
					input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
				}else{
					input.setInputType(InputType.TYPE_CLASS_TEXT);
				}
				break;
			default:
				break;
		}
		
	}
}

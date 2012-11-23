package com.example.ejemplo_adroid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalData extends Activity implements OnItemSelectedListener,
		OnClickListener {

	private TextView canWrite;
	private TextView canRead;
	boolean canR = false, canW = false;
	String state;
	Spinner spinner;
	String stringPath[] = { "Music", "Picture", "Downloads" };
	File path = null, file = null;
	EditText saveFile;
	Button confirm, saveAs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.externaldata);
		confirm = (Button) findViewById(R.id.btConfirm);
		saveAs = (Button) findViewById(R.id.btSaveFile);
		canWrite = (TextView) findViewById(R.id.tvCanWrite);
		canRead = (TextView) findViewById(R.id.tvCanRead);
		saveFile = (EditText) findViewById(R.id.etSaveAs);
		confirm.setOnClickListener(this);
		saveAs.setOnClickListener(this);
		checkState();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				ExternalData.this, android.R.layout.simple_spinner_item,
				stringPath);
		spinner = (Spinner) findViewById(R.id.spinSpiner);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
	}

	private void checkState() {
		// TODO Auto-generated method stub
		state = Environment.getExternalStorageState(); // obtiene el estado del medio de montage externo
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			// si esta montado con WR
			canW = canR = true;
			canWrite.setText("Write: True");
			canRead.setText("Read: True");
		} else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			// si esta montado con R
			canR = true;
			canWrite.setText("Write: False");
			canRead.setText("Read: True");
		} else {
			// cualquiercosa
			canWrite.setText("Write: False");
			canRead.setText("Read: False");
		}
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		int position = spinner.getSelectedItemPosition();
		switch (position) {
		case 0:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;
		case 1:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;
		case 2:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;
		default:
			break;
		}
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btSaveFile:
			String f = saveFile.getText().toString();
			file = new File(path, f + ".png");
			checkState();
			if (canR == canW == true) {
				path.mkdirs();
				try {
					InputStream is = getResources().openRawResource( R.drawable.atomo);
					OutputStream os = new FileOutputStream(file);
					byte[] data = new byte[is.available()];
					is.read(data);
					os.write(data);
					is.close();
					os.close();
					Toast t = Toast.makeText(ExternalData.this,	"file ha been save", Toast.LENGTH_LONG);
					t.show();

					// actialza los archivos para qu eel usuario pueda usarlos
					MediaScannerConnection.scanFile(
							ExternalData.this,new String[] { file.toString() },	null, new MediaScannerConnection.OnScanCompletedListener() {
										public void onScanCompleted( String path, Uri uri) {
											// TODO Auto-generated method stub
											Toast t = Toast.makeText(ExternalData.this,	"scan complete",Toast.LENGTH_SHORT);
											t.show();
										}
									});

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case R.id.btConfirm:
			saveAs.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

}

package com.example.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button scanButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				scanNow();
			}
		});
	}

	public void scanNow() {
		Intent intent = new Intent("com.google.zxing.client.android.SCAN"); 
		intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE"); 
		startActivityForResult(intent, 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent){
		if(requestCode == 0){
			if(resultCode == RESULT_OK){
				String contents = intent.getStringExtra("SCAN_RESULT"); 
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT"); 
				Log.i("xZing", "contents: "+contents+" format: "+format); // Handle successful scan
				Toast.makeText(this, "contents: "+contents+" format: "+format, Toast.LENGTH_LONG).show();
			} 
			else if(resultCode == RESULT_CANCELED){ // Handle cancel 
				Log.i("xZing", "Cancelled"); 
				Toast.makeText(this, "Please try again", Toast.LENGTH_LONG).show();
			} 
		} 
	}
}


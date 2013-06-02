package com.gandan.android.poc;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import com.gandan.android.poc.clipboard.ClipboardGetterUnderSDK11;
import com.gandan.android.poc.clipboard.ClipboardGetterFromAndOverSDK11;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void getTextClipboardUnderSDK11(View view){
		new ClipboardGetterUnderSDK11(this);
	}

	public void getTextClipboardFromAndOverSDK11(View view){
		new ClipboardGetterFromAndOverSDK11(this);
	}

}

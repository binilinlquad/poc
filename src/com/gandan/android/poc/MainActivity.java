package com.gandan.android.poc;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.gandan.android.poc.clipboard.ClipboardGetterUnderSDK11;
import com.gandan.android.poc.clipboard.ClipboardGetterFromAndOverSDK11;
import com.gandan.android.poc.service.local.LocalWordService;

public class MainActivity extends Activity {
	private LocalWordService s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		doBindService();
		
	}

	void doBindService() {
		bindService(new Intent(this, LocalWordService.class), mConnection,
	        Context.BIND_AUTO_CREATE);
	}
	  
	private ServiceConnection mConnection = new ServiceConnection() {
	    public void onServiceConnected(ComponentName className, IBinder binder) {
	      s = ((LocalWordService.MyBinder) binder).getService();
	      Toast.makeText(MainActivity.this, "Connected",
	          Toast.LENGTH_SHORT).show();
	    }

	    public void onServiceDisconnected(ComponentName className) {
	      s = null;
	    }
	  };

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
	
	public void getListWordFromLocalService(View view){
	    if (s != null) {
	    	Toast.makeText(MainActivity.this, "Number of elements" + s.getWordList().size(),
	    		Toast.LENGTH_SHORT).show();
	    }
	}
}

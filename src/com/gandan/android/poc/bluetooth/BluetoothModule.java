package com.gandan.android.poc.bluetooth;

/*
 * This is sample code for learning bluetooth usage
 *
 * @Author Chandra Yang
 */
import android.bluetooth.BluetoothAdapter;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.lang.NullPointerException;

public class BluetoothModule extends BroadcastReceiver {
    private final static String  TAG = BluetoothModule.class.getSimpleName();
    private final BluetoothAdapter mBluetoothAdapter;
    private final static int REQUEST_ENABLE_BT = 1;
    private boolean mEnabled = false;
    private Context mContext;

    public BluetoothModule(Activity activity) {
	mContext = (Context) activity;

	// get BluetoothAdapter
	if(VERSION.SDK_INT< VERSION_CODES.JELLY_BEAN_MR1) {
	    // sdk <= 17 use getDefaultAdapter
	    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	} else {
	    mBluetoothAdapter = (BluetoothAdapter) ((Context)activity).getSystemService(Context.BLUETOOTH_SERVICE);
	}

	if (mBluetoothAdapter == null) {
	    // Device does not support Bluetooth
	    throw new NullPointerException("BluetoothAdapter is null");
	}

	// check if bluetooth is enabled. If not, ask the user to activate it
	if(!mBluetoothAdapter.isEnabled()) {
	    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	    activity.startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
	} else {
	    // change true when bluetooth state on
	    mEnabled = true;
	}
	
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
	if(requestCode == REQUEST_ENABLE_BT) {
	    if(resultCode == Activity.RESULT_OK ) {
		mEnabled = true;
	    } else {
		mEnabled = false;
	    }
	    Toast.makeText(mContext, "status " + mEnabled, Toast.LENGTH_LONG);
	}
    }

    @Override
    public void onReceive(Context context, Intent intent){
	if(intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.STATE_OFF) == 
	   BluetoothAdapter.STATE_ON){
	    mEnabled = true;
	} else {
	    mEnabled = false;
	}
	Toast.makeText(mContext, "status " + mEnabled, Toast.LENGTH_LONG);
    }
}

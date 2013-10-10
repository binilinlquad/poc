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
import android.app.Activity;
import android.content.Intent;

import java.lang.NullPointerException;

public class BluetoothModule {
    private final BluetoothAdapter mBluetoothAdapter;
    private final static int REQUEST_ENABLE_BT = 1;
    
    public BluetoothModule(Activity activity) {
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
	}
	
    }

    public void onActivityResult(int a, int b, Intent intent){
    }
}

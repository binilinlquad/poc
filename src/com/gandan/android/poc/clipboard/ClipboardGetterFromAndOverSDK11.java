package com.gandan.android.poc.clipboard;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

/*
 * This class will used for practice clipboard in android
 * Author Chandra
 * Date 29-05-2013
 */

public class ClipboardGetterFromAndOverSDK11 {

	final String TAG = ClipboardGetterFromAndOverSDK11.class.getCanonicalName();
	
	public ClipboardGetterFromAndOverSDK11(Context context){
		final ClipboardManager clipboardManager = (ClipboardManager)context.
			getSystemService(Context.CLIPBOARD_SERVICE);

		// get text clipboard. Not recommended method, just want to test
		try {
			Log.w(TAG, "Value " + 
					clipboardManager.getPrimaryClip().getItemAt(0).getText());
		} catch(Exception e){
			Log.w(TAG, "Exception");
		}
	}
}

package com.gandan.android.poc.clipboard;

import android.text.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

/*
 * This class will used for practice clipboard in android.
 * Under SDK 11, there is no android.content.ClipboardManager.
 * If used with those package, android will throw noDefClassFound
 * Author Chandra
 * Date 29-05-2013
 */

public class ClipboardGetterUnderSDK11 {
	final String TAG = ClipboardGetterUnderSDK11.class.getCanonicalName();
	
	public ClipboardGetterUnderSDK11(Context context){
		final ClipboardManager clipboardManager = (ClipboardManager)context.
			getSystemService(Context.CLIPBOARD_SERVICE);

		// get text clipboard. Not recommended method, just want to test
		try {
			Log.w(TAG, "Value " + 
					clipboardManager.getText());
		} catch(Exception e){
			Log.w(TAG, "Exception");
		}
	}
}

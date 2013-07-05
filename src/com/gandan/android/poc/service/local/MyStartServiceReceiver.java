package com.gandan.android.poc.service.local;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;

public class MyStartServiceReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent){
		Intent service = new Intent(context, LocalWordService.class);
		context.startService(service);
	}

}

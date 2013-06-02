package com.gandan.android.poc.pm;

/*
 * Author Chandra
 * Date 27-05-2013
 * 
 * This class used for receive broadcast intent, in this ase 
 * PACKAGE_ADDED and PACKAGE_REMOVED and output it with Log
 * This class will be added into AndroidManifest.xml
 */
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.util.Log;

public class IntentReceiver extends BroadcastReceiver {
	private final String TAG = this.getClass().getCanonicalName();
	@Override
	public void onReceive(Context context, Intent intent){
		String action = intent.getAction();
		Log.w(TAG, "Action " + action);
		int uid = intent.getIntExtra("android.intent.extra.UID", -1);
		Log.w(TAG, "UID " + uid);
		PackageManager pm = context.getPackageManager();
		if (action.endsWith("PACKAGE_ADDED")){
			//	get array of String of package names for uid
			String[] packageNames = pm.getPackagesForUid(uid);
			//	looping all package which use uid
			for(String packageName : packageNames){
				Log.w(TAG, "Package Name " + packageName);
				// get Permission
				try{
					PackageInfo permInfo = pm.getPackageInfo(packageName, 
							PackageManager.GET_PERMISSIONS);
					String[] permissions = permInfo.requestedPermissions;
					for(String permission: permissions){
						Log.w(TAG, "Permission " + permission);
					}
				} catch(NameNotFoundException nfe){
					Log.w(TAG, "Permission not found");
				}
			}
		}  else {
			
		}		
	}
}

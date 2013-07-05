package com.gandan.android.poc.service.local;

import java.util.Random;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
/**
 * This is practice create local service from Vogella
 * http://www.vogella.com/articles/AndroidServices/article.html
 * 
 * @author chandra
 *
 */
public class LocalWordService extends Service {
	private final IBinder mBinder = new MyBinder();
	private ArrayList<String> list = new ArrayList<String>();
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startid){
		Random random = new Random();
		if(random.nextBoolean()){
			list.add("Linux");			
		}
		if(random.nextBoolean()){
			list.add("Windows");
		}
		if(random.nextBoolean()){
			list.add("Macintosh");
		}
		if(list.size()>=20){
			list.remove(0);
		}
		return Service.START_NOT_STICKY;
		
	}
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
	
	public class MyBinder extends Binder {
		public LocalWordService getService(){
			return LocalWordService.this;
		}
	}
	
	public List getWordList(){
		return list;
	}

}

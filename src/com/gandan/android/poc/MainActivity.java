package com.gandan.android.poc;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

import com.gandan.android.poc.clipboard.ClipboardGetterUnderSDK11;
import com.gandan.android.poc.clipboard.ClipboardGetterFromAndOverSDK11;
import com.gandan.android.poc.service.local.LocalWordService;
import com.gandan.android.poc.bluetooth.BluetoothModule;
import com.gandan.android.poc.ui.CircleView;

public class MainActivity extends Activity {
	private LocalWordService s;
    private ObjectAnimator mCircleAnimator;

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

    public void getBluetoothAdapter(View view){
	new BluetoothModule(this);
    }

    /**
     * Do animation when CircleView is clicked
     *
     * @param view instance of CircleView
     */
    public void animateCircle(final View view) {
        long duration = 5000;

        // factory for getting ObjectAnimator
        mCircleAnimator = ObjectAnimator.ofInt(view, CircleView.COLOR, Color.CYAN, Color.RED);

        // set evaluator for color
        ArgbEvaluator colorEvaluator = new ArgbEvaluator();
        colorEvaluator.evaluate((float) 0.001, Color.CYAN, Color.RED);
        mCircleAnimator.setEvaluator(colorEvaluator);

        // set animation duration
        mCircleAnimator.setDuration(duration);

        // add animation update event listener
        mCircleAnimator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                // unsafe casting
                ((CircleView)view).setColor((Integer) valueAnimator.getAnimatedValue());

                // redraw
                ((CircleView)view).updateColor();

                // Change background color through view
                //view.setBackgroundColor((Integer)valueAnimator.getAnimatedValue());
            }
        });

        // start animation
        mCircleAnimator.start();
    }
}

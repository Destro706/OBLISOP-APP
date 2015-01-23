package de.oblisop.destro706;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

public class Splash extends Activity{
	
	private static String TAG = Splash.class.getName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash);
		 
	      IntentLauncher launcher = new IntentLauncher();
	      launcher.start();
	   }
	 
	   private class IntentLauncher extends Thread {
	      @Override
	      
	      public void run() {
	         try {
	        	 Thread.sleep(3000);
	         } catch (Exception e) {
	            Log.e(TAG, e.getMessage());
	         }
	 
	         Intent intent = new Intent(Splash.this, LoginActivity.class);
	         Splash.this.startActivity(intent);
	         Splash.this.finish();
	      }
	   }
	
	
	
	

}

package com.example.trackingcar;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

import com.example.trackingcard.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {
	
	//private GPSTracker location = new GPSTracker();
	private BroadcastReceiver mIntentReceiver;
	private GoogleMap myMap;
	private LatLng carLocation;
	private LatLng lastKnowLocation = new LatLng(19.331236049, -99.183676424); //somehow figure out which was the last know locatation
	private Button localizar;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		
		myMap.addMarker(new MarkerOptions().
				position(lastKnowLocation)
				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
				.title("Última ubicación conocida")).showInfoWindow();
		myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastKnowLocation, 16));
		
		localizar = (Button)findViewById(R.id.localizar);
		localizar.getBackground().setAlpha(120);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		//location.servicesConnected();
		 IntentFilter intentFilter = new IntentFilter("SmsMessage.intent.MAIN");
			mIntentReceiver = new BroadcastReceiver(){

				@Override
				public void onReceive(Context context, Intent intent) {
					// TODO Auto-generated method stub
					String msg = intent.getStringExtra("get_msg");
					
					 // Process the sms 
	                msg = msg.replace("\n", "");
	               
	                String [] data = msg.split(" ");
	                String latitude = data[0].substring(data[0].lastIndexOf(":")+1,data[0].length());
	        		String longitude = data[1].substring(data[1].indexOf(":")+1, data[1].length());
	        		
	        		carLocation = new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));
	        		
	        		myMap.clear();
	        		
	        		myMap.addMarker(new MarkerOptions().
	        				position(carLocation)
	        				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
	        				.title("Tu auto esta aquí")).showInfoWindow();
	        		
	        		myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
	                       carLocation,16));
	        		
	        		
	      
	                //Log.e("onResume", "" + msg );
	                //Log.e("On Resume 2 "," "+latitude);
	                //Log.e("On Resume 2 "," " +longitude);
	                //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
				}
				
			};
			this.registerReceiver(mIntentReceiver, intentFilter);
		
		
		
	}
	
	@Override
	protected void onPause() {

		super.onPause();
		this.unregisterReceiver(this.mIntentReceiver);
		
	}
}

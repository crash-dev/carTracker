package com.example.trackingcar;

import com.google.android.gms.maps.model.LatLng;

public class CarLocation {
	
	private LatLng coords;
	
			 
	
	public CarLocation(double lat,double lon){
		this.coords = new LatLng(lat, lon);
	}

	public LatLng getCoords(){
		return this.coords;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String msg = "lat:19.304432 long:-99173295 speed:0.00 dir:43. T:01/03/13 03:36 PWR:ON Door:OFF ACC:OFF http://maps.google.com/mpas?f=q&q=19.304432,-99.173295&z=16";
		System.out.println(msg);

	}

}

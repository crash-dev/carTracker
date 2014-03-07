package com.example.trackingcar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bundle extras = intent.getExtras();
		if(extras == null)
			return;
		
		Object[] pdus = (Object[]) extras.get("pdus"); //get the element with the key
		for(int i = 0 ; i < pdus.length; i++){
			SmsMessage sMessage = SmsMessage.createFromPdu((byte[])pdus[i]);
			String sender = sMessage.getOriginatingAddress();
			String body = sMessage.getMessageBody().toString();
			//Log.d("EN RECEIVER", sender);
			//Log.d("EN RECEIVER", body);
			
			 // A custom Intent that will used as another Broadcast
			Intent in = new Intent("SmsMessage.itent.MAIN").putExtra("get_msg", sender);
			
			//add condition to sender
			//if(sender.equals("##########"))
			context.sendBroadcast(in);
		}
		
	}

}

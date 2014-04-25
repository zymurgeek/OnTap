package com.itllp.barleylegalhomebrewers.ontap;

import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;

public abstract class OnTapContentProviderObserver extends ContentObserver {

	public OnTapContentProviderObserver(Handler handler) {
		super(handler);
	}

	public void registerObserver(NetworkActivityObserver observer) {
		activityObserver = observer;
		Log.e("OT", "OnTapContentProviderObserver registerObserver(" + 
		observer + ")");
	}
	
	public void deregisterObserver() {
		activityObserver = null;
		Log.e("OT", "OnTapContentProviderObserver deregisterObserver()");
	}

	public NetworkActivityObserver getObserver() {
		Log.e("OT", "OnTapContentProviderObserver getObserver() = " + 
		activityObserver);
		return activityObserver;
	}
	
	private NetworkActivityObserver activityObserver = null;
}

package com.itllp.barleylegalhomebrewers.ontap;

import android.database.ContentObserver;
import android.os.Handler;

public abstract class OnTapContentProviderObserver extends ContentObserver {

	public OnTapContentProviderObserver(Handler handler) {
		super(handler);
	}

	public void registerObserver(NetworkActivityObserver observer) {
		activityObserver = observer;
	}
	
	public void deregisterObserver() {
		activityObserver = null;
	}

	public NetworkActivityObserver getObserver() {
		return activityObserver;
	}
	
	private NetworkActivityObserver activityObserver = null;
}

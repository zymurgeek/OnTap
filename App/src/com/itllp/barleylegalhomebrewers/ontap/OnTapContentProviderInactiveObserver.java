package com.itllp.barleylegalhomebrewers.ontap;

import android.net.Uri;
import android.os.Handler;

public class OnTapContentProviderInactiveObserver extends OnTapContentProviderObserver {

	public OnTapContentProviderInactiveObserver(Handler handler) {
		super(handler);
	}

	@Override
	public void onChange(boolean selfChange) {
		super.onChange(selfChange);
		notifyObservers();
	}

	public void onChange(boolean selfChange, Uri uri) {
		onChange(selfChange);
	}

	private void notifyObservers() {
		NetworkActivityObserver observer = getObserver();
		if (observer != null) {
			observer.networkInactive();
		}
	}
}

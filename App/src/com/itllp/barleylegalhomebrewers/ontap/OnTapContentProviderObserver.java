package com.itllp.barleylegalhomebrewers.ontap;

import android.annotation.SuppressLint;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

@SuppressLint("NewApi")
public class OnTapContentProviderObserver extends ContentObserver {

	public OnTapContentProviderObserver(Handler handler) {
		super(handler);
	}

	@Override
	public void onChange(boolean selfChange) {
		super.onChange(selfChange);
	}

	@Override
	public void onChange(boolean selfChange, Uri uri) {
		super.onChange(selfChange, uri);
	}

	
}

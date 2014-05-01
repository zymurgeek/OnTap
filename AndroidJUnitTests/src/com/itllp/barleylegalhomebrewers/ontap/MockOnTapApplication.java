package com.itllp.barleylegalhomebrewers.ontap;

import android.app.Application;

public class MockOnTapApplication extends Application {
	@Override
	public void onCreate() {
	    super.onCreate();
	    setTheme(R.style.Theme_AppCompat);
	}
}

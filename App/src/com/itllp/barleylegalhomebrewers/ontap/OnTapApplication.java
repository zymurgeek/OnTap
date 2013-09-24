package com.itllp.barleylegalhomebrewers.ontap;

import android.app.Application;

public class OnTapApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		EventDatabaseLoaderFactory
		.createSQLiteEventTableFromBetaServerUpdater();
	}
}

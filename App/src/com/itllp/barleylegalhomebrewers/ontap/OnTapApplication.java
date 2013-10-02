package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.contentprovider.EventTableUpdaterFactory;

import android.app.Application;

public class OnTapApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		EventTableUpdaterFactory
		.createSQLiteEventTableFromBetaServerUpdater();
	}
}

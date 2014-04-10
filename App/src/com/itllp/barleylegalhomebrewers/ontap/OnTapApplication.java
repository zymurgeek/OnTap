package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.contentprovider.BeerTableUpdaterFactory;
import com.itllp.barleylegalhomebrewers.ontap.contentprovider.EventTableUpdaterFactory;

import android.annotation.TargetApi;
import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

public class OnTapApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		EventTableUpdaterFactory
		.createSQLiteEventTableFromBetaServerUpdater();
		BeerTableUpdaterFactory
		.createSQLiteBeerTableFromBetaServerUpdater();
		enableStrictModeForDebug();
	}
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	private void enableStrictModeForDebug() {
		if ( Build.VERSION.SDK_INT >= 9 && isDebug() ) {
			StrictMode.enableDefaults();
		}
	}

	private boolean isDebug() {
		return true;
	}
}

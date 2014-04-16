package com.itllp.barleylegalhomebrewers.ontap;

import java.lang.reflect.Method;

import com.itllp.barleylegalhomebrewers.ontap.contentprovider.BeerTableUpdaterFactory;
import com.itllp.barleylegalhomebrewers.ontap.contentprovider.EventTableUpdaterFactory;

import android.annotation.TargetApi;
import android.app.Application;
import android.os.Build;

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
			try {
				Class<?> strictModeClass = Class.forName("android.os.StrictMode", true, Thread.currentThread()
						.getContextClassLoader());
				Method enableDefaultsMethod = strictModeClass.getMethod("enableDefaults", strictModeClass);
				enableDefaultsMethod.invoke(strictModeClass);
			} catch (Exception e) {
				// StrictMode not available
		    }
		}
	}

	private boolean isDebug() {
		return true;
	}
}

package com.itllp.barleylegalhomebrewers.ontap;

import java.lang.reflect.Method;

import com.itllp.barleylegalhomebrewers.ontap.contentprovider.BeerTableUpdaterFactory;
import com.itllp.barleylegalhomebrewers.ontap.contentprovider.EventTableUpdaterFactory;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.os.Build;

public class OnTapApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		CursorLoaderFactory.setImplementation(
				new CursorLoaderFactoryImplementation());
		if (isDebug()) {
			EventTableUpdaterFactory
			.createSQLiteEventTableFromBetaServerUpdater();
			BeerTableUpdaterFactory
			.createSQLiteBeerTableFromBetaServerUpdater();
		} else {
			EventTableUpdaterFactory
			.createSQLiteEventTableFromProductionServerUpdater();
			BeerTableUpdaterFactory
			.createSQLiteBeerTableFromProductionServerUpdater();
		}
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
		boolean isDebug = (0  != (getApplicationInfo().flags & 
				ApplicationInfo.FLAG_DEBUGGABLE));
		return isDebug;
	}
}

package com.itllp.barleylegalhomebrewers.ontap;

import java.lang.reflect.Method;

import com.itllp.barleylegalhomebrewers.ontap.contentprovider.BeerTableUpdaterFactory;
import com.itllp.barleylegalhomebrewers.ontap.contentprovider.EventTableUpdaterFactory;
import com.itllp.barleylegalhomebrewers.ontap.persistence.Persister;
import com.itllp.barleylegalhomebrewers.ontap.persistence.PreferencesPersister;
import com.itllp.barleylegalhomebrewers.ontap.persistence.impl.PreferencesFilePersister;
import com.itllp.barleylegalhomebrewers.ontap.persistence.impl.PreferencesPersisterImpl;
import com.itllp.barleylegalhomebrewers.ontap.preferences.OnTapPreferences;
import com.itllp.barleylegalhomebrewers.ontap.preferences.OnTapPreferencesFactory;
import com.itllp.barleylegalhomebrewers.ontap.preferences.impl.OnTapPreferencesImpl;
import com.itllp.barleylegalhomebrewers.ontap.preferences.persistence.PreferencesPersisterFactory;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.os.Build;

public class OnTapApplication extends Application {
	
	public static final String PREFERENCES_FILE = "OnTapPreferences";

	
	@Override
	public void onCreate() {
		super.onCreate();

		OnTapPreferences preferences = new OnTapPreferencesImpl();
		OnTapPreferencesFactory.setPreferences(preferences);
		Persister persister = new PreferencesFilePersister
				(PREFERENCES_FILE);
		PreferencesPersister preferencesPersister = 
				new PreferencesPersisterImpl(persister); 
		PreferencesPersisterFactory.setPreferencesPersister(
				preferencesPersister);

		CursorLoaderFactory.setImplementation(
				new CursorLoaderFactoryImplementation());
		preferencesPersister.restoreState(preferences, this);
		if (preferences.useBetaServer()) {
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

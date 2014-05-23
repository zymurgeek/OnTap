package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.contentprovider.BeerTableUpdaterFactory;
import com.itllp.barleylegalhomebrewers.ontap.contentprovider.EventTableUpdaterFactory;
import com.itllp.barleylegalhomebrewers.ontap.persistence.Persister;
import com.itllp.barleylegalhomebrewers.ontap.persistence.PreferencesPersister;
import com.itllp.barleylegalhomebrewers.ontap.persistence.impl.PreferencesPersisterImpl;
import com.itllp.barleylegalhomebrewers.ontap.persistence.impl.test.StubPreferencesPersister;
import com.itllp.barleylegalhomebrewers.ontap.preferences.OnTapPreferences;
import com.itllp.barleylegalhomebrewers.ontap.preferences.OnTapPreferencesFactory;
import com.itllp.barleylegalhomebrewers.ontap.preferences.impl.OnTapPreferencesImpl;
import com.itllp.barleylegalhomebrewers.ontap.preferences.persistence.PreferencesPersisterFactory;
import android.app.Application;

/* This is a stub version for testing only! */
public class OnTapApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();

		OnTapPreferences preferences = new OnTapPreferencesImpl();
		OnTapPreferencesFactory.setPreferences(preferences);
		Persister persister = new StubPreferencesPersister();
		PreferencesPersister preferencesPersister = 
				new PreferencesPersisterImpl(persister); 
		PreferencesPersisterFactory.setPreferencesPersister(
				preferencesPersister);

		CursorLoaderFactory.setImplementation(
				new CursorLoaderFactoryImplementation());
		EventTableUpdaterFactory
			.createSQLiteEventTableFromBetaServerUpdater();
		BeerTableUpdaterFactory
			.createSQLiteBeerTableFromBetaServerUpdater();
	}
}

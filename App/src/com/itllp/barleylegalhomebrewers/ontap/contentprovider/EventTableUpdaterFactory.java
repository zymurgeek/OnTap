package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoaderFactory;

public class EventTableUpdaterFactory {
	public static EventTableUpdater getInstance() {
		// TODO Replace database loader factory with this one
		return EventDatabaseLoaderFactory.getInstance();
	}
}

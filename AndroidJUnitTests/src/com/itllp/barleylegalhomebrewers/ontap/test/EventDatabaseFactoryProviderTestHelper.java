package com.itllp.barleylegalhomebrewers.ontap.test;

import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseFactoryProvider;

public class EventDatabaseFactoryProviderTestHelper extends EventDatabaseFactoryProvider {
	
	public EventDatabaseFactoryProviderTestHelper() {
	}

	public static void clearEventDatabaseFactory() {
		eventDatabaseFactory = null;
	}

}

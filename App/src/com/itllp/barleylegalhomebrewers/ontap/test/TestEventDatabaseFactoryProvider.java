package com.itllp.barleylegalhomebrewers.ontap.test;

import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseFactoryProvider;

public class TestEventDatabaseFactoryProvider extends EventDatabaseFactoryProvider {
	
	public TestEventDatabaseFactoryProvider() {
	}

	public static void clearEventDatabaseFactory() {
		eventDatabaseFactory = null;
	}

}

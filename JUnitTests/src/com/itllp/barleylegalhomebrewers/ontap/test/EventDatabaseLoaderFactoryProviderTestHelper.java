package com.itllp.barleylegalhomebrewers.ontap.test;

import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoaderFactoryProvider;

public class EventDatabaseLoaderFactoryProviderTestHelper 
extends EventDatabaseLoaderFactoryProvider {

	public static void clearEventDatabaseLoaderFactory() {
		EventDatabaseLoaderFactoryProvider.factory = null;
	}

}

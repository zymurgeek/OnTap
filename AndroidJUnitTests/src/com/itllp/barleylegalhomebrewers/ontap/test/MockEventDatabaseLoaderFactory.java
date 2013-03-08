package com.itllp.barleylegalhomebrewers.ontap.test;

import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoaderFactory;

public class MockEventDatabaseLoaderFactory implements EventDatabaseLoaderFactory {

	private EventDatabaseLoader eventDatabaseLoader = null;
	
	public void MOCK_setEventDatabaseLoader(MockEventDatabaseLoader mockLoader) {
		eventDatabaseLoader = mockLoader;
	}

	@Override
	public EventDatabaseLoader getEventDatabaseLoader() {
		return eventDatabaseLoader;
	}

}

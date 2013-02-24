package com.itllp.barleylegalhomebrewers.ontap.test;

import com.itllp.barleylegalhomebrewers.ontap.EventDatabase;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseFactory;

public class LocalEventDatabaseFactory implements EventDatabaseFactory {

	private static EventDatabase eventDb = new LocalEventDatabase();
	
	@Override
	public EventDatabase getEventDatabase() {
		return eventDb;
	}

}

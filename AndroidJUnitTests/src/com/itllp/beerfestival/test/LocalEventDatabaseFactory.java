package com.itllp.beerfestival.test;

import com.itllp.barleylegalhomebrewers.ontap.EventDatabase;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseFactory;

public class LocalEventDatabaseFactory implements EventDatabaseFactory {

	private EventDatabase eventDb = new LocalEventDatabase();
	
	@Override
	public EventDatabase getEventDatabase() {
		return eventDb;
	}

}

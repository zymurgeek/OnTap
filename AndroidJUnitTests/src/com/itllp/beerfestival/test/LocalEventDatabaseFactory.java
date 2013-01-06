package com.itllp.beerfestival.test;

import com.itllp.beerfestival.EventDatabase;
import com.itllp.beerfestival.EventDatabaseFactory;
import com.itllp.beerfestival.LocalEventDatabase;

public class LocalEventDatabaseFactory implements EventDatabaseFactory {

	private EventDatabase eventDb = new LocalEventDatabase();
	
	@Override
	public EventDatabase getEventDatabase() {
		return eventDb;
	}

}

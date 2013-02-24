package com.itllp.barleylegalhomebrewers.ontap.test;

import com.itllp.barleylegalhomebrewers.ontap.OldEventDatabase;
import com.itllp.barleylegalhomebrewers.ontap.OldEventDatabaseFactory;

public class OldLocalEventDatabaseFactory implements OldEventDatabaseFactory {

	private static OldEventDatabase eventDb = new OldLocalEventDatabase();
	
	@Override
	public OldEventDatabase getEventDatabase() {
		return eventDb;
	}

}

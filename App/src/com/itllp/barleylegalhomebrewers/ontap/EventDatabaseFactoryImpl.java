package com.itllp.barleylegalhomebrewers.ontap;

import android.content.Context;

public class EventDatabaseFactoryImpl implements EventDatabaseFactory {
	private static EventDatabaseFactory concreteEventDatabaseFactory = null;
	
	public EventDatabaseFactoryImpl(Context context) {
		if (null == concreteEventDatabaseFactory) {
			concreteEventDatabaseFactory = new JsonEventDatabaseFactory(context);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.itllp.barleylegalhomebrewers.ontap.EventDatabaseFactoryIF#getEventDatabase()
	 */
	@Override
	public EventDatabase getEventDatabase() {
		return concreteEventDatabaseFactory.getEventDatabase();
	}

	public static void setEventDatabaseFactory(EventDatabaseFactory factory) {
		concreteEventDatabaseFactory = factory;
	}
	
}

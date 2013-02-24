package com.itllp.barleylegalhomebrewers.ontap;

import android.content.Context;

public class OldEventDatabaseFactoryImpl implements OldEventDatabaseFactory {
	private static OldEventDatabaseFactory concreteEventDatabaseFactory = null;
	
	public OldEventDatabaseFactoryImpl(Context context) {
		if (null == concreteEventDatabaseFactory) {
			concreteEventDatabaseFactory = new OldJsonEventDatabaseFactory(context);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.itllp.barleylegalhomebrewers.ontap.EventDatabaseFactoryIF#getEventDatabase()
	 */
	@Override
	public OldEventDatabase getEventDatabase() {
		return concreteEventDatabaseFactory.getEventDatabase();
	}

	public static void setEventDatabaseFactory(OldEventDatabaseFactory factory) {
		concreteEventDatabaseFactory = factory;
	}
	
}

package com.itllp.barleylegalhomebrewers.ontap;

public class EventDatabaseFactoryProvider {
	protected static EventDatabaseFactory eventDatabaseFactory = null;

	public static void setEventDatabaseFactory(EventDatabaseFactory factory) {
		if (null == factory) {
			throw new NullFactoryException();
		}
		if (null != eventDatabaseFactory) {
			throw new FactoryAlreadySetException();
		}
		eventDatabaseFactory = factory;
	}

	public static EventDatabaseFactory getEventDatabaseFactory() {
		if (null == eventDatabaseFactory) {
			throw new UninitializedFactoryException();
		}
		return eventDatabaseFactory;
	}

}

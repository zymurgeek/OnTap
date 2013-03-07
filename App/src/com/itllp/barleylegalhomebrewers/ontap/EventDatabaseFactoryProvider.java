package com.itllp.barleylegalhomebrewers.ontap;

public class EventDatabaseFactoryProvider {
	private static EventDatabaseFactory eventDatabaseFactory = null;
	// todo move clear to testing class
	// todo move tests for this to non-android junit tests
	public static void setEventDatabaseFactory(EventDatabaseFactory factory)
	throws FactoryAlreadySetException, NullFactoryException {
		if (null == factory) {
			throw new NullFactoryException();
		}
		if (null != eventDatabaseFactory) {
			throw new FactoryAlreadySetException();
		}
		eventDatabaseFactory = factory;
	}

	public static EventDatabaseFactory getEventDatabaseFactory() 
		throws UninitializedFactoryException {
		if (null == eventDatabaseFactory) {
			throw new UninitializedFactoryException();
		}
		return eventDatabaseFactory;
	}

	public static void clearEventDatabaseFactory() {
		eventDatabaseFactory = null;
	}
	
}

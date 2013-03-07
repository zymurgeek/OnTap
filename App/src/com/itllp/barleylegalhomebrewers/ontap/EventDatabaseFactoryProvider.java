package com.itllp.barleylegalhomebrewers.ontap;

public class EventDatabaseFactoryProvider {
	protected static EventDatabaseFactory eventDatabaseFactory = null;

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

}

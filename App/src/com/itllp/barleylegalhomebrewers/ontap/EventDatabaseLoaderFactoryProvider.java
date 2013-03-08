package com.itllp.barleylegalhomebrewers.ontap;

public class EventDatabaseLoaderFactoryProvider {

	protected static EventDatabaseLoaderFactory factory = null;
	
	public static void setEventDatabaseLoaderFactory(
			EventDatabaseLoaderFactory factory) {
		if (null == factory) {
			throw new NullFactoryException();
		}
		if (null != EventDatabaseLoaderFactoryProvider.factory) {
			throw new FactoryAlreadySetException();
		}
		EventDatabaseLoaderFactoryProvider.factory = factory;
	}

	public static EventDatabaseLoaderFactory getEventDatabaseLoaderFactory() {
		if (null == factory) {
			throw new UninitializedFactoryException();
		}
		return factory;
	}

}

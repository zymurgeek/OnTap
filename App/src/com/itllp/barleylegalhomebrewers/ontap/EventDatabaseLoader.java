package com.itllp.barleylegalhomebrewers.ontap;

public abstract class EventDatabaseLoader implements EventDatabaseLoaderIF {
	private static EventDatabaseLoaderIF instance = null;
	
	public static EventDatabaseLoaderIF getInstance() {
		return instance;
	}
	
	// TODO Make this protected
	public static void clearInstance() {
		instance = null;
	}
	
	protected static void setInstance(EventDatabaseLoaderIF newInstance) {
		if (null != newInstance && null != instance) {
			throw new DatabaseLoaderAlreadyInstantiatedException();
		}
		instance = newInstance;
	}

}

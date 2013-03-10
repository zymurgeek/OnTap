package com.itllp.barleylegalhomebrewers.ontap;

public abstract class EventDatabaseLoader {
	private static EventDatabaseLoader instance = null;
	
	public void load() {}

	public static EventDatabaseLoader getInstance() {
		if (null == instance) {
			throw new DatabaseLoaderNotInstantiatedException();
		}
		return instance;
	}
	
	protected static void setInstance(EventDatabaseLoader newInstance) {
		if (null != newInstance && null != instance) {
			throw new DatabaseLoaderAlreadyInstantiatedException();
		}
		instance = newInstance;
	}

}

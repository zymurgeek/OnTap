package com.itllp.barleylegalhomebrewers.ontap;

public class BeerDatabaseLoader {
	private static BeerDatabaseLoader instance = null;
	// TODO event ID does not belong here
	private static int eventId = 0;
	
	public void load(int eventId) {}

	public static BeerDatabaseLoader getInstance() {
		if (null == instance) {
			throw new DatabaseLoaderNotInstantiatedException();
		}
		return instance;
	}
	
	protected static void setInstance(BeerDatabaseLoader newInstance) {
		if (null != newInstance && null != instance) {
			throw new DatabaseLoaderAlreadyInstantiatedException();
		}
		instance = newInstance;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		BeerDatabaseLoader.eventId = eventId;
	}


}

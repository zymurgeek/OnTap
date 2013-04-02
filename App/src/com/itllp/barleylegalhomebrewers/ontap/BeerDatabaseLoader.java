package com.itllp.barleylegalhomebrewers.ontap;

public class BeerDatabaseLoader {
	private static BeerDatabaseLoader instance = null;
	
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


}

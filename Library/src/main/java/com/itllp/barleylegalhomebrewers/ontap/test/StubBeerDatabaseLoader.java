package com.itllp.barleylegalhomebrewers.ontap.test;

import com.itllp.barleylegalhomebrewers.ontap.BeerDatabaseLoader;

public class StubBeerDatabaseLoader extends BeerDatabaseLoader {

	// TODO:  Count by event ID
	private int loadCount = 0;
	
	public static void clearInstance() {
		setInstance(null);
	}

	public static void create() {
		setInstance(new StubBeerDatabaseLoader());
	}

	@Override
	public void load(int eventId) {
		++loadCount;
	}
	
	public void MOCK_clearLoadCount() {
		loadCount = 0;
	}
	
	public int MOCK_getLoadCount() {
		return loadCount;
	}

}

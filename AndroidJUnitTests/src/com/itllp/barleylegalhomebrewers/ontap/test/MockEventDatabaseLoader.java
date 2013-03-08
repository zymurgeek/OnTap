package com.itllp.barleylegalhomebrewers.ontap.test;

import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoader;

public class MockEventDatabaseLoader implements EventDatabaseLoader {

	private int loadCount = 0;
	
	public int MOCK_getLoadCount() {
		return loadCount;
	}
	
	public void MOCK_resetLoadCount() {
		loadCount = 0;
	}

	@Override
	public void load() {
		++loadCount;
	}

}

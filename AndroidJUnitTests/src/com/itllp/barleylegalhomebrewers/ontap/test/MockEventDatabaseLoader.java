package com.itllp.barleylegalhomebrewers.ontap.test;

import android.content.Context;

import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoader;

public class MockEventDatabaseLoader extends EventDatabaseLoader {

	private int loadCount = 0;
	
	public int MOCK_getLoadCount() {
		return loadCount;
	}
	
	public void MOCK_resetLoadCount() {
		loadCount = 0;
	}

	@Override
	public void load(Context unused) {
		++loadCount;
	}

}

package com.itllp.barleylegalhomebrewers.ontap.test;

import android.content.Context;

import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoader;

public class StubEventDatabaseLoader extends EventDatabaseLoader {

	private int loadCount = 0;
	
	public static void clearInstance() {
		setInstance(null);
	}

	public static void create() {
		setInstance(new StubEventDatabaseLoader());
	}

	@Override
	public void load(Context unused) {
		++loadCount;
	}
	
	public void MOCK_clearLoadCount() {
		loadCount = 0;
	}
	
	public int MOCK_getLoadCount() {
		return loadCount;
	}

}

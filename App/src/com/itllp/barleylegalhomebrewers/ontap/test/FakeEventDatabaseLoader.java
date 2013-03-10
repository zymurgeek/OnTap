package com.itllp.barleylegalhomebrewers.ontap.test;

import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoader;

public class FakeEventDatabaseLoader extends EventDatabaseLoader {

	public static void clearInstance() {
		setInstance(null);
	}

	public static void create() {
		setInstance(new FakeEventDatabaseLoader());
	}

	public int MOCK_getLoadCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}

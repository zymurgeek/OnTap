package com.itllp.barleylegalhomebrewers.ontap.test;

import com.itllp.barleylegalhomebrewers.ontap.NewEventDatabase;

public class FakeNewEventDatabase extends NewEventDatabase {

	public static void clearInstance() {
		setInstance(null);
	}
	
	public static void create() {
		setInstance(new FakeNewEventDatabase());
	}

}

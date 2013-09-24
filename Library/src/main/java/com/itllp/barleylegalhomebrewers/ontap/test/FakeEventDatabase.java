package com.itllp.barleylegalhomebrewers.ontap.test;

import com.itllp.barleylegalhomebrewers.ontap.Event;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabase;

public class FakeEventDatabase extends EventDatabase {

	public void addOrUpdateEvent(Event event) {
		eventList.add(event);
	}

	public static void clearInstance() {
		setInstance(null);
	}
	
	public static void create() {
		setInstance(new FakeEventDatabase());
	}

}

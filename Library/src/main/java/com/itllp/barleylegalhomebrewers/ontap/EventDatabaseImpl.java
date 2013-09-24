package com.itllp.barleylegalhomebrewers.ontap;


public class EventDatabaseImpl extends EventDatabase {
	public static void create() {
		setInstance(new EventDatabaseImpl());
	}

	public void addOrUpdateEvent(Event event) {
		if (null == event) {
			return;
		}
		if (containsId(event.getId()))
		{
			deleteId(event.getId());
		}
		eventList.add(event);
	}

}

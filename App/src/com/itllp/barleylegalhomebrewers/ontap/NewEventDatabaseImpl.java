package com.itllp.barleylegalhomebrewers.ontap;


public class NewEventDatabaseImpl extends EventDatabase {
	public static void create() {
		setInstance(new NewEventDatabaseImpl());
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

package com.itllp.barleylegalhomebrewers.ontap;

import java.util.ArrayList;
import java.util.List;

public class EventDatabaseImpl extends EventDatabase {
	private List<Event> eventList = new ArrayList<Event>();

	
	@Override
	public void addEvent(Event event) {
		if (null != event && !eventList.contains(event)) {
			eventList.add(event);
		}
	}

	@Override
	public void clearEventList() {
		eventList.clear();
	}

	@Override
	public List<Event> getEventList() {
		return eventList;
	}

	@Override
	public boolean isEmpty() {
		return eventList.isEmpty();
	}

	@Override
	public int size() {
		return eventList.size();
	}

}

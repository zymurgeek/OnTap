package com.itllp.barleylegalhomebrewers.ontap.test;

import java.util.ArrayList;
import java.util.List;

import com.itllp.barleylegalhomebrewers.ontap.Event;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabase;

public class LocalEventDatabase extends EventDatabase {

	private List<Event> eventList = new ArrayList<Event>();
	
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

}

package com.itllp.barleylegalhomebrewers.ontap;

import java.util.ArrayList;
import java.util.List;

public class EventDatabaseImpl implements EventDatabase {
	private List<Event> eventList = new ArrayList<Event>();

	
	@Override
	public void addOrUpdateEvent(Event event) {
		if (null != event) {
			if (containsId(event.getId())) {
				deleteId(event.getId());
			}
			eventList.add(event);
		}
	}

	@Override
	public void deleteId(int id) {
		Event eventToDelete = getEvent(id);
		eventList.remove(eventToDelete);
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
	public boolean containsId(int id) {
		Event event = getEvent(id);
		if (null != event) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isEmpty() {
		return eventList.isEmpty();
	}

	@Override
	public int size() {
		return eventList.size();
	}

	@Override
	public Event getEvent(int id) {
		for (Event event : eventList) {
			if (event.getId() == id) {
				return event;
			}
		}
		return null;
	}

}

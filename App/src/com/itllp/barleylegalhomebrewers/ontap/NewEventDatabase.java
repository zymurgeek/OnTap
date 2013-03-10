package com.itllp.barleylegalhomebrewers.ontap;

import java.util.ArrayList;
import java.util.List;

// TODO:  Rename NewEventDatabase* classes to EventDatabase*
public abstract class NewEventDatabase {

	private static NewEventDatabase instance = null;
	protected List<Event> eventList = new ArrayList<Event>();

	protected static void setInstance(NewEventDatabase newInstance) {
		if (null != newInstance && null != instance) {
			throw new DatabaseAlreadyInstantiatedException();
		}
		instance = newInstance;
	}
	
	public static NewEventDatabase getInstance() {
		return instance;
	}

	public void addOrUpdateEvent(Event event) {}
	
	public void clearEventList() {
		eventList.clear();
	}

	public boolean containsId(int id) {
		return (getEvent(id) != null);
	}

	public void deleteId(int id) {
		Event e = getEvent(id);
		if (null != e) {
			eventList.remove(e);
		}
	}

	public Event getEvent(int id) {
		for (Event e : eventList) {
			if (e.getId() == id) {
				return e;
			}
		}
		return null;
	}
	
	public List<Event> getEventList() {
		return eventList;
	}
	
	public boolean isEmpty() {
		return eventList.isEmpty();
	}

	public int size() {
		return eventList.size();
	}

}

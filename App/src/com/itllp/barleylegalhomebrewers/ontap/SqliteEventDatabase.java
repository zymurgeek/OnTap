package com.itllp.barleylegalhomebrewers.ontap;

import java.util.ArrayList;
import java.util.List;

// TODO Delete this class if unused
public abstract class SqliteEventDatabase {

	private static SqliteEventDatabase instance = null;

	protected static void setInstance(SqliteEventDatabase newInstance) {
		if (null != newInstance && null != instance) {
			throw new DatabaseAlreadyInstantiatedException();
		}
		instance = newInstance;
	}
	
	public static SqliteEventDatabase getInstance() {
		return instance;
	}

	public static void clearInstance() {
		instance = null;		
	}

	public void addOrUpdateEvent(Event event) {}
	
	public void clearEventList() {}

	public boolean containsId(int id) {
		return false;
	}

	public void deleteId(int id) {}

	public Event getEvent(int id) { return null;}
	
	public List<Event> getEventList() {
		return new ArrayList<Event>();
	}
	
	public boolean isEmpty() {
		return true;
	}

	public int size() {
		return 0;
	}

}

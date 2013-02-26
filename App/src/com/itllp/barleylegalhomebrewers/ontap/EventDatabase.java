package com.itllp.barleylegalhomebrewers.ontap;

import java.util.List;

public abstract class EventDatabase {
	public abstract void addEvent(Event event);
	public abstract void clearEventList();
	public abstract List<Event> getEventList();
	public abstract boolean isEmpty();
	public abstract int size();
}
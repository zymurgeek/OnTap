package com.itllp.barleylegalhomebrewers.ontap;

import java.util.List;

public interface EventDatabase {
	public void addOrUpdateEvent(Event event);
	public void deleteId(int id);
	public Event getEvent(int id);
	public void clearEventList();
	public List<Event> getEventList();
	public boolean isEmpty();
	public int size();
	public boolean containsId(int id);
}
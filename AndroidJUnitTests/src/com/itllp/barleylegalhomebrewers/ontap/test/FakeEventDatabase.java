package com.itllp.barleylegalhomebrewers.ontap.test;

import java.util.ArrayList;
import java.util.List;

import com.itllp.barleylegalhomebrewers.ontap.Event;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabase;

public class FakeEventDatabase implements EventDatabase {

	private List<Event> eventList = new ArrayList<Event>();
	
	@Override
	public void addOrUpdateEvent(Event event) {
		eventList.add(event);
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

	@Override
	public void deleteId(int id) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean containsId(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Event getEvent(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

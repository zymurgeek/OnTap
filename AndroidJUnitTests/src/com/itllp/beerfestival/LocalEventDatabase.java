package com.itllp.beerfestival;

import java.util.ArrayList;
import java.util.HashMap;

public class LocalEventDatabase extends EventDatabase {

	private ArrayList<HashMap<String, String>> eventList
	= new ArrayList<HashMap<String, String>>();
	
	@Override
	public ArrayList<HashMap<String, String>> getEventList() {
		return eventList;
	}

	@Override
	public boolean isEmpty() {
		return eventList.isEmpty();
	}

}

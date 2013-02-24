package com.itllp.barleylegalhomebrewers.ontap.test;

import java.util.ArrayList;
import java.util.HashMap;
import com.itllp.barleylegalhomebrewers.ontap.OldEventDatabase;

public class OldLocalEventDatabase extends OldEventDatabase {

	private ArrayList<HashMap<String, String>> eventList = new ArrayList<HashMap<String, String>>();
	
	@Override
	public ArrayList<HashMap<String, String>> getEventList() {
		return eventList;
	}

	@Override
	public boolean isEmpty() {
		return eventList.isEmpty();
	}

}

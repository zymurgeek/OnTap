package com.itllp.beerfestival;

import java.util.ArrayList;
import java.util.HashMap;

public class LocalEventDatabase extends EventDatabase {

	@Override
	public ArrayList<HashMap<String, String>> getEventList() {
		return new ArrayList<HashMap<String, String>>();
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

}

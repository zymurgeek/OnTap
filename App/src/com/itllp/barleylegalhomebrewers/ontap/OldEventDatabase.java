package com.itllp.barleylegalhomebrewers.ontap;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class OldEventDatabase {
	public static final String ID = "ID";
	public static final String EVENT_NAME = "EventName";
	public static final String EVENT_DATE = "EventDate";
	public static final String ACTIVE = "Active";
	public static final String DELETED = "Deleted";

	public abstract ArrayList<HashMap<String, String>> getEventList();
	public abstract boolean isEmpty();
}
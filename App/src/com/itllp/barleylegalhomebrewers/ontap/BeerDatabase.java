package com.itllp.barleylegalhomebrewers.ontap;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class BeerDatabase {
	public static final String ID = "ID";
	public static final String BEER_STYLE_CODE = "BeerStyle";
	public static final String BEER_STYLE_NAME = "BeerStyleName";
	public static final String BEER_NAME = "BeerName";

	public abstract ArrayList<HashMap<String, String>> getEventList();
	public abstract boolean isEmpty();
}

package com.itllp.barleylegalhomebrewers.ontap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;

public class JsonArrayBeerDatabaseLoaderImpl implements JsonArrayBeerDatabaseLoader {

	public static final String ID = "ID";
	public static final String EVENT_NAME = "BeerName";
	public static final String EVENT_DATE = "BeerDate";
	private BeerDatabase beerDatabase;
	public JsonArrayBeerDatabaseLoaderImpl(StringToJavaDateConverter dateConverter,
			BeerDatabase beerDatabase) {
		if (null == dateConverter) {
			throw new NullPointerException();
		}
		if (null == beerDatabase) {
			throw new NullPointerException();
		}
		this.beerDatabase = beerDatabase;
	}

	@Override
	public void load(JSONArray jsonArray) {
		beerDatabase.clearBeerList();
		
		if (null == jsonArray) {
			return;
		}
		
    	for (int i = 0; i < jsonArray.length(); i++){
        	int id = -1;
        	String beerName = "";

    		try {
    			JSONObject jsonBeer = jsonArray.getJSONObject(i);
    			
    			try {
    				String idString = jsonBeer.getString(ID);
    				id = Integer.parseInt(idString);
        		} catch (JSONException e) {}
   
    			try {
    				beerName = jsonBeer.getString(EVENT_NAME);
        		} catch (JSONException e) {}

    			Beer beer = new Beer(id);
    			beer.setName(beerName);
    			beerDatabase.addOrUpdateBeer(beer);
    		} catch (JSONException e) {}
    	}
	}

}

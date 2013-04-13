package com.itllp.barleylegalhomebrewers.ontap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonArrayBeerDatabaseLoaderImpl implements JsonArrayBeerDatabaseLoader {

	public static final String ID = "ID";
	public static final String BEER_NAME = "BeerName";
	private BeerDatabase beerDatabase;
	
	public JsonArrayBeerDatabaseLoaderImpl(BeerDatabase beerDatabase) {
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
    				beerName = jsonBeer.getString(BEER_NAME);
        		} catch (JSONException e) {}

    			Beer beer = new Beer(id);
    			beer.setBeerName(beerName);
    			beerDatabase.addOrUpdateBeer(beer);
    		} catch (JSONException e) {}
    	}
	}

}

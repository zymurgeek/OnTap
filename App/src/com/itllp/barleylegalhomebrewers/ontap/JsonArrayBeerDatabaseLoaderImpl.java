package com.itllp.barleylegalhomebrewers.ontap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonArrayBeerDatabaseLoaderImpl implements JsonArrayBeerDatabaseLoader {

	public static final String ID = "ID";
	public static final String BEER_NAME = "BeerName";
	public static final String BREWER_FIRST_NAME = "FirstName";
	
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
    		try {
    			JSONObject jsonBeer = jsonArray.getJSONObject(i);
    			
            	int id = -1;
    			try {
    				String idString = jsonBeer.getString(ID);
    				id = Integer.parseInt(idString);
        		} catch (JSONException e) {}
    			Beer beer = new Beer(id);
   
    			try {
    				String beerName = jsonBeer.getString(BEER_NAME);
        			beer.setBeerName(beerName);
        		} catch (JSONException e) {}
    			
    			try {
    				String brewerFirstName = jsonBeer.getString(BREWER_FIRST_NAME);
        			beer.setBrewerFirstName(brewerFirstName);
        		} catch (JSONException e) {}
    			
    			beerDatabase.addOrUpdateBeer(beer);
    		} catch (JSONException e) {}
    	}
	}

}

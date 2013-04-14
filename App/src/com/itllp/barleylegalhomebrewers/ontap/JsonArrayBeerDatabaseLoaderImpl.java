package com.itllp.barleylegalhomebrewers.ontap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonArrayBeerDatabaseLoaderImpl implements JsonArrayBeerDatabaseLoader {

	public static final String ID = "ID";
	public static final String BEER_NAME = "BeerName";
	public static final String BREWER_FIRST_NAME = "FirstName";
	public static final String BREWER_LAST_NAME = "LastName";
	public static final String BEER_STYLE_CODE = "BeerStyle";
	public static final String BEER_STYLE_NAME = "BeerStyleName";
	public static final String BEER_STYLE_OVERRIDE = "BeerStyleOverride";
	public static final String BEER_DESCRIPTION = "Description";
	public static final String PACKAGING = "KegOrBottle";
	public static final String ORIGINAL_GRAVITY = "OG";
	public static final String FINAL_GRAVITY = "FG";
	public static final String ALCOHOL_BY_VOLUME = "ABV";
	public static final String INTERNATIONAL_BITTERNESS_UNITS = "IBU";
	public static final String STANDARD_REFERENCE_METHOD = "SRM";
	public static final String BREWER_EMAIL_ADDRESS = "EmailAddress";
	public static final String SHOW_BREWER_EMAIL_ADDRESS = "CanEmail";
	
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
        		} catch (JSONException e) { /* ignore bad data */ }
    			Beer beer = new Beer(id);
   
    			try {
    				String beerName = jsonBeer.getString(BEER_NAME);
        			beer.setBeerName(beerName);
        		} catch (JSONException e) { /* ignore bad data */ }
    			
    			try {
    				String brewerFirstName = jsonBeer.getString(BREWER_FIRST_NAME);
        			beer.setBrewerFirstName(brewerFirstName);
        		} catch (JSONException e) { /* ignore bad data */ }
    			
    			try {
    				String brewerLastName = jsonBeer.getString(BREWER_LAST_NAME);
        			beer.setBrewerLastName(brewerLastName);
        		} catch (JSONException e) { /* ignore bad data */ }

    			try {
    				String styleCode = jsonBeer.getString(BEER_STYLE_CODE);
    				beer.setStyleCode(styleCode);
    			} catch (JSONException e) { /* ignore bad data */ }
    			
    			try {
    				String styleName = jsonBeer.getString(BEER_STYLE_NAME);
    				beer.setStyleName(styleName);
    			} catch (JSONException e) { /* ignore bad data */ }
    			
    			try {
    				String styleOverride = jsonBeer.getString(BEER_STYLE_OVERRIDE);
    				beer.setStyleOverride(styleOverride);
    			} catch (JSONException e) { /* ignore bad data */ }
    			
    			try {
    				String description = jsonBeer.getString(BEER_DESCRIPTION);
    				beer.setDescription(description);
    			} catch (JSONException e) { /* ignore bad data */ }
    			
    			try {
    				String packaging = jsonBeer.getString(PACKAGING);
    				beer.setPackaging(packaging);
    			} catch (JSONException e) { /* ignore bad data */ }
    			
    			try {
    				String og = jsonBeer.getString(ORIGINAL_GRAVITY);
    				beer.setOriginalGravity(og);
    			} catch (JSONException e) { /* ignore bad data */ }
    			
    			try {
    				String fg = jsonBeer.getString(FINAL_GRAVITY);
    				beer.setFinalGravity(fg);
    			} catch (JSONException e) { /* ignore bad data */ }
    			
    			try {
    				String abv = jsonBeer.getString(ALCOHOL_BY_VOLUME);
    				beer.setAlcoholByVolume(abv);
    			} catch (JSONException e) { /* ignore bad data */ }
    			
    			try {
    				String ibu = jsonBeer.getString(INTERNATIONAL_BITTERNESS_UNITS);
    				beer.setInternationalBitternessUnits(ibu);
    			} catch (JSONException e) { /* ignore bad data */ }
    			
    			try {
    				String srm = jsonBeer.getString(STANDARD_REFERENCE_METHOD);
    				beer.setStandardReferenceMethod(srm);
    			} catch (JSONException e) { /* ignore bad data */ }
    			
    			try {
    				String email = jsonBeer.getString(BREWER_EMAIL_ADDRESS);
    				beer.setBrewerEmailAddress(email);
    			} catch (JSONException e) { /* ignore bad data */ }
    			
    			try {
    				boolean showEmail = jsonBeer.getBoolean(SHOW_BREWER_EMAIL_ADDRESS);
    				beer.setShowBrewerEmailAddress(showEmail);
    			} catch (JSONException e) { /* ignore bad data */ }
    			
    			beerDatabase.addOrUpdateBeer(beer);
    		} catch (JSONException e) {}
    	}
	}

}

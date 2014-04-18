package com.itllp.barleylegalhomebrewers.ontap.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToStringConverter;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;

import android.content.ContentValues;

public class BeerJSONObjectToContentValuesConverterImpl implements
		JSONObjectToContentValuesConverter {

	public BeerJSONObjectToContentValuesConverterImpl(
			StringToJavaDateConverter jsonDateConverter,
			JavaDateToStringConverter javaDateConverter) {
	}
	/**
	 * Returns a ContentValues object populated from a JSON object
	 * @return If the input JSON object is null or has no valid data, 
	 * ContentValues will be empty; never null. 
	 */
	@Override
	public ContentValues getContentValues(JSONObject jsonObject) {
		ContentValues result = new ContentValues();
		
		if (null == jsonObject) {
			return result;
		}
		
		convertID(jsonObject, result);
		convertBeerName(jsonObject, result);
		convertEventID(jsonObject, result);

		return result;
	}

	private void convertID(JSONObject jsonObject, ContentValues result) {
		try {
			String idString = jsonObject.getString(
					com.itllp.barleylegalhomebrewers.ontap.jsonserver.Beer.ID);
			int id = Integer.parseInt(idString);
			result.put(
					com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.ID_COLUMN, 
					id);
		} catch (JSONException e) {}
	}

	private void convertBeerName(JSONObject jsonObject, ContentValues result) {
		try {
			String beerName = jsonObject.getString(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Beer.BEER_NAME);
			result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.NAME_COLUMN, beerName);
		} catch (JSONException e) {}
	}
	
	private void convertEventID(JSONObject jsonObject, ContentValues result) {
		try {
			String idString = jsonObject.getString(
					com.itllp.barleylegalhomebrewers.ontap.jsonserver.Beer.EVENT_ID);
			int id = Integer.parseInt(idString);
			result.put(
					com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.EVENT_ID_COLUMN, 
					id);
		} catch (JSONException e) {}
	}


}

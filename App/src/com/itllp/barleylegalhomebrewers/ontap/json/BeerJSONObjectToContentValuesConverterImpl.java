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
		convertBrewerName(jsonObject, result);
		convertStyleCode(jsonObject, result);
		convertStyleName(jsonObject, result);
		convertStyleOverride(jsonObject, result);
		convertIsKicked(jsonObject, result);
		convertTapNumber(jsonObject, result);
		convertPackaging(jsonObject, result);

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

	private void convertBrewerName(JSONObject jsonObject, ContentValues result) {
		try {
			String brewerFirstName = jsonObject.getString(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Beer.BREWER_FIRST_NAME);
			String brewerLastName = jsonObject.getString(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Beer.BREWER_LAST_NAME);
			String brewerName = brewerFirstName + " " + brewerLastName;
			result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.BREWER_NAME_COLUMN, brewerName);
		} catch (JSONException e) {}
	}
	
	private void convertStyleCode(JSONObject jsonObject, ContentValues result) {
		try {
			String styleCode = jsonObject.getString(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Beer.STYLE_CODE);
			result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STYLE_CODE_COLUMN, styleCode);
		} catch (JSONException e) {}
	}
	
	private void convertStyleName(JSONObject jsonObject, ContentValues result) {
		try {
			String styleName = jsonObject.getString(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Beer.STYLE_NAME);
			result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STYLE_NAME_COLUMN, styleName);
		} catch (JSONException e) {}
	}
	
	private void convertStyleOverride(JSONObject jsonObject, ContentValues result) {
		try {
			String styleOverride = jsonObject.getString(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Beer.STYLE_OVERRIDE);
			result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STYLE_OVERRIDE_COLUMN, styleOverride);
		} catch (JSONException e) {}
	}
	
	private void convertIsKicked(JSONObject jsonObject, ContentValues result) {
		try {
			boolean isKicked = jsonObject.getBoolean(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Beer.IS_KICKED);
			int isKickedValue = isKicked ? 1 : 0;
			result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.IS_KICKED_COLUMN, isKickedValue);
		} catch (JSONException e) {}
	}
	
	private void convertTapNumber(JSONObject jsonObject, ContentValues result) {
		try {
			int tapNumber = jsonObject.getInt(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Beer.TAP_NUMBER);
			result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.TAP_NUMBER_COLUMN, tapNumber);
		} catch (JSONException e) {}
	}
	
	private void convertPackaging(JSONObject jsonObject, ContentValues result) {
		try {
			String packaging = jsonObject.getString(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Beer.PACKAGING);
			result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.PACKAGING_COLUMN, packaging);
		} catch (JSONException e) {}
	}
	

}

package com.itllp.barleylegalhomebrewers.ontap.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToStringConverter;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;
import com.itllp.barleylegalhomebrewers.ontap.util.NumberExtractor;
import com.itllp.barleylegalhomebrewers.ontap.util.UntappdBeerIdParser;

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
		convertDescription(jsonObject, result);
		convertOriginalGravity(jsonObject, result);
		convertFinalGravity(jsonObject, result);
		convertAlcoholByVolume(jsonObject, result);
		convertInternationalBitternessUnits(jsonObject, result);
		convertStandardReferenceMethod(jsonObject, result);
		convertIsEmailShown(jsonObject, result);
		convertEmailAddress(jsonObject, result);
		convertUntappdBeerId(jsonObject, result);

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
			if (styleCode.length() < 3) {
				styleCode = " " + styleCode;
			}
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
	
	private void convertDescription(JSONObject jsonObject, ContentValues result) {
		try {
			String description = jsonObject.getString(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Beer.DESCRIPTION);
			result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.DESCRIPTION_COLUMN, description);
		} catch (JSONException e) {}
	}
	
	private void convertOriginalGravity(JSONObject jsonObject, ContentValues result) {
		try {
			String originalGravityString = jsonObject.getString(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Beer.ORIGINAL_GRAVITY);
			Float originalGravity = numberExtractor.extractNumber(originalGravityString);
			result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.ORIGINAL_GRAVITY_COLUMN, originalGravity);
		} catch (JSONException e) {}
	}

	private void convertFinalGravity(JSONObject jsonObject, ContentValues result) {
		try {
			String finalGravityString = jsonObject.getString(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Beer.FINAL_GRAVITY);
			Float finalGravity = numberExtractor.extractNumber(finalGravityString);
			result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.FINAL_GRAVITY_COLUMN, finalGravity);
		} catch (JSONException e) {}
	}

	private void convertAlcoholByVolume(JSONObject jsonObject, ContentValues result) {
		try {
			String abvString = jsonObject.getString(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Beer.ALCOHOL_BY_VOLUME);
			Float abv = numberExtractor.extractNumber(abvString);
			result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.ALCOHOL_BY_VOLUME_COLUMN, abv);
		} catch (JSONException e) {}
	}

	private void convertInternationalBitternessUnits(JSONObject jsonObject, ContentValues result) {
		try {
			String ibuString = jsonObject.getString(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Beer.INTERNATIONAL_BITTERNESS_UNITS);
			Float ibu = numberExtractor.extractNumber(ibuString);
			result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.INTERNATIONAL_BITTERNESS_UNITS_COLUMN, ibu);
		} catch (JSONException e) {}
	}

	private void convertStandardReferenceMethod(JSONObject jsonObject, ContentValues result) {
		try {
			String srmString = jsonObject.getString(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Beer.STANDARD_REFERENCE_METHOD);
			Float srm = numberExtractor.extractNumber(srmString);
			result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STANDARD_REFERENCE_METHOD_COLUMN, srm);
		} catch (JSONException e) {}
	}

	private void convertIsEmailShown(JSONObject jsonObject, ContentValues result) {
		try {
			boolean isEmailShown = jsonObject.getBoolean(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Beer.IS_EMAIL_SHOWN);
			int isEmailShownValue = isEmailShown ? 1 : 0;
			result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.IS_EMAIL_SHOWN, isEmailShownValue);
		} catch (JSONException e) {}
	}
	
	private void convertEmailAddress(JSONObject jsonObject, ContentValues result) {
		try {
			String emailAddress = jsonObject.getString(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Beer.EMAIL_ADDRESS);
			result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.EMAIL_ADDRESS, emailAddress);
		} catch (JSONException e) {}
	}
	
	private void convertUntappdBeerId(JSONObject jsonObject, ContentValues result) {
		String untappdBeerId = "";
		try {
			untappdBeerId = jsonObject.getString(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Beer.UNTAPPD_BEER_ID);
		} catch (JSONException e) {}
		
		if (untappdBeerId.length() == 0) {  
			// Get ID from description text
			try {
				String description = jsonObject.getString(com.itllp
						.barleylegalhomebrewers.ontap.jsonserver.Beer.DESCRIPTION);
				untappdBeerId = idParser.getUntappdBeerId(description); 
			} catch (JSONException ex) {}
		}
		result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.UNTAPPD_BEER_ID, untappdBeerId);
	}

	
	private static final UntappdBeerIdParser idParser = new UntappdBeerIdParser();
	private static final NumberExtractor numberExtractor = new NumberExtractor();
}

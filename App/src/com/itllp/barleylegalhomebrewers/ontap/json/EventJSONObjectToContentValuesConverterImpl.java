package com.itllp.barleylegalhomebrewers.ontap.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToStringConverter;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;

import android.content.ContentValues;

public class EventJSONObjectToContentValuesConverterImpl implements
		JSONObjectToContentValuesConverter {

	public EventJSONObjectToContentValuesConverterImpl(
			StringToJavaDateConverter jsonDateConverter,
			JavaDateToStringConverter javaDateConverter) {
		this.jsonDateConverter = jsonDateConverter;
		this.javaDateConverter = javaDateConverter;
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
		convertEventName(jsonObject, result);
		convertEventDate(jsonObject, result);

		return result;
	}

	private void convertID(JSONObject jsonObject, ContentValues result) {
		try {
			String idString = jsonObject.getString(
					com.itllp.barleylegalhomebrewers.ontap.jsonserver.Event.ID);
			int id = Integer.parseInt(idString);
			result.put(
					com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.EventTableMetadata.ID_COLUMN, 
					id);
		} catch (JSONException e) {}
	}

	private void convertEventName(JSONObject jsonObject, ContentValues result) {
		try {
			String eventName = jsonObject.getString(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Event.EVENT_NAME);
			result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.EventTableMetadata.NAME_COLUMN, eventName);
		} catch (JSONException e) {}
	}

	private void convertEventDate(JSONObject jsonObject, ContentValues result) {
		try {
			String jsonEventDate = jsonObject.getString(com.itllp
					.barleylegalhomebrewers.ontap.jsonserver.Event.EVENT_DATE);
			java.util.Date javaDate = jsonDateConverter.getJavaDate(jsonEventDate);
			String sqliteDate = javaDateConverter.getString(javaDate);
			result.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.EventTableMetadata.START_LOCAL_TIME_COLUMN, sqliteDate);
		} catch (JSONException e) {}
	}


	private StringToJavaDateConverter jsonDateConverter;
	private JavaDateToStringConverter javaDateConverter;
}

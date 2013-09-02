package com.itllp.barleylegalhomebrewers.ontap.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;


public class JSONArrayToContentValuesListConverterImpl implements
		JSONArrayToContentValuesListConverter {

	public JSONArrayToContentValuesListConverterImpl(
			JSONObjectToContentValuesConverter converter) {
		this.converter = converter;
	}

	/**
	 * Returns a list of content values created from elements of a JSON
	 * array.
	 * @return List of converted elements.  If the input array is null or 
	 * contains no valid elements, the list will be empty; never null.
	 */
	@Override
	public List<ContentValues> getContentValuesList(JSONArray array) {
		List<ContentValues> result = new ArrayList<ContentValues>(); 
		int arrayLength = 0;
		if (null != array) {
			arrayLength = array.length();
		}
		for (int index=0; index<arrayLength; ++index) {
			JSONObject object = null;
			try {
				object = array.getJSONObject(index);
			} catch (Exception e) { /* Ignore bad elements */ }
			ContentValues contentValues = converter.getContentValues(object);
			result.add(contentValues);
		}
		return result;
	}

	JSONObjectToContentValuesConverter converter;
}

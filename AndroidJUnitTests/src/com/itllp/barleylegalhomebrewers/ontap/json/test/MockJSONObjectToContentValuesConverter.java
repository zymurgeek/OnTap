package com.itllp.barleylegalhomebrewers.ontap.json.test;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.itllp.barleylegalhomebrewers.ontap.json.JSONObjectToContentValuesConverter;

public class MockJSONObjectToContentValuesConverter implements
		JSONObjectToContentValuesConverter {

	@Override
	public android.content.ContentValues getContentValues(JSONObject jsonObject) {
		android.content.ContentValues result;
		result = mock_conversionMap.get(jsonObject);
		return result;
	}

	public void mock_addConversion(JSONObject jsonObject,
			android.content.ContentValues contentValues) {
		mock_conversionMap.put(jsonObject, contentValues);
		
	}
	
	private Map<JSONObject, android.content.ContentValues> mock_conversionMap 
	= new HashMap<JSONObject, android.content.ContentValues>();
}

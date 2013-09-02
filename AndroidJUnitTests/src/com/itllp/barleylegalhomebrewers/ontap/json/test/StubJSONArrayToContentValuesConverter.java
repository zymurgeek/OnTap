package com.itllp.barleylegalhomebrewers.ontap.json.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import android.content.ContentValues;

import com.itllp.barleylegalhomebrewers.ontap.json.JSONArrayToContentValuesListConverter;

public class StubJSONArrayToContentValuesConverter 
implements JSONArrayToContentValuesListConverter {
	private Map<JSONArray, List<ContentValues>> conversionMap 
	= new HashMap<JSONArray, List<ContentValues>>();
	
	@Override
	public List<android.content.ContentValues> getContentValuesList(org.json.JSONArray array) {
		List<android.content.ContentValues> result;
		result = conversionMap.get(array);
		return result;
	}

	public void stub_addConversion(JSONArray jsonArray,
			List<ContentValues> contentValuesList) {
		conversionMap.put(jsonArray, contentValuesList);
		
	}

}

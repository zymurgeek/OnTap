package com.itllp.barleylegalhomebrewers.ontap;

import org.json.JSONArray;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;

public class JsonArrayEventDatabaseLoaderImpl implements JsonArrayEventDatabaseLoader {

	public JsonArrayEventDatabaseLoaderImpl(StringToJavaDateConverter dateConverter,
			NewEventDatabase eventDatabase) {
		if (null == dateConverter) {
			throw new NullPointerException();
		}
		if (null == eventDatabase) {
			throw new NullPointerException();
		}
	}

	@Override
	public void load(JSONArray jsonArray) {
		// TODO Auto-generated method stub
		
	}

}

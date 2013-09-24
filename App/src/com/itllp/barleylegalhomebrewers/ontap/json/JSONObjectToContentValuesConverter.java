package com.itllp.barleylegalhomebrewers.ontap.json;

public interface JSONObjectToContentValuesConverter {
	public android.content.ContentValues getContentValues
	(org.json.JSONObject jsonObject);
}

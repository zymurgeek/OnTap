package com.itllp.barleylegalhomebrewers.ontap.json;

import java.util.List;

public interface JSONArrayToContentValuesListConverter {
	public List<android.content.ContentValues> getContentValuesList
	(org.json.JSONArray array);
}

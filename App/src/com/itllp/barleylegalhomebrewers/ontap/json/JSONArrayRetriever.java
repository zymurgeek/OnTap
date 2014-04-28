package com.itllp.barleylegalhomebrewers.ontap.json;

import org.json.JSONArray;

public interface JSONArrayRetriever {

	/** 
	 * @return The source of the data used to update the table.  For server-
	 * based data, this is the URL used to access the data. 
	 */
	public String getDataSource();

	JSONArray getJSONArray();

	JSONArray getJSONArray(String id);
}

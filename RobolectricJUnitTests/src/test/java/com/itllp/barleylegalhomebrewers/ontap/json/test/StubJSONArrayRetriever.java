package com.itllp.barleylegalhomebrewers.ontap.json.test;

import org.json.JSONArray;

import android.util.SparseArray;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONArrayRetriever;

public class StubJSONArrayRetriever implements JSONArrayRetriever {
	private SparseArray<JSONArray> returnJSONArrayForID = 
			new SparseArray<JSONArray>();
	private JSONArray returnJSONArray;
	
	public void stub_setReturnArray(JSONArray jsonArray) {
		returnJSONArray = jsonArray;
	}

	@Override
	public JSONArray getJSONArray() {
		return returnJSONArray;
	}

	@Override
	public JSONArray getJSONArray(String id) {
		int intId = Integer.parseInt(id);
		return returnJSONArrayForID.get(intId);
	}

	public void stub_setReturnArray(int id, JSONArray expectedJSONArray) {
		returnJSONArrayForID.put(id, expectedJSONArray);
	}

}

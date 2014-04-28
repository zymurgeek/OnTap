package com.itllp.barleylegalhomebrewers.ontap.jsonserver;

import org.json.JSONArray;

import com.itllp.barleylegalhomebrewers.ontap.contentprovider.BeerTableUpdater;
import com.itllp.barleylegalhomebrewers.ontap.contentprovider.TableFromJSONArrayUpdater;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONArrayRetriever;


public class BeerTableFromJSONArrayRetrieverUpdater implements
		BeerTableUpdater {

	public BeerTableFromJSONArrayRetrieverUpdater(
			JSONArrayRetriever retriever,
			TableFromJSONArrayUpdater tableUpdater) {
		this.retriever = retriever;
		this.updater = tableUpdater;
		
	}

	
	@Override
	public String getDataSource() {
		return this.retriever.getDataSource();
	}
	
	
	@Override
	public void update(String eventId) {
		JSONArray jsonArray = retriever.getJSONArray(eventId);
		updater.updateTable(jsonArray);
	}

	
	private JSONArrayRetriever retriever = null;
	private TableFromJSONArrayUpdater updater = null;
}

package com.itllp.barleylegalhomebrewers.ontap.jsonserver;

import org.json.JSONArray;

import com.itllp.barleylegalhomebrewers.ontap.contentprovider.TableFromJSONArrayUpdater;
import com.itllp.barleylegalhomebrewers.ontap.contentprovider.EventTableUpdater;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONArrayRetriever;


public class EventTableFromJSONArrayRetrieverUpdater implements
		EventTableUpdater {

	public EventTableFromJSONArrayRetrieverUpdater(
			JSONArrayRetriever retriever,
			TableFromJSONArrayUpdater eventTableUpdater) {
		this.retriever = retriever;
		this.updater = eventTableUpdater;
		
	}

	@Override
	public void update() {
		JSONArray jsonArray = retriever.getJSONArray();
		updater.updateTable(jsonArray);
	}

	private JSONArrayRetriever retriever = null;
	private TableFromJSONArrayUpdater updater = null;
}

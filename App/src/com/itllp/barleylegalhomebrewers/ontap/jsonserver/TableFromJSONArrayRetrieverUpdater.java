package com.itllp.barleylegalhomebrewers.ontap.jsonserver;

import org.json.JSONArray;

import com.itllp.barleylegalhomebrewers.ontap.contentprovider.TableFromJSONArrayUpdater;
import com.itllp.barleylegalhomebrewers.ontap.contentprovider.TableUpdater;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONArrayRetriever;


public class TableFromJSONArrayRetrieverUpdater implements
		TableUpdater {

	public TableFromJSONArrayRetrieverUpdater(
			JSONArrayRetriever retriever,
			TableFromJSONArrayUpdater tableUpdater) {
		this.retriever = retriever;
		this.updater = tableUpdater;
		
	}

	@Override
	public void update() {
		JSONArray jsonArray = retriever.getJSONArray();
		updater.updateTable(jsonArray);
	}

	private JSONArrayRetriever retriever = null;
	private TableFromJSONArrayUpdater updater = null;
}

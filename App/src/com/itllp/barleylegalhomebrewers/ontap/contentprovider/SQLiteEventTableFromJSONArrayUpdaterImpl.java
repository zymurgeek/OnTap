package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import android.content.ContentValues;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.EventTableMetadata;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONArrayToContentValuesListConverter;

class SQLiteEventTableFromJSONArrayUpdaterImpl implements
		TableFromJSONArrayUpdater {

	public SQLiteEventTableFromJSONArrayUpdaterImpl(
			JSONArrayToContentValuesListConverter converter, 
			EventTable eventTable) {
		this.converter = converter;
		this.eventTable = eventTable;
	}

	@Override
	public void updateTable(JSONArray jsonArray) {
		if (null == jsonArray) {
			return;
		}
		List<ContentValues> contentValuesList = converter.getContentValuesList
				(jsonArray);
		if (null == contentValuesList) {
			return;
		}
		
		List<Integer> idsInTable = eventTable.getAllIds();
		List<ContentValues> insertList = new ArrayList<ContentValues>();
		List<ContentValues> updateList = new ArrayList<ContentValues>();
		List<Integer> deleteList = new ArrayList<Integer>();
		deleteList.addAll(idsInTable);
		for (ContentValues contentValues : contentValuesList) {
			Integer id = contentValues.getAsInteger(EventTableMetadata.ID_COLUMN);
			if (!idsInTable.contains(id)) {
				insertList.add(contentValues);
			} else {
				updateList.add(contentValues);
			}
			deleteList.remove(id);
		}
		for (ContentValues contentValues : insertList) {
			eventTable.insert(contentValues);
		}
		for (ContentValues contentValues : updateList) {
			Integer id = contentValues.getAsInteger(EventTableMetadata.ID_COLUMN);
			ContentValues contentValuesInTable = 
					eventTable.getEvent(id);
			if (!cvComp.areEqual(contentValues, contentValuesInTable)) {
				eventTable.update(contentValues);
			}
		}
		for (Integer id : deleteList) {
			eventTable.delete(id);
		}
	}

	private JSONArrayToContentValuesListConverter converter;
	private EventTable eventTable;
	private ContentValuesComparator cvComp = new ContentValuesComparator();
}

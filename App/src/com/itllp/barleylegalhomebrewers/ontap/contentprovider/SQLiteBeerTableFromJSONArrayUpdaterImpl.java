package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.content.ContentValues;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONArrayToContentValuesListConverter;

class SQLiteBeerTableFromJSONArrayUpdaterImpl implements
		TableFromJSONArrayUpdater {

	public SQLiteBeerTableFromJSONArrayUpdaterImpl(
			JSONArrayToContentValuesListConverter converter, 
			BeerTable beerTable) {
		this.converter = converter;
		this.beerTable = beerTable;
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
		
		List<Integer> idsInTable = beerTable.getAllIds();
		List<ContentValues> insertList = new ArrayList<ContentValues>();
		List<ContentValues> updateList = new ArrayList<ContentValues>();
		List<Integer> deleteList = new ArrayList<Integer>();
		deleteList.addAll(idsInTable);
		for (ContentValues contentValues : contentValuesList) {
			Integer id = contentValues.getAsInteger(BeerTableMetadata.ID_COLUMN);
			if (!idsInTable.contains(id)) {
				insertList.add(contentValues);
			} else {
				updateList.add(contentValues);
			}
			deleteList.remove(id);
		}
		for (ContentValues contentValues : insertList) {
			beerTable.insert(contentValues);
		}
		for (ContentValues contentValues : updateList) {
			Integer id = contentValues.getAsInteger(BeerTableMetadata.ID_COLUMN);
			ContentValues contentValuesInTable = 
					beerTable.getBeer(id);
			if (!cvComp.areEqual(contentValues, contentValuesInTable)) {
				beerTable.update(contentValues);
			}

		}
		for (Integer id : deleteList) {
			beerTable.delete(id);
		}
	}

	private JSONArrayToContentValuesListConverter converter;
	private BeerTable beerTable;
	private ContentValuesComparator cvComp = new ContentValuesComparator();
}

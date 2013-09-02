package com.itllp.barleylegalhomebrewers.ontap.database.test;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;

import com.itllp.barleylegalhomebrewers.ontap.database.EventTable;

public class StubEventTable implements EventTable {

	@Override
	public List<ContentValues> getContentValuesInTableList() {
		return stub_contentValuesInTableList;
	}
	
	
	@Override
	public List<Integer> getIdsInTableList() {
		List<Integer> idsInTableList = new ArrayList<Integer>();
		for (ContentValues contentValues : stub_contentValuesInTableList) {
			idsInTableList.add(contentValues.getAsInteger(EventTable.ID_COLUMN));
		}
		return idsInTableList;
	}
	
	
	@Override
	public void insert(ContentValues contentValues) {
		stub_insertedContentValuesList.add(contentValues);
	}

	
	@Override
	public void update(ContentValues contentValues) {
		stub_updatedContentValuesList.add(contentValues);
	}
	
	
	@Override
	public void deleteID(Integer id) {
		stub_deletedIdsList.add(id);
	}
	
	
	@Override
	public ContentValues getContentValuesInTable(Integer id) {
		for (ContentValues contentValue : stub_contentValuesInTableList) {
			Integer idInTable = contentValue.getAsInteger(ID_COLUMN);
			if (idInTable == id) {
				return contentValue;
			}
		}
		return null;
	}

	
	public void stub_setContentValuesInTable
	(List<ContentValues> contentValuesInTableList) {
		stub_contentValuesInTableList = contentValuesInTableList;
	}
	
	
	public List<Integer> stub_getDeletedIdsList() {
		return stub_deletedIdsList;
	}
	
	
	public List<ContentValues> stub_getInsertedContentValuesList() {
		return stub_insertedContentValuesList;
	}

	
	public void stub_resetInsertedList() {
		stub_insertedContentValuesList.clear();
	}

	
	public List<ContentValues> stub_getUpdatedContentValuesList() {
		return stub_updatedContentValuesList;
	}

	
	public void stub_resetUpdatedList() {
		stub_updatedContentValuesList.clear();
	}

	
	private List<ContentValues> stub_contentValuesInTableList = 
			new ArrayList<ContentValues>();
	
	private List<ContentValues> stub_insertedContentValuesList = 
			new ArrayList<ContentValues>();
	
	private List<ContentValues> stub_updatedContentValuesList = 
			new ArrayList<ContentValues>();
	
	private List<Integer> stub_deletedIdsList = new ArrayList<Integer>();

}

package com.itllp.barleylegalhomebrewers.ontap.database;

import java.util.List;

import android.content.ContentValues;

public interface EventTable {

	/**
	 * Column name for the identification number of the event
	 * <P>Type: TEXT</P>
	 */
	public static final String ID_COLUMN = "_id";
	/**
	 * Column name for the name of the event
	 * <P>Type: TEXT</P>
	 */
	public static final String NAME_COLUMN = "name";
	
	ContentValues getContentValuesInTable(Integer id);
	List<ContentValues> getContentValuesInTableList();
	/**
	 * 
	 * @return List of IDs in the table.  This is never null.
	 */
	List<Integer> getIdsInTableList();
	void insert(ContentValues contentValues);
	void update(ContentValues contentValues);
	void deleteID(Integer id);
}

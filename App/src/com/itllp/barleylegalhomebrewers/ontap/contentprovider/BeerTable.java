package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import java.util.List;

import android.content.ContentValues;

public interface BeerTable {

	/**
	 * Column name for the identification number of the beer
	 * <P>Type: TEXT</P>
	 */
	public static final String ID_COLUMN = "_id";
	/**
	 * Column name for the name of the beer
	 * <P>Type: TEXT</P>
	 */
	public static final String NAME_COLUMN = "name";
	
	/** @return A beer in the table.  This is never null, but if the ID 
	 * does not exist, the ContentValues returned will contain no values. */
	ContentValues getEvent(Integer id);
	
	/** @return List of beers in the table.  This is never null. */
	List<ContentValues> getAllBeers();

	/** @return List of IDs in the table.  This is never null. */
	List<Integer> getAllIds();
	void insert(ContentValues event);
	void update(ContentValues event);
	void delete(Integer id);
}

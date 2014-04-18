package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import java.util.List;

import android.content.ContentValues;

public interface BeerTable {

	/** @return A beer in the table.  This is never null, but if the ID 
	 * does not exist, the ContentValues returned will contain no values. */
	ContentValues getBeer(Integer id);
	
	/** @return List of beers in the table.  This is never null. */
	List<ContentValues> getAllBeers();

	/** @return List of IDs in the table.  This is never null. */
	List<Integer> getAllIds();
	void insert(ContentValues beer);
	void update(ContentValues beer);
	void delete(Integer id);
}

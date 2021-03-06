package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

public interface BeerTableUpdater {
	/** 
	 * @return The source of the data used to update the table.  For server-
	 * based data, this is the URL used to access the data. 
	 */
	public String getDataSource();

	public void update(String eventId);
}

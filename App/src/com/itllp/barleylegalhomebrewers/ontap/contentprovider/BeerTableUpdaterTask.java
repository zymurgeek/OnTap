package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;

import android.content.ContentResolver;


class BeerTableUpdaterTask implements Runnable {

	
	public BeerTableUpdaterTask(ContentResolver res, BeerTableUpdater beerTableUpdater, String eventId) {
		contentResolver = res;
		updater = beerTableUpdater;
		this.eventId = eventId;
	}


	@Override
	public void run() {
		if (updateThreadCount < 1) { 
			contentResolver.notifyChange(OnTapContentProviderMetadata.BEER_BUSY_URI, null);
		}
		++updateThreadCount;
		updater.update(eventId);
		--updateThreadCount;
		if (updateThreadCount < 1) {
			contentResolver.notifyChange(OnTapContentProviderMetadata.BEER_NOT_BUSY_URI, null);
		}
	}
	
	
	private static int updateThreadCount = 0;
	private ContentResolver contentResolver;
	private BeerTableUpdater updater;
	private String eventId;
}

package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;

import android.content.ContentResolver;
import android.util.Log;


class BeerTableUpdaterTask implements Runnable {

	
	public BeerTableUpdaterTask(ContentResolver res, BeerTableUpdater beerTableUpdater, String eventId) {
		contentResolver = res;
		updater = beerTableUpdater;
		this.eventId = eventId;
	}


	@Override
	public void run() {
		synchronized(synch) {
			Log.d(tag, "Starting server query");
			if (updateThreadCount < 1) { 
				Log.d(tag, "Sending busy notification");
				contentResolver.notifyChange(OnTapContentProviderMetadata.BEER_BUSY_URI, null);
			}
			++updateThreadCount;
			try {
				updater.update(eventId);
			} catch (Exception e) {
				Log.e(tag, "Failed to update");
				Log.e(tag, e.toString());
			}
			--updateThreadCount;
			if (updateThreadCount < 1) {
				Log.d(tag, "Sending not busy notification");
				contentResolver.notifyChange(OnTapContentProviderMetadata.BEER_NOT_BUSY_URI, null);
			}
			Log.d(tag, "Server query finished");
		}
	}
	
	
	private static int updateThreadCount = 0;
	private static Object synch = new Object();
	private ContentResolver contentResolver;
	private BeerTableUpdater updater;
	private String eventId;
	private final String tag = getClass().getSimpleName();
}

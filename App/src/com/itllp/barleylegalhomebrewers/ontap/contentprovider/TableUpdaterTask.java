package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;

import android.content.ContentResolver;
import android.util.Log;


class TableUpdaterTask implements Runnable {

	
	public TableUpdaterTask(ContentResolver res, TableUpdater eventTableUpdater) {
		contentResolver = res;
		updater = eventTableUpdater;
	}


	@Override
	public synchronized void run() {
		Log.d(tag, "Starting server query");
		if (updateThreadCount < 1) { 
			Log.d(tag, "Sending busy notification");
			contentResolver.notifyChange(OnTapContentProviderMetadata.EVENT_BUSY_URI, null);
		}
		++updateThreadCount;
		try {
			updater.update();
		} catch (Exception e) {
			Log.e(tag, "Failed to update");
			Log.e(tag, e.toString());
		}
		--updateThreadCount;
		if (updateThreadCount < 1) {
			Log.d(tag, "Sending not busy notification");
			contentResolver.notifyChange(OnTapContentProviderMetadata.EVENT_NOT_BUSY_URI, null);
		}
		Log.d(tag, "Server query finished");
	}
	
	private static int updateThreadCount = 0;
	private ContentResolver contentResolver;
	private TableUpdater updater;
	private final String tag = this.getClass().getSimpleName();
}

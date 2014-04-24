package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;

import android.content.ContentResolver;


class TableUpdaterTask implements Runnable {

	
	public TableUpdaterTask(ContentResolver res, TableUpdater eventTableUpdater) {
		contentResolver = res;
		updater = eventTableUpdater;
	}


	@Override
	public void run() {
		if (updateThreadCount < 1) { 
			contentResolver.notifyChange(OnTapContentProviderMetadata.EVENT_BUSY_URI, null);
		}
		++updateThreadCount;
		updater.update();
		--updateThreadCount;
		if (updateThreadCount < 1) {
			contentResolver.notifyChange(OnTapContentProviderMetadata.EVENT_NOT_BUSY_URI, null);
		}
	}
	
	private static int updateThreadCount = 0;
	private ContentResolver contentResolver;
	private TableUpdater updater;
}

package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.R;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;

import android.content.ContentResolver;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.FragmentActivity;

public class EventListActivity extends FragmentActivity 
{
	public EventListActivity() {
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list_fragment);
    }

	@Override
	protected void onResume() {
		super.onResume();
        if (workerThread == null) {
        	workerThread = new HandlerThread("EventListFragment worker");
        	workerThread.start();
        	workerQueue = new Handler(workerThread.getLooper());
        	final ContentResolver r = getContentResolver();
        	contentProviderObserver = new OnTapContentProviderObserver(workerQueue);
        	r.registerContentObserver(OnTapContentProviderMetadata.EVENT_BUSY_URI, true, contentProviderObserver);
        	r.registerContentObserver(OnTapContentProviderMetadata.EVENT_NOT_BUSY_URI, true, contentProviderObserver);
        }
	}

	@Override
	protected void onPause() {
		super.onPause();
		getContentResolver().unregisterContentObserver(contentProviderObserver);
		contentProviderObserver = null;
		workerQueue = null;
		workerThread.stop();
		workerThread = null;
	}

	private static HandlerThread workerThread = null;
	private static Handler workerQueue = null;
	private OnTapContentProviderObserver contentProviderObserver = null;

}
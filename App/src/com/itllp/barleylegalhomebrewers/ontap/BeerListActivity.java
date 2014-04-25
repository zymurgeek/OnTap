package com.itllp.barleylegalhomebrewers.ontap;

import java.lang.reflect.Method;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.FragmentActivity;

public class BeerListActivity  extends FragmentActivity {
	public static final String SKIP_INSTANTIATION_FOR_TESTING 
	= "com.itllp.barleylegalhomebrewers.ontap.skipInstantiation_FOR_TESTING";
	public int eventId = -1;
	public static final String EVENT_ID = "EVENT_ID";
	
	public BeerListActivity() {
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        
        setContentView(R.layout.beer_list_fragment);
        
        String eventIdString = intent.getStringExtra(EVENT_ID);
        try {
        	eventId = Integer.parseInt(eventIdString);
        } catch (NumberFormatException e) {
        	eventId = -1;
        }
        
        // TODO pass event ID via fragment arguments
		//BeerDatabaseLoader loader = BeerDatabaseLoader.getInstance();
		//loader.setEventId(eventId);
        BeerListFragment beerListFrag = (BeerListFragment)
        	    getSupportFragmentManager().findFragmentById(com.itllp
        	    		.barleylegalhomebrewers.ontap.R.id.beer_list_fragment);
        if (null != beerListFrag) {
        	beerListFrag.setEventId(eventId);
        }
    }

	@Override
	protected void onResume() {
		super.onResume();
        if (workerThread == null) {
        	workerThread = new HandlerThread("EventListFragment worker");
        	workerThread.start();
        	workerQueue = new Handler(workerThread.getLooper());
        	final ContentResolver r = getContentResolver();
        	networkActiveObserver = new OnTapContentProviderActiveObserver(workerQueue);
        	networkInactiveObserver = new OnTapContentProviderInactiveObserver(workerQueue);
        	r.registerContentObserver(OnTapContentProviderMetadata.BEER_BUSY_URI, true, networkActiveObserver);
        	r.registerContentObserver(OnTapContentProviderMetadata.BEER_NOT_BUSY_URI, true, networkInactiveObserver);
        	registerObserverIfThereIsOne();
        }
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
	@Override
	protected void onPause() {
		super.onPause();
		getContentResolver().unregisterContentObserver(networkActiveObserver);
		getContentResolver().unregisterContentObserver(networkInactiveObserver);
		deregisterForNetworkActivity();
		networkActiveObserver = null;
		networkInactiveObserver = null;
		workerQueue = null;
		if (Build.VERSION.SDK_INT >= 18) {
			try {
				Class<?> handlerThreadClass = Class.forName("android.os.HandlerThread", true, Thread.currentThread()
						.getContextClassLoader());
				Method quitSafelyMethod = handlerThreadClass.getMethod("quitSafely", handlerThreadClass);
				quitSafelyMethod.invoke(handlerThreadClass);
			} catch (Exception e) {
				// Do nothing if method does not exist
			}
		} else {
			if (Build.VERSION.SDK_INT >= 5) {
				try {
					Class<?> handlerThreadClass = Class.forName("android.os.HandlerThread", true, Thread.currentThread()
							.getContextClassLoader());
					Method quitMethod = handlerThreadClass.getMethod("quit", handlerThreadClass);
					quitMethod.invoke(handlerThreadClass);
				} catch (Exception e) {
					// Do nothing if method does not exist
				}
			}
		}
		workerThread = null;
	}

	private void registerObserverIfThereIsOne() {
		if (networkActiveObserver != null) {
			networkActiveObserver.registerObserver(networkActivityObserver);
		}
		if (networkInactiveObserver != null) {
			networkInactiveObserver.registerObserver(networkActivityObserver);
		}
	}
	
	
	public void registerForNetworkActivity(NetworkActivityObserver observer) {
		networkActivityObserver = observer;
		registerObserverIfThereIsOne();
	}

	public void deregisterForNetworkActivity() {
		if (networkActiveObserver != null) {
			networkActiveObserver.deregisterObserver();
		}
		if (networkInactiveObserver != null) {
			networkInactiveObserver.deregisterObserver();
		}
		networkActivityObserver = null;
	}

	private static HandlerThread workerThread = null;
	private static Handler workerQueue = null;
	private OnTapContentProviderActiveObserver networkActiveObserver = null;
	private OnTapContentProviderInactiveObserver networkInactiveObserver = null;
	private NetworkActivityObserver networkActivityObserver = null;
}

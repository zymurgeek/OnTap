package com.itllp.barleylegalhomebrewers.ontap;

import java.lang.reflect.Method;

import com.itllp.barleylegalhomebrewers.ontap.R;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.os.Build;
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
    	final ContentResolver r = getContentResolver();
        if (activeWorkerThread == null) {
        	activeWorkerThread = new HandlerThread("EventListFragment active worker");
        	activeWorkerThread.start();
        	activeWorkerQueue = new Handler(activeWorkerThread.getLooper());
        	networkActiveObserver = new OnTapContentProviderActiveObserver(activeWorkerQueue);
        	r.registerContentObserver(OnTapContentProviderMetadata.EVENT_BUSY_URI, true, networkActiveObserver);
        }
        if (inactiveWorkerThread == null) {
        	inactiveWorkerThread = new HandlerThread("EventListFragment inactive worker");
        	inactiveWorkerThread.start();
        	inactiveWorkerQueue = new Handler(inactiveWorkerThread.getLooper());
        	networkInactiveObserver = new OnTapContentProviderInactiveObserver(inactiveWorkerQueue);
        	r.registerContentObserver(OnTapContentProviderMetadata.EVENT_NOT_BUSY_URI, true, networkInactiveObserver);
        }
    	registerObserverIfThereIsOne();
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
		activeWorkerQueue = null;
		inactiveWorkerQueue = null;
		if (Build.VERSION.SDK_INT >= 18) {
			try {
				Class<?> handlerThreadClass = Class.forName("android.os.HandlerThread", true, Thread.currentThread()
						.getContextClassLoader());
				Method quitSafelyMethod = handlerThreadClass.getMethod("quitSafely", handlerThreadClass);
				quitSafelyMethod.invoke(activeWorkerThread);
				quitSafelyMethod.invoke(inactiveWorkerThread);
			} catch (Exception e) {
				// Do nothing if method does not exist
			}
		} else {
			if (Build.VERSION.SDK_INT >= 5) {
				try {
					Class<?> handlerThreadClass = Class.forName("android.os.HandlerThread", true, Thread.currentThread()
							.getContextClassLoader());
					Method quitMethod = handlerThreadClass.getMethod("quit", handlerThreadClass);
					quitMethod.invoke(activeWorkerThread);
					quitMethod.invoke(inactiveWorkerThread);
				} catch (Exception e) {
					// Do nothing if method does not exist
				}
			}
		}
		activeWorkerThread = null;
		inactiveWorkerThread = null;
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

	private static HandlerThread activeWorkerThread = null;
	private static Handler activeWorkerQueue = null;
	private static HandlerThread inactiveWorkerThread = null;
	private static Handler inactiveWorkerQueue = null;
	private OnTapContentProviderActiveObserver networkActiveObserver = null;
	private OnTapContentProviderInactiveObserver networkInactiveObserver = null;
	private NetworkActivityObserver networkActivityObserver = null;
}
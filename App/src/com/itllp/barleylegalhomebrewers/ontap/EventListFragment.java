package com.itllp.barleylegalhomebrewers.ontap;

import java.lang.reflect.Method;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.EventTableMetadata;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.ListFragment;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class EventListFragment extends ListFragment 
implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>,
NetworkActivityObserver {
	
	private SimpleCursorAdapter adapter = null;
	private Button refreshButton = null;
	
	
    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText(getString(R.string.no_events_text));

        createListAdapter();
        
        // Start out with a progress indicator.
        setListShown(false);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        final android.support.v4.app.LoaderManager loaderManager 
        = getLoaderManager();
        loaderManager.initLoader(0, null, this);

        View view = getView().getRootView();
        refreshButton = (Button)view.findViewById
        		(R.id.refresh_button);
        refreshButton.setEnabled(false);
        final android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> 
        callbacks = this;
        refreshButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
                refreshButton.setEnabled(false);
        		loaderManager.restartLoader(0, null, callbacks);
        	}
        });
    }

    
	@Override
	public void onResume() {
		super.onResume();
    	final ContentResolver r = getActivity().getContentResolver();
        if (activeWorkerThread == null) {
        	activeWorkerThread = new HandlerThread("EventListFragment active worker");
        	activeWorkerThread.start();
        	activeWorkerQueue = new Handler(activeWorkerThread.getLooper());
        	networkActiveObserver = new OnTapContentProviderActiveObserver(activeWorkerQueue);
        	r.registerContentObserver(OnTapContentProviderMetadata.EVENT_BUSY_URI, true, networkActiveObserver);
            networkActiveObserver.registerObserver(this);
        }
        if (inactiveWorkerThread == null) {
        	inactiveWorkerThread = new HandlerThread("EventListFragment inactive worker");
        	inactiveWorkerThread.start();
        	inactiveWorkerQueue = new Handler(inactiveWorkerThread.getLooper());
        	networkInactiveObserver = new OnTapContentProviderInactiveObserver(inactiveWorkerQueue);
        	r.registerContentObserver(OnTapContentProviderMetadata.EVENT_NOT_BUSY_URI, true, networkInactiveObserver);
            networkInactiveObserver.registerObserver(this);
        }
	}

	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
	@Override
	public void onPause() {
		super.onPause();
		getActivity().getContentResolver().unregisterContentObserver(networkActiveObserver);
		getActivity().getContentResolver().unregisterContentObserver(networkInactiveObserver);
		networkActiveObserver.deregisterObserver();
		networkInactiveObserver.deregisterObserver();
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

	
	private void createListAdapter() {
        // Fields from the database (projection)
        // Must include the _id column for the adapter to work
        String[] from = new String[] { 
        		EventTableMetadata.ID_COLUMN, 
        		EventTableMetadata.NAME_COLUMN,
        		EventTableMetadata.START_LOCAL_TIME_COLUMN };
        // Fields on the UI to which we map
        int[] to = new int[] { 
        		R.id.id,
        		R.id.event_name,
        		R.id.date};
        // TODO Use UtcDateToHumbanReadableDateConverter for the startlocaltime database TEXT field

        adapter = new SimpleCursorAdapter
        		(getActivity(), R.layout.event_list_item, null, from, to, 0);

        setListAdapter(adapter);
      }

    
    // Creates a new loader after the initLoader () call
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    	String[] projection = { EventTableMetadata.ID_COLUMN, EventTableMetadata.NAME_COLUMN,
    			EventTableMetadata.START_LOCAL_TIME_COLUMN};
    	String sortOrder = EventTableMetadata.START_LOCAL_TIME_COLUMN + " DESC";
    	CursorLoader cursorLoader = new CursorLoader(getActivity(),
    			OnTapContentProviderMetadata.EVENT_CONTENT_URI, projection, null, null, sortOrder);
    	return cursorLoader;
    }

    
    @Override
	public void onListItemClick(ListView l, View view, int position, long id) {
		String eventIdString = ((TextView) view.findViewById(R.id.id)).getText().toString();
    	Context context = getActivity().getApplicationContext();
		Intent in = new Intent(context, BeerListActivity.class);
		in.putExtra(BeerListActivity.EVENT_ID, eventIdString);
		startActivity(in);
	}

    
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
	    adapter.swapCursor(data);

        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
		
	}

	
	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor(null);
	}


	@Override
	public void networkActive() {
		getView().post(new Runnable() {
		    public void run() {
				refreshButton.setEnabled(false);
		    }
		});
	}


	@Override
	public void networkInactive() {
		getView().post(new Runnable() {
		    public void run() {
				refreshButton.setEnabled(true);
		    }
		});
	}

	
	private HandlerThread activeWorkerThread = null;
	private Handler activeWorkerQueue = null;
	private HandlerThread inactiveWorkerThread = null;
	private Handler inactiveWorkerQueue = null;
	private OnTapContentProviderActiveObserver networkActiveObserver = null;
	private OnTapContentProviderInactiveObserver networkInactiveObserver = null;
}

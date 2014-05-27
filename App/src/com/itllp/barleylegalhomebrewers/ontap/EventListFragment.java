package com.itllp.barleylegalhomebrewers.ontap;

import java.lang.reflect.Method;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.EventTableMetadata;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;
import com.itllp.barleylegalhomebrewers.ontap.persistence.PreferencesPersister;
import com.itllp.barleylegalhomebrewers.ontap.preferences.OnTapPreferences;
import com.itllp.barleylegalhomebrewers.ontap.preferences.OnTapPreferencesFactory;
import com.itllp.barleylegalhomebrewers.ontap.preferences.persistence.PreferencesPersisterFactory;

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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EventListFragment extends ListFragment 
implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>,
NetworkActivityObserver {
	
    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

		preferences = OnTapPreferencesFactory.getPreferences();
		preferencesPersister = PreferencesPersisterFactory.getPreferencesPersister();
		
        setHasOptionsMenu(true);
        setEmptyText(getString(R.string.no_events_text));

        callbacks = this;

        createListAdapter();
        
        // Start out with a progress indicator.
        setListShown(false);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        loaderManager = getLoaderManager();
        loaderManager.initLoader(0, null, this);
    }

    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    	super.onCreateOptionsMenu(menu, inflater);
	    inflater.inflate(R.menu.event_list_activity_actions, menu);
    	this.menu = menu;
	}

	
	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		if (menu != null) { 
			preferencesPersister.restoreState(preferences, getActivity());
			MenuItem productionMenuItem = menu.findItem(R.id.action_use_production_server);
			if (productionMenuItem != null) {
				productionMenuItem.setVisible(preferences.useBetaServer());
			}
			MenuItem betaMenuItem = menu.findItem(R.id.action_use_beta_server);
			if (betaMenuItem != null) {
				betaMenuItem.setVisible(!preferences.useBetaServer());
			}
		}
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
    	EventListActivity activity = (EventListActivity)getActivity();
	    switch (item.getItemId()) {
	        case R.id.action_help:
	            openHelp();
	            return true;
	        case R.id.action_refresh:
	        	enableRefresh(false);
        		loaderManager.restartLoader(0, null, callbacks);
        		return true;
	        case R.id.action_use_production_server:
	        	setProductionServer(true);
	        	activity.restartApplication();
	        	return true;
	        case R.id.action_use_beta_server:
	        	setProductionServer(false);
	        	activity.restartApplication();
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	
	private void setProductionServer(boolean useProduction) {
		preferencesPersister.restoreState(preferences, getActivity());
		preferences.useBetaServer(!useProduction);
    	preferencesPersister.saveState(preferences, getActivity());
	}

	
	private void openHelp() {
		CharSequence text = "There is no help yet";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(getActivity(), text, duration);
		toast.show();	
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
		enableRefresh(false);
	}


	@Override
	public void networkInactive() {
		enableRefresh(true);
	}

	
	private void enableRefresh(final boolean isEnabled) {
		getView().post(new Runnable() {
		    public void run() {
				MenuItem refreshMenuItem = menu.findItem(R.id.action_refresh);
				if (refreshMenuItem != null) {
					refreshMenuItem.setVisible(isEnabled);
				}
		    }
		});
	}


	private SimpleCursorAdapter adapter = null;
	private HandlerThread activeWorkerThread = null;
	private Handler activeWorkerQueue = null;
	private HandlerThread inactiveWorkerThread = null;
	private Handler inactiveWorkerQueue = null;
	private OnTapContentProviderActiveObserver networkActiveObserver = null;
	private OnTapContentProviderInactiveObserver networkInactiveObserver = null;
	private OnTapPreferences preferences;
	private PreferencesPersister preferencesPersister;
	private android.support.v4.app.LoaderManager loaderManager;
	private android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> callbacks;
	private Menu menu = null;
}

package com.itllp.barleylegalhomebrewers.ontap;

import java.lang.reflect.Method;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;
import com.itllp.barleylegalhomebrewers.ontap.persistence.PreferencesPersister;
import com.itllp.barleylegalhomebrewers.ontap.preferences.OnTapPreferences;
import com.itllp.barleylegalhomebrewers.ontap.preferences.OnTapPreferencesFactory;
import com.itllp.barleylegalhomebrewers.ontap.preferences.persistence.PreferencesPersisterFactory;
import com.itllp.barleylegalhomebrewers.ontap.util.SortTypeToActionId;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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

//TODO Put event name at top of beer list
public class BeerListFragment extends ListFragment 
implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>,
NetworkActivityObserver {
	
	
    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

		checkable_ids = new int[] { 
				R.id.action_sort_by_beer_name,
				R.id.action_sort_by_style,
				R.id.action_sort_by_abv, 
				R.id.action_sort_by_ibu, 
				R.id.action_sort_by_srm, 
		};
		sortTypeToActionId.add(SortType.NAME, R.id.action_sort_by_beer_name);		
		sortTypeToActionId.add(SortType.STYLE, R.id.action_sort_by_style);		
		sortTypeToActionId.add(SortType.ABV, R.id.action_sort_by_abv);		
		sortTypeToActionId.add(SortType.IBU, R.id.action_sort_by_ibu);		
		sortTypeToActionId.add(SortType.SRM, R.id.action_sort_by_srm);		
		
		preferences = OnTapPreferencesFactory.getPreferences();
		preferencesPersister = PreferencesPersisterFactory.getPreferencesPersister();

        setHasOptionsMenu(true);
        setEmptyText(getString(R.string.no_beers_text));

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
	    inflater.inflate(R.menu.beer_list_activity_actions, menu);
    	this.menu = menu;
	}


	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
	    setOptionsMenuChecks();
	}


    /**
     * Reads the previous state of the app from the preferences file
     * @param context - The Activity's Context
     */
    public void restoreInstanceState(Context context) {
    	preferencesPersister.restoreState(preferences, context);
    	SortType sortType = preferences.getSortType();
    	sortOrderActionId = sortTypeToActionId.getActionId(sortType);
    	setOptionsMenuChecks();
    }

    
    /**
     * Writes the app's current state to a properties repository.
     * @param context - The Activity's Context
     *
     */
    public void saveInstanceState(Context context) {
    	SortType sortType = sortTypeToActionId.getSortType(sortOrderActionId);
    	preferences.setSortType(sortType);
    	preferencesPersister.saveState(preferences, context);
    }

    
	private void setOptionsMenuChecks() {
		if (menu != null) { 
			for (int id : checkable_ids) {
				setMenuIdChecked(id, false);
			}
			setMenuIdChecked(sortOrderActionId, true);
		}
	}


	private void setMenuIdChecked(int id, boolean isChecked) {
		MenuItem item = menu.findItem(id);
		if (item != null) {
			item.setChecked(isChecked);
		}
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	    case R.id.action_refresh:
        	reloadList();
    		return true;
	    }
	    
		int itemId = item.getItemId();
		if (isCheckableId(itemId)) {
			setSelectedOptionsMenuId(item.getItemId());
			reloadList();
	    	return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	
	private boolean isCheckableId(final int id) {
        for (final int i : checkable_ids) {
            if (i == id) {
                return true;
            }
        }
        return false;
    }
	private void setSelectedOptionsMenuId(int id) {
		sortOrderActionId = id;
		setOptionsMenuChecks();
	}


	private void reloadList() {
        loaderManager.restartLoader(0, null, callbacks);
	}


	@Override
	public void onResume() {
		super.onResume();
		
		restoreInstanceState(getActivity());
		reloadList();
		
    	final ContentResolver r = getActivity().getContentResolver();
        if (activeWorkerThread == null) {
        	activeWorkerThread = new HandlerThread("BeerListFragment active worker");
        	activeWorkerThread.start();
        	activeWorkerQueue = new Handler(activeWorkerThread.getLooper());
        	networkActiveObserver = new OnTapContentProviderActiveObserver(activeWorkerQueue);
        	r.registerContentObserver(OnTapContentProviderMetadata.BEER_BUSY_URI, true, networkActiveObserver);
            networkActiveObserver.registerObserver(this);
        }
        if (inactiveWorkerThread == null) {
        	inactiveWorkerThread = new HandlerThread("BeerListFragment inactive worker");
        	inactiveWorkerThread.start();
        	inactiveWorkerQueue = new Handler(inactiveWorkerThread.getLooper());
        	networkInactiveObserver = new OnTapContentProviderInactiveObserver(inactiveWorkerQueue);
        	r.registerContentObserver(OnTapContentProviderMetadata.BEER_NOT_BUSY_URI, true, networkInactiveObserver);
            networkInactiveObserver.registerObserver(this);
        }
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
	@Override
	public void onPause() {
		super.onPause();
		
        saveInstanceState(getActivity());

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
    			BeerTableMetadata.ID_COLUMN, 
    			BeerTableMetadata.NAME_COLUMN,
    			BeerTableMetadata.BREWER_NAME_COLUMN,
    			BeerTableMetadata.STYLE_CODE_COLUMN,
    			BeerTableMetadata.STYLE_NAME_COLUMN,
    			BeerTableMetadata.STYLE_OVERRIDE_COLUMN,
    			BeerTableMetadata.ALCOHOL_BY_VOLUME_COLUMN,
    			BeerTableMetadata.INTERNATIONAL_BITTERNESS_UNITS_COLUMN,
    			BeerTableMetadata.STANDARD_REFERENCE_METHOD_COLUMN};
    	// Fields on the UI to which we map
    	int[] to = new int[] { 
    			R.id.id,
    			R.id.beer_name,
    			R.id.brewer_name,
    			R.id.beer_style_code,
    			R.id.beer_style_name,
    			R.id.beer_style_override,
    			R.id.abv,
    			R.id.ibu,
    			R.id.srm};

    	adapter = new BeerListAdapter
    			(getActivity(), R.layout.beer_list_item, null, from, to, 0);

    	setListAdapter(adapter);
    }

    
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
    	String[] projection = { BeerTableMetadata.ID_COLUMN, 
    			BeerTableMetadata.NAME_COLUMN, 
    			BeerTableMetadata.BREWER_NAME_COLUMN,
    			BeerTableMetadata.STYLE_CODE_COLUMN,
    			BeerTableMetadata.STYLE_NAME_COLUMN,
    			BeerTableMetadata.STYLE_OVERRIDE_COLUMN,
    			BeerTableMetadata.IS_KICKED_COLUMN,
    			BeerTableMetadata.TAP_NUMBER_COLUMN,
    			BeerTableMetadata.PACKAGING_COLUMN,
    			BeerTableMetadata.ALCOHOL_BY_VOLUME_COLUMN,
    			BeerTableMetadata.INTERNATIONAL_BITTERNESS_UNITS_COLUMN,
    			BeerTableMetadata.STANDARD_REFERENCE_METHOD_COLUMN};
    	String sortOrderText = BeerTableMetadata.IS_KICKED_COLUMN +	",  ( " +
    			BeerTableMetadata.TAP_NUMBER_COLUMN + " <> 0 ) DESC, ";
    	switch (sortOrderActionId) {
    	case R.id.action_sort_by_beer_name:
    		sortOrderText += BeerTableMetadata.NAME_COLUMN;
    		break;
    	case R.id.action_sort_by_abv:
    		sortOrderText += BeerTableMetadata.ALCOHOL_BY_VOLUME_COLUMN;
    		break;
    	case R.id.action_sort_by_style:
    		sortOrderText += BeerTableMetadata.STYLE_CODE_COLUMN;
    		break;
    	case R.id.action_sort_by_ibu:
    		sortOrderText += BeerTableMetadata.INTERNATIONAL_BITTERNESS_UNITS_COLUMN;
    		break;
    	case R.id.action_sort_by_srm:
    		sortOrderText += BeerTableMetadata.STANDARD_REFERENCE_METHOD_COLUMN;
    		break;
    	}
    	String queryString =
    			OnTapContentProviderMetadata.EVENT_ID_PARAM + "=" +
    			Uri.encode(String.valueOf(eventId));
    	Uri queryUri = Uri.parse(OnTapContentProviderMetadata.BEER_CONTENT_URI 
    			+ "?" +	queryString);
    	CursorLoader cursorLoader = CursorLoaderFactory.createCursorLoader(
    			getActivity(), queryUri, projection, null, null, sortOrderText);
    	return cursorLoader;
	}

	
    @Override
	public void onListItemClick(ListView l, View view, int position, long id) {
		String beerIdString = ((TextView) view.findViewById(R.id.id)).getText().toString();
    	Context context = getActivity().getApplicationContext();
		Intent in = new Intent(context, BeerDetailActivity.class);
		in.putExtra(BeerDetailActivity.BEER_ID, beerIdString);
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


	public void setEventId(int eventId) {
		this.eventId = eventId;
	}


	public int getEventId() {
		return eventId;
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
	private int eventId = -1;
	private int sortOrderActionId = R.id.action_sort_by_beer_name;
	private Menu menu = null;
	private HandlerThread activeWorkerThread = null;
	private Handler activeWorkerQueue = null;
	private HandlerThread inactiveWorkerThread = null;
	private Handler inactiveWorkerQueue = null;
	private OnTapContentProviderActiveObserver networkActiveObserver = null;
	private OnTapContentProviderInactiveObserver networkInactiveObserver = null;
	private int checkable_ids[];
	private SortTypeToActionId sortTypeToActionId = new SortTypeToActionId();
	private OnTapPreferences preferences;
	private PreferencesPersister preferencesPersister;
	private android.support.v4.app.LoaderManager loaderManager;
	private android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> callbacks;
}

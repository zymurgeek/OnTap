package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.contentprovider.EventContentProvider;
import com.itllp.barleylegalhomebrewers.ontap.database.EventTable;
import com.itllp.barleylegalhomebrewers.ontap.database.SQLiteEventTable;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class EventListFragment extends ListFragment 
implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> {
	
	private SimpleCursorAdapter adapter = null;
	
	
    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText(getString(R.string.no_events_text));

        // Start out with a progress indicator.
        setListShown(false);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        final android.support.v4.app.LoaderManager loaderManager 
        = getLoaderManager();
        loaderManager.initLoader(0, null, this);

        createListAdapter();
        
        View view = getView().getRootView();
        Button refreshButton = (Button)view.findViewById
        		(R.id.refresh_button);
        final android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> 
        callbacks = this;
        refreshButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		//TODO Change implementation to kick content provider
        		loaderManager.restartLoader(0, null, callbacks);
        	}
        });
    }

    
    private void createListAdapter() {
        // Fields from the database (projection)
        // Must include the _id column for the adapter to work
        String[] from = new String[] { 
        		EventTable.ID_COLUMN, 
        		EventTable.NAME_COLUMN,
        		SQLiteEventTable.START_LOCAL_TIME_COLUMN };
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
    	String[] projection = { EventTable.ID_COLUMN, EventTable.NAME_COLUMN,
    			SQLiteEventTable.START_LOCAL_TIME_COLUMN};
    	String sortOrder = SQLiteEventTable.START_LOCAL_TIME_COLUMN + " DESC";
    	CursorLoader cursorLoader = new CursorLoader(getActivity(),
    			EventContentProvider.CONTENT_URI, projection, null, null, sortOrder);
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
        // Set the new data in the adapter.
		//adapter.setData(data);
	    adapter.swapCursor(data);

        // The list should now be shown.
        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
		
	}

	
	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// data is not available anymore, delete reference
		adapter.swapCursor(null);
	}

}

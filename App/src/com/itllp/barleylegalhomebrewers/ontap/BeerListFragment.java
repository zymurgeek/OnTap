package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.contentprovider.BeerTable;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.EventTableMetadata;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

//TODO Put event name at top of beer list
//TODO Sort beer list by OnTap, Style
//TODO Have refresh update list, not replace it, so user's place is not lost
public class BeerListFragment extends ListFragment 
implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> {
	
	//private BeerListAdapter beerListAdapter = null;
	private SimpleCursorAdapter adapter = null;

	
    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText(getString(R.string.no_beers_text));

        // Create an empty adapter we will use to display the loaded data.
        /*
        beerListAdapter = new BeerListAdapter(getActivity(), R.layout.beer_list_item);
        setListAdapter(beerListAdapter);
        */

        // Start out with a progress indicator.
        setListShown(false);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        final LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(0, null, this);

        createListAdapter();
        
        View view = getView().getRootView();
        Button refreshButton = (Button)view.findViewById
			(R.id.refresh_button);
        final LoaderManager.LoaderCallbacks<Cursor> callbacks = this;
        refreshButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		loaderManager.restartLoader(0, null, callbacks);
            }
        });
    }

    
    private void createListAdapter() {
    	// Fields from the database (projection)
    	// Must include the _id column for the adapter to work
    	String[] from = new String[] { 
    			EventTableMetadata.ID_COLUMN, 
    			EventTableMetadata.NAME_COLUMN };
    	// Fields on the UI to which we map
    	int[] to = new int[] { 
    			R.id.id,
    			R.id.event_name };
    	// TODO Use UtcDateToHumbanReadableDateConverter for the startlocaltime database TEXT field

    	adapter = new SimpleCursorAdapter
    			(getActivity(), R.layout.beer_list_item, null, from, to, 0);

    	setListAdapter(adapter);
    }


    
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
    	String[] projection = { BeerTable.ID_COLUMN, BeerTable.NAME_COLUMN };
    	String sortOrder = BeerTable.NAME_COLUMN + " DESC";
    	CursorLoader cursorLoader = new CursorLoader(getActivity(),
    			OnTapContentProviderMetadata.CONTENT_URI, projection, null, null, sortOrder);
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
        // Clear the data in the adapter.
        adapter.swapCursor(null);
	}

}

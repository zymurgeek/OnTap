package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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
	
	private SimpleCursorAdapter adapter = null;
	private int eventId = -1;

	
    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText(getString(R.string.no_beers_text));

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
    			BeerTableMetadata.ID_COLUMN, 
    			BeerTableMetadata.NAME_COLUMN,
    			BeerTableMetadata.BREWER_NAME_COLUMN,
    			BeerTableMetadata.STYLE_CODE_COLUMN,
    			BeerTableMetadata.STYLE_NAME_COLUMN,
    			BeerTableMetadata.STYLE_OVERRIDE_COLUMN};
    	// Fields on the UI to which we map
    	int[] to = new int[] { 
    			R.id.id,
    			R.id.beer_name,
    			R.id.brewer_name,
    			R.id.beer_style_code,
    			R.id.beer_style_name,
    			R.id.beer_style_override};

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
    			BeerTableMetadata.IS_KICKED_COLUMN};
    	String sortOrder = BeerTableMetadata.NAME_COLUMN + " DESC";
    	String queryString =
    			OnTapContentProviderMetadata.EVENT_ID_PARAM + "=" +
    			Uri.encode(String.valueOf(eventId));
    	Uri queryUri = Uri.parse(OnTapContentProviderMetadata.BEER_CONTENT_URI 
    			+ "?" +	queryString);
    	CursorLoader cursorLoader = new CursorLoader(getActivity(),
    			queryUri, projection, null, null, sortOrder);
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


	public void setEventId(int eventId) {
		this.eventId = eventId;
	}


	public int getEventId() {
		return eventId;
	}

}

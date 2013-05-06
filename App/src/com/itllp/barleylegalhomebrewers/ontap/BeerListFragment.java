package com.itllp.barleylegalhomebrewers.ontap;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

//TODO Put event name at top of beer list
//TODO Sort beer list by OnTap, Style
//TODO Have refresh update list, not replace it, so user's place is not lost
public class BeerListFragment extends ListFragment 
implements LoaderCallbacks<List<Beer>> {
	
	private BeerListAdapter beerListAdapter = null;

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText(getString(R.string.no_beers_text));

        // Create an empty adapter we will use to display the loaded data.
        beerListAdapter = new BeerListAdapter(getActivity(), R.layout.beer_list_item);
        setListAdapter(beerListAdapter);

        // Start out with a progress indicator.
        setListShown(false);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        final LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(0, null, this);

        View view = getView().getRootView();
        Button refreshButton = (Button)view.findViewById
			(R.id.refresh_button);
        final LoaderManager.LoaderCallbacks<List<Beer>> callbacks = this;
        refreshButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		loaderManager.restartLoader(0, null, callbacks);
            }
        });
    }

	@Override
	public Loader<List<Beer>> onCreateLoader(int arg0, Bundle arg1) {
		BeerListLoaderFactory f = new BeerListLoaderFactory();
		Loader<List<Beer>> loader = f.getLoader(getActivity());
		
        return loader;
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
	public void onLoadFinished(Loader<List<Beer>> loader, List<Beer> data) {
        // Set the new data in the adapter.
        beerListAdapter.setData(data);

        // The list should now be shown.
        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
	}

	@Override
	public void onLoaderReset(Loader<List<Beer>> loader) {
        // Clear the data in the adapter.
        beerListAdapter.setData(null);
	}

}

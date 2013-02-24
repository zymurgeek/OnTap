package com.itllp.barleylegalhomebrewers.ontap;

import java.util.List;

import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

public class EventListFragment extends ListFragment 
implements OnQueryTextListener, LoaderCallbacks<List<Event>> {
	
	private EventListAdapter eventListAdapter = null;
	
    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText(getString(R.string.no_events_text));

        // Create an empty adapter we will use to display the loaded data.
        eventListAdapter = new EventListAdapter(getActivity(), R.layout.event_list_item);
        setListAdapter(eventListAdapter);

        // Start out with a progress indicator.
        setListShown(false);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getLoaderManager().initLoader(0, null, this);
    }

	@Override
	public Loader<List<Event>> onCreateLoader(int id, Bundle args) {
		
		EventListLoaderFactory f = new EventListLoaderFactory();
		Loader<List<Event>> loader = f.getLoader(getActivity());
		
        return loader;
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
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLoadFinished(Loader<List<Event>> loader, List<Event> data) {
        // Set the new data in the adapter.
        eventListAdapter.setData(data);

        // The list should now be shown.
        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
	}

	@Override
	public void onLoaderReset(Loader<List<Event>> loader) {
        // Clear the data in the adapter.
        eventListAdapter.setData(null);
	}

}

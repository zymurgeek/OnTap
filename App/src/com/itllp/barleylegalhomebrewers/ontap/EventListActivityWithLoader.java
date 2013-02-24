package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.R;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class EventListActivityWithLoader extends Activity 
implements LoaderCallbacks<Cursor>
{
	private ListView eventListView;
	private OldEventDatabase eventDb = null;
	
	public EventListActivityWithLoader() {
	}

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list);
        eventListView = (ListView)findViewById(R.id.list);
        getLoaderManager().initLoader(0, null, this);
    }
    
    private void loadEvents() {
    	OldEventDatabaseFactory eventDbFactory = new OldEventDatabaseFactoryImpl(this);
    	eventDb = eventDbFactory.getEventDatabase();

    	ListAdapter adapter = new SimpleAdapter(this, eventDb.getEventList(),
    			R.layout.event_list_item,
    			new String[] { OldEventDatabase.EVENT_NAME, OldEventDatabase.ID, OldEventDatabase.EVENT_DATE }, 
    			new int[] {	R.id.name, R.id.id, R.id.date });

    	this.eventListView.setAdapter(adapter);
    	
    	this.eventListView.setOnItemClickListener(new OnItemClickListener() {

    		@Override
    		public void onItemClick(AdapterView<?> parent, View view,
    				int position, long id) {
    			String eventIdString = ((TextView) view.findViewById(R.id.id)).getText().toString();
    				
    			Intent in = new Intent(getApplicationContext(), BeerListActivity.class);
    			in.putExtra(BeerListActivity.EVENT_ID, eventIdString);
    			startActivity(in);
    		}
    	});

    }

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}
}
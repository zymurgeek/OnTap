package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class EventListActivity extends Activity {
	private ListView eventListView;
	private EventDatabase eventDb = null;
	
	public EventListActivity() {
	}

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list);
        eventListView = (ListView)findViewById(R.id.list);
        this.loadEvents();
    }
    
    private void loadEvents() {
    	EventDatabaseFactory eventDbFactory = new EventDatabaseFactoryImpl(this);
    	eventDb = eventDbFactory.getEventDatabase();

    	ListAdapter adapter = new SimpleAdapter(this, eventDb.getEventList(),
    			R.layout.event_list_item,
    			new String[] { EventDatabase.EVENT_NAME, EventDatabase.ID, EventDatabase.EVENT_DATE }, 
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
}
package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.R;

import android.app.Activity;
import android.os.Bundle;

public class BeerListActivity  extends Activity {
	//private ListView beerListView;
	//private BeerDatabase beerDb = null;
	public static final String EVENT_ID = "EVENT_ID";
	
	public BeerListActivity() {
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_list);
        //beerListView = (ListView)findViewById(R.id.list);
        //Intent in = getIntent();
        //String eventId = in.getStringExtra(EVENT_ID);
        
        this.loadBeers();
    }

    private void loadBeers() {
    	/*
    	EventDatabaseFactory eventDbFactory = new EventDatabaseFactoryProvider(this);
    	beerDb = null; // eventDbFactory.getEventDatabase();

    	ListAdapter adapter = new SimpleAdapter(this, beerDb.getEventList(),
    			R.layout.beer_list_item,
    			new String[] { OldEventDatabase.EVENT_NAME, OldEventDatabase.ID, OldEventDatabase.EVENT_DATE }, 
    			new int[] {	R.id.name, R.id.id, R.id.date });

    	this.beerListView.setAdapter(adapter);
    	
    	this.beerListView.setOnItemClickListener(new OnItemClickListener() {

    		@Override
    		public void onItemClick(AdapterView<?> parent, View view,
    				int position, long id) {
    			String beerIdString = ((TextView) view.findViewById(R.id.id)).getText().toString();
    			Intent in = new Intent(getApplicationContext(), BeerListActivity.class);
    			in.putExtra(BeerListActivity.EVENT_ID, beerIdString);
    			startActivity(in);
    		}
    	});
*/
    }
}

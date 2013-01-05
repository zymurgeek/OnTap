package com.itllp.beerfestival;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BeerListActivity  extends Activity {
	
	// JSON node keys
	public static final String EVENT_ID = "EVENT_ID";
	public static final String TAG_NAME = "name";
	public static final String TAG_EMAIL = "email";
	public static final String TAG_PHONE_MOBILE = "mobile";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_list_item);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get JSON values from previous intent
        String eventId = in.getStringExtra(EVENT_ID);
        
        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.name_label);
        TextView lblCost = (TextView) findViewById(R.id.email_label);
        TextView lblDesc = (TextView) findViewById(R.id.mobile_label);
        
        lblName.setText("event id = " + eventId);
        lblCost.setText("cost");
        lblDesc.setText("description");
    }
}

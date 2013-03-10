package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.R;

import android.app.Activity;
import android.os.Bundle;

public class EventListActivityWithFragments extends Activity 
{
	public EventListActivityWithFragments() {
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
        	NewEventDatabaseImpl.create();
        } catch (DatabaseAlreadyInstantiatedException e) {}
        setContentView(R.layout.event_list_fragment);
    }
    
}
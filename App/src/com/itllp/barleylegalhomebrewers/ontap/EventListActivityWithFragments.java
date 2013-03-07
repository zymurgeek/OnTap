package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class EventListActivityWithFragments extends Activity 
{
	public EventListActivityWithFragments() {
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this.getApplicationContext();
        try {
			EventDatabaseFactoryProvider.setEventDatabaseFactory
				(new JsonEventDatabaseFactory(context));
		} catch (Exception e) {
			e.printStackTrace();
		}
        setContentView(R.layout.event_list_fragment);
    }
    
}
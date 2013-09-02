package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class EventListFromProviderActivity extends FragmentActivity 
{
	public EventListFromProviderActivity() {
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list_from_provider_fragment);
    }

}
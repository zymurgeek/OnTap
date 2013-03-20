package com.itllp.barleylegalhomebrewers.ontap;

import java.util.List;

import android.app.Activity;
import android.support.v4.content.Loader;

public class EventListLoaderFactory {
	private static Loader<List<Event>> loader = null;
	
	public Loader<List<Event>> getLoader(Activity activity) {
		Loader<List<Event>> newLoader = loader;
		
		if (null == newLoader) {
			newLoader = new EventListAsyncTaskLoader(activity);
		}
		
		return newLoader;
	}

	public void setLoader(Loader<List<Event>> newLoader) {
		loader = newLoader;
	}
}

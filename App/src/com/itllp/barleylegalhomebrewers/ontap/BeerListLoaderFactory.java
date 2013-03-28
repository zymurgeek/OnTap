package com.itllp.barleylegalhomebrewers.ontap;

import java.util.List;

import android.app.Activity;
import android.support.v4.content.Loader;

public class BeerListLoaderFactory {
	private static Loader<List<Beer>> loader = null;
	
	public Loader<List<Beer>> getLoader(Activity activity) {
		Loader<List<Beer>> newLoader = loader;
		
		if (null == newLoader) {
			newLoader = new BeerListAsyncTaskLoader(activity);
		}
		
		return newLoader;
	}

	public void setLoader(Loader<List<Beer>> newLoader) {
		loader = newLoader;
	}

}

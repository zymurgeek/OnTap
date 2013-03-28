package com.itllp.barleylegalhomebrewers.ontap.test;

import java.util.ArrayList;
import java.util.List;

import com.itllp.barleylegalhomebrewers.ontap.Beer;
import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

public class MockBeerListAsyncTaskLoader extends AsyncTaskLoader<List<Beer>> {

	private List<Beer> mBeerList = new ArrayList<Beer>();
	private List<Beer> mMockBeerList = null;
	
	public MockBeerListAsyncTaskLoader(Context context) {
		super(context);
	}

	@Override
	public List<Beer> loadInBackground() {
		return mMockBeerList;
	}
	
	public void setMockData(List<Beer> data) {
		mMockBeerList = data;
	}

    @Override public void deliverResult(List<Beer> beerList) {
        mBeerList = beerList;

        if (isStarted()) {
            // If the Loader is currently started, we can immediately
            // deliver its results.
            super.deliverResult(beerList);
        }
    }

    @Override protected void onStartLoading() {
        if (mBeerList != null) {
            // If we currently have a result available, deliver it
            // immediately.
            deliverResult(mBeerList);
        }

        if (takeContentChanged() || mBeerList == null) {
            // If the data has changed since the last time it was loaded
            // or is not currently available, start a load.
            forceLoad();
        }
    }

    @Override protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    @Override public void onCanceled(List<Beer> beerList) {
        super.onCanceled(beerList);
    }

    @Override protected void onReset() {
        super.onReset();

        // Ensure the loader is stopped
        onStopLoading();

        // At this point we can release the resources associated with 'apps'
        // if needed.
        if (mBeerList != null) {
            mBeerList = null;
        }
    }

}

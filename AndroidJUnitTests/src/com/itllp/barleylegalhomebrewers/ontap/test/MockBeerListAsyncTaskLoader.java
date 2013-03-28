package com.itllp.barleylegalhomebrewers.ontap.test;

import java.util.ArrayList;
import java.util.List;

import com.itllp.barleylegalhomebrewers.ontap.Event;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

public class MockBeerListAsyncTaskLoader extends AsyncTaskLoader<List<Event>> {

	private List<Event> mEventList = new ArrayList<Event>();
	private List<Event> mMockEventList = null;
	
	public MockBeerListAsyncTaskLoader(Context context) {
		super(context);
	}

	@Override
	public List<Event> loadInBackground() {
		return mMockEventList;
	}
	
	public void setMockData(List<Event> data) {
		mMockEventList = data;
	}

    @Override public void deliverResult(List<Event> eventList) {
        mEventList = eventList;

        if (isStarted()) {
            // If the Loader is currently started, we can immediately
            // deliver its results.
            super.deliverResult(eventList);
        }
    }

    @Override protected void onStartLoading() {
        if (mEventList != null) {
            // If we currently have a result available, deliver it
            // immediately.
            deliverResult(mEventList);
        }

        if (takeContentChanged() || mEventList == null) {
            // If the data has changed since the last time it was loaded
            // or is not currently available, start a load.
            forceLoad();
        }
    }

    @Override protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    @Override public void onCanceled(List<Event> eventList) {
        super.onCanceled(eventList);
    }

    @Override protected void onReset() {
        super.onReset();

        // Ensure the loader is stopped
        onStopLoading();

        // At this point we can release the resources associated with 'apps'
        // if needed.
        if (mEventList != null) {
            mEventList = null;
        }
    }

}

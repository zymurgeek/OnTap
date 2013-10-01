package com.itllp.barleylegalhomebrewers.ontap;

import java.util.List;
import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

public class EventListFromProviderAsyncTaskLoader extends AsyncTaskLoader<List<Event>> {

	private List<Event> mEventList;
	public EventListFromProviderAsyncTaskLoader(Context context) {
		super(context);
	}

	@Override
	public List<Event> loadInBackground() {
		EventDatabaseLoaderIF loader = EventDatabaseLoader.getInstance();
		loader.load();
		
		EventDatabase database = EventDatabase.getInstance();
		List<Event> eventList = database.getEventList();
		return eventList;
	}

    @Override public void deliverResult(List<Event> eventList) {
        if (isReset()) {
            // An async query came in while the loader is stopped.  We
            // don't need the result.
            if (eventList != null) {
                onReleaseResources(eventList);
            }
        }
        List<Event> oldEventList = eventList;
        mEventList = eventList;

        if (isStarted()) {
            // If the Loader is currently started, we can immediately
            // deliver its results.
            super.deliverResult(eventList);
        }

        // At this point we can release the resources associated with
        // 'oldApps' if needed; now that the new result is delivered we
        // know that it is no longer in use.
        if (oldEventList != null) {
            onReleaseResources(oldEventList);
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

        // At this point we can release the resources associated with 'apps'
        // if needed.
        onReleaseResources(eventList);
    }

    @Override protected void onReset() {
        super.onReset();

        // Ensure the loader is stopped
        onStopLoading();

        // At this point we can release the resources associated with 'apps'
        // if needed.
        if (mEventList != null) {
            onReleaseResources(mEventList);
            mEventList = null;
        }
    }

    protected void onReleaseResources(List<Event> eventList) {
        // For a simple List<> there is nothing to do.  For something
        // like a Cursor, we would close it here.
    }

}

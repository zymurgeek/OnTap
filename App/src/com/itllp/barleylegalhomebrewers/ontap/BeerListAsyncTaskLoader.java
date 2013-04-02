package com.itllp.barleylegalhomebrewers.ontap;

import java.util.List;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

public class BeerListAsyncTaskLoader extends AsyncTaskLoader<List<Beer>> {

	private List<Beer> mBeerList;

	public BeerListAsyncTaskLoader(Context context) {
		super(context);
	}

	@Override
	public List<Beer> loadInBackground() {
		BeerDatabaseLoader loader = BeerDatabaseLoader.getInstance();
		// TODO Parameterize event ID
		loader.load(9);
		
		BeerDatabase database = BeerDatabase.getInstance();
		List<Beer> beerList = database.getBeerList();
		return beerList;
	}

    @Override public void deliverResult(List<Beer> beerList) {
        if (isReset()) {
            // An async query came in while the loader is stopped.  We
            // don't need the result.
            if (beerList != null) {
                onReleaseResources(beerList);
            }
        }
        List<Beer> oldBeerList = beerList;
        mBeerList = beerList;

        if (isStarted()) {
            // If the Loader is currently started, we can immediately
            // deliver its results.
            super.deliverResult(beerList);
        }

        // At this point we can release the resources associated with
        // 'oldApps' if needed; now that the new result is delivered we
        // know that it is no longer in use.
        if (oldBeerList != null) {
            onReleaseResources(oldBeerList);
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

        // At this point we can release the resources associated with 'apps'
        // if needed.
        onReleaseResources(beerList);
    }

    @Override protected void onReset() {
        super.onReset();

        // Ensure the loader is stopped
        onStopLoading();

        // At this point we can release the resources associated with 'apps'
        // if needed.
        if (mBeerList != null) {
            onReleaseResources(mBeerList);
            mBeerList = null;
        }
    }

    protected void onReleaseResources(List<Beer> beerList) {
        // For a simple List<> there is nothing to do.  For something
        // like a Cursor, we would close it here.
    }


}

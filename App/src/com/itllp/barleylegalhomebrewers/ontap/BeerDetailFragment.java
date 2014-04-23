package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;

import android.net.Uri;
import android.os.Bundle;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BeerDetailFragment extends Fragment 
implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> {

	public static final String BEER_ID = "BEER_ID";
	public static final int LOADER_ID = 1;
//	private Cursor cursor;
//	private BeerCursorUtility beerQuery = new BeerCursorUtility();
	private android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> callbacks;
	private SimpleCursorAdapter adapter;
	private int beerId = -1;

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.beer_detail_view, container, false);
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
        
//        setEmptyText(getString(R.string.no_beers_text));

        // Start out with a progress indicator.
//        setListShown(false);

		createCursorAdapter();
		
        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
    	callbacks = this;
        final LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_ID, null, callbacks);
 
		//setContentView(R.layout.activity_beer_detail);
		//updateFields();
	}

    private void createCursorAdapter() {
    	// Fields from the database (projection)
    	// Must include the _id column for the adapter to work
    	String[] from = new String[] { 
    			BeerTableMetadata.ID_COLUMN, 
    			BeerTableMetadata.NAME_COLUMN,
    			BeerTableMetadata.BREWER_NAME_COLUMN,
    			BeerTableMetadata.DESCRIPTION_COLUMN,
    			BeerTableMetadata.PACKAGING_COLUMN,
    			BeerTableMetadata.ORIGINAL_GRAVITY_COLUMN,
    			BeerTableMetadata.FINAL_GRAVITY_COLUMN,
    			BeerTableMetadata.ALCOHOL_BY_VOLUME_COLUMN,
    			BeerTableMetadata.INTERNATIONAL_BITTERNESS_UNITS_COLUMN,
    			BeerTableMetadata.STANDARD_REFERENCE_METHOD_COLUMN
    			};
    	// Fields on the UI to which we map
    	int[] to = new int[] { 
    			R.id.id,
    			R.id.beer_name,
    			R.id.brewer,
    			R.id.description,
    			R.id.packaging,
    			R.id.og,
    			R.id.fg,
    			R.id.abv,
    			R.id.ibu,
    			R.id.srm
    			};

    	adapter = new BeerDetailAdapter(getActivity(), R.layout.beer_detail_view, null, from, to, 0);
    }



	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
    	String[] projection = { BeerTableMetadata.ID_COLUMN, 
    			BeerTableMetadata.NAME_COLUMN, 
    			BeerTableMetadata.BREWER_NAME_COLUMN,
    			BeerTableMetadata.STYLE_CODE_COLUMN,
    			BeerTableMetadata.STYLE_NAME_COLUMN,
    			BeerTableMetadata.STYLE_OVERRIDE_COLUMN,
    			BeerTableMetadata.IS_KICKED_COLUMN,
    			BeerTableMetadata.TAP_NUMBER_COLUMN,
    			BeerTableMetadata.PACKAGING_COLUMN,
    			BeerTableMetadata.DESCRIPTION_COLUMN,
    			BeerTableMetadata.ORIGINAL_GRAVITY_COLUMN,
    			BeerTableMetadata.FINAL_GRAVITY_COLUMN,
    			BeerTableMetadata.ALCOHOL_BY_VOLUME_COLUMN,
    			BeerTableMetadata.INTERNATIONAL_BITTERNESS_UNITS_COLUMN,
    			BeerTableMetadata.STANDARD_REFERENCE_METHOD_COLUMN,
    			BeerTableMetadata.IS_EMAIL_SHOWN,
    			BeerTableMetadata.EMAIL_ADDRESS
    	};
    	String sortOrder = null;
    	Uri queryUri = Uri.parse(OnTapContentProviderMetadata.BEER_CONTENT_URI
    			+ "/" + String.valueOf(beerId));
    	String selection = null;
    	String[] selectionArgs = null;
    	
    	CursorLoader cursorLoader = new CursorLoader(getActivity(),
    			queryUri, projection, selection, selectionArgs, sortOrder);
    	return cursorLoader;
	}


	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		switch (loader.getId()) {
		case LOADER_ID:
	        // Set the new data in the adapter.
		    adapter.swapCursor(data);
		    adapter.getView(0, getView(), (ViewGroup) getView().getParent());
		}
	}
        
	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
        // Clear the data in the adapter.
        adapter.swapCursor(null);
	}




//	private void updateFields() {
//		// TODO add tests for these fields
//		TextView beerNameView = (TextView)findViewById(R.id.beer_name);
//		beerNameView.setText(beerQuery.getBeerName(cursor));
//		
//		TextView statusView = (TextView)findViewById(R.id.status);
//		if (beerQuery.isKicked(cursor)) {
//			statusView.setText(R.string.kicked_text);
//		} else {
//			if (beerQuery.isPouring(cursor)) {
//				if (0 != beerQuery.getTapNumber(cursor)) {
//					String status = getString(R.string.on_tap_number) + " " 
//							+ beerQuery.getTapNumber(cursor);
//					statusView.setText(status);
//				} else {
//					statusView.setText(R.string.pouring);
//				}
//			} else {
//				statusView.setText(R.string.on_deck);
//			}
//		}
//		
//		TextView beerStyleView = (TextView)findViewById(R.id.beer_style);
//		String styleCode = beerQuery.getStyleCode(cursor);
//		String styleName = beerQuery.getStyleName(cursor);
//		String style = styleCode;
//		if (null != styleCode && null != styleName) {
//			style += " - " + styleName;
//		}
//		beerStyleView.setText(style);
//		
//		TextView beerStyleOverrideView = (TextView)findViewById(R.id.beer_style_override);
//		String styleOverride = beerQuery.getStyleOverride(cursor);
//		if (null != styleOverride && 0 != styleOverride.length()) {
//			beerStyleOverrideView.setText(styleOverride);
//		} else {
//			beerStyleOverrideView.setVisibility(View.GONE);
//		}
//		
//		TextView brewerView = (TextView)findViewById(R.id.brewer);
//		String brewer = beerQuery.getBrewerName(cursor);
//		brewerView.setText(brewer);
//		/*
//		TextView descriptionView = (TextView)findViewById(R.id.description);
//		descriptionView.setText(beer.getDescription());
//		*/
//		TextView packagingView = (TextView)findViewById(R.id.packaging);
//		packagingView.setText(beerQuery.getPackaging(cursor));
///*
//		TextView ogView = (TextView)findViewById(R.id.og);
//		ogView.setText(beer.getOriginalGravity());
//
//		TextView fgView = (TextView)findViewById(R.id.fg);
//		fgView.setText(beer.getFinalGravity());
//		
//		TextView abvView = (TextView)findViewById(R.id.abv);
//		abvView.setText(beer.getAlcoholByVolume());
//		
//		TextView ibuView = (TextView)findViewById(R.id.ibu);
//		ibuView.setText(beer.getInternationalBitternessUnits());
//
//		TextView srmView = (TextView)findViewById(R.id.srm);
//		srmView.setText(beer.getStandardReferenceMethod());
//		
//		if (beer.getShowBrewerEmailAddress()) {
//			TextView brewerEmailView = (TextView)findViewById(R.id.brewer_email);
//			brewerEmailView.setText(beer.getBrewerEmailAddress());
//		} else {
//			TextView brewerEmailLabelView = (TextView)findViewById(R.id.brewer_email_label);
//			brewerEmailLabelView.setEnabled(false);
//		}
//	*/
//	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.beer_detail, menu);
//		return true;
//	}

	public void setBeerId(int id) {
		this.beerId = id;
	}


	public int getBeerId() {
		return beerId;
	}
}

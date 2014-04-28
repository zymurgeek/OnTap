package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
        
		createCursorAdapter();
		
        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
    	callbacks = this;
        final LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_ID, null, callbacks);

        View view = getView().getRootView();
        Button refreshButton = (Button)view.findViewById
			(R.id.refresh_button);
        //FIXME enable refresh when load by beer id is working
        refreshButton.setEnabled(false);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		loaderManager.restartLoader(LOADER_ID, null, callbacks);
            }
        });
        
        Button emailButton = (Button)view.findViewById
			(R.id.email_brewer);
        emailButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	TextView emailAddressView = (TextView)getView().findViewById(R.id.brewer_email);
            	String emailAddress = emailAddressView.getText().toString();
            	TextView beerNameView = (TextView)getView().findViewById(R.id.beer_name);
            	String beerName = beerNameView.getText().toString();
            	Intent sendEmail = new Intent(Intent.ACTION_SENDTO);
            	String uriText = "mailto:" + Uri.encode(emailAddress) + 
            	          "?subject=" + Uri.encode("Barley Legal Homebrewers OnTap: " + beerName) + 
            	          "&body=" + Uri.encode("I had your beer " + beerName);
            	Uri uri = Uri.parse(uriText);
            	sendEmail.setData(uri);
            	startActivity(Intent.createChooser(sendEmail, "Send mail..."));        
            }
        });
        
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


	public void setBeerId(int id) {
		this.beerId = id;
	}


	public int getBeerId() {
		return beerId;
	}
}

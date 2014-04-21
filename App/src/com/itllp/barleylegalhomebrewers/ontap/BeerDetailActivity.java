package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata;
import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.OnTapContentProviderMetadata;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class BeerDetailActivity extends Activity {

	public static final String BEER_ID = "BEER_ID";
	private Cursor cursor;
	private BeerCursorUtility beerQuery = new BeerCursorUtility();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String beerIdString = intent.getStringExtra(BEER_ID);
        
    	String[] projection = { BeerTableMetadata.ID_COLUMN, 
    			BeerTableMetadata.NAME_COLUMN, 
    			BeerTableMetadata.BREWER_NAME_COLUMN,
    			BeerTableMetadata.STYLE_CODE_COLUMN,
    			BeerTableMetadata.STYLE_NAME_COLUMN,
    			BeerTableMetadata.STYLE_OVERRIDE_COLUMN,
    			BeerTableMetadata.IS_KICKED_COLUMN,
    			BeerTableMetadata.TAP_NUMBER_COLUMN,
    			BeerTableMetadata.PACKAGING_COLUMN};
    	String sortOrder = null;
    	Uri queryUri = Uri.parse(OnTapContentProviderMetadata.BEER_CONTENT_URI
    			+ "/" + beerIdString);
    	String selection = null; //BeerTableMetadata.ID_COLUMN + " = ?";
    	String[] selectionArgs = { "" /*beerIdString*/ };
        cursor = getContentResolver().query(
        	    queryUri,
        	    projection,
        	    selection,
        	    selectionArgs,
        	    sortOrder);
        
		setContentView(R.layout.activity_beer_detail);
		updateFields();
	}

	private void updateFields() {
		// TODO add tests for these fields
		TextView beerNameView = (TextView)findViewById(R.id.beer_name);
		beerNameView.setText(beerQuery.getBeerName(cursor));
		
		TextView statusView = (TextView)findViewById(R.id.status);
		if (beerQuery.isKicked(cursor)) {
			statusView.setText(R.string.kicked_text);
		} else {
			if (beerQuery.isPouring(cursor)) {
				if (0 != beerQuery.getTapNumber(cursor)) {
					String status = getString(R.string.on_tap_number) + " " 
							+ beerQuery.getTapNumber(cursor);
					statusView.setText(status);
				} else {
					statusView.setText(R.string.pouring);
				}
			} else {
				statusView.setText(R.string.on_deck);
			}
		}
		
		TextView beerStyleView = (TextView)findViewById(R.id.beer_style);
		String styleCode = beerQuery.getStyleCode(cursor);
		String styleName = beerQuery.getStyleName(cursor);
		String style = styleCode;
		if (null != styleCode && null != styleName) {
			style += " - " + styleName;
		}
		beerStyleView.setText(style);
		
		TextView beerStyleOverrideView = (TextView)findViewById(R.id.beer_style_override);
		String styleOverride = beerQuery.getStyleOverride(cursor);
		if (null != styleOverride && 0 != styleOverride.length()) {
			beerStyleOverrideView.setText(styleOverride);
		} else {
			beerStyleOverrideView.setVisibility(View.GONE);
		}
		
		TextView brewerView = (TextView)findViewById(R.id.brewer);
		String brewer = beerQuery.getBrewerName(cursor);
		brewerView.setText(brewer);
		/*
		TextView descriptionView = (TextView)findViewById(R.id.description);
		descriptionView.setText(beer.getDescription());
		*/
		TextView packagingView = (TextView)findViewById(R.id.packaging);
		packagingView.setText(beerQuery.getPackaging(cursor));
/*
		TextView ogView = (TextView)findViewById(R.id.og);
		ogView.setText(beer.getOriginalGravity());

		TextView fgView = (TextView)findViewById(R.id.fg);
		fgView.setText(beer.getFinalGravity());
		
		TextView abvView = (TextView)findViewById(R.id.abv);
		abvView.setText(beer.getAlcoholByVolume());
		
		TextView ibuView = (TextView)findViewById(R.id.ibu);
		ibuView.setText(beer.getInternationalBitternessUnits());

		TextView srmView = (TextView)findViewById(R.id.srm);
		srmView.setText(beer.getStandardReferenceMethod());
		
		if (beer.getShowBrewerEmailAddress()) {
			TextView brewerEmailView = (TextView)findViewById(R.id.brewer_email);
			brewerEmailView.setText(beer.getBrewerEmailAddress());
		} else {
			TextView brewerEmailLabelView = (TextView)findViewById(R.id.brewer_email_label);
			brewerEmailLabelView.setEnabled(false);
		}
	*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.beer_detail, menu);
		return true;
	}

}

package com.itllp.barleylegalhomebrewers.ontap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class BeerDetailActivity extends Activity {

	public static final String BEER_ID = "BEER_ID";
	private int beerId;
	private Beer beer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String beerIdString = intent.getStringExtra(BEER_ID);
        try {
        	beerId = Integer.parseInt(beerIdString);
        } catch (NumberFormatException e) {
        	beerId = -1;
        }
        beer = BeerDatabase.getInstance().getBeer(beerId);
        
		setContentView(R.layout.activity_beer_detail);
		updateFields();
	}

	private void updateFields() {
		// TODO add tests for these fields
		TextView beerNameView = (TextView)findViewById(R.id.beer_name);
		beerNameView.setText(beer.getBeerName());
		
		
		TextView beerStyleView = (TextView)findViewById(R.id.beer_style);
		String styleCode = beer.getStyleCode();
		String styleName = beer.getStyleName();
		String style = styleCode;
		if (null != styleCode && null != styleName) {
			style += " - " + styleName;
		}
		beerStyleView.setText(style);
		
		TextView beerStyleOverrideView = (TextView)findViewById(R.id.beer_style_override);
		String styleOverride = beer.getStyleOverride();
		if (null != styleOverride && 0 != styleOverride.length()) {
			beerStyleOverrideView.setText(styleOverride);
		} else {
			beerStyleOverrideView.setVisibility(View.GONE);
		}
		
		TextView brewerView = (TextView)findViewById(R.id.brewer);
		String brewer = beer.getBrewerName();
		brewerView.setText(brewer);
		
		TextView descriptionView = (TextView)findViewById(R.id.description);
		descriptionView.setText(beer.getDescription());
		
		TextView packagingView = (TextView)findViewById(R.id.packaging);
		packagingView.setText(beer.getPackaging());

		TextView ogView = (TextView)findViewById(R.id.og);
		ogView.setText(beer.getOriginalGravity());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.beer_detail, menu);
		return true;
	}

}

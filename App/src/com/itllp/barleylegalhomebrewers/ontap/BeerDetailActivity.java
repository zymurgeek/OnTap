package com.itllp.barleylegalhomebrewers.ontap;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;


public class BeerDetailActivity extends ActionBarActivity {

	public static final String BEER_ID = "BEER_ID";
	public int beerId = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        
		setContentView(R.layout.beer_detail_fragment);
		
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        String beerIdString = intent.getStringExtra(BEER_ID);
        try {
        	beerId = Integer.parseInt(beerIdString);
        } catch (NumberFormatException e) {
        	beerId = -1;
        }

        BeerDetailFragment beerDetailFrag = (BeerDetailFragment)
        	    getSupportFragmentManager().findFragmentById(com.itllp
        	    		.barleylegalhomebrewers.ontap.R.id.beer_detail_fragment);
        if (null != beerDetailFrag) {
        	beerDetailFrag.setBeerId(beerId);
        }
	}

}

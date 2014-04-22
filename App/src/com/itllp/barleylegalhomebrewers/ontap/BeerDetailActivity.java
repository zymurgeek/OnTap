package com.itllp.barleylegalhomebrewers.ontap;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;


public class BeerDetailActivity extends FragmentActivity {

	public static final String BEER_ID = "BEER_ID";
	public int beerId = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        
		setContentView(R.layout.beer_detail);
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
